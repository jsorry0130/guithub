package com.guithub.service.board.tab;

import java.util.List;

import com.guithub.domain.board.tab.FileVO;
import com.guithub.domain.board.tab.PostVO;
import com.guithub.domain.board.tab.PostVO_view;

public interface PostService {

	//�Խù� �� ����
	int getPostCnt(String field, String keyword) throws Exception;
	//�Խù� ��� (����¡ + ��ġ)
	List<PostVO_view> getList(int page, String field, String keyword) throws Exception;
	//�Խù� �󼼺���
	PostVO getDetail(int id) throws Exception;
	//�Խù� ��ȸ�� ����
	void updateHit(int id) throws Exception;
	//�Խù� ÷������ ���
	List<FileVO> getFileList(int id) throws Exception;
	//�Խù� ���
	void regPost(PostVO post) throws Exception;
	//�Խù� ÷������ ���
	void regFile(FileVO filedata) throws Exception;
	//�Խù� ����
	void delPost(int id) throws Exception;
	//�Խù� ����
	void editPost(PostVO post) throws Exception;
	//�Խù� ÷������ ����
	void delFile(int delFile_id) throws Exception;

}
