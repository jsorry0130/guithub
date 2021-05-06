package com.guithub.service.board.general;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guithub.dao.board.general.ReplyDAO;
import com.guithub.domain.board.general.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDao;
	
	//´ñ±Û ¸ñ·Ï
	@Override
	public List<ReplyVO> getListReply(int post_id) throws Exception {
		
		return replyDao.getListReply(post_id);
	}

	//´ñ±Û µî·Ï
	@Override
	public void regReply(ReplyVO vo) throws Exception {
		
		replyDao.regReply(vo);	
	}

	//´ñ±Û »èÁ¦
	@Override
	public int delReply(int id, String password) throws Exception {
		
		return replyDao.delReply(id, password);
	}

}
