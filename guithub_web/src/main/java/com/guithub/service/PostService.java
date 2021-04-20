package com.guithub.service;

import java.util.List;

import com.guithub.domain.PostVO;

public interface PostService {
	
	public List<PostVO> getList() throws Exception;
	public PostVO getDetail(int id);
}
