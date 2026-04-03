 package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.KeyboardController;
import com.kh.model.vo.Keyboard;

public class KeyboardView { // 클래스 영역 시작
		
	private Scanner sc = new Scanner(System.in);
	private KeyboardController kc = new KeyboardController();
	
	
	public void mainMenu() { // 메소드 영역 시작
		while(true) {
			System.out.println("===== 키보드 관리 프로그램 =====");
			System.out.println("1. 키보드 정보 추가");
			System.out.println("2. 키보드 정보 전체 조회");
			System.out.println("3. 키보드 모델명 키워드 검색");
			System.out.println("4. 키보드 정보 수정");
			System.out.println("5. 키보드 정보 삭제");
			System.out.println("0. 프로그램 종료");
			
			System.out.print("입력 : ");
			int num = sc.nextInt();
			sc.nextLine();
			
			switch(num) {
			case 1 :
				insertKeyboard();
				break;
			case 2 :
				selectAll();
				break;
			case 3 :
				selectKeyboardByModelName();
				break;
			case 4 :
				updateKeyboard();
				break;
			case 5 :
				deleteKeyboard();
				break;
			case 0 :
				System.out.println("프로그램이 종료 되었습니다.");
				return;
			default : 
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
			}
			
		}
		
	} // 메소드 영역 끝


	public void insertKeyboard() { // 메소드 영역 시작
		
		System.out.println("\n===== 키보드 정보 추가 =====");
		
		System.out.print("제조사 입력 : ");
		String brand = sc.nextLine();

		System.out.print("모델명 입력 : ");
		String modelName = sc.nextLine();
		
		System.out.print("키 개수 입력 : ");
		int keyCount = sc.nextInt();
		sc.nextLine();

		System.out.print("스위치 종류 (적축, 갈축, 자석축, 멤브레인 등) 입력 : ");
		String switchType = sc.nextLine();
		
		System.out.print("연결 방식 (유선, 무선, 유무선) 입력 : ");
		String connection = sc.nextLine();
		
		System.out.print("가격 입력 : ");
		int price = sc.nextInt();
		sc.nextLine();
		
		String rgb;
		while(true) {
			System.out.print("RGB 여부(RGB가 있으면 Y, 아니면 N을 입력) : ");
			rgb = sc.nextLine();
			rgb = rgb.toUpperCase();
			
			if(rgb.equals("Y") || rgb.equals("N")) {
				break;
			} else {
				System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			}
		}
		kc.insertKeyboard(brand, modelName, keyCount, switchType, connection, price, rgb);
	} // 메소드 영역 끝
	public void selectAll() { // 메소드 영역 시작

		System.out.println("\n===== 키보드 정보 전체 조회 =====");
		
		kc.selectAll();

	} // 메소드 영역 끝

	public void selectKeyboardByModelName() { // 메소드 영역 시작
		
		System.out.println("\n===== 키보드 모델명 키워드 검색 =====");
		
		System.out.print("키워드 입력 : ");
		String keyword = sc.nextLine();
		
		kc.searchKeyboardByModelName(keyword);
		
	} // 메소드 영역 끝


	public void updateKeyboard() { // 메소드 영역 시작
		
		System.out.println("\n===== 키보드 정보 수정 =====");
		
		System.out.print("모델명 입력 : ");
		String modelName = sc.nextLine();
		
		System.out.print("변경할 제조사 입력 : ");
		String newBrand = sc.nextLine();
	
		System.out.print("변경할 키 개수 입력 : ");
		int newKeyCount = sc.nextInt();
		sc.nextLine();
		
		System.out.print("변경할 스위치 종류 (적축, 갈축, 자석축, 멤브레인 등) 입력 : ");
		String newSwitchType = sc.nextLine();
		
		System.out.print("변경할 연결 방식 (유선, 무선, 유무선) 입력 : ");
		String newConnection = sc.nextLine();
		
		System.out.print("변경할 가격 입력 : ");
		int newPrice = sc.nextInt();
		sc.nextLine();
		
		kc.updateKeyboard(modelName, newBrand, newKeyCount, newSwitchType, newConnection, newPrice);
		
	} // 메소드 영역 끝


	public void deleteKeyboard() { // 메소드 영역 시작
		
		System.out.println("\n===== 키보드 정보 삭제 =====");
		
		System.out.print("삭제할 모델명 입력 : ");
		String modelName = sc.nextLine();
		while(true) {
			System.out.print("삭제 후 복구할 수 없습니다. 정말 삭제하시겠습니까? (y/n) : ");
			String yn = sc.nextLine();
			yn.toLowerCase();
			if(yn.charAt(0) == 'y') {
				kc.deleteKeyboard(modelName);
				break;
			} else if(yn.charAt(0) == 'n') {
				System.out.println("삭제를 취소하겠습니다.");
				break;
			} else {
				System.out.println("잘못 입력했습니다.");
			}
		}
	} // 메소드 영역 끝

	public void displaySuccess(String message) {
		System.out.println("서비스 상태 성공 : " + message);
	}
	
	public void displayFail(String message) {
		System.out.println("서비스 상태 실패 : " + message);
	}
	
	public void displayNodata(String message) {
		System.out.println(message);
	}
	
	public void displayList(ArrayList<Keyboard> list) {
		System.out.println("조회된 데이터는 다음과 같습니다. (총 " + list.size() + "건)");
		for(Keyboard k : list) {
			System.out.println(k);
		}	
	}
	
} // 클래스 영역 끝
