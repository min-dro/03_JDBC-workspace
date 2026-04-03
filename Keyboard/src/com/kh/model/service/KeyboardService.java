package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.KeyboardDao;
import com.kh.model.vo.Keyboard;

public class KeyboardService {

	public int insertKeyboard(Keyboard k) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new KeyboardDao().insertKeyboard(conn, k);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	} // insertKeyboard 끝

	public ArrayList<Keyboard> selectAll() {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Keyboard> list = new KeyboardDao().selectAll(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	} // selectAll 끝

	public ArrayList<Keyboard> selectByModelName(String keyword) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Keyboard> list = new KeyboardDao().selectByModelName(conn, keyword);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public int updateKeyboard(Keyboard k) {
		Connection conn = JDBCTemplate.getConnection();
		
		 int result = new KeyboardDao().updateKeyboard(conn, k);
		 
		 if(result > 0) {
			 JDBCTemplate.commit(conn);
		 } else {
			 JDBCTemplate.rollback(conn);
		 }
		 
		 JDBCTemplate.close(conn);
		 
		return result;
	}

	public int deleteKeyboard(String modelName) {
		 
		Connection conn = JDBCTemplate.getConnection();
		 
		 int result = new KeyboardDao().deleteKeyboard(conn, modelName);
		 
		 if(result > 0) {
			 JDBCTemplate.commit(conn);
		 } else {
			 JDBCTemplate.rollback(conn);
		 }
		 
		 JDBCTemplate.close(conn);
		 
		 return result;
	}

}
