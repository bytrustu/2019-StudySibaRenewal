package com.studysiba.service.messenger;

public interface MessengerService {

	// 닉네임 입력값 확인
	String checkNick(String id, String nick);
	// 유저 프로필 사진 조회
	String getUserImage(String nick);

}
