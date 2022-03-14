<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Date"
    session="true"
    buffer="8kb"
    autoFlush="true"
    isThreadSafe="true"
    info="화면에 출력 안되는 페이지 관련 메모 작성하는 속성"
    errorPage="error.jsp"
    isErrorPage="false"
    isELIgnored="false"
    deferredSyntaxAllowedAsLiteral="false"
    %>
   	<%-- el : #{변수}. ${변수} --%>
    <!-- html 주석 : 페이지 소스 보기 하면 내용물이 다 보임 : 브라우저에서 읽어들임-->
    <%-- jsp 주석 : 페이지 소스보기 해도 내용물이 안보임 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= new Date() %> <%-- 스크립트 출력문 : 자바코드 출력 --%>
	<%-- // 스크립트릿
		// 자바코드 작성 부분
		for(int i = 0; i< 10000;i++){ --%>
			<%-- html로 작성 --%>
			hello (html상 일반텍스트)
	<%-- }
	--%>
	<% int n = 0/0;%>
	<%-- 서블릿 버전 --%>
	<br/>
	서버 : <%= application.getServerInfo() %><br/>
	서블릿 : <%= application.getMajorVersion() %>.<%=application.getMinorVersion() %><br/>
	jsp : <%=JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %>
</body>
</html>