<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br />
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
	</form>
	
</body>
</html>