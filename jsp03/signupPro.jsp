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
	// 넘어온 파라미터 꺼내기
	// vo로 묶어주면서 꺼내주지 않아도 됨
/*	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String email = request.getParameter("email");
	String gender= request.getParameter("gender");
	int age = Integer.parseInt(request.getParameter("age"));
	String music = request.getParameter("music");
	String sports = request.getParameter("sports");
	String movies = request.getParameter("movies");
	String travel = request.getParameter("travel");
	
	String job = request.getParameter("job");
	String bio = request.getParameter("bio");
	*/
	// 한글 깨짐 방지
	request.setCharacterEncoding("UTF-8");
// VO에 꺼낸 데이터 담기
	TestVO vo = new TestVO();
	vo.setId(request.getParameter("id"));
	vo.setPw(request.getParameter("pw"));
	vo.setAge(Integer.parseInt(request.getParameter("age")));
	vo.setGender(request.getParameter("gender"));
	vo.setEmail(request.getParameter("email"));
	vo.setMusic(request.getParameter("music"));
	vo.setMovies(request.getParameter("movies"));
	vo.setTravel(request.getParameter("travel"));
	vo.setSports(request.getParameter("sports"));
	vo.setJob(request.getParameter("job"));
	vo.setBio(request.getParameter("bio"));
	// DB 접속하고, 파라미터들 전달해서 쿼리문 실행 -> DB 저장
	TestDAO dao = new TestDAO();
	//dao.insertMember(id, pw, email, gender,age,music,sports,movies,travel, job, bio);
	int result = dao.insertMember(vo); // -> 메서드 실행되고 DB 저장 까지 완료
%>
<body>
<% if (result == 1){ %>
	<h2> <%=vo.getId()%> 님 회원 가입 성공!</h2>
<%}else{ %>
	<h2> 회원 가입 실패.....</h2>
<%} %>
</body>
</html>