<%@page import="web.jsp03.bean.TestVO"%>
<%@page import="java.util.List"%>
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
	// DB에서 전체 회원정보를 모두 가져와 화면에 출력하고 싶다 
	// -> DB 접속해서 모든 회원정보 가져오고 
	TestDAO dao = new TestDAO(); 
	List memberList = dao.selectAll(); // 모든 회원정보가져오는 기능의 메서드 호출해서 받아오기 
	// 아래 화면에 출력 
%>
<body>
	<%
		for(int i = 0; i < memberList.size(); i++){
			TestVO vo = (TestVO)memberList.get(i); %>
			<h3> 회원 : <%= i+1 %></h3>
			<table border="1">
				<tr>
					<td>ID *</td>
					<td><%= vo.getId() %></td>
				</tr>
				<tr>
					<td>Password *</td>
					<td><%= vo.getId() %></td>
				</tr>
				<tr>
					<td>Email *</td>
					<td><%= vo.getEmail() %></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td>
						<%=vo.getGender() %>
					</td>
				</tr>
				<tr>
					<td>Age</td>
					<td>
						<%= vo.getAge() %>
					</td>
				</tr>
				<tr>
					<td>Hobbies</td>
					<td>
						<%= vo.getMusic() %>, <%= vo.getMovies() %>, <%= vo.getSports() %>, <%=vo.getTravel() %>
					</td>
				</tr>
				<tr>
					<td>Job</td>
					<td>
						<%= vo.getJob() %>
					</td>
				</tr>
				<tr>
					<td>Bio</td>
					<td>
						<textarea name="bio" rows="10" cols="30"><%=vo.getBio() %></textarea>
					</td>
				</tr>
			</table>
	<%	}
	%>
	
	
</body>
</html>