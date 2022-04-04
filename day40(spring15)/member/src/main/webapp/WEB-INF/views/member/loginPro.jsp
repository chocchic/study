<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginPro</title>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br/>
	<h1 align="center">LOGIN Pro</h2>
	<c:if test="${result==1}">
		<script>
			alert("login success!!");
			window.location.href="/member/main";
		</script>
	</c:if>
	<c:if test="${result!=1}">
		<script>
			alert("login failed!! try again~");
			history.go(-1);
		</script>
	</c:if>
</body>
</html>