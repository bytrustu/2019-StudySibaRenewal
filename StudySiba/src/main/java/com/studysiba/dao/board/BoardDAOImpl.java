package com.studysiba.dao.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studysiba.domain.board.CommentVO;
import com.studysiba.domain.board.FreeBoardVO;
import com.studysiba.domain.board.LikeVO;
import com.studysiba.domain.board.PageDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace="com.studysiba.mapper.BoardMapper";
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	@Override
	public int write(FreeBoardVO freeboardVO) {
		return sqlSession.insert(namespace+".write",freeboardVO);
	}
	
	@Override
	public int reWrite(FreeBoardVO freeboardVO) {
		return sqlSession.insert(namespace+".reWrite",freeboardVO);
	}

	@Override
	public FreeBoardVO view(long no) {
		return sqlSession.selectOne(namespace+".view",no);
	}

	@Override
	public void replyShape(FreeBoardVO freeboardVO) {
		sqlSession.update(namespace+".replyShape",freeboardVO);
	}

	@Override
	public int getBoardCount() {
		return sqlSession.selectOne(namespace+".getBoardCount");
	}

	@Override
	public List<FreeBoardVO> getBoardList(PageDTO page) {
		return sqlSession.selectList(namespace+".getBoardList",page);
	}

	@Override
	public void increaseCount(long no) {
		sqlSession.update(namespace+".increaseCount",no);
	}

	@Override
	public String addLike(LikeVO likeVO) {
		return Integer.toString(sqlSession.insert(namespace+".addLike",likeVO));
	}

	@Override
	public String deleteLike(LikeVO likeVO) {
		return Integer.toString(sqlSession.delete(namespace+".deleteLike",likeVO));
	}

	@Override
	public String getLike(int no) {
		return sqlSession.selectOne(namespace+".getLike",no);
	}

	@Override
	public String getLikeId(LikeVO likeVO) {
		return sqlSession.selectOne(namespace+".getLikeId",likeVO);
	}

	@Override
	public String writeComment(CommentVO commentVO) {
		return Integer.toString(sqlSession.insert(namespace+".writeComment", commentVO));
	}

	@Override
	public List<CommentVO> getCommentList(int no) {
		return sqlSession.selectList(namespace+".getCommentList", no);
	}

	@Override
	public void commentShape(CommentVO commentVO) {
		sqlSession.update(namespace+".commentShape", commentVO);
	}

	@Override
	public int reWriteComment(CommentVO commentVO) {
		return sqlSession.insert(namespace+".reWriteComment", commentVO);
	}

	@Override
	public int getCommentCount(int no) {
		return sqlSession.selectOne(namespace+".getCommentCount", no);
	}

	@Override
	public String modify(FreeBoardVO freeboardVO) {
		return Integer.toString(sqlSession.update(namespace+".modify", freeboardVO));
	}

	@Override
	public int delete(FreeBoardVO freeboardVO) {
		return sqlSession.update(namespace+".delete", freeboardVO);
	}

	

}
