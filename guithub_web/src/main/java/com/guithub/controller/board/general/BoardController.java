package com.guithub.controller.board.general;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.guithub.domain.board.Paging;
import com.guithub.domain.board.general.FileVO;
import com.guithub.domain.board.general.PostVO;
import com.guithub.domain.board.general.PostVO_view;
import com.guithub.domain.board.general.ReplyVO;
import com.guithub.service.board.general.PostService;
import com.guithub.service.board.general.ReplyService;

@Controller
@RequestMapping("/board/general/")
public class BoardController {
	@Autowired
	PostService postService;
	@Autowired
	ReplyService replyService;
	
	//�Խù� ��� (����¡ + ��ġ)
	@RequestMapping(value="list", method = {RequestMethod.GET, RequestMethod.POST})
	public String getList(@RequestParam(value="page", required = false, defaultValue = "1") int page, 
			@RequestParam(value="field", required = false, defaultValue = "title") String field,
			@RequestParam(value="keyword", required = false, defaultValue = "") String keyword, 
			Model model) throws Exception {
				
		//�Խù� �� ������ �Խù��� �޸� ��� ����
		int count = postService.getCount(field, keyword);
		//���� �������� ���� �ϴ� ����¡ ��� ���
		Paging paging = new Paging(page, count);
		
		//List<PostVO> listPagingSearch = service.getList(page, field, keyword);
		List<PostVO_view> list_view = postService.getList_view(page,field,keyword);
		//����¡�� ���� �Խù� ���
		model.addAttribute("list", list_view);
		//����¡ ��ȣ ���		
		model.addAttribute("paging", paging);
		
		return "board.general.list";
	}
	
	//�Խù� �󼼺���
	@RequestMapping("detail")
	public String getdetail(@RequestParam("id") int id, Model model) throws Exception {
		//��ȸ�� ���� + �Խù� �� ���� +  ��� ��� + ���� ���
		postService.updateHit(id); 
		PostVO detail = postService.getDetail(id);
		List<ReplyVO> listReply = replyService.getListReply(id);
		List<FileVO> files = postService.getFile(id);
	
		//�ٿ�ε� ��ũ�� ���� ����������� �����̸� ����
		for(FileVO file: files) {
			file.setRealName();
		}
		
		//�信 ��Ÿ�� �������� ��� ���
		model.addAttribute("detail", detail);
		model.addAttribute("listReply", listReply);
		model.addAttribute("files", files);
		
		return"board.general.detail";
	}
	
	
	//�Խù� �ۼ� �� ������
	@RequestMapping("reg")
	public String regPost(){
	  
		return "board.general.reg";
		
	}
	  
	//�Խù� �ۼ� �� redirect  
	@RequestMapping(value="reg", method = RequestMethod.POST)
	public String regPost(PostVO vo, @RequestParam("file") MultipartFile[] files,
			HttpServletRequest request) throws Exception {
		//�Խù� ���
		postService.regPost(vo); 
		
		//���� �̸��� �ߺ� ������ ���� �Խù��� ��¥ ����
		Date now = new Date();
	
		//���� ���ε� �� ���� �̸� ����
		for(MultipartFile file: files) {
			if(file.getOriginalFilename()!="") {
				//������� ���ϱ�
				ServletContext ctx = request.getServletContext();
				String webPath = "/static/upload";
				String realPath = ctx.getRealPath(webPath);
				File savePath = new File(realPath);
				
				//���� ������ ������ ������ ���� �� ���� ����
				FileVO filedata = new FileVO();
				filedata.setName(file.getOriginalFilename());
				filedata.setFile_size((int)file.getSize());
				filedata.setPost_id(vo.getId());
				filedata.setRegdate(now);
				filedata.setRealName();
				postService.regFile(filedata);
						
				//��� ������ ���ٸ� ����
				if(!savePath.exists())
					savePath.mkdirs(); //��ü ��θ� ������ִ� �۾�(mkdirs)
					
				realPath += File.separator + filedata.getRealName(); //���� �������
				File saveFile = new File(realPath); //���� ����(���ε�)
					
				file.transferTo(saveFile);
			}
		} 
		  
		return "redirect:detail?id="+vo.getId();  
		
	}
			
	//��ȸ�� �Խù� ���� ��й�ȣ �Է� �� + ����
	@RequestMapping(value = "delpost", method = {RequestMethod.GET, RequestMethod.POST})
	public String delPost(@RequestParam("id") int id,
			@RequestParam(value="password", required = false, defaultValue = "") String password,
			Model model) throws Exception{
		
		//��й�ȣ�� �Է����� ���� ���� ���� ����
		if(!password.equals("")) {
			int delCnt = postService.delPost(id, password);
			model.addAttribute("delCnt", delCnt);
		}
		
		return "board.general.delpost";
	}
	
	//ȸ�� �Խù� ����
	@RequestMapping(value="delmempost", method = {RequestMethod.GET, RequestMethod.POST})
	public String delMemPost(@RequestParam("id") int id,
			HttpServletRequest request,
			Model model) throws Exception{
		
		PostVO post = postService.getDetail(id);
		HttpSession session = request.getSession();
		

		if(post.getWriter_id().equals(session.getAttribute("mem_nickname"))) {
			//ȸ�� �Խù��� ��й�ȣ�� null
			int delCnt = postService.delPost(id, null);
			model.addAttribute("delCnt", delCnt);
		}else {
			model.addAttribute("warning", true); //���� ���
		}

		return "board.general.delmempost";
	}
	
	//��� ��� �� redirect
	@RequestMapping(value="regreply", method=RequestMethod.POST)
	public String regReply(ReplyVO vo) throws Exception{
		replyService.regReply(vo);
	
		return "redirect:detail?id="+vo.getPost_id();
	}
	
