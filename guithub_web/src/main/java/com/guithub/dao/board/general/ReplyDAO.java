package com.guithub.dao.board.general;

import java.util.List;

import com.guithub.domain.board.general.ReplyVO;

public interface ReplyDAO {
	//��� ���
	public List<ReplyVO> getListReply(int post_id) throws Exception;
	//��� ���
	public void regReply(ReplyVO vo) throws Exception;
	//��� ����
	public int delReply(int id, String password) throws Exception;
}
