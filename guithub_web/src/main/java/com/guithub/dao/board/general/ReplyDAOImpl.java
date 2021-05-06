package com.guithub.dao.board.general;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guithub.domain.board.general.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	@Autowired
	private SqlSession sql;
	
	private static String namespace = "com.guithub.mappers.board.general";
	
	//´ñ±Û ¸ñ·Ï
	@Override
	public List<ReplyVO> getListReply(int post_id) throws Exception {
		
		return sql.selectList(namespace+".getListReply", post_id);
	}

	//´ñ±Û µî·Ï
	@Override
	public void regReply(ReplyVO vo) throws Exception {
		
		sql.insert(namespace + ".regReply", vo);
	}

	//´ñ±Û »èÁ¦
	@Override
	public int delReply(int id, String password) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("id", id);
		data.put("password", password);
		
		return sql.delete(namespace+".delReply", data);
		
	}

}
