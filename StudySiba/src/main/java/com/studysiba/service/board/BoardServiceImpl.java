package com.studysiba.service.board;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studysiba.dao.board.BoardDAO;
import com.studysiba.dao.member.MemberDAO;
import com.studysiba.domain.board.CommentVO;
import com.studysiba.domain.board.FreeBoardVO;
import com.studysiba.domain.board.LikeVO;
import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.common.SearchVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private MemberDAO memberDAO;

	// 닉네임 조회
	public String getNick(String id) {
		String nick = memberDAO.getUserNick(id);
		return nick;
	}
	
	public String getProFile(String id) {
		String proFile = memberDAO.getUserImage(id);
		return proFile;
	}
	
	@Override
	public String write(FreeBoardVO freeboardVO) {
		String result = null;
		if ( freeboardVO.getgNo() == 0) {
			result = Integer.toString(boardDAO.write(freeboardVO));
		} else {
			boardDAO.replyShape(freeboardVO);
			result = Integer.toString(boardDAO.reWrite(freeboardVO));
		}
		return result;
	}

	@Override
	public FreeBoardVO view(long no) {
		FreeBoardVO view = new FreeBoardVO();
		boardDAO.increaseCount(no);
		view = boardDAO.view(no);
		String nick = getNick(view.getId());
		String proFile = getProFile(view.getId());
		view.setNick(nick);
		view.setProFile(proFile);
		return view;
	}

	@Override
	public int getBoardCount() {
		return boardDAO.getBoardCount();
	}

	@Override
	public List<FreeBoardVO> getBoardList(PageDTO page) {
		int startRow = (page.getPageNum()-1)*page.getPageSize()+1;
		page.setStartRow(startRow-1);
		List<FreeBoardVO> list = boardDAO.getBoardList(page);
		
		for ( int i=0; i<list.size(); i++ ) {
			list.get(i).setNick(getNick(list.get(i).getId()));
			list.get(i).setProFile(getProFile(list.get(i).getId()));
		}
		return list;
	}

	@Override
	public String likeFunc(LikeVO likeVO) {
		String result = null;
		if ( likeVO.getType().equals("unlike") ) {
			likeVO.setType("freeboard");
			result = boardDAO.addLike(likeVO);
		} else {
			likeVO.setType("freeboard");
			result = boardDAO.deleteLike(likeVO);
		}
		return result;
	}

	@Override
	public String getLike(int no) {
		return boardDAO.getLike(no);
	}

	@Override
	public HashMap<String, Object> getLikeInfo(LikeVO likeVO) {
		
		HashMap<String, Object> like = new HashMap<String,Object>();
		
		String id = null;
		id = boardDAO.getLikeId(likeVO);
		if ( id == null ) {
			like.put("check", "false");
		} else {
			like.put("check", "true");
		}
		like.put("count", boardDAO.getLike((int)likeVO.getfNo()));
		return like;
	}

	@Override
	public String writeComment(CommentVO commentVO) {
		return boardDAO.writeComment(commentVO);
	}

	@Override
	public List<CommentVO> getCommentList(int no) {
		return boardDAO.getCommentList(no);
	}

	@Override
	public String reWriteComment(CommentVO commentVO) {
		boardDAO.commentShape(commentVO);
		String result = Integer.toString(boardDAO.reWriteComment(commentVO));
		return result;
	}

	@Override
	public int getCommentCount(int no) {
		return boardDAO.getCommentCount(no);
	}

	@Override
	public String modify(FreeBoardVO freeboardVO) {
		return boardDAO.modify(freeboardVO);
	}

	@Override
	public String delete(FreeBoardVO freeboardVO) {
		return Integer.toString(boardDAO.delete(freeboardVO));
	}

	@Override
	public int getSearchCount(SearchVO searchVO) {
		if( searchVO.getSearchType().equals("id") ) {
			searchVO.setSearchText(memberDAO.getUserId(searchVO.getSearchText()));
		}
		return boardDAO.getSearchCount(searchVO);
	}

	@Override
	public List<FreeBoardVO> getSearchList(PageDTO page) {
		if ( page.getSearchType().equals("id")) {
			page.setSearchText(memberDAO.getUserId(page.getSearchText()));
		}
		int startRow = (page.getPageNum()-1)*page.getPageSize()+1;
		page.setStartRow(startRow-1);
		List<FreeBoardVO> list = boardDAO.getSearchList(page);
		
		for ( int i=0; i<list.size(); i++ ) {
			list.get(i).setNick(getNick(list.get(i).getId()));
			list.get(i).setProFile(getProFile(list.get(i).getId()));
		}
		
		return list;
	}
	
	
	
	
}
