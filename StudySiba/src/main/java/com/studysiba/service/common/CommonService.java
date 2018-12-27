package com.studysiba.service.common;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.studysiba.domain.board.LikeVO;
import com.studysiba.domain.common.TotalVO;
import com.studysiba.domain.member.MemberVO;
import com.studysiba.domain.study.StudyVO;

public interface CommonService {

	String upload(String type, String id, MultipartFile file) throws IOException;
	// 이전 파일 삭제
	void delete(String preFileName);
	// 메인 접속자 현황
	List<MemberVO> getConnectList();
	// 메인 좋아요 순위 게시글 리스트
	List<LikeVO> getLikeList();
	// 메인 등록 된 스터디 리스트
	List<StudyVO> getStudyList();
	// 메인 홈페이지 현황 통계
	List<TotalVO> getTotalList();


}
