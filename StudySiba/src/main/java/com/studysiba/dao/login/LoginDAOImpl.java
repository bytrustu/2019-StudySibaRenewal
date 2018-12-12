package com.studysiba.dao.login;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studysiba.domain.member.MemberVO;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public String test() {
		return null;
	}

	@Override
	public MemberVO checkJoinSocial(String sId) {
		return sqlSession.selectOne("checkJoinSocial",sId);
	}
	
	
	
}
