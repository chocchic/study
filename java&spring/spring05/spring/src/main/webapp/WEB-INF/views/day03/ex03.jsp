<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>EX03 page</h1>
<p><%=request.getAttribute("name") %>님 안녕하세요!</p>
<p>제일 좋아하는 것 : ${name2}</p>
</body>
</html>