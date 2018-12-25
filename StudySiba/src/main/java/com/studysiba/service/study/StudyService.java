package com.studysiba.service.study;


import java.util.List;

import com.studysiba.domain.page.PageDTO;
import com.studysiba.domain.study.StudyVO;
import com.studysiba.domain.upload.UploadVO;

public interface StudyService {
	// 스터디룸 등록
	int write(StudyVO studyVO, UploadVO uploadVO);
	// 게시글 수 조회
	int getStudyCount();
	// 스터디룸 리스트 조회
	List<StudyVO> getStudyList(PageDTO page);

}
