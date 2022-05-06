<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
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
	<h1 align="center"> 게시글 삭제 </h1>
	<form action="/board/delete" method="post">
		<table>
			<tr>
				<td> [ #${board.bno}. ${board.title} ] <br />
					이 게시글을 삭제 하시겠습니까?  <br /><br />
					<input type="hidden" name="bno" value="${board.bno}" />
					<input type="hidden" name="pageNum" value="${cri.pageNum}" />
					<input type="hidden" name="listQty" value="${cri.listQty}" />
					<input type="hidden" name="type" value="${cri.type}" />
					<input type="hidden" name="keyword" value="${cri.keyword}" />
					<input type="submit" value="삭제확인" />
					<input type="button" value="취소" onclick="history.back()" />
				</td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	
</body>
</html>