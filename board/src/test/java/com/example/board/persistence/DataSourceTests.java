package com.example.board.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j

public class DataSourceTests {
	
	@Autowired
	private DataSource dataSource;
	//javax.sql xml에 빈을 등록해 놓았기 때문에 사용할 수 있는것이다.
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testConnection() {
		try
		(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
				Connection connection = sqlSession.getConnection();
		) {
			log.info(sqlSession);
			log.info(connection);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
}
