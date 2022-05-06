<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>mypage</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%-- 로그인 안했을때, alert 띄우고 로그인폼으로 이동 --%>
	<c:if test="${sessionScope.memId == null}"> 
		<script>
			alert("로그인 후 사용가능합니다."); 
			window.location.href="/member/login"; 
		</script>
	</c:if>
	<%-- 로그인 했을때 보여주기  --%>
	<c:if test="${sessionScope.memId != null}"> 
		<br />
		<h1 align="center"> 마이페이지 </h1>
		<table>
			<tr>
				<td><a href="/member/modify">회원정보수정</a></td>
			</tr>
			<tr>
				<td><a href="/member/delete">회원탈퇴</a></td>
			</tr>
			<tr>
				<td><a href="/member/main">메인</a></td>
			</tr>
		</table>
	</c:if>

</body>
</html>