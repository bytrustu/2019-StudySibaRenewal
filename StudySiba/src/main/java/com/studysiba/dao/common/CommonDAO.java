package com.studysiba.dao.common;

import java.util.List;

import com.studysiba.domain.board.LikeVO;
import com.studysiba.domain.common.TotalVO;
import com.studysiba.domain.study.StudyVO;

public interface CommonDAO {

	// 메인 좋아요 순위 게시글 리스트
	List<LikeVO> getLikeList();
	// 메인 등록 된 스터디 리스트
	List<StudyVO> getStudyList();
	// 메인 홈페이지 현황 통계
	List<TotalVO> getTotalList();

}
