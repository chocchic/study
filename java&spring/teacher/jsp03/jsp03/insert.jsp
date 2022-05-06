<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	// form 보낸 데이터 꺼내기 
	String id = request.getParameter("id"); // (drop table test)
	String pw = request.getParameter("pw");
	// getParameter는 String으로만 리턴, 숫자로 사용하고싶으면 형변환 필요 
	int age = Integer.parseInt(request.getParameter("age"));  
	
	// DB 접속 해서 받아온 데이터 저장.
	// 1. 드라이버 로딩 
	Class.forName("oracle.jdbc.driver.OracleDriver");
	// 2. 커넥션 객체 생성 
	String user = "gangsa", passwd = "1234";
	String url = "jdbc:oracle:thin:@javaking.iptime.org:1521:ORCL";
	Connection conn = DriverManager.getConnection(url, user, passwd); 
	// 3. SQL 작성, PreparedStatement 객체 생성 
	//String sql = "insert into test values(" + id + "," + pw + "," + age + ", sysdate)"; //보안 취약
	String sql = "insert into test values(?,?,?,sysdate)";
	PreparedStatement pstmt = conn.prepareStatement(sql); // sql문 주고 PreparedStatement받아오기 
	pstmt.setString(1, id); // 물음표에 해당하는 값 체우기 
	pstmt.setString(2, pw); 
	pstmt.setInt(3, age);
	// 4. 쿼리 실행 
	int resultRowCount = pstmt.executeUpdate(); 
	
	// 5. 닫기 
	pstmt.close(); 
	conn.close();
	
%>
<body>
	<h2> insert page </h2>
	<h3> id : <%= id %></h3>
	<h3> pw : <%= pw %></h3>
	<h3> age : <%= age %></h3>
	<h3> 쿼리 실행후 결과 숫자값 : <%= resultRowCount %></h3>
	
</body>
</html>