package com.guithub.dao.board.general;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guithub.domain.board.general.FileVO;
import com.guithub.domain.board.general.PostVO;
import com.guithub.domain.board.general.PostVO_view;
import com.guithub.domain.board.general.ReplyVO;

@Repository
public class PostDAOImpl implements PostDAO {

	@Autowired
	private SqlSession sql;
	
	private static String namespace = "com.guithub.mappers.board.general";
	
	
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
		
		return sql.selectList(namespace + ".getList", data);
	}
	
	//�Խù� ���(����¡+��ġ+��� ī��Ʈ)
	@Override
	public List<PostVO_view> getList_view(int page, String field, String keyword) throws Exception {
		//�� �������� ���� 15���� ��Ÿ���� ���� ī��Ʈ
		int startRowNum = 1+(page-1)*15;
		int endRowNum = page*15;
		//���ۿ� �����ϱ� ���� �ؽ������� ����
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("startRowNum", startRowNum);
		data.put("endRowNum", endRowNum);
		
		data.put("field", field);
		data.put("keyword", keyword);
		
		return sql.selectList(namespace+".getList_view", data);
	}
	
	//�Խù� ���� ī��Ʈ
	@Override
	public int getCount(String field, String keyword) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("field", field);
		data.put("keyword", keyword);
		
		
		return sql.selectOne(namespace + ".getCount", data);
	}
	
	//�Խù� �󼼺���
	@Override
	public PostVO getDetail(int id) {
		
		return sql.selectOne(namespace+".getDetail", id);
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
	
	//�Խù� ��ȸ�� ����
	@Override
	public void updateHit(int id) throws Exception {
		
		sql.update(namespace+".updateHit", id);
	}

	//�Խù� �н����� ����
	@Override
	public boolean checkPwd(int id, String password) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("password", password);
		boolean bool = sql.selectOne(namespace+".checkPwd", data);
		System.out.println("�Ҹ��� Ȯ��" + bool);
		
		return bool;
	}

	//�Խù� ����
	@Override
	public void editPost(PostVO vo) throws Exception {
		
		sql.update(namespace+".editPost", vo);
	}

	//���� ���� ����
	@Override
	public void regFile(FileVO vo) throws Exception {
		
		sql.insert(namespace+".regFile", vo);
	}

	//���� ���� ����
	@Override
	public List<FileVO> getFile(int id) {

		return sql.selectList(namespace+".getFile", id);
	}

	//���� ����
	@Override
	public void delFile(int id) {
		
		sql.delete(namespace+".delFile", id);
	}





}
