package com.kh.member.controller;

import java.util.ArrayList;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;
import com.kh.member.view.MemberView;

/*
 * * Controller : View 를 통해서 요청한 기능을 처리하는 담당
 *  			  해당 Controller 메소드로 전달된 데이터들을 "VO 객체로 가공처리" 후
 *  			  DAO 메소드 호출 시 그 가공한 VO 객체를 매개변수로 전달	
 * 				  DAO 로 부터 반환받은 결과에 따라 사용자가 보게 될 View (응답화면) 지정
 * 
 * * Controller 메소드 코드 작성 방법
 * 1. 매개변수로 전달받은 전달값들을 하나의 VO 객체에 담기 (가공)
 * 2. DAO 단의 메소드를 호출하면서 그 VO 객체를 매개변수로 전달하고, 결과 (리턴값) 받기
 * 3. DAO 로 부터 전달받은 결과값에 따라 사용자가 보게 될 View (응답화면) 지정
 */
public class MemberController { // 클래스 영역 시작

	/**
	 * 사용자의 회원 추가 요청을 처리해주는 메소드
	 * @param userId
	 * @param userPwd
	 * @param userName
	 * @param gender
	 * @param age
	 * @param email
	 * @param phone
	 * @param address => 사용자가 회원 추가 요청 시 입력했던 값들
	 */
	public void insertMember(String userId, String userPwd, String userName,
							 String	gender, int age, String email, String phone,
							 String address) {
		
		// 1) 매개변수로 전달된 데이터들을 하나의 VO 객체로 가공하기
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address);
		
		// 2) VO 객체를 DAO 의 메소드로 매개변수로 넘기면서 호출 및 결과값 받기
		int result = new MemberDao().insertMember(m);
		// > INSERT, UPDATE, DELETE 를 실행할 예정이라면
		//   처리된 행의 갯수를 정수로 리턴받아 주면 된다!!
		
		// System.out.println("result : " + result);
		
