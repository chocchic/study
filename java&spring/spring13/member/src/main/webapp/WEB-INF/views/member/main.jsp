<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1 align="center">Main Page</h1>
	<br />
	<h1 align="center"> 메인 페이지 </h1>
	
	
	<%-- 로그아웃 상태 --%>
	<c:if test="${sessionScope.memId == null}">
		<h3 align="right"> 로그인하세요</h3>
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
	<h3 align="right"> memId : ${sessionScope.memId}</h3>	  
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
	<%-- sessison 추가 전
	<table>
		<tr>
			<td>로그인을 원하시면 버튼을 누르세요<br/><button onclick="window.location='/member/login'">login</button></td>
		</tr>
		<tr>
			<td>
				<a href="/member/signup">signup</a>
			</td>
		</tr>
	</table>--%>
	<br/><br/><br/><br/>
	<div align="center">
		<img src="/resources/imgs/sample.jpg" width="800px"/>
	</div>
</body>
</html>