<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c로 시작하는 태그는 http://java.sun.com/jsp/jstl/core에서 해석한 내용으로 변경.
 여기서 uri는 웹 주소형태로 되어있지만 실제론 jstl.jar파일에서 해석 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Spring에서 JSP 출력하기</title>
</head>
<body>
	<div>
		<table>
			<tr>
				<th>언어</th>
				<th>데이터베이스</th>
				<th>프레임워크</th>
				<th>IDE</th>
			</tr>
			<tr>
			<td>${map.language}</td>
			<td>${map.database}</td>
			<td>${map.framework}</td>
			<td>${map.IDE}</td>
			</tr>
		</table>
	</div>
	<div>
		<table>
			<c:forEach items="${list}" var="task">
				<tr>
					<td>${task}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>