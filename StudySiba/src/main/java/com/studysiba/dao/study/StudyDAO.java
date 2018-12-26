package com.studysiba.dao.study;

import java.util.List;

import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.common.SearchVO;
import com.studysiba.domain.study.StudyGroup;
import com.studysiba.domain.study.StudyVO;
import com.studysiba.domain.upload.UploadVO;

public interface StudyDAO {

	// 스터디룸 게시글 등록
	int write(StudyVO studyVO);
	// 스터디룸 게시글 번호 확인 ( 업로드 참조 번호를 위해 )
	int getStudyNo(StudyVO studyVO);
	// 그룹 생성
	int makeGroup(StudyVO studyVO);
	// 게시글 수 조회
	int getStudyCount();
	// 스터디룸 리스트 조회
	List<StudyVO> getStudyList(PageDTO page);
	// 스터디 그룹 참여
	int joinGroup(StudyGroup studyGroup);
	// 스터디룸 검색 카운트 조회
	int getSearchCount(SearchVO searchVO);
	// 스터디룸 검색 리스트 조회
	List<StudyVO> getSearchList(PageDTO page);
	// 스터디룸 조회
	StudyVO view(int no);
	// 스터디 참여중인 유저 리스트
	List<StudyGroup> getUserList(StudyGroup studyGroup);
	// 스터디에 참여중인지 확인
	int isGroup(StudyGroup studyGroup);
	// 스터디 참여 인원 수
	int groupCount(long gNo);

}
