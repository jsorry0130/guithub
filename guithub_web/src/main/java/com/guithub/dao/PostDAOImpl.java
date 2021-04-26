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
	
	
	//�Խù� ���(����¡ + ��ġ)
	@Override
	public List<PostVO> getList(int page, String field, String keyword) throws Exception {
		
		//�� �������� ���� 15���� ��Ÿ���� ���� ī��Ʈ
		int startRowNum = 1+(page-1)*15;
		int endRowNum = page*15;
		//���ۿ� �����ϱ� ���� �ؽ������� ����
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("startRowNum", startRowNum);
		data.put("endRowNum", endRowNum);
		
		data.put("field", field);
		data.put("keyword", keyword);
		
		return sql.selectList(namespace + ".listPagingSearch", data);
	}
	
	//�Խù� ���� ī��Ʈ
	@Override
	public int getCount(String field, String keyword) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("field", field);
		data.put("keyword", keyword);
		
		
		return sql.selectOne(namespace + ".count", data);
	}
	
	//�Խù� �󼼺���
	@Override
	public PostVO getDetail(int id) {
		
		return sql.selectOne(namespace+".detail", id);
	}

	
	//�Խù� ���
	@Override
	public void regPost(PostVO vo) throws Exception {
		
		System.out.println(sql.insert(namespace + ".regPost", vo));
		
	}
	
	//�Խù� ����
	@Override
	public int delPost(int id, String password) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("id", id);
		data.put("password", password);
			
		return sql.delete(namespace+".delPost", data);
	}
	
	//��� ���
	@Override
	public List<ReplyVO> getListReply(int post_id) throws Exception {
		
		return sql.selectList(namespace+".listReply", post_id);
	}

	//��� ���
	@Override
	public void regReply(ReplyVO vo) throws Exception {
		
		sql.insert(namespace + ".regReply", vo);
	}





}
