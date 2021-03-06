package com.guithub.dao.board.general;

import java.util.List;

import com.guithub.domain.board.general.FileVO;
import com.guithub.domain.board.general.PostVO;
import com.guithub.domain.board.general.PostVO_view;
import com.guithub.domain.board.general.ReplyVO;

public interface PostDAO {

	//게시물 목록 (페이징 + 서치)
	public List<PostVO> getList(int page, String field, String keyword) throws Exception;
	//게시물 목록(페이징+서치+댓글 카운트)
	public List<PostVO_view> getList_view(int page, String field, String keyword) throws Exception;
	//게시물 개수 카운트
	public int getCount(String field, String keyword) throws Exception;
	//게시물 상세보기
	public PostVO getDetail(int id) throws Exception;
	//게시물 등록
	public void regPost(PostVO vo) throws Exception;
	//게시물 삭제
	public int delPost(int id, String password) throws Exception;
	//게시물 패스워드 검증
	public boolean checkPwd(int id, String password) throws Exception;
	//게시물 수정
	public void editPost(PostVO vo) throws Exception;
	//게시물 조회수 갱신
	public void updateHit(int id) throws Exception;
	//파일 정보 삽입
	public void regFile(FileVO vo) throws Exception;
	//파일 정보 보기
	public List<FileVO> getFile(int id);
	//파일 삭제
	public void delFile(int id);
	
}
