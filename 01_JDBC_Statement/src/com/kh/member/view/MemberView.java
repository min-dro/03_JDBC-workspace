package com.kh.member.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.member.controller.MemberController;
import com.kh.member.model.vo.Member;

// View : 사용자가 보게 될 화면 (시각적인 요소) 을 담당
// > CLI 환경에서는 주로 입력문과 출력문을 이용해서 화면을 구성함
public class MemberView { // 클래스 영역 시작

	// 전역으로 다 쓸 수 있게끔 Scanner 객체를 필드로 생성
	private Scanner sc = new Scanner(System.in);
	
	// 전역으로 다 쓸 수 있게끔 MemberController 객체를 필드로 생성
	private MemberController mc = new MemberController();
	
	/**
	 *  사용자가 보게될 첫 화면 (메인 화면)
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
				case 1 : 
					insertMember();
					break;
				case 2 : 
					selectAll();
					break;
				case 3 : 
					selectByUserId();
					break;
				case 4 : 
					selectByUserName();
					break;
				case 5 : 
					updateMember();
					break;
				case 6 :
					deleteMember();
					break;
				case 0 :
					System.out.println("프로그램을 종료합니다.");
					return;
				default : 
					System.out.println("번호를 잘못 입력하셨습니다. 다시 입력해 주세요.");
			}

		}
		
	} // mainMenu 끝

	/**
	 * 회원 정보 추가용 화면
	 * 추가할 회원의 정보를 키보드로 입력받아서 회원 정보 추가 요청할 수 있는 화면
	 */
	public void insertMember() {
	
		System.out.println("\n--- 회원 정보 추가 ---");
		
		// 회원 추가 요청 시 필요한 데이터들
		// - 아이디, 비번, 이름, 성별, 나이, 이메일, 전화번호, 주소
		//   (그 중에서도 아이디, 비번, 이름은 필수입력사항)
		
		System.out.print("* 아이디 : ");
		String userId = sc.nextLine();
		
		System.out.print("* 비밀번호 : ");
		String userPw = sc.nextLine();
		
		System.out.print("* 이름 : ");
		String userName = sc.nextLine();
		
		System.out.print("성별 (M/F) : ");
		String  gender = sc.nextLine().toUpperCase();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("전화번호 (- 제외, 숫자만) : ");
		String phone = sc.nextLine();
		
		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		// Controller 의 어떤 메소드를 호출하면서
		// 방금 위에서 입력받는 8개의 값들을 매개변수로 넘기면서 "회원 정보를 추가해주세요" 라고 요청 보내기
		mc.insertMember(userId, userPw, userName, gender, age, email, phone, address); 
		
	} // insertMember 끝
	
	/**
	 * 회원 전체 조회 요청 화면을 담당하는 메소드
	 */
	public void selectAll() {
		
		System.out.println("\n--- 회원 전체 조회 ---");
		
		// Controller 의 어떤 메소드를 호출하면서 회원 전체 조회 요청
		mc.selectAll();
		
	} // selectAll 끝

	/**
	 * 사용자에게 검색할 회원의 아이디를 검색어로 입력받은 후 조회를 요청하는 화면 담당 메소드
	 */
	public void selectByUserId() {
		
		System.out.println("\n--- 회원 아이디로 검색 ---");
		
		System.out.print("검색할 회원 아이디 입력 : ");
		String userId = sc.nextLine();
		
		// 입력받은 아이디를 Controller 의 어떤 메소드를 호출하면서
		// 아이디 검색 요청 시 같이 전달할 것!!
		mc.selectByUserId(userId);
		
	} // selectByUserId 끝
	
	/**
	 * 사용자에게 검색할 회원의 이름 키워드 (검색어) 를 입력받고 검색 요청을 하는 화면 담당 메소드 
	 */
	public void selectByUserName() {
		
		System.out.println("\n--- 회원 이름 키워드 검색 ---");
		
		System.out.print("회원 이름 키워드 입력 : ");
		String keyword = sc.nextLine();
		
		// 검색어를 넘기면서 Controller 의 어떤 메소드를 호출하여 검색 요청을 보낼 것
		mc.selectByUserName(keyword);
		
	} // selectByUserName 끝
	
