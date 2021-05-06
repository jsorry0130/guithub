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
	private PostDAO postDao;
	
	//게시물 목록 (페이징+서치+댓글카운트)
	@Override
	public List<PostVO_view> getList_view(int page, String field, String keyword) throws Exception {

		return postDao.getList_view(page, field, keyword);
	}
	
	//게시물 개수 카운트
	@Override
	public int getCount(String field, String keyword) throws Exception {
		
		return postDao.getCount(field, keyword);
	}
	
	//게시물 상세보기
	@Override
	public PostVO getDetail(int id) throws Exception {
		
		return postDao.getDetail(id);
	}

	//게시물 등록
	@Override
	public void regPost(PostVO vo) throws Exception {
		
		postDao.regPost(vo);
		
	}
	
	//게시물 삭제
	@Override
	public int delPost(int id, String password) throws Exception {
		
		return postDao.delPost(id, password);
	}

	//게시물 조회수 갱신
	@Override
	public void updateHit(int id) throws Exception {
		
		postDao.updateHit(id);
	}

	//게시물 패스워드 검증
	@Override
	public boolean checkPwd(int id, String password) throws Exception {
		
		return postDao.checkPwd(id, password);
	}

	//게시물 수정
	@Override
	public void editPost(PostVO vo) throws Exception {
		
		postDao.editPost(vo);
	}

	//파일 정보 삽입
	@Override
	public void regFile(FileVO vo) throws Exception {
		
		postDao.regFile(vo);
	}

	//파일 정보 보기
	@Override
	public List<FileVO> getFile(int id) {

		return postDao.getFile(id);
	}

	//파일 삭제
	@Override
	public void delFile(int id) {
		postDao.delFile(id);
	}









	
}
