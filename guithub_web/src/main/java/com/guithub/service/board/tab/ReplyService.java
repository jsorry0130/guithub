package com.guithub.service.board.tab;

import java.util.List;

import com.guithub.domain.board.tab.ReplyVO;

public interface ReplyService {

	//��� ���
	List<ReplyVO> getReplyList(int post_id) throws Exception;
	//��� ���
	void regReply(ReplyVO reply) throws Exception;
	//��� ����
	void delReply(int rid) throws Exception;

}
