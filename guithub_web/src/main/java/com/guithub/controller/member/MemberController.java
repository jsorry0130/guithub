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
	
	//�α��� �� ������
	@RequestMapping("login")
	public String login() throws Exception{
		
		return "main.login";
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
		
		
		
		return "main.login";
	}
	
	//�α׾ƿ� �� ������
	@RequestMapping("logout")
	public String logout() throws Exception{
		
		return "main.logout";
	}
	
	//�α׾ƿ� (���� ����)
	@RequestMapping(value="logout", method= RequestMethod.POST)
	public String logout(HttpServletRequest request, Model model) throws Exception{
		
		//���� ����
	    HttpSession session = request.getSession();
	    session.invalidate();
	    
	   	return "main.logout";
	}
	
	//ȸ������
	@RequestMapping(value="meminfo")
	public String memberInfo() throws Exception{
		
	   	return "main.meminfo";
	}
}
