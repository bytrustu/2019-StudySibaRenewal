package com.studysiba.dao.messenger;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studysiba.domain.messenger.MessageVO;

@Repository
public class MessengerDAOImple implements MessengerDAO {

	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.studysiba.mapper.MessengerMapper";
	
	@Override
	public String checkRoomId(MessageVO messageVO) {
		return sqlSession.selectOne(namespace + ".checkRoomId", messageVO);
	}
	@Override
	public int newMessage(MessageVO messageVO) {
		return sqlSession.insert(namespace + ".newMessage", messageVO);
	}
	@Override
	public int sendMessage(MessageVO messageVO) {
		return sqlSession.insert(namespace + ".sendMessage", messageVO);
	}
	@Override
	public long getRoomId() {
		return sqlSession.selectOne(namespace + ".getRoomId");
	}
	
	@Override
	public List<MessageVO> getMessage(MessageVO messageVO) {
		return sqlSession.selectList(namespace + ".getMessage", messageVO);
	}
	@Override
	public List<MessageVO> getMessengerUserList(String id) {
		return sqlSession.selectList(namespace + ".getMessengerUserList" , id);
	}
	
	
}
