<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2> Test 01 Page</h2>
	<p>이름 : ${name}</p>
	<p>age : ${age }</p>
	<p>requestscope.name : ${requestScope.name}</p>
	<p>requestscope.age : ${requestScope.age}</p>
	<p>sessionScope.name : ${sessionScope.name}</p>
	<p>sessionScope.age : ${sessionScope.age}</p>
<br/>
	<%-- 
	http://localhost:8080/day04/test01?abc=123 
		아래처럼 ${param.파라미터명} 요청시 넘어온 파라미터 값 출력 가능
	--%>
		<p>${param.abc}</p>
	<br/>
	<%-- 값이 없거나 null인 경우는 화면엥 출력 안됨. Error X --%>
	<p>${empty hahaha}</p>
	<%--<p>위에 찍혔니? <%if(%>${empty hahaha}<%){%>아니<% }%></p> --%>
	<br/>
	<p>${arr[0] }</p>

	<p>age>10 : ${age>10}</p>
	<p>null + 10: ${null+10}</p>
	<p>${"10"+10 }</p>
	
	<p>요청URI : ${pageContext.request.requestURI}</p>
</body>
</html>