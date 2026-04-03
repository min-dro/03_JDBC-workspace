package com.kh.member.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.member.controller.MemberController;
import com.kh.member.model.vo.Member;

public class MemberView { // 클래스 영역 시작

	// Scanner, MemberController 객체는 전역변수로 빼기
	private Scanner sc = new Scanner(System.in);
	
	private MemberController mc = new MemberController();
	
	/**
	 * 사용자가 보게 될 첫 화면 (메인 화면)
	 */
	public void mainMenu() {

		while(true) {
			
			System.out.println("\n***** 회원 관리 프로그램 *****");
			
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디로 검색");
			System.out.println("4. 회원 이름 키워드 검색");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			
			System.out.println("--------------------------------");
			
			System.out.print("이용할 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				insertMember();
				break;
			case 2:
				selectAll();
				break;
			case 3:
				selectByUserId();
				break;
			case 4:
				selectByUserName();
				break;
			case 5:
				updateMember();
				break;
			case 6:
				deleteMember();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("번호를 잘못 입력하셨습니다. 다시 입력해 주세요.");
			}
		}
		
	} // mainMenu 끝

	/**
	 * 회원 추가용 화면
	 */
	public void insertMember() {
	 
		System.out.println("\n--- 회원 추가 ---");
		
		// 회원 추가 요청 시 필요한 데이터
		// > * 아이디, * 비번, * 이름, 성별, 나이, 이메일, 전화번호, 주소
		
		System.out.print("* 아이디 : ");
		String userId = sc.nextLine();
		
		System.out.print("* 비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("* 이름 : ");
		String userName = sc.nextLine();
		
		System.out.print("성별 (M/F) : ");
		String gender = sc.nextLine().toUpperCase();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("전화번호 (- 제외, 숫자만) : ");
		String phone = sc.nextLine();
		
		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		// 위에서 입력받은 8 개의 값들을 Controller 의 어떤 메소드를 호출하면서
		// 매개변수로 전달해줄 것!! (회원 추가 요청 보내기)
		mc.insertMember(userId, userPwd, userName, gender,
						    age, email, phone, address);
		
	} // insertMember 끝
	
	/**
	 * 회원 전체 조회용 화면
	 */
	public void selectAll() {
		
		System.out.println("\n--- 회원 전체 조회 ---");

		// Controller 로 바로 회원 전체 조회 요청 보내기 (메소드 하나 만들어서)
		mc.selectAll();
		
	} // selectAll 끝
	
	/**
	 * 회원 아이디 검색용 화면
	 */
	public void selectByUserId() {
		
		System.out.println("\n--- 회원 아이디 검색 ---");
		
		System.out.print("검색할 회원의 아이디 : ");
		String userId = sc.nextLine();
		
		// Controller 로 userId 를 넘기면서 아이디 검색 요청 보내기
		mc.selectByUserId(userId);
		
	} // selectByUserId 끝
	
	/**
	 * 회원 이름 키워드 검색용 화면
	 */
	public void selectByUserName() {
		
		System.out.println("\n--- 회원 이름 키워드 검색 ---");
		
		System.out.print("검색할 회원의 이름 키워드 : ");
		String keyword = sc.nextLine();
		
		// Controller 의 어떤 메소드를 호출하면서 검색 요청
		// 이 때, 검색어로 keyword 값을 같이 전달!!
		mc.selectByUserName(keyword);
		
	} // selectByUserName 끝
	
	/**
	 * 회원 정보 수정용 화면
	 */
	public void updateMember() {
		
		System.out.println("\n--- 회원 정보 변경 ---");
		
		// 무엇을 어떻게 바꿀지?
		// userId 로 누구의 정보를 바꿀지 고르고, 비번, 이메일, 전화번호, 주소를 변경해보자!!
		
		System.out.print("변경할 회원의 아이디 : ");
		String userId = sc.nextLine();
		
		System.out.print("* 변경할 비밀번호 : ");
		String newPwd = sc.nextLine();
		
		System.out.print("변경할 이메일 : ");
		String newEmail = sc.nextLine();
		
		System.out.print("변경할 전화번호 (- 제외, 숫자만) : ");
		String newPhone = sc.nextLine();
		
		System.out.print("변경할 주소 : ");
		String newAddress = sc.nextLine();
		
		// Controller 의 어떤 메소드를 호출하면서 변경 요청
		mc.updateMember(userId, newPwd, newEmail, newPhone, newAddress);
		
	} // updateMember 끝
	
	/**
	 * 회원 탈퇴용 화면
	 */
	public void deleteMember() {
		
		System.out.println("\n--- 회원 탈퇴 ---");
		
		// userId 로 탈퇴시킬 회원 한명만 골라내기
		
		System.out.print("탈퇴할 회원의 아이디 : ");
		String userId = sc.nextLine();
		
		// Controller 로 요청
		mc.deleteMember(userId);
	} // deleteMember 끝
	
	// -----------------------------------
	// 응답 화면들
	
	/**
	 * 요청 성공 시 보여질 화면
	 * @param message => 성공메세지 문구
	 */
	public void displaySuccess(String message) { // 메소드 영역 시작
		
		System.out.println("서비스 요청 성공 : " + message);
	} // 메소드 영역 끝
	
	/**
	 * 요청 실패 시 보여질 화면
	 * @param message => 실패메세지 문구
	 */
	public void displayFail(String message) { // 메소드 영역 시작
		
		System.out.println("서비스 요청 실패 : " + message);
	} // 메소드 영역 끝
	
	/**
	 * 조회 서비스 요청 시 조회 결과가 없을 경우 보여질 화면
	 * @param message => 메세지 내용
	 */
	public void displayNodate(String message) { // 메소드 영역 시작
		
		System.out.println(message);
	} // 메소드 영역 끝
	
	/**
	 * 조회 서비스 요청 시 여러 행이 조회된 결과를 출력해서 보여질 화면
	 * @param list => 여러행이 조회된 결과가 담겨있는 리스트
	 */
	public void displayMemberList(ArrayList<Member> list) { // 메소드 영역 시작
		
		System.out.println("조회된 데이터는 다음과 같습니다. (총 " + list.size() + "건)");
		
		for(Member m : list) {
		
			System.out.println(m);
		}
	}
	
	/**
	 * 조회 서비스 요청 시 한개 행이 조회된 결과를 출력해서 보여줄 화면
	 * @param m => 한개 행이 조회된 결과가 담겨있는 객체
	 */
	public void displayOne(Member m) { // 메소드 영역 시작
		
		System.out.println("조회된 데이터는 다음과 같습니다. (총 1건)");
		
		System.out.println(m);
	}
	
} // 클래스 영역 끝