<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>access denied</title>
</head>
<body>
	<h1> Access Denied Page </h1>

	<h3> ${SPRING_SECURITY_403_EXCEPTION.getMessage()} </h3>
	<h3> ${msg} </h3>

</body>
</html>