package com.guithub.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guithub.domain.PostVO;
import com.guithub.domain.ReplyVO;

@Repository
public class PostDAOImpl implements PostDAO {

	@Autowired
	private SqlSession sql;
	
	private static String namespace = "com.guithub.mappers.post";
	
	
	//게시물 목록(페이징 + 서치)
	@Override
	public List<PostVO> getList(int page, String field, String keyword) throws Exception {
		
		//한 페이지당 글을 15개씩 나타내기 위한 카운트
		int startRowNum = 1+(page-1)*15;
		int endRowNum = page*15;
		//매퍼에 전달하기 위해 해쉬데이터 생성
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("startRowNum", startRowNum);
		data.put("endRowNum", endRowNum);
		
		data.put("field", field);
		data.put("keyword", keyword);
		
		return sql.selectList(namespace + ".listPagingSearch", data);
	}
	
	//게시물 개수 카운트
	@Override
	public int getCount(String field, String keyword) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("field", field);
		data.put("keyword", keyword);
		
		
		return sql.selectOne(namespace + ".count", data);
	}
	
	//게시물 상세보기
	@Override
	public PostVO getDetail(int id) {
		
		return sql.selectOne(namespace+".detail", id);
	}

	
	//게시물 등록
	@Override
	public void regPost(PostVO vo) throws Exception {
		
		System.out.println(sql.insert(namespace + ".regPost", vo));
		
	}
	
	//게시물 삭제
	@Override
	public int delPost(int id, String password) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("id", id);
		data.put("password", password);
			
		return sql.delete(namespace+".delPost", data);
	}
	
	//댓글 목록
	@Override
	public List<ReplyVO> getListReply(int post_id) throws Exception {
		
		return sql.selectList(namespace+".listReply", post_id);
	}

	//댓글 등록
	@Override
	public void regReply(ReplyVO vo) throws Exception {
		
		sql.insert(namespace + ".regReply", vo);
	}





}
