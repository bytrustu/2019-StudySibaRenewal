package com.studysiba.dao.group;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.group.GroupVO;

@Repository
public class GroupDAOImpl implements GroupDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private final static String namespace = "com.studysiba.mapper.GroupMapper";

	@Override
	public int getGroupCount(GroupVO groupVO) {
		return sqlSession.selectOne(namespace+".getGroupCount", groupVO);
	}

	@Override
	public List<GroupVO> getGroupList(PageDTO page) {
		return sqlSession.selectList(namespace+".getGroupList", page);
	}
	
	
}
