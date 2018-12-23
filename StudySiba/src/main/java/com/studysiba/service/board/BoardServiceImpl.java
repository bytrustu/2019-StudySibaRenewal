package com.studysiba.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studysiba.dao.board.BoardDAO;
import com.studysiba.dao.member.MemberDAO;
import com.studysiba.domain.board.FreeBoardVO;
import com.studysiba.domain.board.PageDTO;

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
		System.out.println("여기야여기"+freeboardVO.toString());
		System.out.println(freeboardVO.getgNo());
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
		view = boardDAO.view(no);
		String nick = getNick(view.getId());
		String proFile = getProFile(view.getId());
		view.setNick(nick);
		view.setProFile(proFile);
		System.out.println(view.toString());
		return view;
	}

	@Override
	public int getBoardCount() {
		return boardDAO.getBoardCount();
	}

	@Override
	public List<FreeBoardVO> getBoardList(PageDTO page) {
		List<FreeBoardVO> list = boardDAO.getBoardList(page);
		
		for ( int i=0; i<list.size(); i++ ) {
			list.get(i).setNick(getNick(list.get(i).getId()));
			list.get(i).setProFile(getProFile(list.get(i).getId()));
		}
		return list;
	}
	
	
	
	
}
