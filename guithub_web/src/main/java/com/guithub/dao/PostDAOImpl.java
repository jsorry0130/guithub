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
	
	//�Խù� ���
	@Override
	public List<PostVO> getList() throws Exception {

		return sql.selectList(namespace + ".list");
	}

	//�Խù� ��� (����¡)
	@Override
	public List<PostVO> getList(int page) throws Exception {
		
		//�� �������� ���� 15���� ��Ÿ���� ���� ī��Ʈ
		int startRowNum = 1+(page-1)*15;
		int endRowNum = page*15;
		//���ۿ� �����ϱ� ���� �ؽ������� ����
		HashMap<String, Integer> data = new HashMap<String, Integer>();
		
		data.put("startRowNum", startRowNum);
		data.put("endRowNum", endRowNum);
		
		return sql.selectList(namespace + ".listPaging", data);
	}
	
	//�Խù� ���� ī��Ʈ
	@Override
	public int getCount() throws Exception {
		
		return sql.selectOne(namespace + ".count");
	}
	
	//�Խù� �󼼺���
	@Override
	public PostVO getDetail(int id) {
		
		return sql.selectOne(namespace+".detail", id);
	}

	
	//�Խù� ���
	@Override
	public void regPost(PostVO vo) throws Exception {
		
		sql.insert(namespace + ".reg", vo);
		
	}

}
