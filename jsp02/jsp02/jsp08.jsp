<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>초기화 파라미터</h2>
	<h4>logEnabled : <%=application.getInitParameter("logEnabled") %></h4>
	<h4>debugLevel : <%=application.getInitParameter("debugLevel") %></h4>
	
	<h2>파라미터 목록</h2>
	<ul>
	<%
		// 초기화 파라미터 목록 가져와 내용 추출
		Enumeration<String> pNames = application.getInitParameterNames();
		while(pNames.hasMoreElements()){
			String name = pNames.nextElement(); %>
			<li> <%=name%> = <%=application.getInitParameter(name) %></li>
	<% } %>
	</ul>
</body>
</html>