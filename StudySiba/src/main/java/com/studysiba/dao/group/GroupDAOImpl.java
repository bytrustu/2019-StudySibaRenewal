package com.studysiba.dao.group;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.group.GroupMessageVO;
import com.studysiba.domain.group.GroupVO;
import com.studysiba.domain.upload.UploadVO;

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

	@Override
	public GroupVO view(GroupVO groupVO) {
		return sqlSession.selectOne(namespace+".view",groupVO);
	}

	@Override
	public List<GroupMessageVO> getGroupMessageList(long gNo) {
		return sqlSession.selectList(namespace+".getGroupMessageList",gNo);
	}

	@Override
	public int sendGroupMessage(GroupMessageVO groupMessageVO) {
		return sqlSession.insert(namespace+".sendGroupMessage",groupMessageVO);
	}

	@Override
	public List<GroupMessageVO> viewGroupMessage(long gNo) {
		return sqlSession.selectList(namespace+".viewGroupMessage",gNo);
	}

	@Override
	public int secession(GroupVO groupVO) {
		return sqlSession.delete(namespace+".secession",groupVO);
	}

	@Override
	public int delete(GroupVO groupVO) {
		return sqlSession.delete(namespace+".delete",groupVO);
	}

	
}
