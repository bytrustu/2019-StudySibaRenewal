package com.studysiba.service.member;

import java.util.HashMap;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studysiba.dao.member.MemberDAO;
import com.studysiba.domain.member.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Override
	public int insertTest(HashMap<String, Object> map) {
		return memberDAO.insertTest(map);
	}

	@Override
	public int socialJoin(MemberVO memberVO) {
		return memberDAO.socialJoin(memberVO);
	}
	
	
	
}
