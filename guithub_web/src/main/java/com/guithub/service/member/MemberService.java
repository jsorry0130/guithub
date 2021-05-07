package com.guithub.service.member;

import java.util.List;

import com.guithub.domain.board.tab.PostVO_view;
import com.guithub.domain.member.MemberVO;

public interface MemberService {

	//로그인
	MemberVO login(MemberVO vo);
	//현재 회원의 게시판 별 작성 게시물 총 개수
	int getBoardPostCnt(String nickname, String board, String field, String keyword);
	//현재 회원의 게시판 별 작성 게시물 목록 
	List<PostVO_view> getBoardList(String nickname, String board, int page, String field, String keyword);
	//현재 회원의 게시판 별 작성 댓글 총 개수
	int getBoardReplyCnt(String nickname, String board, String keyword);
	//현재 회원의 게시판 별 작성 댓글 목록
	List<PostVO_view> getBoardReplyList(String nickname, String board, int page, String keyword);
	//회원가입 아이디 중복 확인
	boolean checkIdDup(String id);
	//회원가입 닉네임 중복 확인
	boolean checkNickDup(String nickname);
	//회원 등록
	void regMember(MemberVO vo);

}
