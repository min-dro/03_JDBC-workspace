package com.kh.mouse.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
    
    /** Connection 객체를 생성하고 트랜잭션 설정 후 
     *  Connection 객체를 반환해주는 메소드
     * @return => Connection 객체
     */
    public static Connection getConnection() {
        
        // 변수 선언 및 null로 초기화
        Connection conn = null;
        
        // 유지보수 및 서비스 지속성을 위해 Properties 객체 생성 (동적코딩)
        Properties prop = new Properties();
       
        try {
        	//resources 폴더의 driver.properties 파일 읽기
			prop.load(new FileInputStream("resources/driver.properties"));
        	
        	//Connection 객체 호출을 위한 드라이버 등록
			Class.forName(prop.getProperty("driver"));
			
			//Connection 객체 생성하기
			conn = DriverManager.getConnection(prop.getProperty("url"),
					                           prop.getProperty("username"),
					                           prop.getProperty("password"));
			//DML 작업을 위한 트랜잭션 관련 설정하기(자동 커밋 해제)
			conn.setAutoCommit(false);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        //호출한 메소드에 Connection 객체를 반납
        return conn;
    }//getConnection 메소드 끝
    
    
    
    
    //오버로딩을 통해 자원들을 반납해주는 close 클래스
    
    /**사용한 JDBC 자원을 반납하는 메소드 
     * @param conn => 반납할 Connection 객체
     */
    public static void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**사용한 JDBC 자원을 반납하는 메소드 
     * @param stmt => Statement 객체, PrepardStatement 객체
     */
	public static void close(Statement stmt) {
		// Statement는 PrepardStatement 의 부모! "다형성"에 의해 
    	// PrepardStatement 또한 "오버라이딩" 되어 반납 가능해진다.
		try {
			if((stmt != null)&&(!stmt.isClosed())) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//close끝
    
	/**ResultSet 객체를 전달받아서 반납시켜주는 메소드
	 * @param rset => ResultSet 객체
	 */
	public static void close(ResultSet rset) {
		try {               
			if((rset != null)&&(!rset.isClosed())) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//close끝

	
	
	/** 전달받은 Connection 객체를 가지고 COMMIT 시켜주는 메소드 
	 * @param conn => Connection 객체
	 */
	public static void commit(Connection conn) {
		try {             
			if((conn != null)&&(!conn.isClosed())) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//commit
	

	/**전달받은 Connection 객체를 가지고 ROLLBACK 시켜주는 메소드
	 * @param conn => Connection 객체
	 */
	public static void rollback(Connection conn) {
		try {
			if((conn != null)&&(!conn.isClosed()))
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//rollback끝
    
}// 클래스 영역 끝