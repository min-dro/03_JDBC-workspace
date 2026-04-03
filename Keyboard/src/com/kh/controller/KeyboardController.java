package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.KeyboardService;
import com.kh.model.vo.Keyboard;
import com.kh.view.KeyboardView;

public class KeyboardController { // 클래스 영역 시작
	
	public void insertKeyboard(String board, String modelName, int keyCount, String switchType, String connection, int price,
			String rgb) { // 메소드 영역 시작
		Keyboard k = new Keyboard(board, modelName, keyCount, switchType, connection, price, rgb);
		int result = new KeyboardService().insertKeyboard(k);
		
		if(result > 0) {
			new KeyboardView().displaySuccess("키보드 추가 성공");
		} else {
			new KeyboardView().displayFail("키보드 추가 실패");
		}					
	} // 메소드 영역 끝

	public void selectAll() { // 메소드 영역 시작
		
		ArrayList<Keyboard> list = new KeyboardService().selectAll();
		
		 if(list.isEmpty()) {
			 new KeyboardView().displayNodata("키보드 정보가 없습니다.");
		 } else {
			 new KeyboardView().displayList(list);
		 }
		 
	} // 메소드 영역 끝

	public void searchKeyboardByModelName(String keyword) { // 메소드 영역 시작
		
		ArrayList<Keyboard> list = new KeyboardService().selectByModelName(keyword);
		
		 if(list.isEmpty()) {
			 new KeyboardView().displayNodata(keyword + "에 대한 검색 결과가 없습니다.");
		 } else {
			 new KeyboardView().displayList(list);
		 }
		 
	} // 메소드 영역 끝

	public void updateKeyboard(String modelName, String newBrand, int newKeyCount
			, String newSwitchType, String newConnection, int newPrice) { // 메소드 영역 시작
		Keyboard k = new Keyboard();
		k.setModelName(modelName);
		k.setBrand(newBrand);
		k.setKeyCount(newKeyCount);
		k.setSwitchType(newSwitchType);
		k.setConnection(newConnection);
		k.setPrice(newPrice);
		
		int result = new KeyboardService().updateKeyboard(k);
		
		if(result > 0) {
			new KeyboardView().displaySuccess("키보드 정보 수정 성공");
		} else {
			new KeyboardView().displayFail("키보드 정보 수정 실패");
		}
	} // 메소드 영역 끝

	public void deleteKeyboard(String modelName) { // 메소드 영역 시작
		
		int result = new KeyboardService().deleteKeyboard(modelName);
		
		if(result > 0) {
			new KeyboardView().displaySuccess("키보드 정보 삭제 성공");
		} else {
			new KeyboardView().displayFail("키보드 정보 삭제 실패");
		}
	} // 메소드 영역 끝
	
} // 클래스 영역 끝
