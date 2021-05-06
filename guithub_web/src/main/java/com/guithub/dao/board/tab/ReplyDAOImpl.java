package com.guithub.dao.board.tab;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guithub.domain.board.tab.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	@Autowired
	private SqlSession sql;
	private static String namespace = "com.guithub.mappers.board.tab";
	
	//´ñ±Û ¸ñ·Ï
	@Override
	public List<ReplyVO> getReplyList(int post_id) throws Exception {
		
		return sql.selectList(namespace+".getReplyList", post_id);
	}
	
	//´ñ±Û µî·Ï
	@Override
	public void regReply(ReplyVO reply) throws Exception {
		sql.insert(namespace+".regReply", reply);
	}

	//´ñ±Û »èÁ¦
	@Override
	public void delReply(int rid) throws Exception {
		sql.delete(namespace+".delReply", rid);
	}

}
