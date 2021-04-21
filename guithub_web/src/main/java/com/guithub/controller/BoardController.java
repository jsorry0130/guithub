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
		
	//게시물 목록 페이지
	@RequestMapping("general")
	public String genList(Model model) throws Exception {
		List<PostVO> list = service.getList();
		
		model.addAttribute("list", list);
		
		return "board.general";
	}
	
	//게시물 목록 페이지 (페이징)
	@RequestMapping("generalPaging")
	public String genListPaging(@RequestParam(value="page", required = false) String page_, Model model) throws Exception {
		
		//null 검사를 위해 string으로 parameter를 받음
		int page;
		
		if(page_ == null || page_ == ""){
			page = 1;
		}else {
			page = Integer.parseInt(page_);
		}
		
		//페이징
		int count = service.getCount();
		
		int pagePost_cnt = 15; // 한 페이지당 게시물 개수
		int pageNum_cnt = 10; //한 번에 표시할 페이지 개수
		
		int endPageNum = (int)(Math.ceil((double)page / (double)pageNum_cnt) * pageNum_cnt);

		int startPageNum = endPageNum - (pageNum_cnt - 1);
		
		int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pagePost_cnt));
		
		//처음 계산한 endPage 가 실제 endPage보다 클경우 교체
		if(endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}
		
		//시작번호가 1일때 제외 무조건 출력
		boolean prev = startPageNum == 1 ? false : true; 
		//마지막 페이지 번호*게시물 개수가 총게시물 수보다 작으면 출력
		boolean next = endPageNum * pagePost_cnt >= count ? false : true;  
		
		System.out.println(count);
		System.out.println(page);
		System.out.println(startPageNum);
		System.out.println(endPageNum);
		System.out.println(endPageNum_tmp);
		
		List<PostVO> listPaging = service.getList(page);
		
		//페이징에 따른 게시물 목록
		model.addAttribute("list", listPaging);
		
		//페이징 시작번호, 끝번호
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		
		//이전, 다음 버튼을 위한 불린값
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
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
