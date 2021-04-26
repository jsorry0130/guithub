package com.guithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.guithub.domain.Paging;
import com.guithub.domain.PostVO;
import com.guithub.domain.ReplyVO;
import com.guithub.service.PostService;

@Controller
@RequestMapping("/")
public class BoardController {
	@Autowired
	PostService service;
			
	//�Խù� ��� ������ (����¡ + ��ġ)
	@RequestMapping(value="general", method = {RequestMethod.GET, RequestMethod.POST})
	public String genList(@RequestParam(value="page", required = false, defaultValue = "1") int page, 
			@RequestParam(value="field", required = false, defaultValue = "title") String field,
			@RequestParam(value="keyword", required = false, defaultValue = "") String keyword, 
			Model model) throws Exception {
				
		//�Խù� �� ����
		int count = service.getCount(field, keyword);
		//���� �������� ���� �ϴ� ����¡ ��� ���
		Paging paging = new Paging(page, count);
		
		List<PostVO> listPagingSearch = service.getList(page, field, keyword);
		
		//����¡�� ���� �Խù� ���
		model.addAttribute("list", listPagingSearch);
		//����¡ ��ȣ ���		
		model.addAttribute("paging", paging);
		
		return "board.general";
	}
	
	//�Խù� �󼼺��� ������
	@RequestMapping("detail")
	public String detail(@RequestParam("id") int id, Model model) throws Exception {
		//������ ���� id�� ���� �������� �������� ����
		PostVO detail = service.getDetail(id);
		//������ ���� id�� ���� ��� ����Ʈ�� �������� ����
		List<ReplyVO> listReply = service.getListReply(id);
		
		model.addAttribute("listReply", listReply);
		model.addAttribute("detail", detail);
		
		return"board.detail";
	}
	
	//�Խù� �ۼ� ������
	@RequestMapping("reg")
	public String reg(){
		
		return "board.reg";
	}
	
	//�Խù� �ۼ� �� redirect
	@RequestMapping(value="regPost", method = RequestMethod.POST)
	public String writePost(PostVO vo) throws Exception {
		service.regPost(vo);
		
		return "redirect:general";
	}
	
	//��� ��� �� redirect
	@RequestMapping(value="regReply", method=RequestMethod.POST)
	public String regReply(ReplyVO vo) throws Exception{
		service.regReply(vo);
	
		return "redirect:detail?id="+vo.getPost_id();
	}
	
	//�Խù� ���� ��й�ȣ �Է� ������
	@RequestMapping(value="delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delPost(@RequestParam("password") String password,
			@RequestParam("id") int id, Model model) throws Exception{
	
		//���� ���� ���� Ȯ���� ���� ������ �ο� ī��Ʈ
		int delCnt = service.delPost(id, password);
		
		model.addAttribute("delCnt", delCnt);
		
		
		return "board.delete";
	}
	
}
