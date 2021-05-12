package com.guithub.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.guithub.domain.board.Paging;
import com.guithub.domain.board.tab.PostVO_view;
import com.guithub.service.board.tab.PostService;
import com.guithub.service.member.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	//로그아웃 폼 페이지
	@RequestMapping("logout")
	public String logout() throws Exception{
		
		return "member.logout";
	}
	
	//로그아웃 (세션 끊기)
	@RequestMapping(value="logout", method= RequestMethod.POST)
	public String logout(HttpServletRequest request, Model model) throws Exception{
		
		//세션 삭제
	    HttpSession session = request.getSession();
	    session.invalidate();
	    
	   	return "member.logout";
	}
	
	//회원정보
	@RequestMapping(value="meminfo")
	public String memberInfo() throws Exception{
		
	   	return "member.meminfo";
	}
	
	//회원 게시물 관리
	@RequestMapping("postlist")
	public String adminPost(Model model,
			@RequestParam(value="board", required=false, defaultValue="tab") String board,
			@RequestParam(value="page", required=false, defaultValue="1") int page, 
			@RequestParam(value="field", required=false, defaultValue="title") String field,
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
			HttpServletRequest request) throws Exception {
		
		//현재 로그인 된 회원 닉네임
	    HttpSession session = request.getSession();
	    String nickname = (String)session.getAttribute("mem_nickname");
	    
		//현재 회원 작성 게시물 총 개수 + 페이징 계산 + 게시물 목록
		int count = memberService.getBoardPostCnt(nickname, board, field, keyword);
		Paging paging = new Paging(page, count);
		List<PostVO_view> list = memberService.getBoardList(nickname, board, page, field, keyword);
			
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);

		return "member.postlist";
	}
	
	//회원 댓글 관리
	@RequestMapping("replylist")
	public String adminReply(Model model,
			HttpServletRequest request,
			@RequestParam(value="board", required=false, defaultValue="tab") String board,
			@RequestParam(value="page", required=false, defaultValue="1") int page, 
			@RequestParam(value="field", required=false, defaultValue="title") String field,
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword )throws Exception{
		
		//현재 로그인 된 회원 닉네임
	    HttpSession session = request.getSession();
	    String nickname = (String)session.getAttribute("mem_nickname");
	    
		//현재 회원 작성 댓글 총 개수 + 페이징 계산 + 댓글 목록
		int count = memberService.getBoardReplyCnt(nickname, board, keyword);
		Paging paging = new Paging(page, count);
		List<PostVO_view> list = memberService.getBoardReplyList(nickname, board, page, keyword);
			
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		
		return "member.replylist";
	}
	//회원탈퇴 비밀번호 확인 폼페이지
	@RequestMapping("withdraw")
	public String withdraw() throws Exception{
		
		return "member.withdraw";
	}
	
	//회원탈퇴
	@RequestMapping(value="withdraw", method= {RequestMethod.GET, RequestMethod.POST})
	public String withdraw(Model model, HttpServletRequest request ,
			@RequestParam(value="pwdCheck", required=false ) String pwdCheck,
			@RequestParam(value="reconfirm", required=false, defaultValue="false" ) boolean reconfirm) throws Exception{
		//현재 회원 정보
		HttpSession session = request.getSession();
		String mem_pwd = (String)session.getAttribute("mem_pwd");
		String mem_id = (String)session.getAttribute("mem_id");
		
		//비밀번호 입력 후 확인 성공시
		if(pwdCheck!=null) {
			if(mem_pwd.equals(pwdCheck)) {
				model.addAttribute("check", true); //비밀번호 확인 성공 알림
			}else {
				model.addAttribute("check", false);
			}
		}
		//비밀번호 확인 성공 후 탈퇴 재확인 성공시
		System.out.println("재확인값"+reconfirm);
		if(reconfirm) {
			memberService.delMember(mem_id);
			session.invalidate(); //세션종료
			model.addAttribute("success", true);
		}
		
		return "member.withdraw";
	}

}
