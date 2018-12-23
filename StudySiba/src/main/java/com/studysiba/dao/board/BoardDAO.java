package com.studysiba.dao.board;

import java.util.List;

import com.studysiba.domain.board.FreeBoardVO;
import com.studysiba.domain.board.PageDTO;

public interface BoardDAO {

	// 자유게시판 글 작성
	int write(FreeBoardVO freeboardVO);
	// 자유게시판 답변 글 작성
	int reWrite(FreeBoardVO freeboardVO);
	// 답변글 작성시 먼저 작성 된 글인 경우 , 모두 step+1
	void replyShape(FreeBoardVO freeboardVO);
	// 자유게시판 글 뷰
	FreeBoardVO view(long no);
	// 자유게시판 전체 게시글 수
	int getBoardCount();
	// 자유게시판 페이징 처리 리스트 뷰
	List<FreeBoardVO> getBoardList(PageDTO page);


}
