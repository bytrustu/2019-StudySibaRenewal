package com.studysiba.service.board;

import java.util.List;

import com.studysiba.domain.board.FreeBoardVO;
import com.studysiba.domain.board.PageDTO;

public interface BoardService {

	// 자유게시판 글 작성
	String write(FreeBoardVO freeboardVO);
	// 자유게시판 글 뷰
	FreeBoardVO view(long no);
	// 자유게시판 전체 개시 글 수
	int getBoardCount();
	// 자유게시판 페이징 처리 리스트
	List<FreeBoardVO> getBoardList(PageDTO page);

}
