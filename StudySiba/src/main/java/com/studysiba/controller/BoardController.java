package com.studysiba.controller;

import java.util.ArrayList;
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
import com.studysiba.domain.board.CommentVO;
import com.studysiba.domain.board.FreeBoardVO;
import com.studysiba.domain.board.LikeVO;
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
		page.setPageSize(7);
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
	public String view(Model model, @RequestParam("no") int no, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		FreeBoardVO view = new FreeBoardVO();
		LikeVO likeVO = new LikeVO();
		likeVO.setfNo(no);
		likeVO.setId(id);
		HashMap<String, Object> like = boardService.getLikeInfo(likeVO);
		view = boardService.view(no);
		
		if ( view.getAvailable() == 1 ) {
			session.setAttribute("error", "잘못된 접근 입니다.");
			return "redirect:/board/list";
		}
		
		List<CommentVO> comment = new ArrayList<CommentVO>();
		comment = boardService.getCommentList(no);
		
		int commentCount =boardService.getCommentCount(no);
		
		model.addAttribute("view",view);
		model.addAttribute("like", like);
		model.addAttribute("comment", comment);
		model.addAttribute("commentCount", commentCount);
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
	
	@ResponseBody
	@RequestMapping(value="likeFunc", method = RequestMethod.POST)
	public String likeFunc(HttpSession session, LikeVO likeVO) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		likeVO.setfNo(likeVO.getNo());
		likeVO.setId(id);
		String result = boardService.likeFunc(likeVO);
		JSONArray json = MakeJSON.change(result);
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getLike", method = RequestMethod.POST)
	public String getLike(@RequestParam("no") int no ) {
		String result = boardService.getLike(no);
		JSONArray json = MakeJSON.change(result);
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="writeComment", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String writeComment(CommentVO commentVO, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		commentVO.setType("freeboard");
		commentVO.setId(id);
		commentVO.setPreId("default");
		commentVO.setStep(0);
		commentVO.setIndent(0);
		commentVO.setAvailable(0);
		String result = boardService.writeComment(commentVO);
		if ( result.equals("1") ) {
			session.setAttribute("message", "댓글이 등록 되었습니다.");
		}
		JSONArray json = MakeJSON.change(result);
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="reWriteComment", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String reWriteComment(CommentVO commentVO, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		commentVO.setId(id);
		commentVO.setAvailable(0);
		String result = boardService.reWriteComment(commentVO);
		if ( result.equals("1") ) {
			session.setAttribute("message", "댓글이 등록 되었습니다.");
		}
		JSONArray json = MakeJSON.change(result);
		return json.toString();
	}
	
	@RequestMapping(value="modify", method = RequestMethod.GET)
	public String moveModify(@RequestParam("no") long no, HttpSession session, Model model) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		FreeBoardVO freeboardVO = new FreeBoardVO();
		freeboardVO = boardService.view(no);
		String path = null;
		if ( freeboardVO.getId().equals(id) ) {
			path = "board/modify";
			model.addAttribute("view", freeboardVO);
			System.out.println(freeboardVO.toString());
		} else {
			session.setAttribute("error", "잘못된 접근 입니다.");
			path = "redirect:/board/list";
		}
		return path;
	}
	
	@ResponseBody
	@RequestMapping(value="modify", method = RequestMethod.POST)
	public String modify(FreeBoardVO freeboardVO, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		freeboardVO.setId(id);
		String result = boardService.modify(freeboardVO);
		if ( result.equals("1") ) {
			session.setAttribute("message", "글이 수정 되었습니다.");
		}
		JSONArray json = MakeJSON.change(result);
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="delete", method = RequestMethod.POST)
	public String delete(FreeBoardVO freeboardVO, HttpSession session ) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		freeboardVO.setId(id);
		String result = boardService.delete(freeboardVO);
		System.out.println(freeboardVO.toString());
		System.out.println(result);
		if ( result.equals("1") ) {
			session.setAttribute("message", "글이 삭제 되었습니다.");
		}
		JSONArray json = new JSONArray();
		json = MakeJSON.change(result);
		return json.toString();
	}
	
	
}
