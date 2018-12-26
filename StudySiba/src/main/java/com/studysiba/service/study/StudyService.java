package com.studysiba.service.study;


import java.util.List;

import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.common.SearchVO;
import com.studysiba.domain.study.StudyGroup;
import com.studysiba.domain.study.StudyVO;
import com.studysiba.domain.upload.UploadVO;

public interface StudyService {
	// 스터디룸 등록
	int write(StudyVO studyVO, UploadVO uploadVO);
	// 게시글 수 조회
	int getStudyCount();
	// 스터디룸 리스트 조회
	List<StudyVO> getStudyList(PageDTO page);
	// 스터디룸 검색 카운트 조회
	int getSearchCount(SearchVO searchVO);
	// 스터디룸 검색 리스트 조회
	List<StudyVO> getSearchList(PageDTO page);
	// 스터디룸 조회
	StudyVO view(int no);
	// 스터디 참여중인 유저 리스트
	List<StudyGroup> getUserList(StudyGroup studyGroup);
	// 스터디에 참여중인지 확인
	boolean isGroup(StudyGroup studyGroup);
	// 스터디 참여 인원 수
	int groupCount(long gNo);
	// 스터디 그룹 참여
	String joinGroup(StudyGroup studyGroup);
	// 게시글 재 등록
	String studyRefresh(StudyVO studyVO);

}
