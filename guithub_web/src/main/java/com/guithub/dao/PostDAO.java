package com.guithub.dao;

import java.util.List;

import com.guithub.domain.PostVO;

public interface PostDAO {
	public List<PostVO> getList() throws Exception;
	public PostVO getDetail(int id);
}
