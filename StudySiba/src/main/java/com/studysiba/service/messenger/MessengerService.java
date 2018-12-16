package com.studysiba.service.messenger;

import com.studysiba.domain.messenger.MessageVO;

public interface MessengerService {

	// 닉네임 입력값 확인
	String checkNick(String id, String nick, String type);
	// 유저 프로필 사진 조회
	String getUserImage(String nick);
	// 메세지 전송
	String sendMessage(MessageVO messageVO);
	// 메세지 뷰
	String getMessage(String id, String nick);

}
