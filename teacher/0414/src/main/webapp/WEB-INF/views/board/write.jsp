<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>write form</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br />
	<div align="center">
		<button onclick="window.location='/common/main'">메인</button> 
		<sec:authorize access="isAuthenticated()">
			<form action="/logout" method="post" style="display: inline-block;">
				<button onclick="window.location='/common/main'">로그아웃</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</sec:authorize>
	</div>
	<br /><br />
	<h1 align="center"> 글 작성 </h1>
	<form action="/board/write" method="post">
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" /></td>
			</tr>		
			<tr>
				<td>내용</td>
				<td><textarea rows="10" cols="30" name="content"></textarea></td>
			</tr>		
			<tr>
				<td>작성자</td> <%-- 작성자값에 사용자id(username) 띄우기 --%>
				<td><input type="text" name="writer" value='<sec:authentication property="principal.username" />' readonly="readonly" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="저장" />
					<input type="reset" value="재작성" />
					<input type="button" value="리스트" onclick="window.location='/board/list'" />
				</td>
			</tr>
		</table>
		<%-- 스프링시큐리티 적용시, post 요청에는 CRSF 토큰 값 보내줘야함. --%>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>


</body>
</html>