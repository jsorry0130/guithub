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
	
	//����������
	@RequestMapping("index")
	public String index() {
		
		return "home.index";
	}
	
	//�α��� �� ������
	@RequestMapping("login")
	public String login() throws Exception{
		
		return "home.login";
	}
	
	//�α��� Ȯ�� �� ����
	@RequestMapping(value="login",method= RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest request, Model model) throws Exception{
		
		//�α��� ���� Ȯ��
		MemberVO mem = memberService.login(vo);
		if(mem != null){
			System.out.println("�α���Ȯ�εǾ����ϴ�.");
			System.out.println("�α��ε� ���̵�� "+vo.getId());
			
		    HttpSession session = request.getSession();
		    session.setAttribute("mem_id", mem.getId());
		    session.setAttribute("mem_email", mem.getEmail());
		    session.setAttribute("mem_nickname", mem.getNickname());
		    System.out.println("���� �������� ���� �α��� ���̵�:" + session.getAttribute("mem_id"));
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
	
	//ȸ������
	@RequestMapping(value="join", method= RequestMethod.POST)
	public String join(MemberVO vo, Model model,
			@RequestParam("pwdcheck") String pwdcheck) throws Exception{
		
		//��й�ȣ ����
		if(vo.getPassword().equals(pwdcheck)) {
			//���̵�, �г��� �ߺ�Ȯ��
			boolean idDup = memberService.checkIdDup(vo.getId());
			boolean nickDup = memberService.checkNickDup(vo.getNickname());
			
			model.addAttribute("idDup", idDup);
			model.addAttribute("nickDup", nickDup);
			
			//id, �г��� ���� ��
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
