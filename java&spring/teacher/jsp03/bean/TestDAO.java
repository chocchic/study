package web.jsp03.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// DAO : DB 관련 작업을 해줄 클래스(자바빈) 
public class TestDAO {

	// 커넥션 
	private Connection getConnection() throws Exception {
		// 서버의 컨텍스트(설정)정보를 가져오기위한 객체 생성
		Context ctx = new InitialContext(); 
		// 위에서 가져온 설정정보중 자바 환경 설정관련만 찾아오기 
		Context env = (Context)ctx.lookup("java:comp/env"); 
		// 환경 설정에서 <Resource 태그 정보를 name속성값을 이용해 가져오기 
		DataSource ds = (DataSource)env.lookup("jdbc/orcl");
		// Resource 를 이용해 커넥션 리턴해주기 
		return ds.getConnection();
	}
	
	// DB에 레코드 추가 
	public int insertMember(TestVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		int result = -1;  // 레코드 수정된 행의 수 결과로 돌려주기위해 변수 미리 선언 
		try {
			conn = getConnection(); // 커넥션 얻어오기 
			// SQL 문작성, 쿼리문 주고 pstmt얻어오기, 물음표(place holder)체워주기 
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
			// 쿼리문 실행, 변경된(추가된) 레코드 행의 수 리턴 
			result = pstmt.executeUpdate(); 
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null)try {pstmt.close();}catch(Exception e) { e.printStackTrace();}
			if(conn != null)try {conn.close();}catch(Exception e) { e.printStackTrace();}
		}
		return result;
	}
	// 회원 전체 정보 가져오기 
	public List selectAll() {
		List memberList = new ArrayList(); // 마지막에 회원목록들 정리해서 리턴해줄 변수만 미리선언 
		// DB 작업시 필요한 객체들 변수만 미리 선언 
		// 이유는, try안에서 만들면 finally에서 닫아줄때 변수 유효범위에 걸리니.
		// 미리 밖에다 만들어놓고 try안에서 값을 체워 사용하자
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		try {
			conn = getConnection(); 
			String sql = "select * from signup"; 
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리문 실행, 결과는 rs에 담기 
			rs = pstmt.executeQuery(); 
			
			// 레코드가 있는만큼 반복해라~~~ 
			while(rs.next()) { // 요소가 있는지 t/f받고, 커서는 내려가서 뽑을 준비됨.
				TestVO vo  = new TestVO(); // 한사람의 정보를 담을 객체 생성하기 
				vo.setId(rs.getString("id"));
				vo.setPw(rs.getString("pw"));
	            vo.setEmail(rs.getString("email"));
	            vo.setGender(rs.getString("gender"));
	            vo.setAge(rs.getInt("age"));
	            vo.setMusic(rs.getString("music"));
	            vo.setSports(rs.getString("sports"));
	            vo.setMovies(rs.getString("movies"));
	            vo.setTravel(rs.getString("travel"));
	            vo.setJob(rs.getString("job"));
	            vo.setBio(rs.getString("bio"));
	            vo.setReg(rs.getTimestamp("reg"));
	            memberList.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close();}catch(Exception e) { e.printStackTrace();}
			if(pstmt != null) try { pstmt.close();}catch(Exception e) { e.printStackTrace();}
			if(conn != null) try { conn.close();}catch(Exception e) { e.printStackTrace();}
		}
		return memberList;
	}
	
	
	// 회원정보한개 가져오기 
	// 회원정보한개 수정 
	
	
}
