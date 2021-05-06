package com.guithub.service.board.tab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guithub.dao.board.tab.ReplyDAO;
import com.guithub.domain.board.tab.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDao;
	
	//��� ���
	@Override
	public List<ReplyVO> getReplyList(int post_id) throws Exception {

		return replyDao.getReplyList(post_id);
	}

	//��� ���
	@Override
	public void regReply(ReplyVO reply) throws Exception {
		replyDao.regReply(reply);
	}

	//��� ����
	@Override
	public void delReply(int rid) throws Exception {
		replyDao.delReply(rid);
	}

}
