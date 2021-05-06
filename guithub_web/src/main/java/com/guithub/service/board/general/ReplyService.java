package com.guithub.service.board.general;

import java.util.List;

import com.guithub.domain.board.general.ReplyVO;

public interface ReplyService {
	//´ñ±Û ¸ñ·Ï
	public List<ReplyVO> getListReply(int post_id) throws Exception;
	//´ñ±Û µî·Ï
	public void regReply(ReplyVO vo) throws Exception;
	//´ñ±Û »èÁ¦
	public int delReply(int id, String password) throws Exception;
}
