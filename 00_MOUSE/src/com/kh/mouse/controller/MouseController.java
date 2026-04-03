package com.kh.mouse.controller;

import java.sql.Date;
import java.util.ArrayList;

import com.kh.mouse.common.MyDateTool;
import com.kh.mouse.model.service.MouseService;
import com.kh.mouse.model.vo.Mouse;
import com.kh.mouse.view.MouseView;

public class MouseController {
	
	
	/**
	 * 6개월 이내에 출시된 신상품 조회 요청을 처리해주는 메소드
	 */
	public void selectNewMouse() {
		
		// 1) 요청 시 전달값들을 하나의 VO 객체로 담기
		// > 요청 시 전달값이 없으므로 패스
		
		// 2) Service 로 전달값을 넘기면서 메소드 호출 후 결과 받기
		ArrayList<Mouse> list = new MouseService().selectNewMouse();
		
		// 3) 결과에 따른 응답화면 지정
		if(list.isEmpty()) {
			// > 실패 화면
			
			new MouseView().displayNodata("신상품 조회 결과가 없습니다.");
			
		} else {
			// > 성공 화면
			
			new MouseView().displayList(list);
		}
		
	} // selectNewMouse 끝
	
	/**
	 * 전체 상품 조회 요청을 처리해주는 메소드
	 */
	public void selectAll() {
		
		// 1) 요청 시 전달값들을 하나의 VO 객체로 담기
		// > 요청 시 전달값이 없으므로 패스
		
		// 2) Service 로 전달값을 넘기면서 메소드 호출 후 결과 받기
		ArrayList<Mouse> list = new MouseService().selectAll();
		
		// 3) 결과에 따른 응답화면 지정
		if(list.isEmpty()) {
			// > 실패 화면
			
			new MouseView().displayNodata("전체 상품 조회 결과가 없습니다.");
			
		} else {
			// > 성공 화면
			
			new MouseView().displayList(list);
		}
	}
	
	
	/**키워드검색을 처리하는 메소드입니다.
	 * 데이터 통로역할, 결과 화면 호출을 담당합니다.
	 * @param keyword
	 */
	public void selectByKeyword(String keyword) {
		
		//서비스와 데이터 교환
		ArrayList<Mouse> list = new MouseService().selectByKeyword(keyword);
		
		//검색 결과 화면 호출
		if(list.isEmpty()) {  //--결과가없음
			new MouseView().displayNodata(keyword + "로 조회 결과가 없습니다.");
		}else {               //--결과가있음
			new MouseView().displayList(list);
		}
		
	}//selectByKeyword끝
	
	
	
	
	/**새상품등록을 처리하는 메소드입니다.
	 * 데이터가공, 통로역할, 결과화면 호출을 담당합니다.
	 * @param name
	 * @param brand
	 * @param stock
	 * @param price
	 * @param connectType
	 * @param weight
	 * @param dpi
	 * @param releaseDate  => 사용자로부터 입력받은 새상품정보
	 */
	public void insertMouse(String name, String brand, int stock, int price, 
			                String connectType, int weight, int dpi, String releaseDate) {
		
		//데이터 가공-1 String --> Date 로 가공
		Date coverDate = MyDateTool.convertToDate(releaseDate);
		
		//잘못입력된 문자열 조기 리턴
		if(coverDate == null) {
			new MouseView().displayFail("물품 추가에 실패했습니다. 입력값을 다시 확인해주세요.");
			return;
		}
		
		//데이터 가공-2 VO로 가공
		Mouse m = new Mouse(name, brand, stock, price, connectType, weight, dpi, coverDate);			
			
		// 가공한 자료를 전달하며 결과값(처리된 행의 갯수) 받기
		int result = new MouseService().insertMouse(m);
		
		// INSERT 결과 화면 호출
		if(result > 0) { //--서비스 성공 화면 호출
			new MouseView().displaySuccess("새로운 물품이 추가되었습니다.");
		}else {          //--서비스 실패 화면 호출
			new MouseView().displayFail("물품 추가에 실패했습니다. 입력값을 다시 확인해주세요.");
		}
		
	}//insertMouse끝
	
	/**
	 * 제품 정보 수정 요청을 처리해주는 메소드
	 * @param name => 변경할 제품의 이름
	 * @param newBrand
	 * @param newPrice
	 * @param newConnectType
	 * @param newWeight => 변경할 내용들
	 */
	public void updateMouse(String name, String newBrand, int newStock,
							 int newPrice, String newConnectType,
							 int newWeight) {
		
		// 1) 요청 시 전달값들을 하나의 VO 객체로 담기
		Mouse m = new Mouse();
		m.setName(name);
		m.setBrand(newBrand);
		m.setStock(newStock);
		m.setPrice(newPrice);
		m.setConnectType(newConnectType);
		m.setWeight(newWeight);
		
		// 2) Service 로 전달값을 넘기면서 메소드 호출 후 결과 받기
		int result = new MouseService().updateMouse(m);
		
		// 3) 결과에 따른 응답화면 지정
		if(result > 0) {
			// > 성공 화면
			
			new MouseView().displaySuccess("제품 정보 변경 성공");
			
		} else {
			// > 실패 화면
			
			new MouseView().displayFail("제품 정보 변경 실패");
		}
		
	} // updateMouse 끝
	
	
	/**
	 * 물품삭제요청을 처리하는 메소드
	 * @param mouseNo => 삭제할 물품의 고유번호
	 */
	public void deleteMouse(String name) {
		
		// 1) 요청 시 전달값들을 하나의 VO 로 가공
		// > 전달값이 한개이므로 패스
		
		// 2) Service 로 전달값을 넘기면서 메소드 호출 후 결과 받기
		int result = new MouseService().deleteMouse(name);
		
		// 3) 결과에 따른 응답화면 지정
		if(result > 0) {
			// > 성공화면
			
			new MouseView().displaySuccess("제품 삭제 성공");
			
		} else {
			// > 실패화면

			new MouseView().displayFail("제품 삭제 실패");
		}
		
	} // deleteMouse 끝

	


	
	
	
	
}
