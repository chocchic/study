package com.test.persistence;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

// JDBC 드라이버가 배치
@Log4j
public class JDBCTests {
	// 초기화 블럭에서 oracle 블럭 로딩
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection(){
		try(Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@javaking.iptime.org:1521:ORCL","java12","java12")){
			log.info(conn);
		}catch(Exception e) {
			fail(e.getMessage());
		}
		
	}

}
