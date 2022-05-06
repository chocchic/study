<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>login</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%-- 로그인 이미 했을때, main 돌리기 --%>
	<c:if test="${sessionScope.memId != null}"> 
		<script>
			alert("이미 로그인 하셨습니다."); 
			window.location.href="/member/main"; 
		</script>
	</c:if>
	
	<%-- 비로그인시, 로그인폼 보이게  --%>
	<c:if test="${sessionScope.memId == null}">
		<br />
		<h1 align="center"> 로그인 </h1>
		<form action="/member/login" method="post">
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id" /></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pw" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="auto" value="1" /> 자동로그인
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="로그인" />
						<input type="button" value="회원가입" onclick="window.location='/member/signup'" />
						<input type="button" value="메인" onclick="window.location='/member/main'" />
					</td>
				</tr>
			</table>
		</form>
	</c:if>
	
	
</body>
</html>