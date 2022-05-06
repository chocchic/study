<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>signupPro</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<h2> signup Pro </h2>
	
	<%-- 회원 가입 성공시 --%>
	<c:if test="${result == 1}">
		<h2>회원 가입 성공! ${member.id} 님 환영합니다. </h2>
		<button onclick="window.location='/member/main'">메인으로</button>
		<%-- 3초후 main으로 이동 --%>
		<meta http-equiv="refresh" content="3;url=http://localhost:8080/member/main" >
	</c:if>
	<%-- 회원 가입 실패시 --%>
	<c:if test="${result != 1}">
		<h2> 회원가입 실패... 다시 시도해주세요. </h2>
		<button onclick="window.location='/member/main'">메인으로</button>
		<button onclick="window.location='/member/signup'">회원가입</button>
	</c:if>


</body>
</html>