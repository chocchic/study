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
	
	<br />
	<h1 align="center"> 로그인 </h1>
	<form action="/login" method="post">  <%-- 로그인처리는 시큐리티가 해주므로 경로와 method 주의  --%>
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="username" /></td>  <%-- 스프링시큐리티에 맞게 name속성 주의 --%>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="checkbox" name="remember-me" /> Remember Me
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="로그인" />
					<input type="button" value="회원가입" onclick="window.location='/common/signup'" />
					<input type="button" value="메인" onclick="window.location='/common/main'" />
				</td>
			</tr>
		</table>
		<%-- sec#12. 스프링시큐리티 적용시, post 요청에는 CRSF 토큰 값 보내줘야함. --%>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	
	<%-- sec#11. 컨트롤러에서 전달한 값 화면에 출력 추가  --%>
	<h2> ${error} </h2>
	<h2> ${logout} </h2>
	
	
</body>
</html>