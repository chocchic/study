<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%-- 비로그인인 경우 --%>
	<c:if test="${sessionScope.memId !=null}">
		<script type="text/javascript">
			alert("이미 로그인 하셨습니다.");
			window.location.href="/member/main";
		</script>
	</c:if>
	<c:if test="${sessionScope.memId ==null}">
	<br/>
	<h1 align="center">LOGIN</h1>
	<form action="/member/login" method="post">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="id"/></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><input type="password" name="pw"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="checkbox" name="auto" value="1"/> 자동로그인
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="login"/>
					<input type="button" value="signup" onclick="window.location='/member/signup'"/>
					<input type="button" value="main" onclick="window.location='/member/main'"/>
				</td>
			</tr>
		</table>
	</form>
	</c:if>
</body>
</html>