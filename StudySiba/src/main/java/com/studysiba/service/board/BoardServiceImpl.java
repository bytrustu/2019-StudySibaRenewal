package com.studysiba.service.board;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studysiba.common.makeJSON;
import com.studysiba.dao.board.BoardDAO;
import com.studysiba.domain.board.FreeBoardVO;

@Repository
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public String write(FreeBoardVO freeboardVO) {
		String result = Integer.toString(boardDAO.write(freeboardVO));
		return result;
	}

}
