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
import com.guithub.service.PostService;

@Controller
@RequestMapping("/")
public class BoardController {
	@Autowired
	PostService service;
		
	//�Խù� ��� ������
	@RequestMapping("general")
	public String genList(Model model) throws Exception {
		List<PostVO> list = service.getList();
		
		model.addAttribute("list", list);
		
		return "board.general";
	}
	
	//�Խù� ��� ������ (����¡)
	@RequestMapping("generalPaging")
	public String genListPaging(@RequestParam(value="page", required = false) String page_tmp, Model model) throws Exception {
		
		//null �˻縦 ���� string���� parameter�� ���� �� ��ü
		int page = 1;		
		if(page_tmp != null || !page_tmp.equals("")){
			page = Integer.parseInt(page_tmp);
		}
		
		//�Խù� �� ����
		int count = service.getCount();
		
		Paging paging = new Paging(page, count);
		
		List<PostVO> listPaging = service.getList(page);
		
		//����¡�� ���� �Խù� ���
		model.addAttribute("list", listPaging);
		//����¡ ��ȣ ���		
		model.addAttribute("paging", paging);
		
		return "board.general";
	}
	
	//�Խù� �󼼺��� ������
	@RequestMapping("detail")
	public String detail(@RequestParam("id") int id, Model model) throws Exception {
		PostVO detail = service.getDetail(id);
		
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
		
		return "redirect:bbs";
	}	

	
}
