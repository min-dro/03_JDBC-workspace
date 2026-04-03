package com.kh.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.member.model.vo.Member;

// * DAO (Data Access Object)
// > Controller 를 통해서 호출된 기능을 수행하기 위해
//   "데이터가 담겨있는 외부매체" 인 DB 에 "직접적으로 접근" 한 후
//   필요한 SQL 문을 실행하고 그에 따른 결과를 받아주는 부분
//   (즉, 데이터 입출력 과정을 진행, JDBC 과정 진행)

/*
 * * JDBC 과정에 필요한 객체들
 * 
 * - Connection : DB 의 연결정보를 담으면서 생성 (주소, 계정명, 비번)
 * 				  Connection 객체가 생성되는 순간 해당 DB 와의 연결이 이루어짐 (즉, 통로가 열림)
 * - (Prepared)Statement : 해당 연결된 DB 에 SQL문을 전달하고 실행한 후 결과를 받아내는 객체 (즉, 볼일을 보는 용도)
 * - ResultSet : 만일 실행한 SQL문이 SELECT 문일 경우 그 조회된 결과물들이 담겨오는 객체
 * 
 * * IO 처리 순서
 * 1) 통로 열기
 * 2) 볼일 보기
 * 3) 통로 닫기
 * 
 * * JDBC 처리 순서
 * 1) JDBC Driver 등록
 * : 해당 DBMS 가 제공하고 있는 클래스 등록
 * 
 * 2) Connection 객체 생성 및 트랜잭션 관련 설정
 * : 접속할 DB 의 정보를 제시하면서 DB 에 접속하면서 Connection 객체가 생성
 *   (Connection 객체 생성 == DB 에 접속)
 *   INSERT, UPDATE, DELETE 문을 실행할 경우 트랜잭션 처리에 대한 설정도 함께 진행
 *   (개발자인 내가 직접 트랜잭션 처리를 하겠다. 자동 커밋 해제)
 * 
 * 3) Statement 객체 생성
 * : Connection 객체를 이용해서 생성
 * 
 * 4) SQL 문을 전달하면서 실행
 * : Statement 객체를 이용해서 SQL문을 실행시킴
 *   - SELECT 문 : executeQuery 메소드를 이용해서 실행
 *   - INSERT, UPDATE, DELETE 문 : executeUpdate 메소드를 이용해서 실행
 * 
 * 5) 결과 받기
 *   - SELECT 문 : ResultSet 객체 (조회된 데이터들이 차곡차곡 담겨있음) 로 받기 => 6_1)
 *   - INSERT, UPDATE, DELETE 문 : int (추가, 수정, 삭제된 행의 갯수) 로 받기 => 6_2)
 *   
 * 6_1) ResultSet 에 담겨있는 조회된 데이터들을 하나씩 뽑아서 VO 객체에 옮겨 담기
 * 
 * 6_2) 트랜잭션 처리 (성공 시 COMMIT, 실패 시 ROLLBACK)
 * 
 * 7) 다 쓴 JDBC 용 자원들 반납 (close)
 * : 단, 생성 순서의 역순으로 반납하기
 *   (자원 반납의 대상 : 위에서 적어놨었던 JDBC 용 객체들 모두 다)
 *   
 * 8) Controller 로 결과 반환
 *   - SELECT 문 : 6_1) 에서 만들어진 결과
 *   - INSERT, UPDATE, DELETE 문 : int (처리된 행 갯수)
 *     
 * ** Statement 특징 : 항상 "완성된" SQL문을 실행!!
 */
public class MemberDao { // 클래스 영역 시작

