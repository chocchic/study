<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Main</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br />
	<h1 align="center"> 메인 페이지 </h1>
	<h3> memId : ${sessionScope.memId}</h3>
	
	<%-- 로그아웃 상태 --%>
	<c:if test="${sessionScope.memId == null}">
		<table>
			<tr>
				<td>
					로그인을 원하시면 버튼을 누르세요. <br />
					<button onclick="window.location='/member/login'">로그인</button>
				</td>
			</tr>
			<tr>
				<td>
					<a href="/member/signup" >회원가입</a>
				</td>
			</tr>
		</table>
	</c:if>
	
	<%-- 로그인 상태 --%>
	<c:if test="${sessionScope.memId != null}">  
		<table>
			<tr>
				<td>
					<button onclick="window.location='/member/mypage'">마이페이지</button>
				</td>
			</tr>
			<tr>
				<td>
					<button onclick="window.location='/member/logout'">로그아웃</button>
				</td>
			</tr>
		</table>
	</c:if>
	
	
	
	
	<br /><br /><br /><br />
	<div align="center">
		<img src="/resources/imgs/beach.jpg" width="500px" />
	</div>

</body>
</html>




