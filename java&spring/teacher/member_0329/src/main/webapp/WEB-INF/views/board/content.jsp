<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>content</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<br />
	<h1 align="center"> Content </h1>
	<table>
		<tr>
			<td>글번호</td>
			<td>${board.bno}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${board.title}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.writer}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea rows="10" cols="30" disabled>${board.content}</textarea></td>
		</tr>
		<tr>
			<td>작성일</td>
			<td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
		</tr>
		<tr>
			<td>수정일</td>
			<td><fmt:formatDate value="${board.updateDate}" pattern="yyyy-MM-dd HH:mm:SS"  /></td>
		</tr>
		<tr>
			<td colspan="2">
				<button onclick="window.location='/board/modify?bno=${board.bno}'">수정</button>
				<button onclick="window.location='/board/delete?bno=${board.bno}'">삭제</button>
				<button onclick="window.location='/board/list'">리스트</button>
			</td>
		</tr>
	</table>

</body>
</html>