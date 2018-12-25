package com.studysiba.dao.study;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studysiba.domain.page.PageDTO;
import com.studysiba.domain.study.StudyVO;

@Repository
public class StudyDAOImpl implements StudyDAO {

	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.studysiba.mapper.StudyMapper";

	@Override
	public int write(StudyVO studyVO) {
		return sqlSession.insert(namespace + ".write", studyVO);
	}

	@Override
	public int getStudyNo(StudyVO studyVO) {
		return sqlSession.selectOne(namespace + ".getStudyNo", studyVO);
	}

	@Override
	public int makeGroup(StudyVO studyVO) {
		return sqlSession.insert(namespace + ".makeGroup", studyVO);
	}

	@Override
	public int getStudyCount() {
		return sqlSession.selectOne(namespace + ".getStudyCount");
	}

	@Override
	public List<StudyVO> getStudyList(PageDTO page) {
		return sqlSession.selectList(namespace + ".getStudyList", page);
	}

	@Override
	public int joinGroup(StudyVO studyVO) {
		return sqlSession.insert(namespace + ".joinGroup", studyVO);
	}


}
