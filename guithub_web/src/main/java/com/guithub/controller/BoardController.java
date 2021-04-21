package com.guithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String genListPaging(@RequestParam(value="page", required = false) String page_, Model model) throws Exception {
		
		//null �˻縦 ���� string���� parameter�� ����
		int page;
		
		if(page_ == null || page_ == ""){
			page = 1;
		}else {
			page = Integer.parseInt(page_);
		}
		
		//����¡
		int count = service.getCount();
		
		int pagePost_cnt = 15; // �� �������� �Խù� ����
		int pageNum_cnt = 10; //�� ���� ǥ���� ������ ����
		
		int endPageNum = (int)(Math.ceil((double)page / (double)pageNum_cnt) * pageNum_cnt);

		int startPageNum = endPageNum - (pageNum_cnt - 1);
		
		int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pagePost_cnt));
		
		//ó�� ����� endPage �� ���� endPage���� Ŭ��� ��ü
		if(endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}
		
		//���۹�ȣ�� 1�϶� ���� ������ ���
		boolean prev = startPageNum == 1 ? false : true; 
		//������ ������ ��ȣ*�Խù� ������ �ѰԽù� ������ ������ ���
		boolean next = endPageNum * pagePost_cnt >= count ? false : true;  
		
		System.out.println(count);
		System.out.println(page);
		System.out.println(startPageNum);
		System.out.println(endPageNum);
		System.out.println(endPageNum_tmp);
		
		List<PostVO> listPaging = service.getList(page);
		
		//����¡�� ���� �Խù� ���
		model.addAttribute("list", listPaging);
		
		//����¡ ���۹�ȣ, ����ȣ
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		
		//����, ���� ��ư�� ���� �Ҹ���
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
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
