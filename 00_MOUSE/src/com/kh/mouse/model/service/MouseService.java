package com.kh.mouse.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.mouse.common.JDBCTemplate;
import com.kh.mouse.model.dao.MouseDao;
import com.kh.mouse.model.vo.Mouse;

public class MouseService {

	/**
	 * 마우스 신상품 조회 기능 (트랜잭션) 을 담당하는 메소드
	 * @return => 6개월 이내에 출시된 마우스 정보
	 */
	public ArrayList<Mouse> selectNewMouse() {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 객체를 넘기면서 DAO 메소드 호출 후 결과 받기
		ArrayList<Mouse> list = new MouseDao().selectNewMouse(conn);
		
		// 3) 트랜잭션 처리
		// > select 문 이므로 패스
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return list;
		
	} // selectNewMouse 끝
	
	/**
	 * 마우스 전체 상품 조회 기능 (트랜잭션) 을 담당하는 메소드
	 * @return => 전체 조회된 마우스 정보
	 */
	public ArrayList<Mouse> selectAll() {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 객체를 넘기면서 DAO 메소드 호출 후 결과 받기
		ArrayList<Mouse> list = new MouseDao().selectAll(conn);
		
		// 3) 트랜잭션 처리
		// > select 문 이므로 패스
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return list;
		
	} // selectAll 끝
	
	/**키워드검색(트랜잭션) 을 담당하는 메소드
	 * DB 접속관리, 자료교환을 담당합니다.
	 * @param keyword => 검색어
	 * @return => 검색된물품정보
	 */
	public ArrayList<Mouse> selectByKeyword(String keyword) {
		
		//DB접속
		Connection conn = JDBCTemplate.getConnection();
		
		//Dao 와 자료교환
		ArrayList<Mouse> list = new MouseDao().selectByKeyword(conn, keyword);
		
		//JDBC 자원 반납
		JDBCTemplate.close(conn);
		
		//컨트롤러로 결과 전달
		return list;
		
	}//selectByKeyword끝

	
	
	/**새상품등록(트랜잭션) 을 담당하는 메소드
	 * DB와의 통로를 여닫고, 자료의 통로역할, 결과값에따른 트랜잭션 처리를 담당합니다.
	 * @param m
	 * @param formattedDate
	 * @return
	 */
	public int insertMouse(Mouse m) {

		//DB접속
		Connection conn = JDBCTemplate.getConnection();
		
		//자료전달
		int result = new MouseDao().insertMouse(conn, m);	
		
		//트랜잭션처리
		if(result > 0) { //--결과 성공
			JDBCTemplate.commit(conn);
		}else {          //--결과 실패
			JDBCTemplate.rollback(conn);
		}
		
		//JDBC 자원 반납
		JDBCTemplate.close(conn);
		
		//컨트롤러로 결과 전달
		return result;
		
	}//insertMouse끝
	
	/**
	 * 제품 정보 수정 기능 (트랜잭션) 을 담당하는 메소드
	 * @param m => 전반적인 제품 정보 변경과 관련된 데이터
	 * @return => update 된 행의 갯수
	 */
	public int updateMouse(Mouse m) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 객체와 전달값을 넘기면서 DAO 메소드 호출 후 결과 받기
		int result = new MouseDao().updateMouse(conn, m);
		
		// 3) 트랜잭션 처리
		if(result > 0) {
			// > commit
			
			JDBCTemplate.commit(conn);
			
		} else {
			// > rollback
			
			JDBCTemplate.rollback(conn);
		}
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return result;
		
	} // updateMouse 끝
	
	/**
	 * 제품 삭제 기능 (트랜잭션) 을 담당하는 메소드
	 * @param name => 삭제할 제품의 이름
	 * @return => delete 된 행의 갯수
	 */
	public int deleteMouse(String name) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서 DAO 의 메소드 호출 후 결과 받기
		int result = new MouseDao().deleteMouse(conn, name);
		
		// 3) 트랜잭션 처리
		if(result > 0) { 
			// > commit
			
			JDBCTemplate.commit(conn);
			
		} else {
			// > rollback
			
			JDBCTemplate.rollback(conn);
		}
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return result;
		
	} // deleteMouse 끝
	
}