<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>write form</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br />
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
				<td>작성자</td>
				<td><input type="text" name="writer" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="저장" />
					<input type="reset" value="재작성" />
					<input type="button" value="리스트" onclick="window.location='/board/list'" />
				</td>
			</tr>
		</table>
	</form>


</body>
</html>