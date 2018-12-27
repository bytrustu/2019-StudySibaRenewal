package com.studysiba.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.group.GroupMessageVO;
import com.studysiba.domain.group.GroupVO;
import com.studysiba.domain.upload.UploadVO;
import com.studysiba.service.group.GroupService;

@Controller
@RequestMapping(value = "/group")
public class GroupController {
	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		GroupVO groupVO = new GroupVO();
		groupVO.setId(id);
		PageDTO page = new PageDTO();
		page.setPageSize(5);
		page.setPageNum(pageNum);
		page.setCount(groupService.getGroupCount(groupVO));
		page.setId(id);
		List<GroupVO> list = groupService.getGroupList(page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "group/list";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(GroupVO groupVO, HttpSession session, Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		groupVO.setId(id);
		groupVO = groupService.view(groupVO);
		if (groupVO == null) {
			session.setAttribute("error", "참여하지 않은 그룹은 접근 할 수 없습니다.");
			return "redirect:/group/list";
		}
		
		UploadVO uploadVO = new UploadVO();
		uploadVO.setType("group");
		uploadVO.setuNo(groupVO.getgNo());
		PageDTO page = new PageDTO();
		page.setPageSize(3);
		page.setPageNum(pageNum);
		page.setCount(groupService.getUploadCount(uploadVO));
		page.setuNo(uploadVO.getuNo());
		page.setType(uploadVO.getType());
		List<UploadVO> list = groupService.getUploadList(page);
		
		List<GroupMessageVO> message = groupService.getGroupMessageList(groupVO.getgNo());
		
		model.addAttribute("view", groupVO);
		model.addAttribute("page", page);
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "group/view";
	}

	@RequestMapping(value="upload", method = RequestMethod.POST)
	public String upload(MultipartFile file, HttpSession session, @RequestParam("gNo") int gNo,
			@RequestParam("comment") String comment, Model model) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		UploadVO uploadVO = new UploadVO();
		uploadVO.setId(id);
		uploadVO.setuNo(gNo);
		uploadVO.setContent(comment);
		uploadVO.setType("group");
		if (file.isEmpty()) {
			session.setAttribute("error", "첨부파일은 필수 조건 입니다.");
			return "redirect:/group/view?gNo=" + gNo;
		} else {
			uploadVO.setuFile(file.getOriginalFilename());
		}
		String uploadPath = "C:/upload/group/";
		File destdir = new File(uploadPath);
		if (!destdir.exists()) {
			destdir.mkdirs();
		}
		String fileName = file.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf("."));
		try {
			fileName = FileUpload.upload(uploadPath, ext, file.getBytes());
			uploadVO.setUuid(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		groupService.upload(uploadVO);
		session.setAttribute("message", "첨부파일이 등록 되었습니다.");
		model.addAttribute("gNo", gNo);
		return "redirect:/group/view";
	}
	
	@ResponseBody
	@RequestMapping(value="sendGroupMessage", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String sendGroupMessage(GroupMessageVO groupMessageVO, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		groupMessageVO.setId(id);
		String result = groupService.sendGroupMessage(groupMessageVO);
		JSONArray json = MakeJSON.change(result);
		System.out.println(json.toString());
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="viewGroupMessage", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String viewGroupMessage(@RequestParam("gNo") long gNo) {
		List<GroupMessageVO> list = groupService.viewGroupMessage(gNo);
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for ( int i=0; i<list.size(); i++ ) {
			JSONObject value = new JSONObject();
			value.put("no", list.get(i).getNo());
			value.put("gNo", list.get(i).getgNo());
			value.put("id", list.get(i).getId());
			value.put("content", list.get(i).getContent());
			value.put("gDate", list.get(i).getgDate());
			value.put("nick", list.get(i).getNick());
			value.put("proFile", list.get(i).getProFile());
			array.add(value);
		}
		obj.put("result", array);
		return obj.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="secession", method = RequestMethod.POST)
	public String secession(GroupVO groupVO, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		groupVO.setId(id);
		String result = groupService.secession(groupVO);
		if ( result.equals("1") ) {
			session.setAttribute("message", "해당 스터디를 탈퇴 했습니다.");
		}
		JSONArray json = new JSONArray();
		json = MakeJSON.change(result);
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="delete", method = RequestMethod.POST)
	public String delete(GroupVO groupVO, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		groupVO.setId(id);
		String result = groupService.delete(groupVO);
		if ( result.equals("1") ) {
			session.setAttribute("message", "해당 스터디를 탈퇴 했습니다.");
		}
		JSONArray json = new JSONArray();
		json = MakeJSON.change(result);
		return json.toString();
	}
	
}
