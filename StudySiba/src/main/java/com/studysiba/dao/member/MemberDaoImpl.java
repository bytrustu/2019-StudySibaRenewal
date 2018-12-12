package com.studysiba.dao.member;

import java.util.HashMap;

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
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);

	@Override
	public int insertTest(HashMap<String, Object> map) {
		return sqlSession.insert("insertTest",map);
	}

	@Override
	public int socialJoin(MemberVO memberVO) {
		return sqlSession.insert("socialJoin", memberVO);
	}
	
	
	
}
