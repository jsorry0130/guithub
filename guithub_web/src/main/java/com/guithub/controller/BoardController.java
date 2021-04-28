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
			
	//게시물 목록 페이지 (페이징 + 서치)
	@RequestMapping(value="general", method = {RequestMethod.GET, RequestMethod.POST})
	public String genList(@RequestParam(value="page", required = false, defaultValue = "1") int page, 
			@RequestParam(value="field", required = false, defaultValue = "title") String field,
			@RequestParam(value="keyword", required = false, defaultValue = "") String keyword, 
			Model model) throws Exception {
				
		//게시물 총 개수
		int count = service.getCount(field, keyword);
		//현재 페이지에 따른 하단 페이징 목록 계산
		Paging paging = new Paging(page, count);
		
		List<PostVO> listPagingSearch = service.getList(page, field, keyword);
		
		//페이징에 따른 게시물 목록
		model.addAttribute("list", listPagingSearch);
		//페이징 번호 목록		
		model.addAttribute("paging", paging);
		
		return "board.general";
	}
	
	//게시물 상세보기 페이지
	@RequestMapping("detail")
	public String detail(@RequestParam("id") int id, Model model) throws Exception {
		//선택한 글의 id에 따른 상세정보를 가져오는 서비스
		PostVO detail = service.getDetail(id);
		//선택한 글의 id에 따른 댓글 리스트를 가져오는 서비스
		List<ReplyVO> listReply = service.getListReply(id);
		
		model.addAttribute("listReply", listReply);
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
		
		return "redirect:general";
	}
	
	//댓글 등록 후 redirect
	@RequestMapping(value="regReply", method=RequestMethod.POST)
	public String regReply(ReplyVO vo) throws Exception{
		service.regReply(vo);
	
		return "redirect:detail?id="+vo.getPost_id();
	}
	
	//댓글 삭제 비밀번호 입력 페이지
	@RequestMapping(value = "delReply", method = {RequestMethod.POST , RequestMethod.GET})
	public String delReply(@RequestParam("rid") int id,
			@RequestParam(value="password", required = false, defaultValue = "") String password, Model model) throws Exception{
		
		//비밀번호를 입력했을 때만 리플 삭제 진행
		if(!password.equals("")) {
			System.out.println("아이디 비번 입력 확인 완료 검증 후 삭제 진행");
			int delCnt = service.delReply(id, password);
			model.addAttribute("delCnt", delCnt);
		}
		
		return "board.delReply";
	}
	
	
	//게시물 삭제 비밀번호 입력 페이지
	@RequestMapping(value = "delPost", method = {RequestMethod.POST , RequestMethod.GET})
	public String delPost(@RequestParam("id") int id,
			@RequestParam(value="password", required = false, defaultValue = "") String password, Model model) throws Exception{
		
		//비밀번호를 입력했을 때만 게시물 삭제 진행
		if(!password.equals("")) {
			System.out.println("아이디 비번 입력 확인 완료 검증 후 삭제 진행");
			int delCnt = service.delPost(id, password);
			model.addAttribute("delCnt", delCnt);
		}
		
		return "board.delPost";
	}
	
}
