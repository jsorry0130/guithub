package com.guithub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guithub.dao.PostDAO;
import com.guithub.domain.PostVO;
import com.guithub.domain.ReplyVO;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDAO dao;
	
	//게시물 목록 (페이징 + 서치)
	@Override
	public List<PostVO> getList(int page, String field, String keyword) throws Exception {
		
		return dao.getList(page, field, keyword);
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







	
}
