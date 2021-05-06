package com.guithub.service.board.tab;

import java.util.List;

import com.guithub.domain.board.tab.FileVO;
import com.guithub.domain.board.tab.PostVO;
import com.guithub.domain.board.tab.PostVO_view;

public interface PostService {

	//게시물 총 개수
	int getPostCnt(String field, String keyword) throws Exception;
	//게시물 목록 (페이징 + 서치)
	List<PostVO_view> getList(int page, String field, String keyword) throws Exception;
	//게시물 상세보기
	PostVO getDetail(int id) throws Exception;
	//게시물 조회수 갱신
	void updateHit(int id) throws Exception;
	//게시물 첨부파일 목록
	List<FileVO> getFileList(int id) throws Exception;
	//게시물 등록
	void regPost(PostVO post) throws Exception;
	//게시물 첨부파일 등록
	void regFile(FileVO filedata) throws Exception;
	//게시물 삭제
	void delPost(int id) throws Exception;
	//게시물 수정
	void editPost(PostVO post) throws Exception;
	//게시물 첨부파일 삭제
	void delFile(int delFile_id) throws Exception;

}
