<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>pro1 page</h2>
	<%
		// C -> S POST 방식으로 데이터 전송했을 때
		// getParameter() 하기 전에 먼저 캐릭터셋을 지정해준다.
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String msg = request.getParameter("msg");
		System.out.println(username + " : " + msg);
	
	%>
	<h3>username : <%=username%></h3>
	<h3>msg : <%=msg %></h3>
</body>
</html>