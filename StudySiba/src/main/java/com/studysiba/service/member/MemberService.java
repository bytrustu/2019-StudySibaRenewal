package com.studysiba.service.member;

import java.util.HashMap;

import com.studysiba.domain.member.MemberVO;

public interface MemberService {

	int insertTest(HashMap<String, Object> map);
	// 회원정보 조회
	MemberVO getUserInfomation(MemberVO memberVO);
	// 소셜 회원가입
	int socialJoin(MemberVO memberVO);
	// 회원가입 값 체크 : 아이디
	String valueCheckId(String value);
	// 회원가입 값 체크 : 닉네임
	String valueCheckNick(String value);
	// 회원가입 값 체크 : 이메일
	String valueCheckEmail(String value);
	// 회원가입 값 체크 : 비밀번호
	boolean valueCheckPass(String id, String pass);
	// 입력값 체크
	String checkResult(String type, String value, int index);
	// 리턴 값 변환
	String changeResult(String value, String checkVal);
	// 이메일 입력값
	boolean checkEmailResult(String value);
	// validation check 리턴 값
	String returnResult(String type,String value);
	// 로그인시 접속로그 기록
	void insertVisitLog(MemberVO memberVO);
	// 로그인 세션 정보 처리
	HashMap<String, String> setUserSession(MemberVO memberVO);
	// 회원정보수정 값 체크 : 닉네임
	String checkNick(String nick);
	// 회원정보수정
	void updateUserInfo(MemberVO memberVO);
	
	
}
