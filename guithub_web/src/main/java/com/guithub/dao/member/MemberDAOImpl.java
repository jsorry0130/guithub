package com.guithub.dao.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guithub.domain.member.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sql;
	private static String namespace = "com.guithub.mappers.main.member";
	
	//·Î±×ÀÎ
	@Override
	public MemberVO login(MemberVO vo) {

		return sql.selectOne(namespace+".login",vo);
	}

}
