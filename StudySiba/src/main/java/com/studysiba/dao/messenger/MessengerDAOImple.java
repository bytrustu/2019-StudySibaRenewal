package com.studysiba.dao.messenger;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studysiba.domain.messenger.MessageVO;
import com.studysiba.domain.messenger.UserListVO;

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
	public List<UserListVO> getMessengerUserList(String id) {
		return sqlSession.selectList(namespace + ".getMessengerUserList", id);
	}

	@Override
	public String deleteMessage(MessageVO messageVO) {
		return Integer.toString(sqlSession.delete(namespace + ".deleteMessage", messageVO));
	}

	@Override
	public String checkFriendStatus(FriendVO friendVO) {
		return Integer.toString(sqlSession.selectOne(namespace + ".checkFriendStatus", friendVO));
	}

	@Override
	public String applyFriend(FriendVO friendVO) {
		return Integer.toString(sqlSession.insert(namespace + ".applyFriend", friendVO));
	}

	@Override
	public int deleteMessageByNum(int no) {
		return sqlSession.delete(namespace + ".deleteMessageByNum", no);
	}

	@Override
	public String refuseFriend(FriendVO friendVO) {
		return Integer.toString(sqlSession.delete(namespace + ".refuseFriend", friendVO));
	}

	@Override
	public String acceptFriend(FriendVO friendVO) {
		return Integer.toString(sqlSession.update(namespace + ".acceptFriend", friendVO));
	}

}
