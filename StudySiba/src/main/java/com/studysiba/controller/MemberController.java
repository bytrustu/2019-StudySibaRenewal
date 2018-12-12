package com.studysiba.controller;



import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.studysiba.domain.member.MemberVO;
import com.studysiba.service.member.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	
	@RequestMapping(value="text", method=RequestMethod.GET)
	public String test() {
		return "member/test";
	}
	
	// 회원가입 처리
	@RequestMapping(value="join", method=RequestMethod.POST)
	public String join(MemberVO memberVO, HttpSession session) {
		logger.info("페이지 이동 : /member/join");
		
		// 소셜 가입 경우
		if ( session.getAttribute("socialInfo") != null ) {
			logger.info("소셜 가입 진행");
			
			@SuppressWarnings("unchecked")
			HashMap<String, String> socialInfo = (HashMap<String, String>) session.getAttribute("socialInfo");
			String sId = socialInfo.get("sId");
			String type = socialInfo.get("type");
			String eMail = socialInfo.get("eMail");
			if ( type.equals("google") || type.equals("kakao") ) {
				memberVO.setsId(sId);
				memberVO.setType(type);
			} else if (  type.equals("facebook") ) {
				memberVO.setsId(sId);
				memberVO.seteMail(eMail);
				memberVO.setType(type);
			}
			logger.info(memberVO.toString());
			int joinResult = memberService.socialJoin(memberVO);
			logger.info("소셜 가입 결과 : " + joinResult);
			if ( joinResult == 1 ) {
				session.removeAttribute("socialInfo");
				session.setAttribute("userId", memberVO.getId());
				session.setAttribute("userNick", memberVO.getNick());
				session.setAttribute("message", "환영합니다. 회원가입에 성공 했습니다.");
			}
		// 일반 가입 경우
		} else {
			logger.info("일반 가입 진행");
		}
		
		return "redirect:/";
	}
}
