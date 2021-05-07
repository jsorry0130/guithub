package com.guithub.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guithub.dao.member.MemberDAO;
import com.guithub.domain.board.tab.PostVO_view;
import com.guithub.domain.member.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO dao;
	
	//�α���
	@Override
	public MemberVO login(MemberVO vo) {
		
		return dao.login(vo);
	}

	//���� ȸ���� �Խ��� �� �ۼ� �Խù� �� ����
	@Override
	public int getBoardPostCnt(String nickname, String board, String field, String keyword) {
		
		return dao.getBoardPostCnt(nickname, board, field, keyword);
	}
	
	//���� ȸ���� �Խ��� �� �ۼ� �Խù� ���
	@Override
	public List<PostVO_view> getBoardList(String nickname, String board, int page, String field, String keyword) {
		
		return dao.getBoardList(nickname, board, page, field, keyword);
	}

	//���� ȸ���� �Խ��� �� �ۼ� ��� �� ����
	@Override
	public int getBoardReplyCnt(String nickname, String board, String keyword) {

		return dao.getBoardReplyCnt(nickname, board, keyword);
	}
	
	//���� ȸ���� �Խ��� �� �ۼ� ��� ���
	@Override
	public List<PostVO_view> getBoardReplyList(String nickname, String board, int page, String keyword) {

		return dao.getBoardReplyList(nickname, board, page , keyword);
	}

	//ȸ������ ���̵� �ߺ� Ȯ��
	@Override
	public boolean checkIdDup(String id) {

		return dao.checkIdDup(id);
	}

	//ȸ������ �г��� �ߺ� Ȯ��
	@Override
	public boolean checkNickDup(String nickname) {
		return dao.checkNickDup(nickname);
	}

	//ȸ�� ���
	@Override
	public void regMember(MemberVO vo) {
		dao.regMember(vo);
	}

}
