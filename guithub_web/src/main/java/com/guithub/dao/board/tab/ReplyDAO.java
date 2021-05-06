package com.guithub.dao.board.tab;

import java.util.List;

import com.guithub.domain.board.tab.ReplyVO;

public interface ReplyDAO {

	//´ñ±Û ¸ñ·Ï
	List<ReplyVO> getReplyList(int post_id) throws Exception;
	//´ñ±Û µî·Ï
	void regReply(ReplyVO reply) throws Exception;
	//´ñ±Û »èÁ¦
	void delReply(int rid) throws Exception;

}
