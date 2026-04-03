package com.kh.mouse.common;

import java.sql.Date;
import java.text.SimpleDateFormat;


// String 타입을 DATE로 형변환하여 돌려주는 목적의 클래스입니다.
public class MyDateTool {//클래스 시작
	
	
	public static Date convertToDate(String releaseDate) {
		
		//null처리
		if (releaseDate == null || releaseDate.isBlank()) {
			return null;
		}
		
		// 데이터 가공 - (표준 규격 YYYY-MM-DD)
		// 사용자가 YYYYMMDD,YYMMDD, YYYY/MM/DD 형식을 사용한다고 가정
		String coverDate = releaseDate;
		switch(releaseDate.length()) {
		
		case 6 :  //--YYMMDD
			coverDate = "20" + releaseDate; 
			break;
		case 8 :  //--YYYYMMDD
			coverDate = releaseDate;
			break;
		case 10 : //--YYYY/MM/DD, YYYY-MM-DD, YYYY.MM.DD
			coverDate = releaseDate.substring(0,4) 
					  + releaseDate.substring(5,7) 
					  + releaseDate.substring(8,10);
			break;
			
		// 정상적인 데이터가 아닐경우 null로 반환
		default : return null;
		}
		
		// 지속적인 서비스공급을 위한 예외처리
		try {
			// 정상적으로 포맷시 date 타입을 반환
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			sdf.setLenient(false);
			
			java.util.Date utilDate = sdf.parse(coverDate);
			return new java.sql.Date(utilDate.getTime());
			
        } catch (Exception e) {
        	// 정상적인 데이터가 아닐경우 null로 반환
            return null;
        }
		
	}
	
	
	
	
}//클래스 끝
