package com.studysiba.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.studysiba.common.FileUpload;
import com.studysiba.common.MakeJSON;
import com.studysiba.domain.board.FreeBoardVO;
import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.common.SearchVO;
import com.studysiba.domain.study.StudyGroup;
import com.studysiba.domain.study.StudyVO;
import com.studysiba.domain.upload.UploadVO;
import com.studysiba.service.study.StudyService;

@Controller
@RequestMapping(value = "/study")
public class StudyController {

	@Autowired
	private StudyService studyService;
	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
		
		PageDTO page = new PageDTO();
		page.setPageSize(3);
		page.setPageNum(pageNum);
		page.setCount(studyService.getStudyCount());
		List<StudyVO> list = studyService.getStudyList(page);
		
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		
		return "study/list";
	}

	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(StudyVO studyVO, MultipartFile file, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		UploadVO uploadVO = new UploadVO();
		studyVO.setId(id);
		uploadVO.setId(id);
		uploadVO.setContent(studyVO.getTitle());
		uploadVO.setType("study");
		
		if ( file.isEmpty() ) {
			session.setAttribute("error", "그룹사진은 필수 조건 입니다.");
			return "redirect:/study/list";
		} else {
			uploadVO.setuFile(file.getOriginalFilename());
		}
		
		String uploadPath = "/home/hosting_users/bytrustu/tomcat/webapps/uploads/study/";
		File destdir = new File(uploadPath); 
	    if(!destdir.exists()){
	         destdir.mkdirs();
	    }
		
		String fileName = file.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf("."));
			if (ext.equals(".jpg") || ext.equals(".png") || ext.equals(".bmp") || ext.equals(".gif")) {
				try {
					fileName = FileUpload.upload(uploadPath, ext, file.getBytes());
					uploadVO.setUuid(fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				session.setAttribute("error", "이미지 파일만 업로드 가능 합니다.");
				return "redirect:/study/list";
			}
			
			int result = studyService.write(studyVO, uploadVO);
			if ( result == 1 ) {
				session.setAttribute("message", "스터디가 등록 되었습니다.");
			} else {
				session.setAttribute("error", "오류발생. 관리자에게 문의 바랍니다.");
			}

		return "redirect:/study/list";
	}
	
	
	@RequestMapping(value="search", method = RequestMethod.GET)
	public String search(Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum, SearchVO searchVO) {
		
		PageDTO page = new PageDTO();
		page.setPageSize(3);
		page.setPageNum(pageNum);
		page.setCount(studyService.getSearchCount(searchVO));
		page.setSearchType(searchVO.getSearchType());
		page.setSearchText(searchVO.getSearchText());
		
		List<StudyVO> list = studyService.getSearchList(page);
		
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		model.addAttribute("search",searchVO);
		
		return "study/search";
	}
	
	@RequestMapping(value="view", method = RequestMethod.GET)
	public String view(@RequestParam("no") int no, Model model, HttpSession session) {
		StudyVO studyVO = new StudyVO();
		studyVO = studyService.view(no);
		
		StudyGroup studyGroup = new StudyGroup();
		studyGroup.setgNo(no);
		studyGroup.setId(studyVO.getId());
		List<StudyGroup> userList = studyService.getUserList(studyGroup);
		
		studyGroup.setId(((HashMap<String, String>) session.getAttribute("userSession")).get("id"));
		boolean isGroup = studyService.isGroup(studyGroup);
		int groupCount = studyService.groupCount(studyGroup.getgNo());
		
		model.addAttribute("view", studyVO);
		model.addAttribute("userList", userList);
		model.addAttribute("isGroup", isGroup);
		model.addAttribute("groupCount", groupCount);
		
		return "study/view";
	}
	
	@ResponseBody
	@RequestMapping(value="joinGroup", method = RequestMethod.POST)
	public String joinGroup(StudyGroup studyGroup, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		studyGroup.setId(id);
		String result = studyService.joinGroup(studyGroup);
		if ( result.equals("1") ) {
			session.setAttribute("message", "스터디에 참여 되었습니다.");
		}
		JSONArray json = MakeJSON.change(result);
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="refreshStudy", method = RequestMethod.POST)
	public String refreshStudy(StudyVO studyVO, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		studyVO.setId(id);
		String result = studyService.studyRefresh(studyVO);
		if ( result.equals("1") ) {
			session.setAttribute("message", "해당 스터디가 재등록 되었습니다.");
		} else {
			session.setAttribute("error", "잘못된 접근 입니다.");
		}
		JSONArray json = new JSONArray();
		json = MakeJSON.change(result);
		return json.toString();
	}
	
	
}