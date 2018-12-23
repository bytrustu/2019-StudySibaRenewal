package com.studysiba.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.studysiba.common.MakeJSON;
import com.studysiba.service.common.CommonService;

@Controller
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private CommonService commonService;
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;

	private OAuth2Operations oauthOperations;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model, HttpSession session) throws UnsupportedEncodingException {
		logger.info("move main");

		if (session.getAttribute("socialInfo") != null) {
			session.removeAttribute("socialInfo");
			session.setAttribute("message", "소셜 로그인 가입에 실패 했습니다.");
		}

		// 구글 로그인 API 주소값
		oauthOperations = googleConnectionFactory.getOAuthOperations();
		String google_url = oauthOperations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		model.addAttribute("google_url", google_url);

		// 네이버 로그인 API 주소값
		String clientId = "zNHid8dBGPt5XXNtGjyU";// 애플리케이션 클라이언트 아이디값";
		String redirectURI = URLEncoder.encode("http://127.0.0.1:8282/", "UTF-8");

		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String naver_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		naver_url += "&client_id=" + clientId;
		naver_url += "&redirect_uri=" + redirectURI;
		naver_url += "&state=" + state;
		session.setAttribute("state", state);
		model.addAttribute("naver_url", naver_url);
		return "/common/main";
	}

	@RequestMapping(value = "/social", method = RequestMethod.GET)
	public String social(HttpSession session) {
		String path = null;
		if (session.getAttribute("socialInfo") == null) {
			path = "redirect:/";
			session.setAttribute("message", "잘못 된 접근 입니다.");
		} else {
			path = "common/social";
		}
		return path;
	}

	// 파일 업로드
	@ResponseBody
	@RequestMapping(value = "/upload", produces = "text/plain;charset=utf-8", method = RequestMethod.POST)
	public String uploadFile(MultipartFile file, @RequestParam("type") String type, HttpSession session)
			throws IOException, InterruptedException {
		logger.info("name : " + file.getOriginalFilename());
		logger.info("size : " + file.getSize());
		logger.info("contentType : " + file.getContentType());
		logger.info("type : " + type);
		
		String id = ((HashMap<String, String>)session.getAttribute("userSession")).get("id");
		String saveName = commonService.upload(type, id, file);

		return saveName;
	}
}
