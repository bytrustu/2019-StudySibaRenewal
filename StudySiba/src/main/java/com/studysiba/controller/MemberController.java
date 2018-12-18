package com.studysiba.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() {
		return "member/test";
	}

	// 회원로그인 처리
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(MemberVO memberVO, Model model, HttpSession session) {

		String id = memberVO.getId();
		String pass = memberVO.getPass();

		if (memberVO == null || id.equals("") || pass.equals("")) {
			session.setAttribute("message", "로그인 실패 했습니다.");
		} else {
			if (id.equals(memberService.valueCheckId(id))) {
				if (memberService.valueCheckPass(id, pass)) {
					// 로그인 성공 시
					memberVO = memberService.getUserInfomation(memberVO);
					// 세션 등록
					HashMap<String, String> userSession = memberService.setUserSession(memberVO);
					session.setAttribute("userSession", userSession);
					session.setAttribute("message", id + "님, 로그인 되었습니다.");
					memberService.insertVisitLog(memberVO);

					// 메인 페이지 뷰
					HashMap<String, Object> view = new HashMap<String, Object>();

					// 유저 정보
					HashMap<String, Object> userWarp = new HashMap<String, Object>();
					view.put("userWarp", userWarp);
					model.addAttribute("view", view);
				} else {
					session.setAttribute("message", "비밀번호가 일치하지 않습니다.");
				}
			} else {
				session.setAttribute("message", "아이디가 존재하지 않습니다.");
			}
		}
		return "redirect:/";
	}

	// 회원가입 처리
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String join(MemberVO memberVO, HttpSession session) {
		logger.info("페이지 이동 : /member/join");

		// 소셜 가입의 경우
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

			// 일반 가입의 경우
		} else {
			memberVO.setAuth("normal");
			memberVO.setType("normal");
			memberVO.setsId("notuse");
		}
		int joinResult = memberService.socialJoin(memberVO);
		if (joinResult == 1) {
			// 로그인 방문 기록
			// 소셜 세션 삭제 및 로그인 세션 등록
			session.removeAttribute("socialInfo");
			HashMap<String, String> userSession = memberService.setUserSession(memberVO);
			session.setAttribute("userSession", userSession);
			memberService.insertVisitLog(memberVO);
			session.setAttribute("message", "환영합니다. 회원가입에 성공 했습니다.");
		}
		return "redirect:/";
	}

	@ResponseBody
	@RequestMapping(value = "validationCheck", method = RequestMethod.POST)
	public String validationCheck(@RequestParam HashMap<String, String> map) {
		String type = map.get("type");
		String value = map.get("value");
		String result = null;
		result = memberService.returnResult(type, value);
		return result;
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("userSession");
		session.setAttribute("message", "로그아웃 되었습니다.");
		return "redirect:/";
	}

	@ResponseBody
	@RequestMapping(value = "checkNick", method = RequestMethod.POST)
	public String checkNick(@RequestParam("nick") String nick) {
		String result = null;
		result = memberService.checkNick(nick);
		return result;
	}
	
	@RequestMapping(value = "changeNick", method = RequestMethod.POST)
	public String changeNick(MemberVO memberVO, HttpSession session) {
		memberService.updateUserInfo(memberVO);
		session.setAttribute("message", "닉네임이 변경 되었습니다.");
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value="changPasswrod", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	public String changPasswrod(@RequestParam HashMap<String, String> pass, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		pass.put("id", id);
		String result = memberService.changPasswrod(pass);
		String json = jsonArr(result);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="addConnect", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	public String addConnect(HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		String result = memberService.addConnect(id);
		String json = jsonArr(result);
		return json;
	}
	
	
	public String jsonArr(String data) {
		JSONArray array = new JSONArray();
		array.add(data);
		return array.toString();
	}
	
}
