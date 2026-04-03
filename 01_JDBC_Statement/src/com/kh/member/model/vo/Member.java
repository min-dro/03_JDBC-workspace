package com.kh.member.model.vo;

import java.sql.Date;

/*
 * * * VO (Value Object)
 * 
 * - 현실 세계의 정보 하나를 담을 수 있는 "구조체" 개념
 * - 사실 DB 테이블의 "한 행" 에 대한 데이터를 담을 수 있는 저장용 클래스
 *   (매칭시킬 DB 테이블의 각 컬럼을 필드로써 유사하게 구성해서 씀)
 * 
 * > 유사용어 : DTO (Data Transfer Object) 같은뜻임!!
 * 
 * * VO 작성 조건
 * 1. 반드시 캡슐화 적용
 * 2. 기본생성자 및 매개변수 생성자를 작성할 것
 * 3. 모든 필드에 대한 setter / getter 메소드를 작성할 것
 */
public class Member { // 클래스 영역 시작

	// 필드부
	// > 필드는 DB 테이블의 컬럼 정보롸 유사하게 작업 (컬럼 1개 --> 필드 1개)
	private int userNo; 				//	USERNO	NUMBER
	private String userId;				//	USERID	VARCHAR2(15 BYTE)
	private String userPwd;				//	USERPWD	VARCHAR2(20 BYTE)
	private String userName;			//	USERNAME	VARCHAR2(20 BYTE)
	private String gender;				//	GENDER	CHAR(1 BYTE)
	// > 아무리 한 글자가 저장된다고 하더라도 문자열로 취급!! "M" 또는 "F" (문자열로 취급)
	private int age;					//	AGE	NUMBER
	private String email;				//	EMAIL	VARCHAR2(30 BYTE)
	private String phone;				//	PHONE	CHAR(11 BYTE)
	private String address;				//	ADDRESS	VARCHAR2(100 BYTE)
	private Date enrollDate;			//	ENROLLDATE	DATE
	// > 오라클의 DATA 자료형 값을 담을 때에는 
	//   자바의 java.sql.Date 타입으로 import 해야 한다!!
	
	// 생성자부
	public Member() { }
	
	public Member(int userNo, String userId, String userPwd, String userName, String gender, int age, String email,
			String phone, String address, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.enrollDate = enrollDate;
	}
	
	// 회원 추가용 생성자 (매개변수 8개 짜리)
	public Member(String userId, String userPwd, String userName, String gender, int age, String email, String phone,
			String address) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	// 메소드부
	public int getUserNo() { // 메소드 영역 시작
		return userNo;
	} // 메소드 영역 끝

	public void setUserNo(int userNo) { // 메소드 영역 시작
		this.userNo = userNo;
	} // 메소드 영역 끝

	public String getUserId() { // 메소드 영역 시작
		return userId;
	} // 메소드 영역 끝

	public void setUserId(String userId) { // 메소드 영역 시작
		this.userId = userId;
	} // 메소드 영역 끝

	public String getUserPwd() { // 메소드 영역 시작
		return userPwd;
	} // 메소드 영역 끝

	public void setUserPwd(String userPwd) { // 메소드 영역 시작
		this.userPwd = userPwd;
	} // 메소드 영역 끝

	public String getUserName() { // 메소드 영역 시작
		return userName;
	} // 메소드 영역 끝

	public void setUserName(String userName) { // 메소드 영역 시작
		this.userName = userName;
	} // 메소드 영역 끝

	public String getGender() { // 메소드 영역 시작
		return gender;
	} // 메소드 영역 끝

	public void setGender(String gender) { // 메소드 영역 시작
		this.gender = gender;
	} // 메소드 영역 끝

	public int getAge() { // 메소드 영역 시작
		return age;
	} // 메소드 영역 끝

	public void setAge(int age) { // 메소드 영역 시작
		this.age = age;
	} // 메소드 영역 끝

	public String getEmail() { // 메소드 영역 시작
		return email;
	} // 메소드 영역 끝

	public void setEmail(String email) { // 메소드 영역 시작
		this.email = email;
	} // 메소드 영역 끝

	public String getPhone() { // 메소드 영역 시작
		return phone;
	} // 메소드 영역 끝

	public void setPhone(String phone) { // 메소드 영역 시작
		this.phone = phone;
	} // 메소드 영역 끝

	public String getAddress() { // 메소드 영역 시작
		return address;
	} // 메소드 영역 끝

	public void setAddress(String address) { // 메소드 영역 시작
		this.address = address;
	} // 메소드 영역 끝

	public Date getEnrollDate() { // 메소드 영역 시작
		return enrollDate;
	} // 메소드 영역 끝

	public void setEnrollDate(Date enrollDate) { // 메소드 영역 시작
		this.enrollDate = enrollDate;
	} // 메소드 영역 끝

	@Override
	public String toString() { // 메소드 영역 시작
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", gender=" + gender + ", age=" + age + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", enrollDate=" + enrollDate + "]";
	} // 메소드 영역 끝
	
} // 클래스 영역 끝