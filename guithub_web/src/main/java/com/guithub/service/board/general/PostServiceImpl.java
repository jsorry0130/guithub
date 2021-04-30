package com.guithub.service.board.general;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guithub.dao.board.general.PostDAO;
import com.guithub.domain.board.general.FileVO;
import com.guithub.domain.board.general.PostVO;
import com.guithub.domain.board.general.PostVO_view;
import com.guithub.domain.board.general.ReplyVO;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDAO dao;
	
	//게시물 목록 (페이징 + 서치)
	@Override
	public List<PostVO> getList(int page, String field, String keyword) throws Exception {
		
		return dao.getList(page, field, keyword);
	}
	
	//게시물 목록 (페이징+서치+댓글카운트)
	@Override
	public List<PostVO_view> getList_view(int page, String field, String keyword) throws Exception {

		return dao.getList_view(page, field, keyword);
	}
	
	//게시물 개수 카운트
	@Override
	public int getCount(String field, String keyword) throws Exception {
		
		return dao.getCount(field, keyword);
	}
	
	//게시물 상세보기
	@Override
	public PostVO getDetail(int id) throws Exception {
		
		return dao.getDetail(id);
	}

	//게시물 등록
	@Override
	public void regPost(PostVO vo) throws Exception {
		
		dao.regPost(vo);
		
	}
	
	//게시물 삭제
	@Override
	public int delPost(int id, String password) throws Exception {
		
		return dao.delPost(id, password);
	}

	//댓글 목록
	@Override
	public List<ReplyVO> getListReply(int post_id) throws Exception {
		
		return dao.getListReply(post_id);
	}

	//댓글 등록
	@Override
	public void regReply(ReplyVO vo) throws Exception {
		
		dao.regReply(vo);	
	}

	//댓글 삭제
	@Override
	public int delReply(int id, String password) throws Exception {
		
		return dao.delReply(id, password);
	}

	//댓글 개수 카운트
	@Override
	public int getCountReply(int id) throws Exception {
		
		return dao.getCountReply(id);
	}

	//게시물 조회수 갱신
	@Override
	public void updateHit(int id) throws Exception {
		
		dao.updateHit(id);
	}

	//게시물 패스워드 검증
	@Override
	public boolean checkPwd(int id, String password) throws Exception {
		
		return dao.checkPwd(id, password);
	}

	//게시물 수정
	@Override
	public void updatePost(PostVO vo) throws Exception {
		
		dao.updatePost(vo);
	}

	//파일 정보 삽입
	@Override
	public void regFile(FileVO vo) throws Exception {
		
		dao.regFile(vo);
	}

	//파일 정보 보기
	@Override
	public List<FileVO> getFile(int id) {

		return dao.getFile(id);
	}









	
}
