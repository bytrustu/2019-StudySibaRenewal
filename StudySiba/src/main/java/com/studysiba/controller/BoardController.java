package com.studysiba.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.xml.ws.soap.Addressing;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studysiba.common.makeJSON;
import com.studysiba.domain.board.FreeBoardVO;
import com.studysiba.service.board.BoardService;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String freeList() {
		return "board/list";
	}
	
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public String moveWrite() {
		return "board/write";
	}
	
	@RequestMapping(value="/view", method = RequestMethod.GET)
	public String view(Model model) {
		return "board/view";
	}
	
	@ResponseBody
	@RequestMapping(value="/write", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String write(FreeBoardVO freeboardVO, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		freeboardVO.setId(id);
		freeboardVO.setType("freeboard");
		freeboardVO.setStep(0);
		freeboardVO.setIndent(0);
		freeboardVO.setCount(0);
		freeboardVO.setAvailable(0);
		System.out.println(freeboardVO.toString());
		String result = boardService.write(freeboardVO);
		
		System.out.println(result);
		session.setAttribute("message", "게시글이 등록 되었습니다.");
		JSONArray json = makeJSON.change(result);
		return json.toString();
	}
	
	
	
	
}
