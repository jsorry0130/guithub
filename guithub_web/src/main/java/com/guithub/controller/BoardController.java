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
		
	//게시물 목록 페이지
	@RequestMapping("general")
	public String genList(Model model) throws Exception {
		List<PostVO> list = service.getList();
		
		model.addAttribute("list", list);
		
		return "board.general";
	}
	
	//게시물 목록 페이지 (페이징)
	@RequestMapping("generalPaging")
	public String genListPaging(@RequestParam(value="page", required = false) String page_tmp, Model model) throws Exception {
		
		//null 검사를 위해 string으로 parameter를 받은 후 교체
		int page = 1;		
		if(page_tmp != null || !page_tmp.equals("")){
			page = Integer.parseInt(page_tmp);
		}
		
		//게시물 총 개수
		int count = service.getCount();
		
		Paging paging = new Paging(page, count);
		
		List<PostVO> listPaging = service.getList(page);
		
		//페이징에 따른 게시물 목록
		model.addAttribute("list", listPaging);
		//페이징 번호 목록		
		model.addAttribute("paging", paging);
		
		return "board.general";
	}
	
	//게시물 상세보기 페이지
	@RequestMapping("detail")
	public String detail(@RequestParam("id") int id, Model model) throws Exception {
		PostVO detail = service.getDetail(id);
		
		model.addAttribute("detail", detail);
		return"board.detail";
	}
	
	//게시물 작성 페이지
	@RequestMapping("reg")
	public String reg(){
		
		return "board.reg";
	}
	
	//게시물 작성 후 redirect
	@RequestMapping(value="regPost", method = RequestMethod.POST)
	public String writePost(PostVO vo) throws Exception {
		service.regPost(vo);
		
		return "redirect:bbs";
	}	

	
}
