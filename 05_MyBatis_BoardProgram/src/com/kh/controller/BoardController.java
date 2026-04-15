package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.BoardService;
import com.kh.model.vo.Board;
import com.kh.view.BoardView;

public class BoardController { // 클래스 영역 시작

	public void selectAll() { // 메소드 영역 시작
		
		ArrayList<Board> list = new BoardService().selectAll();
		
		if(list.isEmpty()) {
			
			new BoardView().displayNodata("게시글이 존재하지 않습니다");
			
		} else {
			
			new BoardView().displayList(list);
		}
		
	} // 메소드 영역 끝

	public void insertBoard(String boardTitle, String boardWriter, String boardContent) { // 메소드 영역 시작
		
		Board b = new Board(boardTitle, boardWriter, boardContent);
		
		int result = new BoardService().insertBoard(b);
		
		if(result > 0) {
			
			new BoardView().displaySuccess("게시글 추가 성공");
			
		} else {
			
			new BoardView().displayFail("게시글 추가 실패");
		}
		
	} // 메소드 영역 끝

	public void selectByBoardTitle(String keyword) { // 메소드 영역 시작

		ArrayList<Board> list = new BoardService().selectByBoardTitle(keyword);
		
		if(list.isEmpty()) {
			
			new BoardView().displayNodata("검색한 게시글이 존재하지 않습니다.");
			
		} else {
			
			new BoardView().displayList(list);
		}
		
	} // 메소드 영역 끝

	public void updateBoard(int boardNo, String upBoardTitle, String upBoardContent) { // 메소드 영역 시작
		
		Board b = new Board();
		b.setBoardNo(boardNo);
		b.setBoardTitle(upBoardTitle);
		b.setBoardContent(upBoardContent);
		
		int result = new BoardService().updateBoard(b);
		
		if(result > 0) {
			
			new BoardView().displaySuccess("게시글 수정 성공");
			
		} else {
			
			new BoardView().displayFail("게시글 수정 실패");
		}
		
	} // 메소드 영역 끝

	public void deleteBoard(int boardNo) { // 메소드 영역 시작
		
		int result = new BoardService().deleteBoard(boardNo);
		
		if(result > 0) {
			
			new BoardView().displaySuccess("게시글 삭제 성공");
			
		} else {
			
			new BoardView().displayFail("게시글 삭제 실패");
		}
		
	} // 메소드 영역 끝

} // 클래스 영역 끝
