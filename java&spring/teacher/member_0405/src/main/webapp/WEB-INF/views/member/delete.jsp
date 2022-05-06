<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>delete</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%-- 비로그인, --%>
	<c:if test="${sessionScope.memId == null}"> 
		<script>
			alert("로그인 후 사용가능합니다."); 
			window.location.href="/member/login"; 
		</script>
	</c:if>

	<%-- 로그인시,  --%>
	<c:if test="${sessionScope.memId != null}">
		<br />
		<h1 align="center"> 회원 탈퇴 </h1>
		<form action="/member/delete" method="post">
			<table>
				<tr>
					<td> 
						탈퇴를 원하시면 비밀번호를 입력해주세요. <br />
						<input type="password" name="pw" />
					</td>
				</tr>
				<tr>
					<td> 
						<input type="submit" value="회원 탈퇴" />
						<input type="button" value="취소" onclick="window.location='/member/mypage'" />
					</td>
				</tr>
			</table>
		</form>
	</c:if>
</body>
</html>