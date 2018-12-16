package com.studysiba.dao.login;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studysiba.domain.member.MemberVO;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace="com.studysiba.mapper.LoginMapper";

	@Override
	public String test() {
		return null;
	}

	@Override
	public MemberVO checkJoinSocial(String sId) {
		return sqlSession.selectOne(namespace + ".checkJoinSocial",sId);
	}
	
}
