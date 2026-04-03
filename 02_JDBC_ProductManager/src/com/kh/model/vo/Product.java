package com.kh.model.vo;

public class Product { // 클래스 영역 시작

	private String productId;
	private String productName;
	private int price;
	private String description;
	private int stock;

	public Product() {}

	public Product(String productId, String productName, int price, String description, int stock) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.stock = stock;
	}

	public String getProductId() { // 메소드 영역 시작
		return productId;
	} // 메소드 영역 끝

	public void setProductId(String productId) { // 메소드 영역 시작
		this.productId = productId;
	} // 메소드 영역 끝

	public String getProductName() { // 메소드 영역 시작
		return productName;
	} // 메소드 영역 끝

	public void setProductName(String productName) { // 메소드 영역 시작
		this.productName = productName;
	} // 메소드 영역 끝

	public int getPrice() { // 메소드 영역 시작
		return price;
	} // 메소드 영역 끝

	public void setPrice(int price) { // 메소드 영역 시작
		this.price = price;
	} // 메소드 영역 끝

	public String getDescription() { // 메소드 영역 시작
		return description;
	} // 메소드 영역 끝

	public void setDescription(String description) { // 메소드 영역 시작
		this.description = description;
	} // 메소드 영역 끝

	public int getStock() { // 메소드 영역 시작
		return stock;
	} // 메소드 영역 끝

	public void setStock(int stock) { // 메소드 영역 시작
		this.stock = stock;
	} // 메소드 영역 끝

	@Override
	public String toString() { // 메소드 영역 시작
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price
				+ ", description=" + description + ", stock=" + stock + "]";
	} // 메소드 영역 끝
	
} // 클래스 영역 끝
