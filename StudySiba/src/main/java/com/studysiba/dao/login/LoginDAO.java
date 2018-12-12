package com.studysiba.dao.login;

import java.util.List;

import com.studysiba.domain.member.MemberVO;

public interface LoginDAO {

	String test();

	// 소셜 로그인 가입 여부 확인
	MemberVO checkJoinSocial(String sId);

}
