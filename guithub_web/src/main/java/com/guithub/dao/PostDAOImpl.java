package com.guithub.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guithub.domain.PostVO;

@Repository
public class PostDAOImpl implements PostDAO {

	@Autowired
	private SqlSession sql;
	
	private static String namespace = "com.guithub.mappers.post";
	
	@Override
	public List<PostVO> getList() throws Exception {

		return sql.selectList(namespace + ".list");
	}

	@Override
	public PostVO getDetail(int id) {
		
		return sql.selectOne(namespace+".detail", id);
	}

}
