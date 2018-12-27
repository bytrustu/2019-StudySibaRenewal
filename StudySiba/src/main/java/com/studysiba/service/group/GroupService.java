package com.studysiba.service.group;

import java.util.List;

import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.group.GroupMessageVO;
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
	// 그룹메세지 리스트
	List<GroupMessageVO> getGroupMessageList(long gNo);
	// 그룹메세지 전송
	String sendGroupMessage(GroupMessageVO groupMessageVO);
	// 그룹 메세지 조회
	List<GroupMessageVO> viewGroupMessage(long gNo);
	// 그룹 탈퇴
	String secession(GroupVO groupVO);
	// 그룹 삭제
	String delete(GroupVO groupVO);

}
