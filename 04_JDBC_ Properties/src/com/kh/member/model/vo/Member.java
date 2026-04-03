package com.kh.member.model.vo;

import java.sql.Date;

public class Member { // 클래스 영역 시작

	// 필드부 (컬럼 정보와 유사)
	private int userNo;				//	USERNO	NUMBER
	private String userId;			//	USERID	VARCHAR2(15 BYTE)
	private String userPwd;			//	USERPWD	VARCHAR2(20 BYTE)
	private String userName;		//	USERNAME	VARCHAR2(20 BYTE)
	private String gender;			//	GENDER	CHAR(1 BYTE)
	private int age;				//	AGE	NUMBER
	private String email;			//	EMAIL	VARCHAR2(30 BYTE)
	private String phone;			//	PHONE	CHAR(11 BYTE)
	private String address;			//	ADDRESS	VARCHAR2(100 BYTE)
	private Date enrollate;			//	ENROLLDATE	DATE
	
	// 생성자부
	public Member() { }

	public Member(int userNo, String userId, String userPwd, String userName, String gender, int age, String email,
			String phone, String address, Date enrollate) {
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
		this.enrollate = enrollate;
	}
	
	// 회원 추가용 생성자 (매개변수 8개 짜히)
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

	public Date getEnrollate() { // 메소드 영역 시작
		return enrollate;
	} // 메소드 영역 끝

	public void setEnrollate(Date enrollate) { // 메소드 영역 시작
		this.enrollate = enrollate;
	} // 메소드 영역 끝

	@Override
	public String toString() { // 메소드 영역 시작
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", gender=" + gender + ", age=" + age + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", enrollate=" + enrollate + "]";
	} // 메소드 영역 끝
	 
} // 클래스 영역 끝