	/**
	 * 사용자에게 변경할 회원의 아이디 (누구의 정보를 바꿀건지),
	 * 변경할 정보들 (비밀번호, 이메일, 전화번호, 주소) 을 입력받은 후 변경을 요청하는 화면 담당 메소드
	 */
	public void updateMember() {
		
		System.out.println("\n--- 회원 정보 변경 ---");
		
		// 데이터 갱신 (update) 기능 구현 시 고려해야 할 것!!
		// "어떤 데이터를 어떻게 변경할건지"
		// > 회원의 아이디로 변경할 회원 한놈만 골라낼 것!! (unique 제약조건)
		// > 비밀번호, 이메일, 전화번호, 주소만 바꿀 것
		
		// 변경할 회원의 아이디
		System.out.print("변경할 회원의 아이디 : ");
		String userId = sc.nextLine();
		
		// 변경할 내용들
		System.out.print("* 변경할 비밀번호 입력 : ");
		String newPwd = sc.nextLine();
		
		System.out.print("변경할 이메일 입력 : ");
		String newEmail = sc.nextLine();
		
		System.out.print("변경할 전화번호 입력 : ");
		String newPhone = sc.nextLine();
		
		System.out.print("변경할 주소 입력 : ");
		String newAddress = sc.nextLine();
		
		// 위의 입력받은 값들을 Controller 의 어떤 메소드를 호출하면서 회원 정보 수정 요청
		mc.updateMember(userId, newPwd, newEmail, newPhone, newAddress);
		
	} // updateMember 끝
	
	/**
	 * 사용자에게 아이디를 입력받아 회원 탈퇴 요청을 할 수 있는 화면 담당 메소드
	 */
	public void deleteMember() {
		
		System.out.println("\n--- 회원 탈퇴 ---");
		
		System.out.print("탈퇴할 회원의 아이디 입력 : ");
		String userId = sc.nextLine();
		
		// 아이디를 Controller 의 어떤 메소드를 호출하면서 넘기면서 삭제 처리 요청 보내기
		mc.deleteMember(userId);
		
	} // deleteMember 끝
	
	// --------------------------------------
	// 서비스 요청 처리  후 사용자가 보게 될 응답화면들
	
	/**
	 * 서비스 요청 성공 시 보게 될 화면
	 * @param message => 성공 시 보여줄 메시지 내용
	 */
	public void displaySuccess(String message) {
		
		System.out.println("서비스 요청 성공 : " + message);
	}
	
	/**
	 * 서비스 요청 실패 시 보게 될 화면
	 * @param message => 실패 시 보여줄 메시지 내용
	 */
	public void displayFail(String message) {
		
		System.out.println("서비스 요청 실패 : " + message);
	}
	
	/**
	 * 조회 서비스 요청 시 조회결과가 없을 경우 보게 될 화면
	 * @param message => 사용자에게 출력할 메시지 내용
	 */
	public void displayNoData(String message) {
		
		System.out.println(message);
	}
	
	/**
	 * 조회 결과가 여러개일 경우 반복문을 통해서 보여질 응답화면
	 * @param list => 여러개의 조회 결과들이 담겨있는 리스트
	 */
	public void displayList(ArrayList<Member> list) {
		
		System.out.println("\n조회된 데이터는 다음과 같습니다. (총 " + list.size() + "건)");
		
		for(Member m : list) {
			
			System.out.println(m);
		}
		
	}
	
	/**
	 * 조회 결과가 한개일 경우 보여질 응답화면
	 * @param m => 한개의 조회 결과가 담긴 회원 객체
	 */
	public void displayOne(Member m) {
		
		System.out.println("\n조회된 데이터는 다음과 같습니다. (총 1건)");
		
		System.out.println(m);	
	}
	
	
} // 클래스 영역 끝