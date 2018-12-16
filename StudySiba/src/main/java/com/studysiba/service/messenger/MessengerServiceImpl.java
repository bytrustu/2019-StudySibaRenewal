package com.studysiba.service.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studysiba.dao.member.MemberDAO;
import com.studysiba.dao.messenger.MessengerDAO;
import com.studysiba.domain.member.MemberVO;

@Service
public class MessengerServiceImpl implements MessengerService {

	@Autowired
	private MessengerDAO messengerDAO;
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public String checkNick(String id, String nick) {
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
				result = memberDAO.valueCheckNick(nick);
				if (result == null) {
					result = "false";
				} else {
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
	
	

}
