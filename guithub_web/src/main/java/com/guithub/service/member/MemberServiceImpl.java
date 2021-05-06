package com.guithub.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guithub.dao.member.MemberDAO;
import com.guithub.domain.member.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO dao;
	
	//·Î±×ÀÎ
	@Override
	public MemberVO login(MemberVO vo) {
		
		return dao.login(vo);
	}

}