	//��ȸ�� ��� ���� ��й�ȣ �Է� �� + ����
	@RequestMapping(value = "delreply", method = {RequestMethod.POST, RequestMethod.GET})
	public String delReply(@RequestParam("rid") int id,
			@RequestParam(value="password", required = false, defaultValue = "") String password,
			Model model) throws Exception{
		
		//��й�ȣ�� �Է����� ���� ���� ���� ����
		if(!password.equals("")) {
			int delCnt = replyService.delReply(id, password);
			model.addAttribute("delCnt", delCnt);
		}
		
		return "board.general.delreply";
	}
	
	//ȸ�� ��� ���� ����
	@RequestMapping(value = "delmemreply", method = {RequestMethod.POST, RequestMethod.GET})
	public String delMemReply(@RequestParam("rid") int id,
			@RequestParam(value="confirm", required=false, defaultValue="false") boolean confirm,
			@RequestParam(value="writer", required=false) String writer,
			HttpServletRequest request,
			Model model) throws Exception{
		
		HttpSession session = request.getSession();
		
		//�α����� ȸ���� ����� �ƴҽ� ���ٰ��
		if(writer.equals(session.getAttribute("mem_nickname"))) {		
			//���� ��Ȯ�ν�
			if(confirm) {
				replyService.delReply(id, null);
				model.addAttribute("success", true);
			}
		}else {
			model.addAttribute("warning", true);
		}
		return "board.general.delmemreply";
	}
	
	//��ȸ�� �Խù� ���� �н����� �Է� �� ������
	@RequestMapping(value = "checkpwd", method = {RequestMethod.POST, RequestMethod.GET})
	public String checkPwd() throws Exception{
		
		return "board.general.checkpwd";
	}
	
	//��ȸ�� �Խù� ���� �� ������
	@RequestMapping(value="edit", method= {RequestMethod.POST, RequestMethod.GET})
	public String editForm(@RequestParam("id") int id,
			@RequestParam("password") String password,
			Model model) throws Exception{
		
		//��й�ȣ�� �Է����� ���� Ȯ�� ���� ����
		if(!password.equals("")) {
			//��й�ȣ ����ġ�� �ٽ� ��й�ȣ �Է� �������� ����Ʈ
			boolean check = postService.checkPwd(id, password);
			model.addAttribute("check", check);
		}else { 
			//���Է½�
			model.addAttribute("nopwd", true);
		}
		
		//���� �� �Խù� ���� ǥ�⸦ ���� �Խù� �� ������ ÷������ ���		
		PostVO detail = postService.getDetail(id);
		List<FileVO> files = postService.getFile(id);
		
		model.addAttribute("detail", detail);
		model.addAttribute("files", files);
		
		return "board.general.edit";
	}
	
	//ȸ�� �Խù� ���� �� ������
	@RequestMapping(value="editmem", method= {RequestMethod.POST, RequestMethod.GET})
	public String editMemForm(@RequestParam("id") int id,
			HttpServletRequest request,
			Model model) throws Exception{
		
		PostVO post = postService.getDetail(id);
		HttpSession session = request.getSession();
		String writer_id = post.getWriter_id();
		String mem_nickname = (String) session.getAttribute("mem_nickname");
		String post_pwd = post.getPassword();
		
		//�α����� ȸ���� �Խù��� �ƴҽ� ����� ����Ʈ
		if(!(post_pwd==null && writer_id.equals(mem_nickname)))
			model.addAttribute("warning", true);
		
		//���� �� �Խù� ���� ǥ�⸦ ���� �Խù� �� ������ ÷������ ���		
		PostVO detail = postService.getDetail(id);
		List<FileVO> files = postService.getFile(id);
		
		model.addAttribute("detail", detail);
		model.addAttribute("files", files);
		
		return "board.general.edit";
	}
	
	//�Խù� ���� ���� (ȸ��+��ȸ�� ����)
	@RequestMapping(value="editPost", method= RequestMethod.POST)
	public String editPost(PostVO vo,
			@RequestParam("password") String password,
			@RequestParam("file") MultipartFile[] files,
			@RequestParam(value="delFiles", required = false) int[] delFiles,
			HttpServletRequest request, Model model) throws Exception{
		
		//�Խù� ����
		postService.editPost(vo);
		
		//���� �̸��� �ߺ� ������ ���� �Խù��� ��¥ ����
		Date now = new Date();
	
		//���� ���ε� �� ���� �̸� ����
		for(MultipartFile file: files) {
			if(file.getOriginalFilename()!="") {
				//������� ���ϱ�
				ServletContext ctx = request.getServletContext();
				String webPath = "/static/upload";
				String realPath = ctx.getRealPath(webPath);
				File savePath = new File(realPath);
				
				//���� ������ ������ ������ ���� �� ���� ����
				FileVO filedata = new FileVO();
				filedata.setName(file.getOriginalFilename());
				filedata.setFile_size((int)file.getSize());
				filedata.setPost_id(vo.getId());
				filedata.setRegdate(now);
				filedata.setRealName();
				postService.regFile(filedata);
						
				//��� ������ ���ٸ� ����
				if(!savePath.exists())
					savePath.mkdirs(); //��ü ��θ� ������ִ� �۾�(mkdirs)
					
				realPath += File.separator + filedata.getRealName(); //���� �������
				File saveFile = new File(realPath); //���� ����(���ε�)
					
				file.transferTo(saveFile);
			}
		}
		
		//üũ�� ���� ����
		if(delFiles!=null) {
			for(int delFile_id: delFiles) {
				System.out.println("üũ�� ������ ����: " + delFile_id);
				postService.delFile(delFile_id);
			}
		}
		
		return "redirect:detail?id="+vo.getId();
	}
	
}
