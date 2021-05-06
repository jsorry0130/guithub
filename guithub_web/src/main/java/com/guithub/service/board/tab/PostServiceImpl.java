package com.guithub.service.board.tab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guithub.dao.board.tab.PostDAO;
import com.guithub.domain.board.tab.FileVO;
import com.guithub.domain.board.tab.PostVO;
import com.guithub.domain.board.tab.PostVO_view;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO postDao;
	
	//게시물 총 개수
	@Override
	public int getPostCnt(String field, String keyword) throws Exception {
		
		return postDao.getPostCnt(field, keyword);
	}
	
	//게시물 목록 (페이징 + 서치)
	@Override
	public List<PostVO_view> getList(int page, String field, String keyword) throws Exception {
		
		return postDao.getList(page, field, keyword);
	}

	//게시물 상세보기
	@Override
	public PostVO getDetail(int id) throws Exception {
		
		return postDao.getDetail(id);
	}

	//게시물 조회수 갱신
	@Override
	public void updateHit(int id) throws Exception {
		
		postDao.updateHit(id);
	}

	//게시물 첨부파일 목록
	@Override
	public List<FileVO> getFileList(int id) throws Exception {
		
		return postDao.getFileList(id);
	}

	//게시물 등록
	@Override
	public void regPost(PostVO post) throws Exception {
		postDao.regPost(post);
	}

	//게시물 첨부파일 등록
	@Override
	public void regFile(FileVO filedata) throws Exception {
		
		postDao.regFile(filedata);
	}

	//게시물 삭제
	@Override
	public void delPost(int id) throws Exception {
		postDao.delPost(id);
	}

	//게시물 수정
	@Override
	public void editPost(PostVO post) throws Exception {
		postDao.editPost(post);
	}

	//게시물 첨부파일 삭제
	@Override
	public void delFile(int delFile_id) throws Exception {
		postDao.delFile(delFile_id);
	}
	

}
