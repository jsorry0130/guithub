package com.guithub.dao;

import java.util.List;

import com.guithub.domain.PostVO;
import com.guithub.domain.ReplyVO;

public interface PostDAO {

	//게시물 목록 (페이징 + 서치)
	public List<PostVO> getList(int page, String field, String keyword) throws Exception;
	//게시물 개수 카운트
	public int getCount(String field, String keyword) throws Exception;
	//게시물 상세보기
	public PostVO getDetail(int id) throws Exception;
	//게시물 등록
	public void regPost(PostVO vo) throws Exception;
	//게시물 삭제
	public int delPost(int id, String password) throws Exception;	
	//댓글 목록
	public List<ReplyVO> getListReply(int post_id) throws Exception;
	//댓글 등록
	public void regReply(ReplyVO vo) throws Exception;
}
