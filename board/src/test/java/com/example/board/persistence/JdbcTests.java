package com.example.board.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class JdbcTests {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			driver를 메모리에 올리는법
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}			
	}
	
	@Test
	public void testConnection() {
		//외부장치를 열어준다.
		try(Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr")) {
			log.info(connection); // connection 객체를 뽑아온다.
		} catch (Exception e) {
			fail(e.getMessage()); //JUnit에 있는 메소드 => 무조건 실패라고 처리 후 실행을 중지시킴, 강제중지
		}
	}
}
