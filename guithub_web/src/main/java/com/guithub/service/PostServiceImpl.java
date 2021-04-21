package com.guithub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guithub.dao.PostDAO;
import com.guithub.domain.PostVO;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDAO dao;
	
	//�Խù� ���
	@Override
	public List<PostVO> getList() throws Exception {

		return dao.getList();
	}

	//�Խù� ��� (����¡)
	@Override
	public List<PostVO> getList(int page) throws Exception {
		
		
		return dao.getList(page);
	}
	
	//�Խù� ���� ī��Ʈ
	@Override
	public int getCount() throws Exception {
		
		return dao.getCount();
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



	
}
