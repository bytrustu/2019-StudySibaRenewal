package com.studysiba.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.studysiba.common.FileUpload;
import com.studysiba.domain.board.FreeBoardVO;
import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.common.SearchVO;
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
		System.out.println(studyService.getStudyCount());
		List<StudyVO> list = studyService.getStudyList(page);
		
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		
		System.out.println(page.toString());
		
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
		
		String uploadPath = "C:/upload/study/";
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
	
	
}
