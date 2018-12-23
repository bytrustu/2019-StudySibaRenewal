package com.studysiba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/group")
public class GroupController {
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String freeList() {
		return "group/list";
	}
}
