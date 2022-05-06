<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>modify</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br />
	<h1 align="center"> 글 수정 </h1>
	<form action="/board/modify" method="post">
		<input type="hidden" name="pageNum" value="${cri.pageNum}" />
		<input type="hidden" name="listQty" value="${cri.listQty}" />
		<table>
			<tr>
				<td>글번호</td>
				<td><input type="text" name="bno" value="${board.bno}" readonly /></td>
				<%--<td><input type="text" name="title" value='<c:out value="${board.bno}"/>' readonly /></td>  --%>
			</tr>		
			<tr>
				<td>제목</td>
				<td><input type="text" name="title"  value="${board.title}" /></td>
			</tr>		
			<tr>
				<td>내용</td>
				<td><textarea rows="10" cols="30" name="content">${board.content}</textarea></td>
			</tr>		
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer" value="${board.writer}"/></td>
			</tr>
			<tr>
				<td>작성일</td>
				<td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
			</tr>
			<tr>
				<td>수정일</td>
				<td><fmt:formatDate value="${board.updateDate}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정" />
					<input type="button" value="리스트" onclick="window.location='/board/list?pageNum=${cri.pageNum}&listQty=${cri.listQty}'" />
				</td>
			</tr>
		</table>
	</form>
	


</body>
</html>