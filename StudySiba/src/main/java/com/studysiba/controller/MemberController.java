package com.studysiba.controller;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studysiba.domain.member.MemberVO;
import com.studysiba.service.member.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@RequestMapping(value = "text", method = RequestMethod.GET)
	public String test() {
		return "member/test";
	}
	
	// 회원로그인 처리
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(MemberVO memberVO, HttpSession session) {
		
		String id = memberVO.getId();
		String pass = memberVO.getPass();
		
		if ( memberVO == null || id.equals("") || pass.equals("") ) {
			session.setAttribute("message", "로그인 실패 했습니다.");
		} else {
			if ( id.equals( memberService.valueCheckId(id) ) ) {
				if ( pass.equals( memberService.valueCheckPass(id) ) ) {
					session.setAttribute( "message", id+ "님, 로그인 되었습니다.");
				} else {
					session.setAttribute( "message", "비밀번호가 일치하지 않습니다.");
				}
			} else {
				session.setAttribute( "message", "아이디가 존재하지 않습니다.");
			}
		}
		return "redirect:/";
	}

	// 회원가입 처리
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String join(MemberVO memberVO, HttpSession session) {
		logger.info("페이지 이동 : /member/join");

		// 소셜 가입 경우
		if (session.getAttribute("socialInfo") != null) {

			@SuppressWarnings("unchecked")
			HashMap<String, String> socialInfo = (HashMap<String, String>) session.getAttribute("socialInfo");
			String sId = socialInfo.get("sId");
			String type = socialInfo.get("type");
			if (!type.equals("facebook")) {
				String eMail = socialInfo.get("eMail");
				memberVO.seteMail(eMail);
			}
			memberVO.setType(type);
			memberVO.setAuth("normal");
			memberVO.setsId(sId);
			memberVO.setPass("");
		
		// 일반 가입 경우
		} else {
			memberVO.setAuth("normal");
			memberVO.setType("normal");
			memberVO.setsId("notuse");
		}
		int joinResult = memberService.socialJoin(memberVO);
		if (joinResult == 1) {
			session.removeAttribute("socialInfo");
			session.setAttribute("userId", memberVO.getId());
			session.setAttribute("userNick", memberVO.getNick());
			session.setAttribute("message", "환영합니다. 회원가입에 성공 했습니다.");
		}
		return "redirect:/";
	}

	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "validationCheck", method = RequestMethod.POST)
	public String validationCheck(@RequestParam HashMap<String, String> map) {
		logger.info("type : " + map.get("type") + " , " + "value : " + map.get("value"));

		String type = map.get("type");
		String value = map.get("value");
		String checkVal;
		String result = null;

		String exp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		boolean isCheck = false;

		if (type.equals("id")) {
			result = checkResult(type, value, 3);
		} else if (type.equals("nick")) {
			result = checkResult(type, value, 0);
		} else if (type.equals("eMail")) {
			Pattern p = Pattern.compile(exp);
			Matcher m = p.matcher(value);
			isCheck = m.matches();

			if (!isCheck) {
				result = "false";
			} else if (isCheck) {
				checkVal = memberService.valueCheckEmail(value);
				result = changeResult(value, checkVal);
			}

		}
		return result;
	}

	public String checkResult(String type, String value, int index) {
		String result = null;
		if (value == null || value.length() <= index) {
			result = "false";
		} else if (value.length() > index) {
			String checkVal = null;
			if (type.equals("id")) {
				checkVal = memberService.valueCheckId(value);
			} else if (type.equals("nick")) {
				checkVal = memberService.valueCheckNick(value);
			} else if (type.equals("eMail")) {
				checkVal = memberService.valueCheckEmail(value);
			}
			result = changeResult(value, checkVal);
		}
		return result;
	}

	public static String changeResult(String value, String checkVal) {
		String result = null;
		if (value.equals(checkVal)) {
			result = "true";
		} else if (!value.equals(checkVal)) {
			result = "false";
		}
		return result;
	}

}
