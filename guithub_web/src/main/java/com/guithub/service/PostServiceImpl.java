package com.guithub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guithub.dao.PostDAO;
import com.guithub.domain.PostVO;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDAO dao;
	
	//게시물 목록
	@Override
	public List<PostVO> getList() throws Exception {

		return dao.getList();
	}

	//게시물 목록 (페이징)
	@Override
	public List<PostVO> getList(int page) throws Exception {
		
		
		return dao.getList(page);
	}
	
	//게시물 개수 카운트
	@Override
	public int getCount() throws Exception {
		
		return dao.getCount();
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



	
}
