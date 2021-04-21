package com.guithub.dao;

import java.util.List;

import com.guithub.domain.PostVO;

public interface PostDAO {
	//게시물 목록
	public List<PostVO> getList() throws Exception;
	//게시물 목록 (페이징)
	public List<PostVO> getList(int page) throws Exception;
	//게시물 개수 카운트
	public int getCount() throws Exception;
	//게시물 상세보기
	public PostVO getDetail(int id) throws Exception;
	//게시물 등록
	public void regPost(PostVO vo) throws Exception;
	
}
