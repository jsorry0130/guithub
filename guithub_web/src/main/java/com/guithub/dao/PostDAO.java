package com.guithub.dao;

import java.util.List;

import com.guithub.domain.PostVO;

public interface PostDAO {
	//�Խù� ���
	public List<PostVO> getList() throws Exception;
	//�Խù� ��� (����¡)
	public List<PostVO> getList(int page) throws Exception;
	//�Խù� ���� ī��Ʈ
	public int getCount() throws Exception;
	//�Խù� �󼼺���
	public PostVO getDetail(int id) throws Exception;
	//�Խù� ���
	public void regPost(PostVO vo) throws Exception;
	
}
