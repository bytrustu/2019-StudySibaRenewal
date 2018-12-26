package com.studysiba.service.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studysiba.dao.group.GroupDAO;
import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.group.GroupVO;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDAO groupDAO;

	@Override
	public int getGroupCount(GroupVO groupVO) {
		return groupDAO.getGroupCount(groupVO);
	}

	@Override
	public List<GroupVO> getGroupList(PageDTO page) {
		int startRow = (page.getPageNum()-1)*page.getPageSize()+1;
		page.setStartRow(startRow-1);
		List<GroupVO> list = groupDAO.getGroupList(page);
		
		for ( int i=0; i<list.size(); i++ ) {
			if ( list.get(i).getAddress().length() > 25 ) {
				String addressStr = list.get(i).getAddress().substring(5, 25)+"...";
				list.get(i).setAddress(addressStr);
			}
		}
		return list;
	}
	
	
}
