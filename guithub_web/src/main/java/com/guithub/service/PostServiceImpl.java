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
	
	@Override
	public List<PostVO> getList() throws Exception {

		return dao.getList();
	}

	@Override
	public PostVO getDetail(int id) {
		
		return dao.getDetail(id);
	}

}
