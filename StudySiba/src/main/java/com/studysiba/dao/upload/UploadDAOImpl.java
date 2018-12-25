package com.studysiba.dao.upload;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studysiba.domain.upload.UploadVO;

@Repository
public class UploadDAOImpl implements UploadDAO {

	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.studysiba.mapper.UploadMapper";
	
	@Override
	public void uploadFile(UploadVO uploadVO) {
		sqlSession.insert(namespace+".uploadFile",uploadVO);
	}
	
	
	
}