	/**
	 * 사용자가 회원 정보 추가 요청 시 입력했던 값들을 가지고
	 * MEMBER 테이블이 INSERT 문을 실행하는 메소드
	 * @param m => 사용자가 입력했던 아이디 ~ 주소 까지의 값들이 담겨있는 Member 객체
	 * @return => INSERT 된 행의 갯수 (1개 행이(가) 삽입되었습니다.)
	 */
	public int insertMember(Member m) {
		// insert 문 => 처리된 행의 갯수 ->  트랜잭션 처리
		
		// 실행할 SQL문 (단, 완성된 형태로 만들 것 / 끝에 ; (세미콜론) 이 있으면 안됨!!)
		/*
		INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL
                , 'XXX'
                , 'XXXXXX'
                , 'XXX'
                , 'X'
                , XX
                , 'XXXXXXX'
                , 'XXXXXXX'
                , 'XXXXXXXXXX'
                , DEFAULT)
		*/
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL"
										 + ", '" + m.getUserId() + "'"
										 + ", '" + m.getUserPwd() + "'"
										 + ", '" + m.getUserName() + "'"
										 + ", '" + m.getGender() + "'"
										 + ", " + m.getAge()
										 + ", '" + m.getEmail() + "'"
										 + ", '" + m.getPhone() + "'"
										 + ", '" + m.getAddress() + "'"
										 + ", DEFAULT)";
		
		// 쿼리문이 제대로 만들어졌는지 확인
		// System.out.println(sql);
		
		// 0) 필요한 변수들을 먼저 셋팅 (지역변수 이슈 해결용)
		int result = 0; // 처리된 행의 갯수를 담을 변수
		Connection conn = null; // 접속할 DB 의 정보를 담을 변수
		Statement stmt = null; // SQL 문을 전달 후 실행해줄 변수
		
		try {
			// 1) JDBC Driver 등록
			// [ 표현법 ]
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			// > ojdbc11.jar 파일에서 제공하는 oracle.jdbc.driver 패키지의
			//   OracleDriver 클래스를 등록해서 가져다 쓰겠다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// > 풀클래스명에 오타가 있거나, ojdbc11.jar 파일 연동이 안되어있다면
			//   ClassNotFoundException 오류가 발생한다!!
		
			// 2) Connection 객체 생성 및 트랜잭션 설정
			// > DB 접속과 관련된 정보들을 넘기면서 Connection 객체 생성
			// [ 표현법 ]
			// Connection conn = DriverManager.getConnection(url주소, 계정명, 비번);
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC") ;
			// > Connection 객체가 생성되는 순간 해당 DB 에 접속됨
			
			// 트랜잭션 관련 설정
			// > Connection 객체에서는 기본 설정이 "자동 커밋" 임!!
			//   DML 문 실행 후 개발자인 내가 직접 커밋 또는 롤백 해 주는 것이 가장 좋음!!
			// > 이 자동 커밋 설정을 풀어줄것임!!
			conn.setAutoCommit(false);
			
			// 이 시점 부터는 DB 에 접속이 된 것!! (즉, 통로가 열린 것)
			
			// 3) Statement 객체 생성
			// > 2 단계에서 생성했던 Connection 객체를 통해서 생성해야함!!
			// [ 표현법 ]
			// Statement stmt = conn.createStatement();
			stmt = conn.createStatement();
			
			// 이 시점 부터 쿼리문을 날려서 실행될 수 있음!! (즉, 볼일을 볼 수 있는 것)
			
			// 4, 5)DB에 완성된 SQL 문을 전달하면서 실행 후 결과 받기
			// - 실행할 SQL 문 종류 : INSERT
			// - 호출해야할 메소드명 : executeUpdate
			// [ 표현법 ]
			// int result = stmt.executeUpdate(sql문);
			// Result rset = stmt.executeQuery(sql문);
			
			result = stmt.executeUpdate(sql);
			// > insert 가 제대로 되었다면 result 에 1 이 담겨올것임!!
			//   insert 가 제대로 되지 않았다면 result 에 0 이 담겨올것임!!
			
			// 6_2) 트랜잭션 처리
			// > 성공이면 commit, 실패면 rollback
			if(result > 0) {
				// > 성공했을 경우 (commit)
				
				conn.commit();
			
			} else { 
				// > 실패했을 경우 (rollback)
			
				conn.rollback();
			
			}
			
			// 7) 다 쓴 JDBC 용 자원 반납
			// > 자원 반납은 반드시 해줘야 함!! (finally 블록)
			//   생성 순서의 역순으로!!
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				// 7) 다 쓴 JDBC 용 자원 반납
				// > 위에서 Connection 과 Statement 객체만 사용했었음!!
				stmt.close();
				conn.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 8) Controller 로 결과 반환
		return result; // 처리된 행의 갯수
		
	} // insertMember 끝

