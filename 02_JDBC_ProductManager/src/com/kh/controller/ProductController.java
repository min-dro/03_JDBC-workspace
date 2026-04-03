package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;
import com.kh.view.ProductView;

public class ProductController { // 클래스 영역 시작

	public void selectAll() { // 메소드 영역 시작
		
		ArrayList<Product> list = new ProductDao().selectAll();
		if(list.isEmpty()) {
			new ProductView().displayNoData("조회 결과 없음!");
		} else {
			new ProductView().displayList(list);
		}
		
	} // 메소드 영역 끝
	
	public void insertProduct(String productId, String productName, int price, String description, int stock) { // 메소드 영역 시작
		
		Product p = new Product(productId, productName, price, description, stock);
		int result = new ProductDao().insertProduct(p);
		
		if(result > 0) {
			new ProductView().displaySuccess("상품 추가 성공!");
		} else {
			new ProductView().displayFail("상품 추가 실패!");
		}
		
	} // 메소드 영역 끝
	
	public void selectByProductName(String keyword) { // 메소드 영역 시작
		
		ArrayList<Product> list = new ProductDao().selectByProductName(keyword);
		if(list.isEmpty()) {
			new ProductView().displayNoData("검색 결과 없음!");
		} else {
			new ProductView().displayList(list);
		}
		
	} // 메소드 영역 끝
	
	public void updateProduct(String productId, int price, String description, int stock) { // 메소드 영역 시작
		
		Product p = new Product();

		p.setProductId(productId);
		p.setPrice(price);
		p.setDescription(description);
		p.setStock(stock);
				
		int result = new ProductDao().updateProduct(p);
		
		if(result > 0) {
			new ProductView().displaySuccess("상품 수정 성공!");
		} else {
			new ProductView().displayFail("상품 수정 실패!");
		}
		
	} // 메소드 영역 끝
	
	public void deleteProduct(String productId) { // 메소드 영역 시작
		
		int result = new ProductDao().deleteProduct(productId);
		
		if(result > 0) {
			new ProductView().displaySuccess("상품 삭제 성공!");
		} else {
			new ProductView().displayFail("상품 삭제 실패!");
		}
		
	} // 메소드 영역 끝
	
} // 클래스 영역 끝
