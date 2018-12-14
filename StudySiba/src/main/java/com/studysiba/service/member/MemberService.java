package com.studysiba.service.member;

import java.util.HashMap;

import com.studysiba.domain.member.MemberVO;

public interface MemberService {

	int insertTest(HashMap<String, Object> map);
	// 소셜 회원가입
	int socialJoin(MemberVO memberVO);
	
	// 회원가입 값 체크 : 아이디
	String valueCheckId(String value);
	// 회원가입 값 체크 : 닉네임
	String valueCheckNick(String value);
	// 회원가입 값 체크 : 이메일
	String valueCheckEmail(String value);
	// 회원가입 값 체크 : 비밀번호
	String valueCheckPass(String id);

}
