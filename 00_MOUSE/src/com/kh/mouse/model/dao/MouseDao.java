package com.kh.mouse.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.mouse.common.JDBCTemplate;
import com.kh.mouse.model.vo.Mouse;

public class MouseDao {

	private Properties prop = new Properties(); 
		
	
	public MouseDao() { 
			
		try {
			
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		
	// 메소드부
	
	/**
	 * 마우스 신상품 조회 요청 시
	 * MOUSE 테이블에 SELECT 문을 실행시켜주는 메소드
	 * @param conn => 이미 DB 접속 정보가 담겨있는 객체 (이미 접속됨)
	 * @return => 조회된 회원들의 정보가 담겨있는 리스트
	 */
	public ArrayList<Mouse> selectNewMouse(Connection conn) {
		// select 문 => ResultSet (여러행 조회) => ArrayList<Mouse>
		
		// 실행할 SQL 문 (세미콜론 X)
		String sql = prop.getProperty("selectNewMouse");
		
		// 0) 필요한 변수들 먼저 셋팅
		ArrayList<Mouse> list = new ArrayList<>(); // 최종 결과를 담을 변수
		
		PreparedStatement pstmt = null; // SQL 문 실행 후 결과를 받을 변수
		ResultSet rset = null; // select 문 실행 후 결과를 받을 변수
		
		try {
			
			// 1_1) SQL 문을 넘기면서 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 1_2) 미완성된 SQL 문 완성시키기
			
			// 2, 3) SQL 문 실행 후 결과 받기
			// - 실행할 쿼리문 : select
			// - 호출할 메소드 : executeQuery
			rset = pstmt.executeQuery();
			
			// 4) ResultSet 에 담긴 조회 결과를 VO 객체로 옮겨 담기
			while(rset.next()) {
				
				Mouse m = new Mouse();
				
				m.setMouseNo(rset.getInt("MOUSE_NO"));
				m.setName(rset.getString("NAME"));
				m.setBrand(rset.getString("BRAND"));
				m.setStock(rset.getInt("STOCK"));
				m.setPrice(rset.getInt("PRICE"));
				m.setConnectType(rset.getString("CONNECT_TYPE"));
				m.setWeight(rset.getInt("WEIGHT"));
				m.setDpi(rset.getInt("DPI"));
				m.setReleaseDate(rset.getDate("RELEASE_DATE"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	
			// 5) 다 쓴 JDBC 용 자원 반납 (역순) - conn 제외
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// 6) Service 로 결과 반환
		return list;
		
	} // selectNewMouse 끝
	
	/**
	 * 마우스 전체 상품 조회 요청 시
	 * MOUSE 테이블에 SELECT 문을 실행시켜주는 메소드
	 * @param conn => 이미 DB 접속 정보가 담겨있는 객체 (이미 접속됨)
	 * @return => 조회된 회원들의 정보가 담겨있는 리스트
	 */
	public ArrayList<Mouse> selectAll(Connection conn) {
		// select 문 => ResultSet (여러행 조회)=> ArrayList<Mouse>
		
		// 실행할 SQL 문 (세미콜론 X)
		String sql = prop.getProperty("selectAll");
		// 0) 필요한 변수들 먼저 셋팅
		
		ArrayList<Mouse> list = new ArrayList<>(); // 최종 결과를 담을 변수
		
		PreparedStatement pstmt = null; // SQL 문 실행 후 결과를 받을 변수
		ResultSet rset = null; // select 문 실행 후 결과를 받을 변수
		
		try {
			
			// 1_1) SQL 문을 넘기면서 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 1_2) 미완성된 SQL 문 완성시키기
			
			// 2, 3) SQL 문 실행 후 결과 받기
			// - 실행할 쿼리문 : select
			// - 호출할 메소드 : executeQuery
			rset = pstmt.executeQuery();
			
			// 4) ResultSet 에 담긴 조회 결과를 VO 객체로 옮겨 담기
			while(rset.next()) {
				
				list.add(new Mouse(rset.getInt("MOUSE_NO"),
								   rset.getString("NAME"),
								   rset.getString("BRAND"),
								   rset.getInt("STOCK"),
								   rset.getInt("PRICE"),
								   rset.getString("CONNECT_TYPE"),
								   rset.getInt("WEIGHT"),
								   rset.getInt("DPI"), 
								   rset.getDate("RELEASE_DATE")));		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	
			// 5) 다 쓴 JDBC 용 자원 반납 (역순) - conn 제외
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// 6) Service 로 결과 반환
		return list;
		
	} // selectAll 끝
	
	/**키워드로 검색시
	 * MOUSE 테이블에 SELECT 문을 실행시켜주는 메소드
	 * @param conn => DB 접속정보
	 * @param keyword => 검색어
	 * @return => 검색결과(마우스정보)
	 */
	public ArrayList<Mouse> selectByKeyword(Connection conn, String keyword) {
		
		//사용할 SQL문 호출
		String sql = prop.getProperty("selectByKeyword");
		//지역변수셋팅(지역변수이슈해결)
		ArrayList<Mouse> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			//PreparedStatement 객체 생성 및 sql 전달
			pstmt = conn.prepareStatement(sql);
			//sql 문 완성
			pstmt.setString(1, "%"+keyword+"%");
			
			//DB와 통신
			rset = pstmt.executeQuery();
			

			while(rset.next()) {
				//결과물 가공하기			
				Mouse m = new Mouse(
						rset.getInt("MOUSE_NO"),
						rset.getString("NAME"),
						rset.getString("BRAND"),
						rset.getInt("STOCK"),
						rset.getInt("PRICE"),
						rset.getString("CONNECT_TYPE"),
						rset.getInt("WEIGHT"),
						rset.getInt("DPI"),
						rset.getDate("RELEASE_DATE")
						);
				//가공한 자료를 list 에 저장
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// JDBC 자원 반납 
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		//서비스로 결과 전달
		return list;
	} // selectByKeyword끝
	
	/**새상품등록 시 
	 * MOUSE 테이블에 INSERT 문을 실행시켜주는 메소드
	 * SQL문을 완성시켜 DB에 전달하며 결과를 받아 전달합니다.
	 * @param conn => 접속정보
	 * @param m => 상품정보
	 * @param formattedDate => 상품정보(출시일)
	 * @return => 입력결과
	 */
	public int insertMouse(Connection conn, Mouse m) {
		
		//사용할 SQL문 호출
		String sql = prop.getProperty("insertMouse");
		
		//지역변수셋팅(지역변수이슈해결)
		int result = 0;	
		PreparedStatement pstmt = null;
		
		
		try {
			//PreparedStatement 객체 생성 및 sql 전달
			pstmt = conn.prepareStatement(sql);
			//sql 문 완성
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getBrand());
			pstmt.setInt(	3, m.getStock());
			pstmt.setInt(	4, m.getPrice());
			pstmt.setString(5, m.getConnectType());
			pstmt.setInt(	6, m.getWeight());
			pstmt.setInt(	7, m.getDpi());
			pstmt.setDate(8, m.getReleaseDate());
			
			//DB와 통신
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// JDBC 자원 반납
			JDBCTemplate.close(pstmt);
		}
		
		//서비스로 결과 전달
		return result;
		
	} // insertMouse끝
	
	/**
	 * 제품 정보 수정 요청 시
	 * MOUSE 테이블에 UPDATE 문을 실행시켜주는 메소드
	 * @param conn => 이미 DB 접속 정보가 담겨있는 객체 (이미 접속됨)
	 * @param m => 전반적인 수정할 내용들이 들어있는 객체 (누구의, 어떻게)
	 * @return => 수정된 행의 갯수
	 */
	public int updateMouse(Connection conn, Mouse m) {
		 // update 문 => int (처리된 행의 갯수) => 트랜잭션 처리
		
		// 실행할 SQL 문 (미완성된 형태로, 세미콜론 X)
		String sql = prop.getProperty("updateMouse");  
		
		// 0) 필요한 변수들 먼저 셋팅
		int result = 0; // 최종 결과를 담을 변수
		
		PreparedStatement pstmt = null; // SQL 문 실행 후 결과를 받을 변수
		
		try {
			
			// 1_1) SQL 문을 넘기면서 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 1_2) 미완성된 SQL 문 완성시키기
			pstmt.setString(1, m.getBrand());
			pstmt.setInt(2, m.getStock());
			pstmt.setInt(3, m.getPrice());
			pstmt.setString(4, m.getConnectType());
			pstmt.setInt(5, m.getWeight());
			pstmt.setString(6, m.getName());
			
			// 2, 3) SQL 문 실행 후 결과 받기
			// - 실행할 쿼리문 : update
			// - 호출할 메소드 : executeUpdate
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	
			// 4) 다 쓴 JDBC 용 자원 반납 (역순) - conn 제외
			JDBCTemplate.close(pstmt);
		}
		
		// 5) Service 로 결과 반환
		return result; // 성공 시 1, 실패 시 0
		
	} // updateMouse 끝

	/**
	 * 제품 삭제 요청 시
	 * MOUSE 테이블에 DELETE 문을 실행시켜주는 메소드
	 * @param conn => 이미 DB 접속 정보가 담긴 객체 (이미 접속됨)
	 * @param name => 삭제할 제품의 이름
	 * @return => 삭제된 행의 갯수
	 */
	public int deleteMouse(Connection conn, String name) {
		// delete 문 => int (처리된 행의 갯수) => 트랜잭션 처리
		
		// 실행할 SQL 문 (미완성된 형태로, 세미콜론 X)
		String sql = prop.getProperty("deleteMouse");
		
		// 0) 필요한 변수들 먼저 셋팅
		int result = 0; // 최종 결과를 담을 변수
		
		PreparedStatement pstmt = null; // SQL 문 실행 후 결과를 받을 변수
		
		try {
	
			// 1_1) SQL 문을 넘기면서 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 1_2) 미완성된 SQL 문 완성시키기
			pstmt.setString(1, name);
			
			// 2, 3) SQL 문 실행 후 결과 받기
			// - 실행할 쿼리문 : delete
			// - 호출할 메소드 : executeUpdate
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	
			// 4) 다 쓴 JDBC 용 자원 반납 (역순) - conn 제외
			JDBCTemplate.close(pstmt);
		}
		
		// 5) Service 로 결과 반환
		return result; // 성공 시 1, 실패 시 0
	}

} // deleteMouse 끝

