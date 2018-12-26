package com.studysiba.service.group;

import java.util.List;

import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.group.GroupVO;

public interface GroupService {

	// 스터디 참가 목록 카운트
	int getGroupCount(GroupVO groupVO);
	// 스터디 참가 목록 조회
	List<GroupVO> getGroupList(PageDTO page);

}
