<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%
	for(int i = 0; i < 10; i++){
		//if(i % 2 == 0) out.println(i + "<br/>");		
	}
	int count = 0;
	int a = 1; int b = 2; int c = 3;
	Date day = new Date();
%>

<h2><%=++count%></h2>
<h2><%=a+b+c%></h2>
<h2><%=day%></h2>
<h2> jsp 주석</h2>
<%--
<h1> hello</h1>
<h1> hello</h1>
<h1> hello</h1>
 --%>
<h2> html 주석</h2>
<!--
<h1> hello</h1>
<h1> hello</h1>
<h1> hello</h1>
 -->