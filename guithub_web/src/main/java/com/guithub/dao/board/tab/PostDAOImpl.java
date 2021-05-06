package com.guithub.dao.board.tab;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guithub.domain.board.tab.FileVO;
import com.guithub.domain.board.tab.PostVO;
import com.guithub.domain.board.tab.PostVO_view;

@Repository
public class PostDAOImpl implements PostDAO {
	@Autowired
	private SqlSession sql;
	private static String namespace = "com.guithub.mappers.board.tab";
	
	//�Խù� �� ����
	@Override
	public int getPostCnt(String field, String keyword) throws Exception{
		
		HashMap<String, Object> data = new HashMap<String, Object >();
		
		data.put("field", field);
		data.put("keyword", keyword);
		
		return sql.selectOne(namespace+".getPostCnt", data);
	}
	
	//�Խù� ���(����¡ + ��ġ)
	@Override
	public List<PostVO_view> getList(int page, String field, String keyword) throws Exception{
		
		//�� �������� �Խù� 15���� ��Ÿ���� ���� ī��Ʈ
		int startRowNum = 1+(page-1)*15;
		int endRowNum = page*15;
		
		HashMap<String, Object> data = new HashMap<String, Object >();
		
		data.put("startRowNum", startRowNum);
		data.put("endRowNum", endRowNum);
		
		data.put("field", field);
		data.put("keyword", keyword);
		
		return sql.selectList(namespace + ".getList_view", data);
	}

	//�Խù� �󼼺���
	@Override
	public PostVO getDetail(int id) throws Exception {

		return sql.selectOne(namespace+".getDetail", id);
	}

	//�Խù� ��ȸ�� ����
	@Override
	public void updateHit(int id) throws Exception {
		
		sql.update(namespace+".updateHit", id);
	}

	//�Խù� ÷������ ���
	@Override
	public List<FileVO> getFileList(int id) throws Exception {
		
		return sql.selectList(namespace+".getFileList", id);
	}

	//�Խù� ���
	@Override
	public void regPost(PostVO post) {
		sql.insert(namespace+".regPost", post);
	}

	//�Խù� ÷������ ���
	@Override
	public void regFile(FileVO filedata) {
		sql.insert(namespace+".regFile", filedata);
	}

	//�Խù� ����
	@Override
	public void delPost(int id) throws Exception {
		sql.delete(namespace+".delPost", id);
	}

	//�Խù� ����
	@Override
	public void editPost(PostVO post) throws Exception {
		sql.update(namespace+".editPost", post);
	}
	
	//�Խù� ÷������ ����
	@Override
	public void delFile(int delFile_id) throws Exception {
		sql.delete(namespace+".delFile", delFile_id);
	}
}
