<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>B page</h1>
	<%
		System.out.println("bbbbbbbbbbb");
		response.sendRedirect("c.jsp"); // c.jsp페이지로 이동~
	%>
</body>
</html>