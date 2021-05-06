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
	
	//게시물 목록 (페이징 + 서치)
	@RequestMapping("list")
	public String getList(@RequestParam(value="page", required = false, defaultValue = "1") int page,
			@RequestParam(value="field", required = false, defaultValue = "title") String field,
			@RequestParam(value="keyword", required = false, defaultValue = "") String keyword,
			Model model) throws Exception{
		
		//총 게시물 개수 + 페이징 목록 계산 + 게시물 목록
		int postCnt = postService.getPostCnt(field, keyword);
		Paging paging = new Paging(page, postCnt);
		List<PostVO_view> list = postService.getList(page, field, keyword);
		
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		return "board.tab.list";
	}
	
	//게시물 상세보기
	@RequestMapping("detail")
	public String getDetail(@RequestParam("id") int id, Model model) throws Exception{
		//조회수 갱신 + 게시물 상세 보기 +  댓글 목록 + 파일 목록
		postService.updateHit(id); 
		PostVO detail = postService.getDetail(id);
		List<ReplyVO> listReply = replyService.getReplyList(id);
		List<FileVO> files = postService.getFileList(id);
	
		//다운로드 링크를 위한 실제물리경로 파일이름 세팅
		for(FileVO file: files) {
			file.setRealName();
		}
		
		model.addAttribute("detail", detail);
		model.addAttribute("listReply", listReply);
		model.addAttribute("files", files);
		
		return "board.tab.detail";
	}
	
	//게시물 작성 폼 페이지
	@RequestMapping("reg")
	public String regPost() throws Exception{
		
		return "board.tab.reg";
	}
	
	//게시물 작성 후 redirect  
	@RequestMapping(value="reg", method=RequestMethod.POST)
	public String regPost(PostVO post, @RequestParam("file") MultipartFile[] files,
			HttpServletRequest request) throws Exception{
		//게시물 등록
		postService.regPost(post);
		
		//파일 이름의 중복 방지를 위해 게시물의 날짜 추출
		Date now = new Date();
	
		//파일 업로드 및 파일 이름 생성
		for(MultipartFile file: files) {
			if(file.getOriginalFilename()!="") {
				//물리경로 구하기
				ServletContext ctx = request.getServletContext();
				String webPath = "/static/tab/upload";
				String realPath = ctx.getRealPath(webPath);
				File savePath = new File(realPath);
				
				//파일 정보를 저장할 도메인 세팅 후 정보 삽입
				FileVO filedata = new FileVO();
				filedata.setName(file.getOriginalFilename());
				filedata.setFile_size((int)file.getSize());
				filedata.setPost_id(post.getId());
				filedata.setRegdate(now);
				filedata.setRealName();
				postService.regFile(filedata);
						
				//경로 폴더가 없다면 생성
				if(!savePath.exists())
					savePath.mkdirs(); //전체 경로를 만들어주는 작업(mkdirs)
					
				realPath += File.separator + filedata.getRealName(); //최종 물리경로
				File saveFile = new File(realPath); //파일 생성(업로드)
					
				file.transferTo(saveFile);
			}
		} 
		  
		return "redirect:detail?id="+post.getId();		
	}	
	
	//게시물 삭제
	@RequestMapping("delpost")
	public String delPost(@RequestParam("id") int id,
			@RequestParam(value="delcheck", required = false, defaultValue ="0" ) int delcheck,
			Model model) throws Exception{
		
		//삭제 권한 확인을 위한 게시물 작성자 
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
	
	//게시물 수정 폼 페이지
	@RequestMapping("edit")
	public String editPost(@RequestParam("id") int id, Model model) throws Exception{
		//수정 전 게시물 상태 표기를 위한 게시물 상세 정보와 첨부파일 목록
		PostVO detail = postService.getDetail(id);
		List<FileVO> files = postService.getFileList(id);
		
		model.addAttribute("detail", detail);
		model.addAttribute("files", files);
		
		return "board.tab.edit";
	}
	
	//게시물 수정 실행
	@RequestMapping(value="edit", method= RequestMethod.POST)
	public String editPost(@RequestParam("id") int id, PostVO post,
			@RequestParam("file") MultipartFile[] files,
			@RequestParam(value="delFiles", required = false) int[] delFiles,
			HttpServletRequest request, Model model) throws Exception{
		
		//게시물 수정
		postService.editPost(post);
		
		//파일 이름의 중복 방지를 위해 게시물의 날짜 추출
		Date now = new Date();
	
		//파일 업로드 및 파일 이름 생성
		for(MultipartFile file: files) {
			if(file.getOriginalFilename()!="") {
				//물리경로 구하기
				ServletContext ctx = request.getServletContext();
				String webPath = "/static/tab/upload";
				String realPath = ctx.getRealPath(webPath);
				File savePath = new File(realPath);
				
				//파일 정보를 저장할 도메인 세팅 후 정보 삽입
				FileVO filedata = new FileVO();
				filedata.setName(file.getOriginalFilename());
				filedata.setFile_size((int)file.getSize());
				filedata.setPost_id(post.getId());
				filedata.setRegdate(now);
				filedata.setRealName();
				postService.regFile(filedata);
						
				//경로 폴더가 없다면 생성
				if(!savePath.exists())
					savePath.mkdirs(); //전체 경로를 만들어주는 작업(mkdirs)
					
				realPath += File.separator + filedata.getRealName(); //최종 물리경로
				File saveFile = new File(realPath); //파일 생성(업로드)
					
				file.transferTo(saveFile);
			}
		}
		
		//체크한 파일 삭제
		
		if(delFiles!=null) {
			for(int delFile_id: delFiles) {
				System.out.println("체크된 삭제할 파일: " + delFile_id);
				postService.delFile(delFile_id);
			}
		}
		
		return "redirect:detail?id="+post.getId();
	}
	
	//댓글 등록
	@RequestMapping(value="regreply", method=RequestMethod.POST)
	public String regReply(ReplyVO reply) throws Exception{
		replyService.regReply(reply);
		System.out.println(reply.getPost_id());
		
		return "redirect:detail?id="+reply.getPost_id();
	}
	
	//댓글 삭제
	@RequestMapping(value="delreply")
	public String delReply(@RequestParam("rid") int rid,
			@RequestParam(value="pid", required = false) String pid,
			@RequestParam(value="confirm", required = false, 
			defaultValue = "false") boolean confirm) throws Exception{
		
		//뷰 스크립트 알림창에서 삭제 확인을 눌렀다면
		if(confirm) {
			replyService.delReply(rid);
			return "redirect:detail?id="+pid;
		}
		
		return "board.tab.delreply";
	}
	




}
