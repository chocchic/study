<%@page import="web.jsp03.bean.TestVO"%>
<%@page import="web.jsp03.bean.TestDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("UTF-8"); // 한글깨짐방지. 
	// 넘어온 파라미터 꺼내서 VO에 담기  
	TestVO vo = new TestVO();
	vo.setId(request.getParameter("id"));
	vo.setPw(request.getParameter("pw"));
	vo.setEmail(request.getParameter("email"));
	vo.setGender(request.getParameter("gender"));
	if(request.getParameter("age") != null){
		vo.setAge(Integer.parseInt(request.getParameter("age")));
	}else{
		vo.setAge(1);
	}
	vo.setMusic(request.getParameter("music"));
	vo.setSports(request.getParameter("sports"));
	vo.setMovies(request.getParameter("movies"));
	vo.setTravel(request.getParameter("travel"));
	vo.setJob(request.getParameter("job"));
	vo.setBio(request.getParameter("bio"));
	
	// DB 접속하고, 파라미터들 전달해서 쿼리문실행 -> DB 저장 
	TestDAO dao = new TestDAO(); 
	int result = dao.insertMember(vo); // -> 메서드 실행되고 DB 저장까지 완료 

%>
<body>

	<% if(result == 1){ %>
		<h2> <%= vo.getId() %> 님 회원 가입 성공! </h2>
	<%}else{%>
		<h2> 회원가입 실패......</h2>
	<%} %>
	
</body>
</html>









