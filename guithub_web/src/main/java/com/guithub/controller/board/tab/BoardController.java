package com.guithub.controller.board.tab;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.guithub.domain.board.Paging;
import com.guithub.domain.board.tab.FileVO;
import com.guithub.domain.board.tab.PostVO;
import com.guithub.domain.board.tab.ReplyVO;
import com.guithub.domain.board.tab.PostVO_view;
import com.guithub.service.board.tab.PostService;
import com.guithub.service.board.tab.ReplyService;


@Controller
@RequestMapping("/board/tab/")
public class BoardController {
	@Autowired
	PostService postService;
	@Autowired
	ReplyService replyService;
	
	//�Խù� ��� (����¡ + ��ġ)
	@RequestMapping("list")
	public String getList(@RequestParam(value="page", required = false, defaultValue = "1") int page,
			@RequestParam(value="field", required = false, defaultValue = "title") String field,
			@RequestParam(value="keyword", required = false, defaultValue = "") String keyword,
			Model model) throws Exception{
		
		//�� �Խù� ���� + ����¡ ��� ��� + �Խù� ���
		int postCnt = postService.getPostCnt(field, keyword);
		Paging paging = new Paging(page, postCnt);
		List<PostVO_view> list = postService.getList(page, field, keyword);
		
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		return "board.tab.list";
	}
	
	//�Խù� �󼼺���
	@RequestMapping("detail")
	public String getDetail(@RequestParam("id") int id, Model model) throws Exception{
		//��ȸ�� ���� + �Խù� �� ���� +  ��� ��� + ���� ���
		postService.updateHit(id); 
		PostVO detail = postService.getDetail(id);
		List<ReplyVO> listReply = replyService.getReplyList(id);
		List<FileVO> files = postService.getFileList(id);
	
		//�ٿ�ε� ��ũ�� ���� ����������� �����̸� ����
		for(FileVO file: files) {
			file.setRealName();
		}
		
		model.addAttribute("detail", detail);
		model.addAttribute("listReply", listReply);
		model.addAttribute("files", files);
		
		return "board.tab.detail";
	}
	
	//�Խù� �ۼ� �� ������
	@RequestMapping("reg")
	public String regPost() throws Exception{
		
		return "board.tab.reg";
	}
	
	//�Խù� �ۼ� �� redirect  
	@RequestMapping(value="reg", method=RequestMethod.POST)
	public String regPost(PostVO post, @RequestParam("file") MultipartFile[] files,
			HttpServletRequest request) throws Exception{
		//�Խù� ���
		postService.regPost(post);
		
		//���� �̸��� �ߺ� ������ ���� �Խù��� ��¥ ����
		Date now = new Date();
	
		//���� ���ε� �� ���� �̸� ����
		for(MultipartFile file: files) {
			if(file.getOriginalFilename()!="") {
				//������� ���ϱ�
				ServletContext ctx = request.getServletContext();
				String webPath = "/static/tab/upload";
				String realPath = ctx.getRealPath(webPath);
				File savePath = new File(realPath);
				
				//���� ������ ������ ������ ���� �� ���� ����
				FileVO filedata = new FileVO();
				filedata.setName(file.getOriginalFilename());
				filedata.setFile_size((int)file.getSize());
				filedata.setPost_id(post.getId());
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
		  
		return "redirect:detail?id="+post.getId();		
	}	
	
	//�Խù� ����
	@RequestMapping("delpost")
	public String delPost(@RequestParam("id") int id,
			@RequestParam(value="delcheck", required = false, defaultValue ="0" ) int delcheck,
			Model model) throws Exception{
		
		//���� ���� Ȯ���� ���� �Խù� �ۼ��� 
		PostVO detail = postService.getDetail(id); 
		String nickname = detail.getWriter_id();
		
		model.addAttribute("nickname", nickname);
		if(delcheck == 1) {
			postService.delPost(id);
			
			return "redirect:list";
			
		}else {
		
			return "board.tab.delpost";
		}
	}
	
	//�Խù� ���� �� ������
	@RequestMapping("edit")
	public String editPost(@RequestParam("id") int id, Model model) throws Exception{
		//���� �� �Խù� ���� ǥ�⸦ ���� �Խù� �� ������ ÷������ ���
		PostVO detail = postService.getDetail(id);
		List<FileVO> files = postService.getFileList(id);
		
		model.addAttribute("detail", detail);
		model.addAttribute("files", files);
		
		return "board.tab.edit";
	}
	
	//�Խù� ���� ����
	@RequestMapping(value="edit", method= RequestMethod.POST)
	public String editPost(@RequestParam("id") int id, PostVO post,
			@RequestParam("file") MultipartFile[] files,
			@RequestParam(value="delFiles", required = false) int[] delFiles,
			HttpServletRequest request, Model model) throws Exception{
		
		//�Խù� ����
		postService.editPost(post);
		
		//���� �̸��� �ߺ� ������ ���� �Խù��� ��¥ ����
		Date now = new Date();
	
		//���� ���ε� �� ���� �̸� ����
		for(MultipartFile file: files) {
			if(file.getOriginalFilename()!="") {
				//������� ���ϱ�
				ServletContext ctx = request.getServletContext();
				String webPath = "/static/tab/upload";
				String realPath = ctx.getRealPath(webPath);
				File savePath = new File(realPath);
				
				//���� ������ ������ ������ ���� �� ���� ����
				FileVO filedata = new FileVO();
				filedata.setName(file.getOriginalFilename());
				filedata.setFile_size((int)file.getSize());
				filedata.setPost_id(post.getId());
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
		
		return "redirect:detail?id="+post.getId();
	}
	
	//��� ���
	@RequestMapping(value="regreply", method=RequestMethod.POST)
	public String regReply(ReplyVO reply) throws Exception{
		replyService.regReply(reply);
		System.out.println(reply.getPost_id());
		
		return "redirect:detail?id="+reply.getPost_id();
	}
	
	//��� ����
	@RequestMapping(value="delreply")
	public String delReply(@RequestParam("rid") int rid,
			@RequestParam(value="pid", required = false) String pid,
			@RequestParam(value="confirm", required = false, 
			defaultValue = "false") boolean confirm) throws Exception{
		
		//�� ��ũ��Ʈ �˸�â���� ���� Ȯ���� �����ٸ�
		if(confirm) {
			replyService.delReply(rid);
			return "redirect:detail?id="+pid;
		}
		
		return "board.tab.delreply";
	}
	




}
