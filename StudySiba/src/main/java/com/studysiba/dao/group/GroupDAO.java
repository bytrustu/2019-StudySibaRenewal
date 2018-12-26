package com.studysiba.dao.group;

import java.util.List;

import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.group.GroupVO;

public interface GroupDAO {

	// 참여중인 그룹 카운트
	int getGroupCount(GroupVO groupVO);
	// 참여중인 그룹 조회
	List<GroupVO> getGroupList(PageDTO page);

}
