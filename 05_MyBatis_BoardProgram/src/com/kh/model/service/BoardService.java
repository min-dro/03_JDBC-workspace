package com.kh.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.Template;
import com.kh.model.dao.BoardDao;
import com.kh.model.vo.Board;

public class BoardService {

	public ArrayList<Board> selectAll() {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Board> list = new BoardDao().selectAll(sqlSession);
		
		sqlSession.close();
		
		return list;
	}

	public int insertBoard(Board b) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = new BoardDao().insertBoard(sqlSession, b);
		
		if(result > 0) {
			
			sqlSession.commit();
		
		} else {
		
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}

	public ArrayList<Board> selectByBoardTitle(String keyword) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Board> list = new BoardDao().selectByBoardTitle(sqlSession, keyword);
		
		sqlSession.close();
		
		return list;
	}

	public int updateBoard(Board b) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = new BoardDao().updateBoard(sqlSession, b);
		
		if(result > 0) {
			
			sqlSession.commit();
		
		} else {
		
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}

	public int deleteBoard(int boardNo) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = new BoardDao().deleteBoard(sqlSession, boardNo);
		
		if(result > 0) {
		
			sqlSession.commit();
		
		} else {
		
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}

}
