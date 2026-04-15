package com.kh.model.vo;

import java.sql.Date;

public class Board { // 클래스 영역 시작

	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private Date createDate;
	
	public Board() {}

	public Board(int boardNo, String boardTitle, String boardWriter, String boardContent, Date createDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.createDate = createDate;
	}

	public Board(String boardTitle, String boardWriter, String boardContent) {
		super();
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
	}

	public int getBoardNo() { // 메소드 영역 시작
		return boardNo;
	} // 메소드 영역 끝

	public void setBoardNo(int boardNo) { // 메소드 영역 시작
		this.boardNo = boardNo;
	} // 메소드 영역 끝

	public String getBoardTitle() { // 메소드 영역 시작
		return boardTitle;
	} // 메소드 영역 끝

	public void setBoardTitle(String boardTitle) { // 메소드 영역 시작
		this.boardTitle = boardTitle;
	} // 메소드 영역 끝

	public String getBoardWriter() { // 메소드 영역 시작
		return boardWriter;
	} // 메소드 영역 끝

	public void setBoardWriter(String boardWriter) { // 메소드 영역 시작
		this.boardWriter = boardWriter;
	} // 메소드 영역 끝

	public String getBoardContent() { // 메소드 영역 시작
		return boardContent;
	} // 메소드 영역 끝

	public void setBoardContent(String boardContent) { // 메소드 영역 시작
		this.boardContent = boardContent;
	} // 메소드 영역 끝

	public Date getCreateDate() { // 메소드 영역 시작
		return createDate;
	} // 메소드 영역 끝

	public void setCreateDate(Date createDate) { // 메소드 영역 시작
		this.createDate = createDate;
	} // 메소드 영역 끝

	@Override
	public String toString() { // 메소드 영역 시작
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardContent=" + boardContent + ", createDate=" + createDate + "]";
	} // 메소드 영역 끝
	
} // 클래스 영역 끝
