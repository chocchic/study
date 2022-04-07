<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>modify</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<br />
	<h1 align="center"> 글 수정 </h1>
	<form action="/board/modify" method="post">
		<input type="hidden" name="pageNum" value="${cri.pageNum}" />
		<input type="hidden" name="listQty" value="${cri.listQty}" />
		<input type="hidden" name="type" value="${cri.type}" />
		<input type="hidden" name="keyword" value="${cri.keyword}" />
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
					<button type="submit" class="btn" data-oper="modify" >수정</button>
					<button type="submit" class="btn" data-oper="delete">삭제</button>
					<button type="submit" class="btn" data-oper="list">리스트</button>
				</td>
			</tr>
		</table>
	</form>
	<script>
	$(document).ready(function(){
		let formObj = $("form"); 
		$("button").click(function(e){
			e.preventDefault(); 			
			let operation = $(this).data("oper");
			if(operation === 'delete') {
				formObj.attr("action", "/board/delete"); // 삭제폼 GET 
				formObj.attr("method", "get"); // form 태그에 method를 post로 해놓아서 
												// 전송전에 get방식으로 변경 
			}else if(operation === 'list'){
				formObj.attr("action", "/board/list"); // 게시판리스트 GET
				formObj.attr("method", "get");
			}
			formObj.submit();
		});
	});
	</script>


</body>
</html>