package com.test.persistence;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import lombok.extern.log4j.Log4j;

// JDBC 드라이버 배치가 잘 되었는지 확인하는 테스트 
@Log4j
public class JDBCTests {

	// 초기화 블럭에서 oracle 드라이버 로딩 
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		
		try (Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@javaking.iptime.org:1521:ORCL",
				"gangsa", 
				"1234")){
			
			log.info(conn);
			
		}catch(Exception e) {
			fail(e.getMessage());
		}
		
	}

}
