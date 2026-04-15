package com.kh.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.kh.model.vo.Board;

public class BoardDao {

	public ArrayList<Board> selectAll(SqlSession sqlSession) {
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectAll");
	}

	public int insertBoard(SqlSession sqlSession, Board b) {
	
		return sqlSession.insert("boardMapper.insertBoard", b);
	}

	public ArrayList<Board> selectByBoardTitle(SqlSession sqlSession, String keyword) {
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectByBoardTitle", keyword);
	}

	public int updateBoard(SqlSession sqlSession, Board b) {
		
		return sqlSession.update("boardMapper.updateBoard", b);
	}

	public int deleteBoard(SqlSession sqlSession, int boardNo) {
		
		return sqlSession.delete("boardMapper.deleteBoard", boardNo);
	}

}