	/**
	 * 사용자가 회원 전체 정보 조회 요청 시
	 * MEMBER 테이블에 SELECT * 구문을 실행하는 메소드
	 * @return => 조회된 회원들의 정보들이 담겨있는 리스트
	 */
	public ArrayList<Member> selectAll() {
		// SELECT 문 => ResultSet 객체 (여러행 조회) => ArrayList<Member>
		
		// 실행할 SQL 문 (완성된 형태로, 세미콜론 X)
		String sql = "SELECT * "
				   + "FROM MEMBER";
		
		// 0) 변수 선언 및 초기화 (지역변수 이슈 해결용)
		ArrayList<Member> list = new ArrayList<>(); // 최종 조회 결과를 담을 리스트
		Connection conn = null; // DB 접속 정보를 담을 변수
		Statement stmt = null; // SQL 문을 실행 후 결과를 받아내는 변수
		ResultSet rset = null; // 조회 결과를 담아오는 변수

		try {
			
			// 1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			// 2) Connection 객체 생성 및 트랜잭션 설정
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC") ;
			
			// SELECT 문의 경우 트랜잭션 관련 설정을 생략해도 된다!!
			// > SELECT 문은 트랜잭션의 관리 대상이 아니기 때문
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5) SQL 문을 전달해서 실행 후 결과 받기
			// - 실행할 SQL 문 : select
			// - 호출할 메소드명 : executeQuery
			rset = stmt.executeQuery(sql);
			
			// 6_1) ResultSet 의 결과를 하나씩 뽑아서 VO 객체에 옮겨담기
			// > ResultSet 또한 JDBC 용 자원이기 때문에 이따가 finally 에서 반납시켜줘야함!!
			//   자원 반납을 통해 조회된 데이터가 날라가기 전에 다른 변수에 옮기는 작업!!
			
			// * ResultSet 객체의 구조
			// > ResultSet 객체의 내부에는 "커서" 가 존재함!!
			//   커서 : 현재 행의 위치를 알려주는 화살표 같은 존재
			// > 조회를 하자마자 기준으로 커서는 항상 0번째 행을 가리키고 있음!!
			
			// 커서를 한 줄 (한 행) 단위로 아래로 내려서
			// 현재 커서가 가리키는 행에 데이터가 있나 없나 검사 후
			// 데이터가 있다면 각 컬럼의 값을 하나하나씩 뽑아서 VO 로 가공
			
			// 더이상 뽑을 데이터가 없을 때 까지만 반복 진행
			while(rset.next()) {
				// > rset.next() : 커서를 한 행 아래로 움직여주고
				//				   해당 행의 데이터가 존재할 경우 true / 없으면 false 반환
				
				// 이 while 문 안으로 들어왔다라는 것은
				// 현재 커서가 가리키는 행의 데이터가 존재한다 라는 것!!
				// > 그 행의 컬럼값들을 하나하나씩 다 뽑아서 Member 객체의 각 필드에 담아보자
				
				// MEMBER 테이블의 한 행 == Member VO 객체 한 개
				Member m = new Member();
				
				// ResultSet 객체로부터 어떤 컬럼에 해당하는 값을 뽑을건지 제시 (매개변수로)
				// > 그 값을 뽑아서 각 필드로 옮기기
				
				// > rset.getInt(매개변수) : int 형 값을 뽑아낼 때 
				//   rset.getString(매개변수) : String 형 값을 뽑아낼 때
				//   rset.gerDate(매개변수) : java.sql.Date 형 값을 뽑아낼 때
				//   rset.getDouble(매개변수) : double 형 값을 뽑아낼 때
				//   ...
				// > 각 자바의 타입별로 컬럼값들을 뽑아낼 수 있는 메소드들!!
				//   매개변수 : SELECT 절에 제시한 컬럼명 (대소문자를 가리지 않음)
				//   		   또는
				//			   SELECT 절에 제시한 별칭 (대소문자를 가지리 않음)
				//			   또는
				//			   컬럼의 순번
				//   가독성 측면에서 컬럼명이나 별칭을 대문자로 쓰는걸 권장함!!
				
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				// > 한 행에 대한 모든 데이터값을 하나의 Member 객체로 옮겨담는 과정 끝!!
			
				// 지역변수로써 m 이 소멸되기 직전에
				// 리스트에 해당 Member m 객체를 add 해둘 것임!!
				list.add(m);
			}
			
			// 이 시점 기준으로
			// list 에는 조회된 회원들의 정보가 차곡차곡 담겨있을 것!!
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		
			try {
				
				// 7) 다 쓴 JDBC 용 자원들 반납 (생성 순서의 역순으로)
				rset.close();
				stmt.close();
				conn.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8) Controller 로 결과 반환
		return list; // 조회 결과들이 모두 뽑혀서 담겨있는 리스트
		
	} // selectAll 끝

