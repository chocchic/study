<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deletePro</title>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br/>
	<h3> deletePro</h3>
	<c:if test="${result==1}">
		<h3> 탈퇴 되셨습니다.</h3>
		<button onclick="window.location='/member/main'">main</button>
	</c:if>
	<c:if test="${result!=1}">
		<script type="text/javascript">
			alert("비밀번호가 잘못 입력되었습니다. 다시 입력해주세요.");
			history.go(-1);
		</script>
	</c:if>
	
</body>
</html>