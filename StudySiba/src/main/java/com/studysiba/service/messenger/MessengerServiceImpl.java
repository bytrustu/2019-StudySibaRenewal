package com.studysiba.service.messenger;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studysiba.dao.member.MemberDAO;
import com.studysiba.dao.messenger.FriendVO;
import com.studysiba.dao.messenger.MessengerDAO;
import com.studysiba.domain.member.MemberVO;
import com.studysiba.domain.messenger.MessageVO;
import com.studysiba.domain.messenger.UserListVO;

@Service
public class MessengerServiceImpl implements MessengerService {

	@Autowired
	private MessengerDAO messengerDAO;
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public String checkNick(String id, String nick, String type) {
		String result = null;
		MemberVO memberVO = new MemberVO();
		memberVO.setId(id);
		memberVO = memberDAO.getUserInfomation(memberVO);
		System.out.println("sessionNick : " + memberVO.getNick() + " , nick : " + nick);
		if (nick == null || nick.equals("")) {
			result = "error";
		} else {
			if (memberVO.getNick().equals(nick)) {
				result = "equal";
			} else {
				// 닉네임을 통한 아이디 값 반환
				result = memberDAO.getUserId(nick);
				if (type != null && type.equals("getId") && result != null) {
					return result;
				} else if (result == null && type.equals("getBoolean")) {
					result = "false";
				} else if (result != null && type.equals("getBoolean")) {
					result = "true";
				}
			}
		}
		return result;
	}

	@Override
	public String getUserImage(String nick) {
		String id = memberDAO.getUserId(nick);
		String result = memberDAO.getUserImage(id);
		return result;
	}

	@Override
	public String sendMessage(MessageVO messageVO) {
		int result = 0;
		long roomId = 0;
		String strRoomId = null;
		String returnVal = null;
		strRoomId = messengerDAO.checkRoomId(messageVO);
		if (strRoomId == null) {
			// 신규 대화 대상일 경우 roomId 발급
			roomId = messengerDAO.getRoomId();
			messageVO.setRoomId(roomId);
		} else {
			messageVO.setRoomId(Integer.parseInt(strRoomId));
		}
		result = messengerDAO.sendMessage(messageVO);
		if (result == 0) {
			returnVal = "false";
		} else {
			returnVal = "true";
		}
		return returnVal;
	}

	@Override
	public String getMessage(String id, String nick) {
		String toId = checkNick(id, nick, "getId");
		MessageVO messageVO = new MessageVO();
		messageVO.setId(id);
		messageVO.setToId(toId);
		List<MessageVO> list = new ArrayList<MessageVO>();
		list = messengerDAO.getMessage(messageVO);
		String myNick = memberDAO.getUserNick(id);
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			JSONObject value = new JSONObject();
			value.put("no", list.get(i).getNo());
			value.put("roomId", list.get(i).getRoomId());
			value.put("type", list.get(i).getType());
			value.put("myNick", myNick);
			if (list.get(i).getId().equals(id)) {
				value.put("nick", myNick);
			} else {
				value.put("nick", nick);
			}
			if (list.get(i).getToId().equals(id)) {
				value.put("toNick", myNick);
			} else {
				value.put("toNick", nick);
			}
			value.put("myProfile", list.get(i).getMyProfile());
			value.put("toProfile", list.get(i).getToProfile());
			value.put("content", list.get(i).getContent());
			value.put("mRead", list.get(i).getmRead());
			value.put("mDate", list.get(i).getmDate());
			array.add(value);
		}
		result.put("result", array);
		return result.toString();
	}

	@Override
	public String getMessengerUserList(String id) {
		List<UserListVO> list = new ArrayList<UserListVO>();
		List<MemberVO> connect = memberDAO.getConnectList();
		list = messengerDAO.getMessengerUserList(id);
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			JSONObject value = new JSONObject();
			if (list.get(i).getPreId().equals(id)) {
				value.put("id", list.get(i).getLastId());
				value.put("nick", list.get(i).getLastNick());
				value.put("proFile", list.get(i).getLastProfile());
				if (checkConnect(connect, list.get(i).getLastId())) {
					value.put("connect", "on");
				} else {
					value.put("connect", "off");
				}
			} else {
				value.put("id", list.get(i).getPreId());
				value.put("nick", list.get(i).getPreNick());
				value.put("proFile", list.get(i).getPreProfile());
				if (checkConnect(connect, list.get(i).getPreId())) {
					value.put("connect", "on");
				} else {
					value.put("connect", "off");
				}
			}
			value.put("unRead", list.get(i).getUnRead());
			array.add(value);
		}
		result.put("result", array);
		return result.toString();
	}

	public boolean checkConnect(List<MemberVO> connect, String id) {
		boolean result = false;
		if (connect != null) {
			for (int i = 0; i < connect.size(); i++) {
				if (connect.get(i).getId().equals(id)) {
					result = true;
				}
			}
		}
		return result;
	}

	@Override
	public String deleteMessage(String id, String nick) {
		String toId = checkNick(id, nick, "getId");
		MessageVO messageVO = new MessageVO();
		messageVO.setId(id);
		messageVO.setToId(toId);
		String result = messengerDAO.deleteMessage(messageVO);
		return result;
	}

	@Override
	public String checkFriendStatus(String id, String nick) {
		FriendVO friendVO = addFriendVO(id, nick);
		return messengerDAO.checkFriendStatus(friendVO);
	}

	@Override
	public String applyFriend(String id, String nick) {
		FriendVO friendVO = addFriendVO(id, nick);
		return messengerDAO.applyFriend(friendVO);
	}

	public FriendVO addFriendVO(String id, String nick) {
		String fId = checkNick(id, nick, "getId");
		FriendVO friendVO = new FriendVO();
		friendVO.setId(id);
		friendVO.setfId(fId);
		return friendVO;
	}

	@Override
	public String refuseFriend(int no, String id, String nick) {
		String refuseResult = null;
		int deleteResult = messengerDAO.deleteMessageByNum(no);
		if (deleteResult == 1) {
			FriendVO friendVO = addFriendVO(id, nick);
			refuseResult = messengerDAO.refuseFriend(friendVO);
		}
		return refuseResult;
	}

	@Override
	public String acceptFriend(int no, String id, String nick) {
		String acceptResult = null;
		int deleteResult = messengerDAO.deleteMessageByNum(no);
		if (deleteResult == 1) {
			FriendVO friendVO = addFriendVO(id, nick);
			acceptResult = messengerDAO.acceptFriend(friendVO);
		}
		return acceptResult;
	}
}