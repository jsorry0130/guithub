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
	
	//�α���
	@Override
	public MemberVO login(MemberVO vo) {

		return sql.selectOne(namespace+".login",vo);
	}
	//���� ȸ���� �Խ��� �� �ۼ� �Խù� �� ����
	@Override
	public int getBoardPostCnt(String nickname, String board, String field, String keyword) {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("nickname", nickname);
		data.put("board", board);
		data.put("field", field);
		data.put("keyword", keyword);
	
		return sql.selectOne(namespace+".getBoardPostCnt", data);
	}
	//���� ȸ���� �Խ��� �� �ۼ� �Խù� ���
	@Override
	public List<PostVO_view> getBoardList(String nickname, String board, int page, String field, String keyword) {
		
		//�� �������� �Խù� 15���� ��Ÿ���� ���� ī��Ʈ
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
			System.out.println("sql �� ����!");
			return null;
		}
	}
	
	//���� ȸ���� �Խ��� �� �ۼ� ��� �� ����
	@Override
	public int getBoardReplyCnt(String nickname, String board, String keyword) {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("nickname", nickname);
		data.put("board", board);
		data.put("keyword", keyword);
		
		return sql.selectOne(namespace+".getBoardReplyCnt", data );
	}
	
	//���� ȸ���� �Խ��� �� �ۼ� ��� ���
	@Override
	public List<PostVO_view> getBoardReplyList(String nickname, String board, int page, String keyword) {
		
		//�� �������� �Խù� 15���� ��Ÿ���� ���� ī��Ʈ
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
	
	//ȸ������ ���̵� �ߺ� Ȯ��
	@Override
	public boolean checkIdDup(String id) {

		return sql.selectOne(namespace+".checkIdDup", id);
	}
	
	//ȸ������ �г��� �ߺ� Ȯ��
	@Override
	public boolean checkNickDup(String nickname) {
		return sql.selectOne(namespace+".checkNickDup", nickname);
	}
	
	//ȸ�����
	@Override
	public void regMember(MemberVO vo) {
		sql.insert(namespace+".regMember", vo);
	}

}
