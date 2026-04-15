package com.kh.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template { // 클래스 영역 시작

	public static SqlSession getSqlSession() { // 메소드 영역 시작
		
		SqlSession sqlSession = null;
		
		String resource = "mybatis-config.xml";
		
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			
			SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
			
			SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
			
			sqlSession = sqlSessionFactory.openSession();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSession;
		
	} // 메소드 영역 끝
	
} // 클래스 영역 끝
