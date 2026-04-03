package com.kh.member.run;

import com.kh.member.view.MemberView;

/*
 * * MVC 패턴
 * 
 * - 역할에 따라
 *   데이터 관련 (Model), 화면 관련 (View), 요청처리-기능 관련 (Controller)
 *   코드를 분류하여 개발하고 유지보수하는 기법
 *   
 *  - M : Model 의 약자
 *        데이터와 관련된 코드를 담는 부분
 *        데이터를 담는 용도의 VO (Value Object),
 *        데이터가 보관된 외부매체에 직접적으로 접근하여 데이터 입출력이 일어나는 DAO (Data Access Object)
 *  - V : View 의 약자
 *        화면 관련한 코드를 나타내는 부분
 *        CLI (Command Line Interface) 환경 상에서는 입력, 출력문을 통해 오로지 텍스트를 이용하여 화면을 짜고
 *        GUI (Graphic User Interface) 환경 상에서는 버튼, 텍스트상자 등 (html, css 등) 을 가지고 화면을 짤 수 있음!!
 *  - C : Controller 의 약자
 *        실제 사용자의 요청을 받아서 처리하는 기능 관련 코드를 담는 부분
 *        주로 사용자로부터 전달받은 입력값들을 VO 객체로 가공하여 DAO 로 넘겨주는 용도
 *        DAO 로 부터 전달받은 결과값에 따라 응답화면 (View) 을 지정
 *        
 *  * 코드의 흐름
 *  MemberRun --> MemberView --> MemberController --> MemberDao --> DB
 *  						 <--				  <--			<--				
 */

public class MemberRun { // 클래스 영역 시작

	public static void main(String[] args) { // main 영역 시작

		// 프로그램의 시작만 담당하고 끝
		// > MemberView 클래스의 메인화면 (제일 먼저 보여져야 하는 화면) 을 담당하는 메소드먼 호출하고 끝
		
		// MemberView mv = new MemberView();
		// mv.mainMenu();
		
		new MemberView().mainMenu();
		
	} // main 영역 끝

} // 클래스 영역 끝
