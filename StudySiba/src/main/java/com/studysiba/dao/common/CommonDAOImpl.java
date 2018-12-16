package com.studysiba.dao.common;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studysiba.domain.member.MemberVO;

@Repository
public class CommonDAOImpl implements CommonDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace="com.studysiba.mapper.CommonMapper";

}
