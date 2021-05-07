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
	
	//로그인
	@Override
	public MemberVO login(MemberVO vo) {
		
		return dao.login(vo);
	}

	//현재 회원의 게시판 별 작성 게시물 총 개수
	@Override
	public int getBoardPostCnt(String nickname, String board, String field, String keyword) {
		
		return dao.getBoardPostCnt(nickname, board, field, keyword);
	}
	
	//현재 회원의 게시판 별 작성 게시물 목록
	@Override
	public List<PostVO_view> getBoardList(String nickname, String board, int page, String field, String keyword) {
		
		return dao.getBoardList(nickname, board, page, field, keyword);
	}

	//현재 회원의 게시판 별 작성 댓글 총 개수
	@Override
	public int getBoardReplyCnt(String nickname, String board, String keyword) {

		return dao.getBoardReplyCnt(nickname, board, keyword);
	}
	
	//현재 회원의 게시판 별 작성 댓글 목록
	@Override
	public List<PostVO_view> getBoardReplyList(String nickname, String board, int page, String keyword) {

		return dao.getBoardReplyList(nickname, board, page , keyword);
	}

	//회원가입 아이디 중복 확인
	@Override
	public boolean checkIdDup(String id) {

		return dao.checkIdDup(id);
	}

	//회원가입 닉네임 중복 확인
	@Override
	public boolean checkNickDup(String nickname) {
		return dao.checkNickDup(nickname);
	}

	//회원 등록
	@Override
	public void regMember(MemberVO vo) {
		dao.regMember(vo);
	}

}
