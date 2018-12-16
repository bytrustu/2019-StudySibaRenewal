package com.studysiba.dao.messenger;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessengerDAOImple implements MessengerDAO {

	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.studysiba.mapper.MessengerMapper";
	
	
}
