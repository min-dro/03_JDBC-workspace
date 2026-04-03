package com.kh.mouse.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.mouse.controller.MouseController;
import com.kh.mouse.model.vo.Mouse;

public class MouseView { // 클래스 영역 시작

	private Scanner sc = new Scanner(System.in);
	
	private MouseController mc = new MouseController();
	
	public void mainMenu() {
		
		while(true) {
			
			System.out.println("\n***** 마우스 재고 관리 프로그램 *****");
			
			System.out.println("1. 신상품 조회");
			System.out.println("2. 전체 상품 조회");
			System.out.println("3. 키워드로 검색");
			System.out.println("4. 물품 등록");
			System.out.println("5. 물품 정보 변경");
			System.out.println("6. 물품 삭제");
			System.out.println("7. 프로그램 종료");
			
			System.out.println("--------------------------------");
			
			System.out.print("이용할 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				selectNewMouse();
				break;
			case 2:
				selectAll();
				break;
			case 3:
				selectByKeyword();
				break;
			case 4:
				insertMouse();
				break;
			case 5:
				updateMouse();
				break;
			case 6:
				deleteMouse();
				break;
			case 7:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("번호를 잘못 입력하셨습니다. 다시 입력해 주세요.");
			}
		}
		
	}
	
	/**
	 * 신상품 조회용 화면
	 */
	public void selectNewMouse() {
		System.out.println("\n--- 신상품 조회 ---");
		
		// Controller 로 요청
		mc.selectNewMouse();
		
	} // selectNewMouse 끝
	
	/**
	 * 전체 상품 조회용 화면
	 */
	public void selectAll() {
	
		System.out.println("\n--- 전체 상품 조회 ---");
		
		// Controller 로 요청
		mc.selectAll();
	
	} // selectAll 끝
	
	
	
	/**키워드검색시 보여지는 화면
	 * 사용자로부터 키워드를 입력받아 컨트롤러에게 전달
	 */
	public void selectByKeyword(){
		
		System.out.println("\n--- 키워드로 검색 ---");
		
		//키워드 입력받기
		System.out.print("검색할 마우스 이름 : ");
		String keyword = sc.nextLine().toUpperCase();
		
		//컨트롤러에게 전달
		mc.selectByKeyword(keyword);
		
	}//selectByKeyword끝
	
	
	/**물품 등록 요청시 보여지는 화면
	 * 사용자로부터 물품 정보를 입력받아 controller 로 보내는 클래스
	 */
	public void insertMouse() {
		
		System.out.println("\n--- 물픔 등록 ---");
		
		// 사용자로부터 물품의 모든 컬럼정보를 입력받기
		// mouseNo 는 시퀀스로 자동입력되므로 생략
		
		System.out.println("물품 정보를 입력해주세요 (* 항목은 필수입력사항입니다)");
		System.out.print("* 제품명 : ");
		String name = sc.nextLine().toUpperCase();
		
		System.out.print("* 제조사 : ");
		String brand = sc.nextLine();
		
		System.out.print("* 재고 : ");
		int stock = sc.nextInt();
		sc.nextLine();
		
		System.out.print("* 가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		
		System.out.print("  연결방식(유선/무선/유무선) : ");
		String connectType = sc.nextLine();
		
		System.out.print("  마우스무게(g) : ");
		int weight = sc.nextInt();
		sc.nextLine();
		
		System.out.print("  속도(DPI) : ");
		int dpi = sc.nextInt();
		sc.nextLine();
		
		System.out.print("* 출시일(YYYY-MM-DD로 입력) : ");
		String releaseDate = sc.nextLine();
		
		mc.insertMouse(name, brand, stock, price, connectType 
				       , weight, dpi, releaseDate);
		
	}//insertMouse끝
	
	
		
		/**
	 * 물품 정보 수정용 화면
	 */
	public void updateMouse() {
		
		System.out.println("\n--- 물품 정보 변경 ---");
		
		System.out.print("* 수정할 제품명 : ");
		String name = sc.nextLine();
		
		System.out.print("* 변경할 브랜드 : ");
		String newBrand= sc.nextLine();
		
		System.out.print("* 변경할 재고수량 : ");
		int newStock = sc.nextInt();
		sc.nextLine();
		
		System.out.print("* 변경할 가격 : ");
		int newPrice = sc.nextInt();
		sc.nextLine();
		
		System.out.print("변경할 연결방식(유선/무선/유무선) : ");
		String newConnectType = sc.nextLine();
		
		System.out.print("변경할 무게 : ");
		int newWeight = sc.nextInt();
		sc.nextLine();
		
		// Controller 로 요청	
		mc.updateMouse(name, newBrand, newStock, newPrice, newConnectType, newWeight);
		
	} // updateMouse 끝
	
	/**
	 * 제품 삭제용 화면
	 */
	public void deleteMouse() {
		
		System.out.println("\n--- 제품 삭제 ---");
		
		// 사용자로부터 수정할 제품의 NAME을 받아 controller 로 보냄
		
		System.out.print("삭제할 제품의 이름 : ");
		String name = sc.nextLine();
		
		// Controller 로 요청
		mc.deleteMouse(name);
	
	} // deleteMouse 끝
	
	// ----------------------------------
	// 응답 화면들
	
	/**
	 * 요청 성공 시 보여질 화면
	 * @param message => 성공메세지 문구
	 */
	public void displaySuccess(String message) {
		
		System.out.println("서비스 요청 성공 : " + message);
	}
	
	/**
	 * 요청 실패 시 보여질 화면
	 * @param message => 실패메세지 문구
	 */
	public void displayFail(String message) {
		
		System.out.println("서비스 요청 실패 : " + message);
	}
	
	/**
	 * 조회 서비스 요청 시 조회 결과가 없을 경우 보여질 화면
	 * @param message => 메시지 내용
	 */
	public void displayNodata(String message) {
		
		System.out.println(message);
	}
	
	/**
	 * 조회 서비스 요청 시 여러 행이 조회된 결과를 출력해서 보여줄 화면
	 * @param list => 여러 행이 조회된 결과가 담겨있는 객체
	 */
	public void displayList(ArrayList<Mouse> list) {
		
		System.out.println("조회된 데이터는 다음과 같습니다. (총 " + list.size() + "건)");
		
		for(Mouse m : list) {
		
			System.out.println(m);
		}
		
	}
	
} // 클래스 영역 끝
