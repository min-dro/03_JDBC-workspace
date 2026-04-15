package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.BoardController;
import com.kh.model.vo.Board;

public class BoardView { // 클래스 영역 시작

	private Scanner sc = new Scanner(System.in);
	private BoardController bc = new BoardController();
	
	public void mainMenu() { // 메소드 영역 시작
		while(true) {
			
			System.out.println("1. 게시글 전제 조회 하기");
			System.out.println("2. 게시글 작성 하기");
			System.out.println("3. 게시글 제목으로 검색 하기");
			System.out.println("4. 게시글 내용 수정 하기");
			System.out.println("5. 게시글 삭제 하기");
			System.out.println("0. 프로그램 종료하기");
			
			System.out.print("입력 : ");
			int num = sc.nextInt();
			sc.nextLine();
			
			switch(num) {
			case 1:
				selectAll();
				break;
			case 2:
				insertBoard();
				break;
			case 3:
				selectByBoardTitle();
				break;
			case 4:
				updateBoard();
				break;
			case 5:
				deleteBoard();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			default :
				System.out.println("잘못 입력했습니다. 다시입력해주세요.");
			}
			
		}
		
	} // 메소드 영역 끝
	
	public void selectAll() { // 메소드 영역 시작
		
		System.out.println("\n--- 게시글 전체 조회 하기 ---");
		
		bc.selectAll();
		
	} // 메소드 영역 끝
	
	public void insertBoard() { // 메소드 영역 시작
		
		System.out.println("\n--- 게시글 작성 하기 ---");
		
		System.out.print("게시글제목 입력 : ");
		String boardTitle = sc.nextLine();
		
		System.out.print("작성자명 입력 : ");
		String boardWriter = sc.nextLine();
		
		System.out.print("게시글내용 입력 : ");
		String boardContent = sc.nextLine();
		
		bc.insertBoard(boardTitle, boardWriter, boardContent);
		
	} // 메소드 영역 끝
	
	public void selectByBoardTitle() { // 메소드 영역 시작
		
		System.out.println("\n--- 게시글 제목으로 검색 하기 ---");
		
		System.out.print("검색어 입력 : ");
		String keyword = sc.nextLine();
		
		bc.selectByBoardTitle(keyword);
	} // 메소드 영역 끝
	
	public void updateBoard() { // 메소드 영역 시작
		
		System.out.println("\n --- 게시글 내용 수정 하기 ---");
		
		System.out.print("수정할 게시글번호 입력 : ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		System.out.print("변경할 게시글제목 입력 : ");
		String upBoardTitle = sc.nextLine();
		
		System.out.print("변경할 게시글내용 입력 : ");
		String upBoardContent = sc.nextLine();
		
		bc.updateBoard(boardNo, upBoardTitle, upBoardContent);
		
	} // 메소드 영역 끝
	
	public void deleteBoard() { // 메소드 영역 시작
		
		System.out.println("\n--- 게시글 삭제 하기 ---");
		
		System.out.print("삭제할 게시글번호 입력 : ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		bc.deleteBoard(boardNo);
		
	} // 메소드 영역 끝
	
	public void displaySuccess(String message) { // 메소드 영역 시작
		
		System.out.println("서비스 요청 성공 : " + message);
		
	} // 메소드 영역 끝
	
	public void displayFail(String message) { // 메소드 영역 시작
			
		System.out.println("서비스 요청 실패 : " + message);
			
	} // 메소드 영역 끝
	
	public void displayNodata(String message) { // 메소드 영역 시작
		
		System.out.println(message);
		
	} // 메소드 영역 끝
	
	public void displayList(ArrayList<Board> list) { // 메소드 영역 시작
		
		System.out.println("조회된 리스트는 다음과 같습니다 : " + list.size() + "건");
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	} // 메소드 영역 끝

} // 클래스 영역 끝
