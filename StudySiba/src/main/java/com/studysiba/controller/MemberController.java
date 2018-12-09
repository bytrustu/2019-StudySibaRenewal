package com.studysiba.controller;

import java.util.HashMap;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studysiba.service.member.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Inject
	private MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	
	@RequestMapping(value="text", method=RequestMethod.GET)
	public String test() {
		return "member/test";
	}
	
	@ResponseBody
	@RequestMapping(value="test", method=RequestMethod.POST)
	public String test(@RequestParam HashMap<String, Object> map) {
		logger.info("페이지 이동 : /member/test");
		logger.info((String) map.get("text"));
		int result = memberService.insertTest(map);
		
		return Integer.toString(result);
	}
}
