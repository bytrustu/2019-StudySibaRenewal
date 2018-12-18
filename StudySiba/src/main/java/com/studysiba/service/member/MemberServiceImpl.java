package com.studysiba.service.member;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.studysiba.dao.member.MemberDAO;
import com.studysiba.domain.member.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	
	@Override
	public int insertTest(HashMap<String, Object> map) {
		return memberDAO.insertTest(map);
	}
	
	@Override
	public MemberVO getUserInfomation(MemberVO memberVO) {
		return memberDAO.getUserInfomation(memberVO);
	}
	
	@Override
	public int socialJoin(MemberVO memberVO) {
		// 비밀번호 암호화
		String encodePass = passwordEncoder.encode(memberVO.getPass());
		memberVO.setPass(encodePass);
		return memberDAO.socialJoin(memberVO);
	}

	@Override
	public String valueCheckId(String value) {
		return memberDAO.valueCheckId(value);
	}

	@Override
	public String valueCheckNick(String value) {
		return memberDAO.valueCheckNick(value);
	}

	@Override
	public String valueCheckEmail(String value) {
		return memberDAO.valueCheckEmail(value);
	}

	@Override
	public boolean valueCheckPass(String id, String pass) {
		String encodePass = memberDAO.valueCheckPass(id);
		return passwordEncoder.matches(pass, encodePass);
	}
	
	@Override
	public String checkResult(String type, String value, int index) {
		String result = null;
		if (value == null || value.length() <= index) {
			result = "false";
		} else if (value.length() > index) {
			String checkVal = null;
			if (type.equals("id")) {
				checkVal = valueCheckId(value);
			} else if (type.equals("nick")) {
				checkVal = valueCheckNick(value);
			} else if (type.equals("eMail")) {
				checkVal = valueCheckEmail(value);
			}
			result = changeResult(value, checkVal);
		}
		return result;
	}
	
	@Override
	public String changeResult(String value, String checkVal) {
		String result = null;
		if (value.equals(checkVal)) {
			result = "true";
		} else if (!value.equals(checkVal)) {
			result = "false";
		}
		return result;
	}

	@Override
	public boolean checkEmailResult(String value) {
		String exp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(exp);
		Matcher m = p.matcher(value);
		return m.matches();
	}

	@Override
	public String returnResult(String type, String value) {
		String result = null;
		String checkVal = null;
		boolean isCheck = false;
		if (type.equals("id")) {
			result = checkResult(type, value, 3);
		} else if (type.equals("nick")) {
			result = checkResult(type, value, 0);
		} else if (type.equals("eMail")) {
			isCheck = checkEmailResult(value);
			if (!isCheck) {
				result = "false";
			} else if (isCheck) {
				checkVal = valueCheckEmail(value);
				result = changeResult(value, checkVal);
			}
		}
		return result;
	}

	@Override
	public void insertVisitLog(MemberVO memberVO) {
		memberDAO.insertVisitLog(memberVO);
		memberDAO.updateConnectLog(memberVO);
	}

	@Override
	public HashMap<String, String> setUserSession(MemberVO memberVO) {
		HashMap<String, String> userSession = new HashMap<String, String>();
		userSession.put("auth", memberVO.getAuth());
		userSession.put("id", memberVO.getId());
		userSession.put("cDate", memberVO.getcDate());
		return userSession;
	}

	@Override
	public String checkNick(String nick) {
		String result = null;
		if ( nick.length() <= 0 ) {
			result = "emptyValue";
		} else {
			if (memberDAO.valueCheckNick(nick) == null ) {
				result = "false";
			} else {
				result = "true";
			}
		}
		return result;
	}

	@Override
	public void updateUserInfo(MemberVO memberVO) {
		memberDAO.updateUserInfo(memberVO);
	}

	@Override
	public String changPasswrod(HashMap<String, String> pass) {
		String currPass = pass.get("currPass");
		String changePass = pass.get("changePass");
		String type = null;
		if ( currPass.length() <= 0 || changePass.length() <= 0 ) {
			type = "empty";
		} else if ( currPass.length() <= 3 || changePass.length() <= 3 ) {
			type = "length";
		} else if ( currPass.equals(changePass) ) {
			type = "equal";
		} else {
			String dbPass = memberDAO.valueCheckPass(pass.get("id"));
			if ( passwordEncoder.matches( currPass, dbPass) ) {
				pass.put("currPass", dbPass);
				pass.put("encodePass", passwordEncoder.encode(changePass));
				int result = memberDAO.changPasswrod(pass);
				if ( result == 1 ) {
					type = "success";
				} else {
					type = "error";
				}
			} else {
				type = "currpass";
			}
		}
		return type;
	}

	@Override
	public String addConnect(String id) {
		String result = null;
		if ( memberDAO.addConnect(id) == 1 ) {
			result = "true";
		} else {
			result = "false";
		}
		return result;
	}

	
}
