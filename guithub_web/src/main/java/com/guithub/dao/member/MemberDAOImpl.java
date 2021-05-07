package com.guithub.dao.member;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guithub.domain.board.tab.PostVO_view;
import com.guithub.domain.member.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sql;
	private static String namespace = "com.guithub.mappers.main.member";
	
	//로그인
	@Override
	public MemberVO login(MemberVO vo) {

		return sql.selectOne(namespace+".login",vo);
	}
	//현재 회원의 게시판 별 작성 게시물 총 개수
	@Override
	public int getBoardPostCnt(String nickname, String board, String field, String keyword) {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("nickname", nickname);
		data.put("board", board);
		data.put("field", field);
		data.put("keyword", keyword);
	
		return sql.selectOne(namespace+".getBoardPostCnt", data);
	}
	//현재 회원의 게시판 별 작성 게시물 목록
	@Override
	public List<PostVO_view> getBoardList(String nickname, String board, int page, String field, String keyword) {
		
		//한 페이지당 게시물 15개씩 나타내기 위한 카운트
		int startRowNum = 1+(page-1)*15;
		int endRowNum = page*15;
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("nickname", nickname);
		data.put("page", page);
		data.put("field", field);
		data.put("keyword", keyword);
		data.put("startRowNum", startRowNum);
		data.put("endRowNum", endRowNum);
		
		if(board.equals("tab"))
			return sql.selectList(namespace+".getTabList", data);
		else {
			System.out.println("sql 값 없음!");
			return null;
		}
	}
	
	//현재 회원의 게시판 별 작성 댓글 총 개수
	@Override
	public int getBoardReplyCnt(String nickname, String board, String keyword) {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("nickname", nickname);
		data.put("board", board);
		data.put("keyword", keyword);
		
		return sql.selectOne(namespace+".getBoardReplyCnt", data );
	}
	
	//현재 회원의 게시판 별 작성 댓글 목록
	@Override
	public List<PostVO_view> getBoardReplyList(String nickname, String board, int page, String keyword) {
		
		//한 페이지당 게시물 15개씩 나타내기 위한 카운트
		int startRowNum = 1+(page-1)*15;
		int endRowNum = page*15;
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("nickname", nickname);
		data.put("page", page);
		data.put("keyword", keyword);
		data.put("startRowNum", startRowNum);
		data.put("endRowNum", endRowNum);
		
		if(board.equals("tab"))
			return sql.selectList(namespace+".getTabReplyList", data);
		else
			return null;
	}
	
	//회원가입 아이디 중복 확인
	@Override
	public boolean checkIdDup(String id) {

		return sql.selectOne(namespace+".checkIdDup", id);
	}
	
	//회원가입 닉네임 중복 확인
	@Override
	public boolean checkNickDup(String nickname) {
		return sql.selectOne(namespace+".checkNickDup", nickname);
	}
	
	//회원등록
	@Override
	public void regMember(MemberVO vo) {
		sql.insert(namespace+".regMember", vo);
	}

}
