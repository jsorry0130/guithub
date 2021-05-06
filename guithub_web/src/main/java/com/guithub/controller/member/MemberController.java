package com.guithub.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.guithub.domain.member.MemberVO;
import com.guithub.service.member.MemberService;

@Controller
@RequestMapping("/")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	//로그인 폼 페이지
	@RequestMapping("login")
	public String login() throws Exception{
		
		return "main.login";
	}
	
	//로그인 확인 및 세션
	@RequestMapping(value="login",method= RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest request, Model model) throws Exception{
		
		//로그인 정보 확인
		MemberVO mem = memberService.login(vo);
		if(mem != null){
			System.out.println("로그인확인되었습니다.");
			System.out.println("로그인된 아이디는 "+vo.getId());
			
		    HttpSession session = request.getSession();
		    session.setAttribute("mem_id", mem.getId());
		    session.setAttribute("mem_email", mem.getEmail());
		    session.setAttribute("mem_nickname", mem.getNickname());
		    System.out.println("현재 세션유지 중인 로그인 아이디:" + session.getAttribute("mem_id"));
		    model.addAttribute("check", true);
		}else {
		    model.addAttribute("check", false);
		}
		
		
		
		return "main.login";
	}
	
	//로그아웃 폼 페이지
	@RequestMapping("logout")
	public String logout() throws Exception{
		
		return "main.logout";
	}
	
	//로그아웃 (세션 끊기)
	@RequestMapping(value="logout", method= RequestMethod.POST)
	public String logout(HttpServletRequest request, Model model) throws Exception{
		
		//세션 삭제
	    HttpSession session = request.getSession();
	    session.invalidate();
	    
	   	return "main.logout";
	}
	
	//회원정보
	@RequestMapping(value="meminfo")
	public String memberInfo() throws Exception{
		
	   	return "main.meminfo";
	}
}
