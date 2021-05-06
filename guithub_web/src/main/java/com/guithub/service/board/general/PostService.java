package com.guithub.service.board.general;

import java.util.List;

import com.guithub.domain.board.general.FileVO;
import com.guithub.domain.board.general.PostVO;
import com.guithub.domain.board.general.PostVO_view;

public interface PostService {
	//�Խù� ���(����¡+��ġ+��� ī��Ʈ)
	public List<PostVO_view> getList_view(int page, String field, String keyword) throws Exception;
	//�Խù� ���� ī��Ʈ
	public int getCount(String field, String keyword) throws Exception;
	//�Խù� �󼼺���
	public PostVO getDetail(int id) throws Exception;
	//�Խù� ���
	public void regPost(PostVO vo) throws Exception;
	//�Խù� ����
	public int delPost(int id, String password) throws Exception;
	//�Խù� �н����� ����
	public boolean checkPwd(int id, String password) throws Exception;
	//�Խù� ����
	public void editPost(PostVO vo) throws Exception;
	//�Խù� ��ȸ�� ����
	public void updateHit(int id) throws Exception;
	//�Խù� ÷������ ���� ����
	public void regFile(FileVO vo) throws Exception;
	//�Խù� ÷������ ���
	public List<FileVO> getFile(int id);
	//�Խù� ÷������ ����
	public void delFile(int id);
}
