package com.guithub.dao;

import java.util.List;

import com.guithub.domain.PostVO;
import com.guithub.domain.ReplyVO;

public interface PostDAO {

	//�Խù� ��� (����¡ + ��ġ)
	public List<PostVO> getList(int page, String field, String keyword) throws Exception;
	//�Խù� ���� ī��Ʈ
	public int getCount(String field, String keyword) throws Exception;
	//�Խù� �󼼺���
	public PostVO getDetail(int id) throws Exception;
	//�Խù� ���
	public void regPost(PostVO vo) throws Exception;
	//�Խù� ����
	public int delPost(int id, String password) throws Exception;	
	//��� ���
	public List<ReplyVO> getListReply(int post_id) throws Exception;
	//��� ���
	public void regReply(ReplyVO vo) throws Exception;
}
