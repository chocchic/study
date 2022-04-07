<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br/>
	<%-- 로그인 안했으면 바로 로그인 페이지로 이동 --%>
	<c:if test="${sessionScope.memId ==null}">
		<script type="text/javascript">
			alert("로그인 후 사용 가능합니다.");
			window.location.href="/member/login";
		</script>
	</c:if>
		
	<c:if test="${sessionScope.memId!=null}">
	<h1 align="center">Mypage</h1>
	<table>
		<tr>
			<td><a href="/member/main">MAIN</a></td>
		</tr>
		<tr>
			<td><a href="/member/modify">회원정보 수정</a></td>
		</tr>
		<tr>
			<td><a href="/member/delete">회원정보 삭제</a></td>
		</tr>
	</table>
	</c:if>
</body>
</html>