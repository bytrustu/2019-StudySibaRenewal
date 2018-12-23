package com.studysiba.dao.board;

import java.util.List;

import com.studysiba.domain.board.FreeBoardVO;
import com.studysiba.domain.board.LikeVO;
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
	// 게시글 조회수 증가
	void increaseCount(long no);
	// 좋아요 추가
	String addLike(LikeVO likeVO);
	// 좋아요 삭제
	String deleteLike(LikeVO likeVO);
	// 좋아요 개수 확인
	String getLike(int no);
	// 좋아요 아이디 조회
	String getLikeId(LikeVO likeVO);


}