		// 3) 결과에 따른 응답화면을 지정 (View 지정)
		if(result > 0) { 
			// > 성공했을 경우 - 성공 화면을 보여주기

			new MemberView().displaySuccess("회원 추가 성공");
			
		} else { 
			// > 실패했을 경우 - 실패 화면을 보여주기

			new MemberView().displayFail("회원 추가 실패");
		}
		
	} // insertMember 끝
	
	/**
	 * 사용자의 회원 전체 조회 요청을 처리해주는 메소드
	 */
	public void selectAll() {
		
		// 1) 전달된 데이터들을 하나의 VO 객체로 가공하기
		// > 전달된 데이터들이 없으므로 패스
		
		// 2) DAO 로 메소드 호출 후 결과 받기
		// 요청 시 전달값이 없었기 때문에 매개변수 없이 그냥 호출
		ArrayList<Member> list = new MemberDao().selectAll();
		// > SELECT 문을 이용한 조회 기능은 크게 2 가지로 나뉜다!!
		// - 여러행 조회 : 2개 이상의 데이터가 조회될 경우
		// - 단일행 조회 : 많아봤자 최대 1개의 데이터가 조회될 경우 (VO)

		// 3) 결과에 따른 응답화면 지정
		// > 조회 결과기 있는지 판별 후 그에 맞는 응답 화면으로 각각 지정
		if(list.isEmpty()) { 
			// > 조회 결과가 없을 경우

			new MemberView().displayNoData("전체 조회 결과가 없습니다.");
			
		} else { 
			// > 조회 결과가 있을 경우

			new MemberView().displayList(list);
		}
		
	} // selectAll 끝

	/**
	 * 사용자의 아이디로 검색 요청을 처리해주는 메소드
	 * @param userId => 검색할 회원의 아이디값 (검색어)
	 */
	public void selectByUserId(String userId) {
		
		// 1) 요청 시 전달값들을 하나의 VO 객체로 가공하기
		// > 어차피 전달값이 단 한개이므로 가공하는 과정은 패스하기!!
		
		// 2) DAO 로 그 전달값을 넘기면서 호출 후 결과 받기
		Member m = new MemberDao().selectByUserId(userId);
		// > 아이디로 검색하겠다.
		//   아이디는 unique 제약조건이 걸린 컬럼이므로
		//   일치하는 아이디에 대한 회원은 많아봤자 1명에 해당됨!!
		// > 즉, 단일행 조회 가능임!!
		
		// 3) 결과에 따른 응답화면 지정
		if(m == null) { 
			// > 검색된 결과가 없는 경우 (조회된 결과가 없는 경우)

			new MemberView().displayNoData(userId + "에 해당하는 검색 결과가 없습니다.");
			
		} else { 
			// > 검색된 결과가 있을 경우 (조회된 결과가 있을 경우)

			new MemberView().displayOne(m);
		}
		
	} // selectByUserId 끝

	/**
	 * 사용자의 회원명 키워드로 검색 요청 시 처리해주는 메소드
	 * @param keyword => 사용자가 입력했던 검색할 회원의 이름 키워드 (검색어)
	 */
	public void selectByUserName(String keyword) {
		
		// 1) 요청 시 전달값들을 하나의 VO 객체로 가공하기
		// > 요청 시 전달값이 1개이므로 패스
		
		// 2) DAO 로 전달값을 넘기면서 호출 후 결과값 받기
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		// > 이름 키워드 검색은 여러행 조회 기능임!! (같은 성씨, 동명이인 등)
		
		// 3) 결과에 따른 응답화면 지정
		if(list.isEmpty()) { 
			// > 검색 결과가 없을 경우

			new MemberView().displayNoData(keyword + " 에 대한 검색 결과가 없습니다.");
			
		} else { 
			// > 검색 결과가 있을 경우

			new MemberView().displayList(list);
		}
		
	} // selectByUserName 끝
	
	/**
	 * 회원 정보 수정 요청 시 처리해주는 메소드
	 * @param userId => 변경할 회원의 아이디
	 * @param newPwd
	 * @param newEmail
	 * @param newPhone
	 * @param newAddress => 변경할 내용
	 */
	public void updateMember(String userId, String newPwd, String newEmail,
								String newPhone, String newAddress) {
		
		// 1) 요청 시 전달받은 값들을 하나의 VO 객체로 가공
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(newPwd);
		m.setEmail(newEmail);
		m.setPhone(newPhone);
		m.setAddress(newAddress);
		
		// 2) DAO 로 전달값을 넘기면서 메소드 호출 후 결과 받기
		int result = new MemberDao().updateMember(m);
		
		// 3) 결과에 따른 응답화면 지정
		if(result > 0) { 
			// > 성공 화면

			new MemberView().displaySuccess("회원 정보 변경 성공");
			
		} else { 
			// > 실패 화면

			new MemberView().displayFail("회원 정보 변경 실패");
		}
	
	} // updateMember 끝
	
	/**
	 * 사용자의 회원 탈퇴 요청을 받아서 처리해주는 메소드
	 * @param userId => 탈퇴시킬 회원의 아이디
	 */
	public void deleteMember(String userId) {
		
		// 1) 요청 시 전달값들을 하나의 VO 로 가공
		// > 어차피 전달값이 한개이므로 패스
		
		// 2) DAO 로 요청 시 전달값을 넘기면서 호출 후 결과 받기
		int result = new MemberDao().deleteMember(userId);
		
		// 3) 결과에 따른 응답화면 지정
		if(result > 0) { 
			// > 성공 화면
			
			new MemberView().displaySuccess("회원 탈퇴 성공");
			
		} else { 
			// > 실패 화면

			new MemberView().displayFail("회원 탈퇴 실패");
		}
		
	} // deleteMember 끝
	
} // 클래스 영역 끝