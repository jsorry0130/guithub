package com.guithub.controller.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.guithub.domain.member.MemberVO;
import com.guithub.service.board.general.PostService;
import com.guithub.service.member.MemberService;

@Controller
@RequestMapping("/home/")
public class HomeController {
	@Autowired
	MemberService memberService;
	
	//메인페이지
	@RequestMapping("index")
	public String index() {
		
		return "home.index";
	}
	
	//로그인 폼 페이지
	@RequestMapping("login")
	public String login() throws Exception{
		
		return "home.login";
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
		
		
		
		return "home.login";
	}
	
	@RequestMapping("join")
	public String join() throws Exception{
		
		return "home.join";
	}
	
	//회원가입
	@RequestMapping(value="join", method= RequestMethod.POST)
	public String join(MemberVO vo, Model model,
			@RequestParam("pwdcheck") String pwdcheck) throws Exception{
		
		//비밀번호 검증
		if(vo.getPassword().equals(pwdcheck)) {
			//아이디, 닉네임 중복확인
			boolean idDup = memberService.checkIdDup(vo.getId());
			boolean nickDup = memberService.checkNickDup(vo.getNickname());
			
			model.addAttribute("idDup", idDup);
			model.addAttribute("nickDup", nickDup);
			
			//id, 닉네임 검증 후
			if(!idDup & !nickDup) {
				memberService.regMember(vo);
				model.addAttribute("success", true);
			}
		}else {
			model.addAttribute("pwdError", true);
		}
		return "home.join";
	}
}
