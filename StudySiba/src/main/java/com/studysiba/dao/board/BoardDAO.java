package com.studysiba.dao.board;

import java.util.HashMap;
import java.util.List;

import com.studysiba.domain.board.CommentVO;
import com.studysiba.domain.board.FreeBoardVO;
import com.studysiba.domain.board.LikeVO;
import com.studysiba.domain.board.PageDTO;
import com.studysiba.domain.board.SearchVO;

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
	// 댓글 작성
	String writeComment(CommentVO commentVO);
	// 댓글 조회
	List<CommentVO> getCommentList(int no);
	// 대댓글 작성시 먼저 작성 된 댓글 인 경우 , 모두 step+1
	void commentShape(CommentVO commentVO);
	// 대댓글 작성
	int reWriteComment(CommentVO commentVO);
	// 댓글 카운트 조회
	int getCommentCount(int no);
	// 자유게시판 글 수정
	String modify(FreeBoardVO freeboardVO);
	// 자유게시판 글 삭제
	int delete(FreeBoardVO freeboardVO);
	// 검색한 게시글 숫자 카운트
	int getSearchCount(SearchVO searchVO);
	// 검색한 게시글 리스트
	List<FreeBoardVO> getSearchList(PageDTO page);
	


}