	/**
	 * 사용자가 아이디 검색 기능을 요청했을 때
	 * MEMBER 테이블에 SELECT 문을 실행하는 메소드
	 * @param userId => 검색할 회원의 아이디 (검색어)
	 * @return => 검색 (조회) 결과 회원 한명의 정보
	 */
	public Member selectByUserId(String userId) {
		// select 문 => ResultSet 객체 (단일행 조회) => Member 객체
		
		// 실행할 SQL 문 (완성된 형태로, 세미콜론 X)
		/*
		SELECT *
  		  FROM MEMBER
 		 WHERE USERID = 'XXX';
		*/
		String sql = "SELECT * "
				     + "FROM MEMBER "
				    + "WHERE USERID = '" + userId + "'";
		
		// 0) 변수 선언 및 초기화 (지역변수 이슈 해결)
		Member m = null; // 최종 조회된 결과를 담을 변수
		Connection conn = null; // 접속할 DB 의 정보를 담을 변수
		Statement stmt = null; // SQL 문 실행 후 결과를 받아낼 변수
		ResultSet rset = null; // 조회 결과를 담겨올 변수
		
		try {
			
			// 1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			// 2) Connection 객체 생성 및 트랜잭션 설정
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC") ;
			 
			// SELECT 문의 트랜잭션 관련 설정 패스
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5) SQL 문 실행 후 결과 받기
			// - 실행할 SQL 문 : SELECT
			// - 호출할 메소드명 : executeQuery
			rset = stmt.executeQuery(sql);
			
			// 6_1) 현재 조회 결과가 담긴 ResultSet 에서 한행씩 뽑아서 VO 로 옮겨담기
			// > 기존에 여러행 조회일 경우에는 정확히 몇개의 행이 조회될지 가늠이 안되기 때문에
			//   while(rset.next()) 을 이용했었음!!
			// > 단일행 조회일 경우에는 어차피 많이 조회되봤자 최대 1개의 행이 조회되기 때문에
			//   if(rset.next()) 을 이용해도 됨!!
			if(rset.next()) {
				
				// 조회된 데이터가 있다면 이 if 문 안으로 들어옴!!
				// > 조회된 한 행에 대한 데이터값들은 모두 뽑아서 하나의 Member 객체로 담기
				
				m = new Member(rset.getInt("USERNO")
								 , rset.getString("USERID")
								 , rset.getString("USERPWD")
								 , rset.getString("USERNAME")
								 , rset.getString("GENDER")
								 , rset.getInt("AGE")
								 , rset.getString("EMAIL")
								 , rset.getString("PHONE")
								 , rset.getString("ADDRESS")
								 , rset.getDate("ENROLLDATE"));
			}
			
			// 이 시점 기준으로 조회된 회원이 없다면 m == null
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				// 7) 다 쓴 JDBC 용 자원 반납 (역순)
				rset.close();
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8) Controller 로 결과 리턴
		return m; // 조회된 결과가 없다면 null 이 리턴됨!!
		
	} // selectByUserId 끝

	/**
	 * 사용자가 이름 키워드 검색 기능을 요청했을 때
	 * MEMBER 테이블에 SELECT 문을 실행하는 메소드
	 * @param keyword => 이름 키워드 검색어
	 * @return => 검색 결과에 해당하는 회원들의 정보가 담긴 리스트
	 */
	public ArrayList<Member> selectByUserName(String keyword) {
		// select 문 => ResultSet 객체 (여러행 조회) => ArrayList<Member>
		
		// 실행할 SQL 문 (완성된 형태로, 세미콜론 X)
		/*
		SELECT *
  		  FROM MEMBER
 		 WHERE USERNAME LIKE '%XXX%';
		*/
		String sql = "SELECT * "
				     + "FROM MEMBER "
				    + "WHERE USERNAME LIKE '%" + keyword + "%'";
		
		// 0) 필요란 변수들 먼저 셋팅
		ArrayList<Member> list = new ArrayList<>(); // 최종 조회 결과를 담을 변수 (빈 리스트)
		
		Connection conn = null; // DB 접속 정보를 담을 변수
		Statement stmt = null; // SQL 문 전달 및 실행 후 결과를 받을 변수
		ResultSet rset = null; // 조회 결과를 담겨올 변수
	
		try {
			
			// 1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성 및 트랜잭션 설정
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC") ;
			
			// SELECT 문의 경우 트랜잭션 관련 설정은 패스
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5) SQL 문 실행 후 결과 받기
			// - 실행할 SQL 문 : select
			// - 호출할 메소드명 : executeQuery
			rset = stmt.executeQuery(sql);
			
			// 6_1) ResultSet 에 담겨있는 조회 결과를 VO 객체로 하나씩 옮겨담기
			while(rset.next()) {
				
				 list.add(new Member(rset.getInt("USERNO"),
								     rset.getString("USERID"),
								     rset.getString("USERPWD"),
								     rset.getString("USERNAME"),
								     rset.getString("GENDER"),
								     rset.getInt("AGE"),
								     rset.getString("EMAIL"),
								     rset.getString("PHONE"),
								     rset.getString("ADDRESS"),
								     rset.getDate("ENROLLDATE")));
				 // > 각 컬럼으로부터 값을 뽑자마자
				 //   매개변수 생성자로 객체 생성과 동시에 초기화 후
				 //   리스트에 한번에 add 까지 해버린 것!!
			}
			
			// 이 시점 기준으로
			// 조회된 회원의 데이터가 있으면 list.isEmpty() == false
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				// 7) 다 쓴 JDBC 용 자원 반납 (역순)
				rset.close();
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		
		// 8) Controller 로 결과 반환
		return list; // 검색 결과들이 담겨있음
		
	} // selectByUserName 끝

	/**
	 * 회원 정보 변경 요청 시
	 * MEMBER 테이블에 UPDATE 문을 실행해주는 메소드
	 * @param m => 전반적인 수정 관련 내용들이 담겨있는 객체 (누구의, 어떻게)
	 * @return => 수정된 행의 갯수 (정수)
	 */
	public int updateMember(Member m) {
		// update 문 => int (처리된 행의 갯수) => 트랜잭션 처리
		
		// 실행할 SQL 문 (완성된 형태로, 세미콜론 X)
		/*
		UPDATE MEMBER
   		   SET USERPWD = 'XXXX'
             , EMAIL = 'XXXXX@XXX.XXX'
             , PHONE = 'XXXXXXXXXX'
     		 , ADDRESS = 'XXX XXX XXX'
 		 WHERE USERID = 'XXX'
		*/
		String sql = "UPDATE MEMBER "
				      + "SET USERPWD = '" + m.getUserPwd() + "'"
				        + ", EMAIL = '" + m.getEmail() + "'"
				        + ", PHONE = '" + m.getPhone() + "'"
				        + ", ADDRESS = '" + m.getAddress() + "'"
				    + "WHERE USERID = '" + m.getUserId() + "'";
		
		// 0) 필요한 변수들 먼저 셋팅
		int result = 0; // 결과를 담을 변수 (행의 갯수)
		
		Connection conn = null; // DB 접속 정보를 담을 변수
		Statement stmt = null; // SQL 문 실행 후 결과를 받아올 변수
		
		try {
		
			// 1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성 및 트랜잭션 설정
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 자동 커밋해제
			conn.setAutoCommit(false);
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5) SQL 문 전달 후 실행 및 결과 받기
			// - 실행할 SQL 문 : update
			// - 호출할 메소드명 : executeUpdate
			result = stmt.executeUpdate(sql);
			
			// 6_2) 트랜잭션 처리
			if(result > 0) {
				// > 성공인 경우 commit
				
				conn.commit();
			
			} else {
				// > 실패인 경우 rollback

				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				// 7) 다 쓴 JDBC 용 자원 반납 (역순)
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8) Controller 로 결과 리턴
		return result; // 성공 시 1, 실패 시 0
		
	} // updateMember 끝

	/**
	 * 사용자의 회원 탈퇴 요청 시
	 * MEMBER 테이블에 DELETE 문을 실행시켜주는 메소드
	 * @param userId => 탈퇴시킬 회원의 아이디
	 * @return => 삭제된 행의 갯수
	 */
	public int deleteMember(String userId) {
		// delete 문 => int (처리된 행의 갯수) => 트랜잭션 처리
		
		// 실행할 SQL 문 (완성된 형태로, 세미콜론 X)
		/*
		DELETE
  		  FROM MEMBER
 		 WHERE USERID = 'XXXX';
		*/
		String sql = "DELETE "
				     + "FROM MEMBER "
				    + "WHERE USERID = '" + userId + "'";
		
		// 0) 필요한 변수들 먼저 셋팅
		int result = 0; // 최종 결과가 담길 변수 (처리된 행의 갯수)
		
		Connection conn = null; // DB 접속 정보가 담길 변수
		Statement stmt = null; // SQL 문들 실행하고 결과를 받아올 변수
		
		try {
			
			// 1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성 및 트랜잭션 설정
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 자동 커밋 해제
			conn.setAutoCommit(false);
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5) SQL 문 실행 후 결과 받기
			// - 실행할 SQL 문 : delete
			// - 호출할 메소드명 : executeUpdate
			result = stmt.executeUpdate(sql);
			
			// 6_2) 트랜잭션 처리
			if(result > 0) {
				// > 성공이므로 commit
				
				conn.commit();
				
			} else {
				// > 실패이므로 rollback
				
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				// 7) 다 쓴 JDBC 용 자원 반납 (역순)
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8) Controller 로 결과 리턴
		return result; // 성공 시 1, 실패 시 0
		
	} // deleteMember 끝

	
} // 클래스 영역 끝