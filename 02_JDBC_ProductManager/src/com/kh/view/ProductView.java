package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductView { // 클래스 영역 시작

	private Scanner sc = new Scanner(System.in);
	private ProductController pc = new ProductController();
	
	public void mainMenu() { // 메소드 영역 시작
		
		while(true) {
			System.out.println("1. 상품 전체 조회하기");
			System.out.println("2. 상품 추가 하기");
			System.out.println("3. 상품명 검색 하기");
			System.out.println("4. 상품 정보 수정 하기");
			System.out.println("5. 상품 삭제 하기");
			System.out.println("0. 프로그램 종료하기");
			
			System.out.print("메뉴 번호 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				selectAll();
				break;
			case 2:
				insertProduct();
				break;
			case 3:
				selectByProductName();
				break;
			case 4:
				updateProduct();
				break;
			case 5:
				deleteProduct();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시입력해주세요.");
			}
		}
		
	} // 메소드 영역 끝

	public void selectAll() { // 메소드 영역 시작
		
		pc.selectAll();
		
	} // 메소드 영역 끝
	
	public void insertProduct() { // 메소드 영역 시작
		
		System.out.print("상품 아이디 : ");
		String productId = sc.nextLine();
		
		System.out.print("상품명 : ");
		String productName = sc.nextLine();
		
		System.out.print("가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		
		System.out.print("상품 설명 : ");
		String description = sc.nextLine();
		
		System.out.print("재고 수량 : ");
		int stock = sc.nextInt();
		sc.nextLine();
		
		pc.insertProduct(productId, productName, price, description, stock);
		
	} // 메소드 영역 끝
	
	public void selectByProductName() { // 메소드 영역 시작
		
		System.out.print("검색할 상품 키워드명 : ");
		String keyword = sc.nextLine();
		pc.selectByProductName(keyword);
		
	} // 메소드 영역 끝
	
	public void updateProduct() { // 메소드 영역 시작
		
		System.out.print("아이디 : ");
		String productId = sc.nextLine();
		
		System.out.print("수정할 가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		
		System.out.print("수정할 상품 설명 : ");
		String description = sc.nextLine();
		
		System.out.print("수정할 재고 수량 : ");
		int stock = sc.nextInt();
		sc.nextLine();
		
		pc.updateProduct(productId, price, description, stock);
		
	} // 메소드 영역 끝
	
	public void deleteProduct() { // 메소드 영역 시작
		
		System.out.print("삭제할 상품 아이디 : ");
		String productId = sc.nextLine();
		
		pc.deleteProduct(productId);
		
	} // 메소드 영역 끝
	
	public void displaySuccess(String message) { // 메소드 영역 시작
		
		System.out.println("서비스 요청 성공 : " + message);
		
	} // 메소드 영역 끝
	
	public void displayFail(String message) { // 메소드 영역 시작
		
		System.out.println("서비스 요청 실패 : " + message);
		
	} // 메소드 영역 끝
	
	public void displayNoData(String message) { // 메소드 영역 시작
		
		System.out.println(message);
		
	} // 메소드 영역 끝
	
	public void displayList(ArrayList<Product> list) { // 메소드 영역 시작
		System.out.println("조회된 데이터는 다음과 같습니다. (총 " + list.size() + "건)");
		for (Product p : list) {
			System.out.println(p);
		}
	}
} // 클래스 영역 끝
