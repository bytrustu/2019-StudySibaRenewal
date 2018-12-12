package com.studysiba.service.member;

import java.util.HashMap;

import com.studysiba.domain.member.MemberVO;

public interface MemberService {

	int insertTest(HashMap<String, Object> map);
	// 소셜 회원가입
	int socialJoin(MemberVO memberVO);

}
