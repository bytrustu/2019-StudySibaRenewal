package com.studysiba.service.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studysiba.dao.login.LoginDAO;
import com.studysiba.domain.member.MemberVO;

@Service
public class LoginServiceImpl implements LoginService {

	
	@Autowired
	private LoginDAO loginDAO;

	@Override
	public String test() {
		// TODO Auto-generated method stub
		return loginDAO.test();
	}

	@Override
	public MemberVO checkJoinSocial(String sId) {
		return loginDAO.checkJoinSocial(sId);
	}

	
}
