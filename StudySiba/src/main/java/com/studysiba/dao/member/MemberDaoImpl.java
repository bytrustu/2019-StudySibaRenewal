package com.studysiba.dao.member;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studysiba.domain.member.MemberVO;
import com.studysiba.service.member.MemberServiceImpl;

@Repository
public class MemberDaoImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace="com.studysiba.mapper.MemberMapper";
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);

	@Override
	public int insertTest(HashMap<String, Object> map) {
		return sqlSession.insert(namespace+ ".insertTest",map);
	}
	
	@Override
	public MemberVO getUserInfomation(MemberVO memberVO) {
		return sqlSession.selectOne(namespace + ".getUserInfomation", memberVO);
	}
	
	@Override
	public int socialJoin(MemberVO memberVO) {
		return sqlSession.insert(namespace + ".socialJoin", memberVO);
	}

	@Override
	public String valueCheckId(String value) {
		return sqlSession.selectOne(namespace + ".valueCheckId", value);
	}

	@Override
	public String valueCheckNick(String value) {
		return sqlSession.selectOne(namespace + ".valueCheckNick", value);
	}

	@Override
	public String valueCheckEmail(String value) {
		return sqlSession.selectOne(namespace + ".valueCheckEmail", value);
	}

	@Override
	public String valueCheckPass(String id) {
		return sqlSession.selectOne(namespace + ".valueCheckPass", id);
	}

	@Override
	public void insertVisitLog(MemberVO memberVO) {
		sqlSession.insert(namespace + ".insertVisitLog", memberVO);
	}

	@Override
	public void updateConnectLog(MemberVO memberVO) {
		sqlSession.update(namespace + ".updateConnectLog", memberVO);
	}

	@Override
	public void updateUserInfo(MemberVO memberVO) {
		if ( memberVO.getType().equals("nick") ) {
			sqlSession.update(namespace + ".changeNick", memberVO);
		}
	}

	@Override
	public void updateProFile(MemberVO memberVO) {
		sqlSession.update(namespace + ".updateProFile", memberVO);
	}

	@Override
	public String getUserId(String nick) {
		return sqlSession.selectOne(namespace + ".getUserId", nick);
	}

	@Override
	public String getUserImage(String id) {
		return sqlSession.selectOne(namespace + ".getUserImage", id);
	}

}
