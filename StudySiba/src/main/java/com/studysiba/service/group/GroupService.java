package com.studysiba.service.group;

import java.util.List;

import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.group.GroupVO;
import com.studysiba.domain.upload.UploadVO;

public interface GroupService {

	// 스터디 참가 목록 카운트
	int getGroupCount(GroupVO groupVO);
	// 스터디 참가 목록 조회
	List<GroupVO> getGroupList(PageDTO page);
	// 스터디 그룹 뷰
	GroupVO view(GroupVO groupVO);
	// 그룹 첨부파일 업로드
	void upload(UploadVO uploadVO);
	// 그룹 업로드 카운트
	int getUploadCount(UploadVO uploadVO);
	// 그룹 업로드 리스트
	List<UploadVO> getUploadList(PageDTO page);

}
