package com.guithub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guithub.dao.PostDAO;
import com.guithub.domain.PostVO;
import com.guithub.domain.ReplyVO;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDAO dao;
	
	//�Խù� ��� (����¡ + ��ġ)
	@Override
	public List<PostVO> getList(int page, String field, String keyword) throws Exception {
		
		return dao.getList(page, field, keyword);
	}
	
	//�Խù� ���� ī��Ʈ
	@Override
	public int getCount(String field, String keyword) throws Exception {
		
		return dao.getCount(field, keyword);
	}
	
	//�Խù� �󼼺���
	@Override
	public PostVO getDetail(int id) throws Exception {
		
		return dao.getDetail(id);
	}

	//�Խù� ���
	@Override
	public void regPost(PostVO vo) throws Exception {
		
		dao.regPost(vo);
		
	}
	
	//�Խù� ����
	@Override
	public int delPost(int id, String password) throws Exception {
		
		return dao.delPost(id, password);
	}

	//��� ���
	@Override
	public List<ReplyVO> getListReply(int post_id) throws Exception {
		
		return dao.getListReply(post_id);
	}

	//��� ���
	@Override
	public void regReply(ReplyVO vo) throws Exception {
		
		dao.regReply(vo);	
	}







	
}
