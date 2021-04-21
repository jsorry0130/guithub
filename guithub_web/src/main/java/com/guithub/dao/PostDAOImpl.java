package com.guithub.dao;

import java.util.HashMap;
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
	
	//게시물 목록
	@Override
	public List<PostVO> getList() throws Exception {

		return sql.selectList(namespace + ".list");
	}

	//게시물 목록 (페이징)
	@Override
	public List<PostVO> getList(int page) throws Exception {
		
		//한 페이지당 글을 15개씩 나타내기 위한 카운트
		int startRowNum = 1+(page-1)*15;
		int endRowNum = page*15;
		//매퍼에 전달하기 위해 해쉬데이터 생성
		HashMap<String, Integer> data = new HashMap<String, Integer>();
		
		data.put("startRowNum", startRowNum);
		data.put("endRowNum", endRowNum);
		
		return sql.selectList(namespace + ".listPaging", data);
	}
	
	//게시물 개수 카운트
	@Override
	public int getCount() throws Exception {
		
		return sql.selectOne(namespace + ".count");
	}
	
	//게시물 상세보기
	@Override
	public PostVO getDetail(int id) {
		
		return sql.selectOne(namespace+".detail", id);
	}

	
	//게시물 등록
	@Override
	public void regPost(PostVO vo) throws Exception {
		
		sql.insert(namespace + ".reg", vo);
		
	}

}
