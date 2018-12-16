package com.studysiba.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.studysiba.domain.member.MemberVO;
import com.studysiba.service.login.LoginService;
import com.studysiba.service.member.MemberService;

@RequestMapping("/login")
@Controller
public class LoginController {

	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;
	private OAuth2Operations oauthOperations;

	@Autowired
	private MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String test() {
		String reulst = loginService.test();
		return "";
	}
	
	// 구글 로그인
	@RequestMapping(value = "/googleSignInCallback", method = { RequestMethod.GET, RequestMethod.POST })
	public String googleSignInCallback(@RequestParam("code") String code, HttpSession session) throws Exception {

        oauthOperations = googleConnectionFactory.getOAuthOperations();
        AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, googleOAuth2Parameters.getRedirectUri(), null);
 
        String accessToken = accessGrant.getAccessToken();
        Long expireTime = accessGrant.getExpireTime();
        
        if (expireTime != null && expireTime < System.currentTimeMillis()) {
            accessToken = accessGrant.getRefreshToken();
            logger.info("accessToken is expired. refresh token = {}", accessToken);
        }
        
        Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
        Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();
 
        PlusOperations plusOperations = google.plusOperations();
        Person profile = plusOperations.getGoogleProfile();
        
        String sId = profile.getId();
        String path = null;
        MemberVO memberVO = loginService.checkJoinSocial(sId);
        if ( memberVO == null ) {
        	path = "/social";
        	HashMap<String, String> googleUser = new HashMap<String, String>();
        	googleUser.put("sId", sId);
        	googleUser.put("type", "google");
        	session.setAttribute("socialInfo", googleUser);
        } else if ( sId.equals(memberVO.getsId()) ) {
        	path = "/";
        	HashMap<String, String> userSession = memberService.setUserSession(memberVO);
			session.setAttribute( "userSession", userSession);
			memberService.insertVisitLog(memberVO);
        	session.setAttribute( "message", memberVO.getId()+ "님, 로그인 되었습니다.");
        }
 
        // Access Token 취소
        try {
            String revokeUrl = "https://accounts.google.com/o/oauth2/revoke?token=" + accessToken + "";
            URL url = new URL(revokeUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
 
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		return "redirect:"+path;
	}
	
	
	// 페이스북 로그인
	@RequestMapping(value = "/facebookSignInCallback", method = RequestMethod.POST )
	public String facebookSignInCallback(MemberVO memberVO, HttpSession session) {
		
		logger.info(memberVO.toString());
		
		String path = null;
		String sId = memberVO.getsId();
		String eMail = memberVO.geteMail();
		MemberVO facebookInfo = loginService.checkJoinSocial(sId);
		
		if ( facebookInfo == null ) {
        	path = "/social";
        	HashMap<String, String> facebookUser = new HashMap<String, String>();
        	facebookUser.put("sId", sId);
        	facebookUser.put("eMail", eMail);
        	facebookUser.put("type", "facebook");
        	session.setAttribute("socialInfo", facebookUser);
        } else if ( sId.equals(facebookInfo.getsId()) ) {
        	path = "/";
        	
        	HashMap<String, String> userSession = memberService.setUserSession(facebookInfo);
			session.setAttribute( "userSession", userSession);
			memberService.insertVisitLog(facebookInfo);
        	session.setAttribute( "message", facebookInfo.getId()+ "님, 로그인 되었습니다.");
        }
		return "redirect:"+path;
	}
	
	@RequestMapping(value = "/naverSignInCallback", method = RequestMethod.POST )
	public String naverSignInCallback(MemberVO memberVO, HttpSession session) {
		
		String path = null;
		String sId = memberVO.getsId();
		String eMail = memberVO.geteMail();
		MemberVO facebookInfo = loginService.checkJoinSocial(sId);
		
		if ( facebookInfo == null ) {
        	path = "/social";
        	HashMap<String, String> facebookUser = new HashMap<String, String>();
        	facebookUser.put("sId", sId);
        	facebookUser.put("eMail", eMail);
        	facebookUser.put("type", "facebook");
        	session.setAttribute("socialInfo", facebookUser);
        } else if ( sId.equals(facebookInfo.getsId()) ) {
        	memberService.insertVisitLog(memberVO);
        	path = "/";
        	session.setAttribute( "userId", facebookInfo.getId() );
        	session.setAttribute( "userNick", facebookInfo.getNick() );
        	session.setAttribute( "message", facebookInfo.getId()+ "님, 로그인 되었습니다.");
        }
		return "redirect:"+path;
	}
	
	@RequestMapping(value = "/kakaoSignInCallback", method = RequestMethod.POST )
	public String kakaoSignInCallback(MemberVO memberVO, HttpSession session) {
		logger.info("move kakaoSignInCallback");
		String path = null;
		String sId = memberVO.getsId();
		MemberVO kakaoInfo = loginService.checkJoinSocial(sId);
		
		if ( kakaoInfo == null ) {
        	path = "/social";
        	HashMap<String, String> kakaoUser = new HashMap<String, String>();
        	kakaoUser.put("sId", sId);
        	kakaoUser.put("type", "kakao");
        	session.setAttribute("socialInfo", kakaoUser);
        } else if ( sId.equals(kakaoInfo.getsId()) ) {
        	memberService.insertVisitLog(memberVO);
        	path = "/";
        	HashMap<String, String> userSession = memberService.setUserSession(kakaoInfo);
			session.setAttribute( "userSession", userSession);
			memberService.insertVisitLog(kakaoInfo);
        	session.setAttribute( "message", kakaoInfo.getId()+ "님, 로그인 되었습니다.");
        }
		return "redirect:"+path;
	}
}


