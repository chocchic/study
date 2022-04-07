<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signupPro</title>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>Signup Pro</h1>
	
	<%-- 회원가입 성공시 --%>	
	<c:if test='${result == 1}'>
		<h2> ${member.id}님, 회원가입 성공!!</h2>
		<button onclick="window.location='/member/main'">main</button>
		<%-- 3초 뒤 main으로 이동 --%>
		<meta http-equiv="refresh" content="3;url=http://localhost:8080/member/main">
	</c:if>
	
	<%-- 회원가입 실패시 --%>
	<c:if test='${result != 1}'>
		<h2> 회원가입 실패... 다시 시도해주세요.</h2>
		<button onclick="window.location='/member/main'">main</button>
		<button onclick="window.location='/member/signup'">signup</button>
	</c:if>
	
	
</body>
</html>