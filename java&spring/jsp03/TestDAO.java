package web.jsp03.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// DAO : DB 관련 작업을 해줄 클래스(자바빈)
public class TestDAO {
	//DB 접속, 쿼리문도 실행 결과도 받아올 것
	// 커넥션
	private Connection getConnection() throws Exception {
		 // 서버의 컨텍스트(설정)정보를 가져오기 위해 객체 생성
		Context ctx = new InitialContext();
		// 위에서 가졍노 설정 정보중 자바 환경 설정 관련만 찾아오기
		Context env = (Context)ctx.lookup("java:comp/env");
		// 환경 설정에서 <Resource 태그 정보를 name 속성값을 이용해 가져오기
		DataSource ds = (DataSource)env.lookup("jdbc/orcl");
		// Resource를 이용해 커넥션 리턴해주기
		return ds.getConnection();
	}
	
	//DB에 레코드 추가
	public int insertMember(TestVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			conn = getConnection(); // 커넥션 얻어오기
			//SQL문 작성, 쿼리문 주고 pstmt 얻어오기, 물음표(place holder)채워주기
			String sql = "insert into signup values(?,?,?,?,?,?,?,?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getGender());
			pstmt.setInt(5, vo.getAge());
			pstmt.setString(6, vo.getMusic());
			pstmt.setString(7, vo.getSports());
			pstmt.setString(8, vo.getMovies());
			pstmt.setString(9, vo.getTravel());
			pstmt.setString(10, vo.getJob());
			pstmt.setString(11, vo.getBio());
			//쿼리문 실행
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try{pstmt.close();} catch(Exception e) {e.printStackTrace();}
			if(conn != null) try{conn.close();} catch(Exception e) {e.printStackTrace();}
		}
		System.out.println(result);
		return result;
	}
	
	// 회원정보한개 가져오기
	
	// 회원정보 한개 수정
}
