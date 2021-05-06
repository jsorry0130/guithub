package com.guithub.controller.board.general;

import java.io.File;
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
	
	//게시물 목록 (페이징 + 서치)
	@RequestMapping(value="list", method = {RequestMethod.GET, RequestMethod.POST})
	public String getList(@RequestParam(value="page", required = false, defaultValue = "1") int page, 
			@RequestParam(value="field", required = false, defaultValue = "title") String field,
			@RequestParam(value="keyword", required = false, defaultValue = "") String keyword, 
			Model model) throws Exception {
				
		//게시물 총 개수와 게시물에 달린 댓글 개수
		int count = postService.getCount(field, keyword);
		//현재 페이지에 따른 하단 페이징 목록 계산
		Paging paging = new Paging(page, count);
		
		//List<PostVO> listPagingSearch = service.getList(page, field, keyword);
		List<PostVO_view> list_view = postService.getList_view(page,field,keyword);
		//페이징에 따른 게시물 목록
		model.addAttribute("list", list_view);
		//페이징 번호 목록		
		model.addAttribute("paging", paging);
		
		return "board.general.list";
	}
	
	//게시물 상세보기
	@RequestMapping("detail")
	public String getdetail(@RequestParam("id") int id, Model model) throws Exception {
		//조회수 갱신 + 게시물 상세 보기 +  댓글 목록 + 파일 목록
		postService.updateHit(id); 
		PostVO detail = postService.getDetail(id);
		List<ReplyVO> listReply = replyService.getListReply(id);
		List<FileVO> files = postService.getFile(id);
	
		//다운로드 링크를 위한 실제물리경로 파일이름 세팅
		for(FileVO file: files) {
			file.setRealName();
		}
		
		//뷰에 나타낼 상세정보와 댓글 목록
		model.addAttribute("detail", detail);
		model.addAttribute("listReply", listReply);
		model.addAttribute("files", files);
		
		return"board.general.detail";
	}
	
	
	//게시물 작성 폼 페이지
	@RequestMapping("reg")
	public String regPost(){
	  
		return "board.general.reg";
		
	}
	  
	//게시물 작성 후 redirect  
	@RequestMapping(value="reg", method = RequestMethod.POST)
	public String regPost(PostVO vo, @RequestParam("file") MultipartFile[] files,
			HttpServletRequest request) throws Exception {
		//게시물 등록
		postService.regPost(vo); 
		
		//파일 이름의 중복 방지를 위해 게시물의 날짜 추출
		Date now = new Date();
	
		//파일 업로드 및 파일 이름 생성
		for(MultipartFile file: files) {
			if(file.getOriginalFilename()!="") {
				//물리경로 구하기
				ServletContext ctx = request.getServletContext();
				String webPath = "/static/upload";
				String realPath = ctx.getRealPath(webPath);
				File savePath = new File(realPath);
				
				//파일 정보를 저장할 도메인 세팅 후 정보 삽입
				FileVO filedata = new FileVO();
				filedata.setName(file.getOriginalFilename());
				filedata.setFile_size((int)file.getSize());
				filedata.setPost_id(vo.getId());
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
		  
		return "redirect:detail?id="+vo.getId();  
		
	}
	 
		
	//게시물 삭제 비밀번호 입력 폼 + 실행
	@RequestMapping(value = "delpost", method = {RequestMethod.POST , RequestMethod.GET})
	public String delPost(@RequestParam("id") int id,
			@RequestParam(value="password", required = false, defaultValue = "") String password,
			Model model) throws Exception{
		
		//비밀번호를 입력했을 때만 삭제 쿼리 실행
		if(!password.equals("")) {
			int delCnt = postService.delPost(id, password);
			model.addAttribute("delCnt", delCnt);
		}
		
		return "board.general.delpost";
	}
	
	//댓글 등록 후 redirect
	@RequestMapping(value="regreply", method=RequestMethod.POST)
	public String regReply(ReplyVO vo) throws Exception{
		replyService.regReply(vo);
	
		return "redirect:detail?id="+vo.getPost_id();
	}
	
	//댓글 삭제 비밀번호 입력 폼 + 실행
	@RequestMapping(value = "delreply", method = {RequestMethod.POST, RequestMethod.GET})
	public String delReply(@RequestParam("rid") int id,
			@RequestParam(value="password", required = false, defaultValue = "") String password,
			Model model) throws Exception{
		
		//비밀번호를 입력했을 때만 삭제 쿼리 실행
		if(!password.equals("")) {
			int delCnt = replyService.delReply(id, password);
			model.addAttribute("delCnt", delCnt);
		}
		
		return "board.general.delreply";
	}
	
	//게시물 수정 패스워드 입력 폼 페이지
	@RequestMapping(value = "checkpwd", method = {RequestMethod.POST, RequestMethod.GET})
	public String checkPwd(@RequestParam("id") int id,
			@RequestParam(value="password", required = false, defaultValue = "") String password,
			Model model) throws Exception{
		
		//비밀번호를 입력했을 때만 확인 쿼리 실행
		if(!password.equals("")) {
			System.out.println("아이디 비번 입력 확인 완료 검증 후 수정 페이지로 리다이렉트");
			boolean check = postService.checkPwd(id, password);
			model.addAttribute("check", check);
			
		}
		
		return "board.general.checkpwd";
	}
	
	//게시물 수정 폼 페이지
	@RequestMapping("edit")
	public String editPost(@RequestParam("id") int id, Model model) throws Exception{
		//수정 전 게시물 상태 표기를 위한 게시물 상세 정보와 첨부파일 목록
		PostVO detail = postService.getDetail(id);
		List<FileVO> files = postService.getFile(id);
		
		model.addAttribute("detail", detail);
		model.addAttribute("files", files);
		
		return "board.general.edit";
	}
	
	//게시물 수정 실행
	@RequestMapping(value="edit", method= RequestMethod.POST)
	public String editPost(@RequestParam("id") int id, PostVO vo,
			@RequestParam("file") MultipartFile[] files,
			@RequestParam(value="delFiles", required = false) int[] delFiles,
			HttpServletRequest request, Model model) throws Exception{
		//게시물 수정
		postService.editPost(vo);
		
		//파일 이름의 중복 방지를 위해 게시물의 날짜 추출
		Date now = new Date();
	
		//파일 업로드 및 파일 이름 생성
		for(MultipartFile file: files) {
			if(file.getOriginalFilename()!="") {
				//물리경로 구하기
				ServletContext ctx = request.getServletContext();
				String webPath = "/static/upload";
				String realPath = ctx.getRealPath(webPath);
				File savePath = new File(realPath);
				
				//파일 정보를 저장할 도메인 세팅 후 정보 삽입
				FileVO filedata = new FileVO();
				filedata.setName(file.getOriginalFilename());
				filedata.setFile_size((int)file.getSize());
				filedata.setPost_id(vo.getId());
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
		
		return "redirect:detail?id="+vo.getId();
	}
	
	
}
