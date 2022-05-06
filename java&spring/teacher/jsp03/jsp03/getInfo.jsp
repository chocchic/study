<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	//DB 접속 해서 받아온 데이터 저장.
	// 1. 드라이버 로딩 
	Class.forName("oracle.jdbc.driver.OracleDriver");
	// 2. 커넥션 객체 생성 
	String user = "gangsa", passwd = "1234";
	String url = "jdbc:oracle:thin:@javaking.iptime.org:1521:ORCL";
	Connection conn = DriverManager.getConnection(url, user, passwd); 
	// 3. SQL 작성, PreparedStatement 객체 생성 
	//String id = "java";
	String sql = "select * from test where id = ?";
	PreparedStatement pstmt = conn.prepareStatement(sql);	
	pstmt.setString(1, "java");
	
	// 4. 쿼리 실행 
	ResultSet rs = pstmt.executeQuery(); 
	if(rs.next()){
		String id = rs.getString("id");
		String pw = rs.getString("pw");
		int age = rs.getInt("age"); 
		Timestamp reg = rs.getTimestamp("reg"); %>
		
		<h2>id : <%= id %></h2>
		<h2>pw : <%= pw %></h2>
		<h2>age : <%= age %></h2>
		<h2>reg : <%= reg %></h2>
		
<%	}
 
	// 객체 닫기 
	rs.close();
	pstmt.close(); 
	conn.close(); 
%>

<body>

</body>
</html>