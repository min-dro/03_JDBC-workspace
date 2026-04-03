package com.kh.mouse.model.vo;

import java.sql.Date;

public class Mouse { // 클래스 영역 시작

	// 필드부
	private int mouseNo;
	private String name;
	private String brand;
	private int stock;
	private int price;
	private String connectType;
	private int weight;
	private int dpi;
	private Date releaseDate;
	
	// 생성자부
	public Mouse() {}

	public Mouse(int mouseNo, String name, String brand, int stock, int price, String connectType, int weight, int dpi,
			Date releaseDate) {
		super();
		this.mouseNo = mouseNo;
		this.name = name;
		this.brand = brand;
		this.stock = stock;
		this.price = price;
		this.connectType = connectType;
		this.weight = weight;
		this.dpi = dpi;
		this.releaseDate = releaseDate;
	}
	
	//새상품입력에서 사용할 생성자를 추가했습니다
		public Mouse(String name, String brand, int stock, int price, String connectType, int weight, int dpi,
				Date releaseDate) {
			super();
			this.name = name;
			this.brand = brand;
			this.stock = stock;
			this.price = price;
			this.connectType = connectType;
			this.weight = weight;
			this.dpi = dpi;
			this.releaseDate = releaseDate;
		}

	// 메소드부
	public int getMouseNo() {
		return mouseNo;
	}

	public void setMouseNo(int mouseNo) {
		this.mouseNo = mouseNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getConnectType() {
		return connectType;
	}

	public void setConnectType(String connectType) {
		this.connectType = connectType;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getDpi() {
		return dpi;
	}

	public void setDpi(int dpi) {
		this.dpi = dpi;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Mouse [mouseNo=" + mouseNo + ", name=" + name + ", brand=" + brand + ", stock=" + stock + ", price="
				+ price + ", connectType=" + connectType + ", weight=" + weight + ", dpi=" + dpi + ", releaseDate="
				+ releaseDate + "]";
	}
	
} // 클래스 영역 끝
