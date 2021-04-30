package com.guithub.service.board.general;

import java.util.List;

import com.guithub.domain.board.general.FileVO;
import com.guithub.domain.board.general.PostVO;
import com.guithub.domain.board.general.PostVO_view;
import com.guithub.domain.board.general.ReplyVO;

public interface PostService {
	//�Խù� ��� (����¡ + ��ġ)
	public List<PostVO> getList(int page, String field, String keyword) throws Exception;
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
	public void updatePost(PostVO vo) throws Exception;
	//��� ���
	public List<ReplyVO> getListReply(int post_id) throws Exception;
	//��� ���
	public void regReply(ReplyVO vo) throws Exception;
	//��� ����
	public int delReply(int id, String password) throws Exception;
	//��� ���� ī��Ʈ
	public int getCountReply(int id) throws Exception;
	//�Խù� ��ȸ�� ����
	public void updateHit(int id) throws Exception;
	//���� ���� ����
	public void regFile(FileVO vo) throws Exception;
	//���� ���� ����
	public List<FileVO> getFile(int id);
}
