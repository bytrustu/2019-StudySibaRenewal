package com.studysiba.dao.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studysiba.domain.board.LikeVO;
import com.studysiba.domain.common.TotalVO;
import com.studysiba.domain.study.StudyVO;

@Repository
public class CommonDAOImpl implements CommonDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace="com.studysiba.mapper.CommonMapper";

	@Override
	public List<LikeVO> getLikeList() {
		return sqlSession.selectList(namespace+".getLikeList");
	}

	@Override
	public List<StudyVO> getStudyList() {
		return sqlSession.selectList(namespace+".getStudyList");
	}

	@Override
	public List<TotalVO> getTotalList() {
		return sqlSession.selectList(namespace+".getTotalList");
	}

	
}
