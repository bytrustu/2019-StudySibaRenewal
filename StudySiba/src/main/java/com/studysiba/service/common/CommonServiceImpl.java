package com.studysiba.service.common;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.studysiba.dao.common.CommonDAO;
import com.studysiba.dao.member.MemberDAO;
import com.studysiba.domain.board.LikeVO;
import com.studysiba.domain.common.TotalVO;
import com.studysiba.domain.member.MemberVO;
import com.studysiba.domain.study.StudyVO;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonDAO commonDAO;
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public String upload(String type, String id, MultipartFile file) throws IOException {

		String folderPath = "/home/hosting_users/bytrustu/tomcat/webapps/uploads/profile";
		File destdir = new File(folderPath);

		if (!destdir.exists()) {
			destdir.mkdirs();
		}
		
		String uuid = UUID.randomUUID().toString();
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
		uuid = uuid + extension;
		String uFile = file.getOriginalFilename();

		String uploadPath = null;
		if ( type.equals("profile")) {
			MemberVO memberVO = new MemberVO();
			memberVO.setId(id);
			memberVO.setProFile(uuid);
			uploadPath = "/home/hosting_users/bytrustu/tomcat/webapps/uploads/profile";
			memberDAO.updateProFile(memberVO);
		}
		
		File target = new File(uploadPath, uuid);
		FileCopyUtils.copy(file.getBytes(), target);
		Runtime.getRuntime().exec("chmod 644 "+uploadPath + uuid);
		return uuid;
	}

	@Override
	public void delete(String preFileName) {
		String folderPath = "/home/hosting_users/bytrustu/tomcat/webapps/uploads/profile";
		File prevFile = new File(folderPath + "/" + preFileName);
		if (prevFile.exists()) {
	          prevFile.delete();
	        }
	}

	@Override
	public List<MemberVO> getConnectList() {
		return memberDAO.getConnectList();
	}

	@Override
	public List<LikeVO> getLikeList() {
		return commonDAO.getLikeList();
	}

	@Override
	public List<StudyVO> getStudyList() {
		return commonDAO.getStudyList();
	}

	@Override
	public List<TotalVO> getTotalList() {
		return commonDAO.getTotalList();
	}

	
}
