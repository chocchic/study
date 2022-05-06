<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>deletePro</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<br />
	<form action="/logout" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<c:if test="${result == 1}">
		<script>
		$(document).ready(function(){
			let formObj = $("form");
			alert("회원 탈퇴 되셨습니다."); 
			formObj.submit(); 
		});
		</script>
	</c:if>
	<c:if test="${result != 1}">
		<script>
			alert("비밀번호를 잘못 입력하셨습니다... 다시 입력해주세요."); 
			history.go(-1); 
		</script>
	</c:if>
	
	

</body>
</html>