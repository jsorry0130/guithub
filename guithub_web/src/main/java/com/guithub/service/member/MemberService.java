package com.guithub.service.member;

import java.util.List;

import com.guithub.domain.board.tab.PostVO_view;
import com.guithub.domain.member.MemberVO;

public interface MemberService {

	//�α���
	MemberVO login(MemberVO vo);
	//���� ȸ���� �Խ��� �� �ۼ� �Խù� �� ����
	int getBoardPostCnt(String nickname, String board, String field, String keyword);
	//���� ȸ���� �Խ��� �� �ۼ� �Խù� ��� 
	List<PostVO_view> getBoardList(String nickname, String board, int page, String field, String keyword);
	//���� ȸ���� �Խ��� �� �ۼ� ��� �� ����
	int getBoardReplyCnt(String nickname, String board, String keyword);
	//���� ȸ���� �Խ��� �� �ۼ� ��� ���
	List<PostVO_view> getBoardReplyList(String nickname, String board, int page, String keyword);
	//ȸ������ ���̵� �ߺ� Ȯ��
	boolean checkIdDup(String id);
	//ȸ������ �г��� �ߺ� Ȯ��
	boolean checkNickDup(String nickname);
	//ȸ�� ���
	void regMember(MemberVO vo);

}
