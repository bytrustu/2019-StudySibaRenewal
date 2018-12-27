package com.studysiba.dao.group;

import java.util.List;

import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.group.GroupMessageVO;
import com.studysiba.domain.group.GroupVO;
import com.studysiba.domain.upload.UploadVO;

public interface GroupDAO {

	// 참여중인 그룹 카운트
	int getGroupCount(GroupVO groupVO);
	// 참여중인 그룹 조회
	List<GroupVO> getGroupList(PageDTO page);
	// 스터디 그룹 뷰
	GroupVO view(GroupVO groupVO);
	// 그룹메세지 조회
	List<GroupMessageVO> getGroupMessageList(long gNo);
	// 그룹메세지 전송
	int sendGroupMessage(GroupMessageVO groupMessageVO);
	// 그룹메세지 조회
	List<GroupMessageVO> viewGroupMessage(long gNo);
	// 그룹 탈퇴
	int secession(GroupVO groupVO);
	// 그룹 삭제
	int delete(GroupVO groupVO);
}
