<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifypro</title>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<br/>
	<c:if test="${result==1}">
		<h3>${sessionScope.memId}님의 회원정보가 수정되었습니다.</h3>
		<button onclick="window.location='/member/main'">main</button>
		<button onclick="window.location='/member/mypage'">mypage</button>
	</c:if>
	<c:if test="${result!=1 }">
		<script type="text/javascript">
			alert("수정실패 다시 시도해주세요");
			history.go(-1);
		</script>
	</c:if>
</body>
</html>