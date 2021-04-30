package com.guithub.controller.board.general;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.guithub.domain.board.general.FileVO;
import com.guithub.domain.board.general.Paging;
import com.guithub.domain.board.general.PostVO;
import com.guithub.domain.board.general.PostVO_view;
import com.guithub.domain.board.general.ReplyVO;
import com.guithub.service.board.general.PostService;

@Controller
@RequestMapping("/board/general/")
public class BoardController {
	@Autowired
	PostService service;
	
	//�Խù� ��� (����¡ + ��ġ)
	@RequestMapping(value="list", method = {RequestMethod.GET, RequestMethod.POST})
	public String getList(@RequestParam(value="page", required = false, defaultValue = "1") int page, 
			@RequestParam(value="field", required = false, defaultValue = "title") String field,
			@RequestParam(value="keyword", required = false, defaultValue = "") String keyword, 
			Model model) throws Exception {
				
		//�Խù� �� ������ �Խù��� �޸� ��� ����
		int count = service.getCount(field, keyword);
		//���� �������� ���� �ϴ� ����¡ ��� ���
		Paging paging = new Paging(page, count);
		
		//List<PostVO> listPagingSearch = service.getList(page, field, keyword);
		List<PostVO_view> list_view = service.getList_view(page,field,keyword);
		//����¡�� ���� �Խù� ���
		model.addAttribute("list", list_view);
		//����¡ ��ȣ ���		
		model.addAttribute("paging", paging);
		
		return "board.general.list";
	}
	
	//�Խù� �󼼺���
	@RequestMapping("detail")
	public String getdetail(@RequestParam("id") int id, Model model) throws Exception {
		//��ȸ�� ���� + �Խù� �� ���� +  ��� ���
		service.updateHit(id); 
		PostVO detail = service.getDetail(id);
		List<ReplyVO> listReply = service.getListReply(id);
		List<FileVO> files = service.getFile(id);
	
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
		service.regPost(vo); 
		
		//���� �̸��� �ߺ� ������ ���� �Խù��� ��¥ ����
		Date now = new Date();
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyMMddHHmmss");
		
		//���� ���ε� �� ���� �̸� ����
		for(MultipartFile file: files) {
			if(file.getOriginalFilename()!="") {
				//������� ���ϱ�
				ServletContext ctx = request.getServletContext();
				String webPath = "/static/upload";
				String realPath = ctx.getRealPath(webPath);
				System.out.printf("realPath: %s \n", realPath);
				File savePath = new File(realPath); //���ϸ��� ���Ե��� ���� ���
				//���� ������ ������ ������ ���� �� ���� ����
				FileVO filedata = new FileVO();
				filedata.setName(file.getOriginalFilename());
				filedata.setFile_size((int)file.getSize());
				filedata.setPost_id(vo.getId());
				filedata.setRegdate(now);
				filedata.setRealName();
				service.regFile(filedata);
				
				//���� ������ο� ������ ���ϸ� �ߺ� ������ ���� ���ϸ� + ��¥ + Ȯ���ڸ� ����
				/*
				String realFileName = filedata.getName().split("\\.")[0]+"_"
						+simpleDate.format(filedata.getRegdate())+
						"."+filedata.getName().split("\\.")[1];
				*/
				
				//��� ������ �������� �����Ƿ� Ȯ�� �� ������ ����
				if(!savePath.exists())
					savePath.mkdirs(); //��ü ��θ� ������ִ� �۾�(mkdirs)
					
				realPath += File.separator + filedata.getRealName(); //���� �������
				File saveFile = new File(realPath); //���� ����(���ε�)
					
				file.transferTo(saveFile);
			}
		} 
		  
		return "redirect:list";  
		
	}
	 
		
	//�Խù� ���� ��й�ȣ �Է� �� + ����
	@RequestMapping(value = "delpost", method = {RequestMethod.POST , RequestMethod.GET})
	public String delPost(@RequestParam("id") int id,
			@RequestParam(value="password", required = false, defaultValue = "") String password,
			Model model) throws Exception{
		
		//��й�ȣ�� �Է����� ���� ���� ���� ����
		if(!password.equals("")) {
			int delCnt = service.delPost(id, password);
			model.addAttribute("delCnt", delCnt);
		}
		
		return "board.general.delpost";
	}
	
	//��� ��� �� redirect
	@RequestMapping(value="regreply", method=RequestMethod.POST)
	public String regReply(ReplyVO vo) throws Exception{
		service.regReply(vo);
	
		return "redirect:detail?id="+vo.getPost_id();
	}
	
	//��� ���� ��й�ȣ �Է� �� + ����
	@RequestMapping(value = "delreply", method = {RequestMethod.POST, RequestMethod.GET})
	public String delReply(@RequestParam("rid") int id,
			@RequestParam(value="password", required = false, defaultValue = "") String password,
			Model model) throws Exception{
		
		//��й�ȣ�� �Է����� ���� ���� ���� ����
		if(!password.equals("")) {
			int delCnt = service.delReply(id, password);
			model.addAttribute("delCnt", delCnt);
		}
		
		return "board.general.delreply";
	}
	
	//�Խù� ���� �н����� �Է� �� ������
	@RequestMapping(value = "checkpwd", method = {RequestMethod.POST, RequestMethod.GET})
	public String checkPwd(@RequestParam("id") int id,
			@RequestParam(value="password", required = false, defaultValue = "") String password,
			Model model) throws Exception{
		
		//��й�ȣ�� �Է����� ���� Ȯ�� ���� ����
		if(!password.equals("")) {
			System.out.println("���̵� ��� �Է� Ȯ�� �Ϸ� ���� �� ���� �������� �����̷�Ʈ");
			boolean check = service.checkPwd(id, password);
			model.addAttribute("check", check);
			
		}
		
		return "board.general.checkpwd";
	}
	
	//�Խù� ���� �� + ����
	@RequestMapping(value="edit", method= {RequestMethod.GET, RequestMethod.POST})
	public String editPost(@RequestParam("id") int id, PostVO vo, Model model) throws Exception{
		//���� �� �Խù� ���� ǥ�⸦ ���� �Խù� �� ����
		PostVO detail = service.getDetail(id);
		model.addAttribute("detail", detail);
		//�Խù� ����
		service.updatePost(vo);
		
		return "board.general.edit";
	}
	
	
}
