package com.studysiba.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.group.GroupVO;
import com.studysiba.service.group.GroupService;

@Controller
@RequestMapping(value="/group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum, HttpSession session) {
		
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		
		GroupVO groupVO = new GroupVO();
		groupVO.setId(id);
		
		PageDTO page = new PageDTO();
		page.setPageSize(5);
		page.setPageNum(pageNum);
		page.setCount(groupService.getGroupCount(groupVO));
		page.setId(id);
		List<GroupVO> list = groupService.getGroupList(page);
		
		for ( int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		
		System.out.println(page);
		
		
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		
		return "group/list";
	}
}
