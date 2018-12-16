package com.studysiba.service.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.studysiba.dao.common.CommonDAO;
import com.studysiba.dao.member.MemberDAO;
import com.studysiba.domain.member.MemberVO;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonDAO commonDAO;
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public String upload(String type, String id, MultipartFile file) throws IOException {

		String folderPath = "C:/upload/profile";
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
			uploadPath = "C:/upload/profile";
			memberDAO.updateProFile(memberVO);
		}
		
		File target = new File(uploadPath, uuid);
		FileCopyUtils.copy(file.getBytes(), target);
		
		/*String saveName = file.getOriginalFilename();

		String uuid = UUID.randomUUID().toString();
		String uFile = file.getOriginalFilename();

		String uploadPath = "C:/upload/profile";
		File target = new File(uploadPath, saveName);
		FileCopyUtils.copy(file.getBytes(), target);

		Upload upload = new Upload();
		upload.setUuid(uuid);
		upload.setuFile(uFile);*/

		return uuid;
	}

}
