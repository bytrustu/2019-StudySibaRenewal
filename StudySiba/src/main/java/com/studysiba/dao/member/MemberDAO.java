package com.studysiba.dao.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.studysiba.domain.member.MemberVO;
import com.studysiba.domain.member.SubInfoVO;
import com.studysiba.domain.messenger.MessageVO;

public interface MemberDAO {

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
	String valueCheckPass(String id);
	// 로그인시 방문로그 기록
	void insertVisitLog(MemberVO memberVO);
	// 로그인시 접속로그 갱신
	void updateConnectLog(MemberVO memberVO);
	// 회원정보수정 
	void updateUserInfo(MemberVO memberVO);
	// 프로필 사진 수정
	void updateProFile(MemberVO memberVO);
	// 닉네임으로 아이디 조회
	String getUserId(String nick);
	// 유저 프로필 사진 조회
	String getUserImage(String id);
	// 아이디로 닉네임 조회
	String getUserNick(String id);
	// 접속중인 회원 목록
	List<MemberVO> getConnectList();
	// 비밀번호 변경
	int changPasswrod(HashMap<String, String> pass);
	// 접속기록 갱신
	int addConnect(String id);
	// 서브페이지 정보 뷰
	SubInfoVO getSubInfo(String id);
	
}
