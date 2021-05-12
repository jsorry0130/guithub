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
	
	//�α׾ƿ� �� ������
	@RequestMapping("logout")
	public String logout() throws Exception{
		
		return "member.logout";
	}
	
	//�α׾ƿ� (���� ����)
	@RequestMapping(value="logout", method= RequestMethod.POST)
	public String logout(HttpServletRequest request, Model model) throws Exception{
		
		//���� ����
	    HttpSession session = request.getSession();
	    session.invalidate();
	    
	   	return "member.logout";
	}
	
	//ȸ������
	@RequestMapping(value="meminfo")
	public String memberInfo() throws Exception{
		
	   	return "member.meminfo";
	}
	
	//ȸ�� �Խù� ����
	@RequestMapping("postlist")
	public String adminPost(Model model,
			@RequestParam(value="board", required=false, defaultValue="tab") String board,
			@RequestParam(value="page", required=false, defaultValue="1") int page, 
			@RequestParam(value="field", required=false, defaultValue="title") String field,
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
			HttpServletRequest request) throws Exception {
		
		//���� �α��� �� ȸ�� �г���
	    HttpSession session = request.getSession();
	    String nickname = (String)session.getAttribute("mem_nickname");
	    
		//���� ȸ�� �ۼ� �Խù� �� ���� + ����¡ ��� + �Խù� ���
		int count = memberService.getBoardPostCnt(nickname, board, field, keyword);
		Paging paging = new Paging(page, count);
		List<PostVO_view> list = memberService.getBoardList(nickname, board, page, field, keyword);
			
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);

		return "member.postlist";
	}
	
	//ȸ�� ��� ����
	@RequestMapping("replylist")
	public String adminReply(Model model,
			HttpServletRequest request,
			@RequestParam(value="board", required=false, defaultValue="tab") String board,
			@RequestParam(value="page", required=false, defaultValue="1") int page, 
			@RequestParam(value="field", required=false, defaultValue="title") String field,
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword )throws Exception{
		
		//���� �α��� �� ȸ�� �г���
	    HttpSession session = request.getSession();
	    String nickname = (String)session.getAttribute("mem_nickname");
	    
		//���� ȸ�� �ۼ� ��� �� ���� + ����¡ ��� + ��� ���
		int count = memberService.getBoardReplyCnt(nickname, board, keyword);
		Paging paging = new Paging(page, count);
		List<PostVO_view> list = memberService.getBoardReplyList(nickname, board, page, keyword);
			
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		
		return "member.replylist";
	}
	//ȸ��Ż�� ��й�ȣ Ȯ�� ��������
	@RequestMapping("withdraw")
	public String withdraw() throws Exception{
		
		return "member.withdraw";
	}
	
	//ȸ��Ż��
	@RequestMapping(value="withdraw", method= {RequestMethod.GET, RequestMethod.POST})
	public String withdraw(Model model, HttpServletRequest request ,
			@RequestParam(value="pwdCheck", required=false ) String pwdCheck,
			@RequestParam(value="reconfirm", required=false, defaultValue="false" ) boolean reconfirm) throws Exception{
		//���� ȸ�� ����
		HttpSession session = request.getSession();
		String mem_pwd = (String)session.getAttribute("mem_pwd");
		String mem_id = (String)session.getAttribute("mem_id");
		
		//��й�ȣ �Է� �� Ȯ�� ������
		if(pwdCheck!=null) {
			if(mem_pwd.equals(pwdCheck)) {
				model.addAttribute("check", true); //��й�ȣ Ȯ�� ���� �˸�
			}else {
				model.addAttribute("check", false);
			}
		}
		//��й�ȣ Ȯ�� ���� �� Ż�� ��Ȯ�� ������
		System.out.println("��Ȯ�ΰ�"+reconfirm);
		if(reconfirm) {
			memberService.delMember(mem_id);
			session.invalidate(); //��������
			model.addAttribute("success", true);
		}
		
		return "member.withdraw";
	}

}
