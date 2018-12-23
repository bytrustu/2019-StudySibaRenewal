package com.studysiba.controller;

import java.util.HashMap;
import java.util.List;

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

import com.studysiba.common.MakeJSON;
import com.studysiba.domain.board.FreeBoardVO;
import com.studysiba.domain.board.PageDTO;
import com.studysiba.service.board.BoardService;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String freeList(Model model, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
		
		PageDTO page = new PageDTO();
		page.setPageSize(8);
		page.setPageNum(pageNum);
		page.setCount(boardService.getBoardCount());
		List<FreeBoardVO> list = boardService.getBoardList(page);
		
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		
		return "board/list";
	}
	
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public String moveWrite() {
		return "board/write";
	}
	
	@RequestMapping(value="/rewrite", method = RequestMethod.GET)
	public String moveReWrite(FreeBoardVO view, Model model) {
		model.addAttribute("view", view);
		return "board/rewrite";
	}
	
	@RequestMapping(value="/view", method = RequestMethod.GET)
	public String view(Model model, @RequestParam("no") long no) {
		FreeBoardVO view = new FreeBoardVO();
		view = boardService.view(no);
		model.addAttribute("view",view);
		return "board/view";
	}
	
	@ResponseBody
	@RequestMapping(value="/write", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String write(FreeBoardVO freeboardVO, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		
		freeboardVO.setId(id);
		freeboardVO.setType("freeboard");
		freeboardVO.setCount(0);
		freeboardVO.setAvailable(0);
		String result = boardService.write(freeboardVO);
		if ( result.equals("1") ) {
			session.setAttribute("message", "게시글이 등록 되었습니다.");
		}
		JSONArray json = MakeJSON.change(result);
		return json.toString();
	}
	
	
	
	
}
