<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up Finished</title>
<link href="signupProstyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String id = request.getParameter("id");
		String bday = "무응답";
		if(request.getParameter("bday")!=null) bday = request.getParameter("bday");
		String addr = "무응답";
		if(request.getParameter("addr")!=null) addr = request.getParameter("addr");
		String addr2 = "무응답";
		if(request.getParameter("addr2")!=null) addr2 = request.getParameter("addr2");
		String teleNo = request.getParameter("localphone")+"-" + request.getParameter("telephone2")+"-" + request.getParameter("telephone3");
		String phoneNo = request.getParameter("phone1")+"-" + request.getParameter("phone2")+"-" + request.getParameter("phone3");
		String email = request.getParameter("email1")+"@"+request.getParameter("email2")+request.getParameter("emailself");
		String interest[] = request.getParameterValues("interest");
		String mail = null;
		if(request.getParameter("mailing").equals("mailingY")){
			mail = "메일링 동의";
		}else{
			mail = "메일링 반대";
		}
	%>
	<header>
        <div class = "hdr-title"><h2>회원 가입 성공!!</h2></div>
    </header>
	<section>
	<div class = "signup-info">
	<table>
	<tr>
		<td> 이름 : </td><td> <%=name %></td>
	</tr>
	<tr>
		<td>ID : </td><td><%=id%></td>
	</tr>
	<tr>
		<td>성별 : </td><td><%=gender %></td>
	</tr>
	<tr>
		<td>생일 : </td><td><%if(bday == null){ %>미기입<%}else{%> <%=bday %><%} %></td>
	</tr>
	<tr>
		<td>우편번호 : </td><td><%=addr %></td>
	</tr>
	<tr>
		<td>주소 : </td><td> <%=addr2 %></td>
	</tr>
	<tr>
		<td>전화번호 : </td><td><%=teleNo%></td>
	</tr>
	<tr>
		<td>휴대폰 번호 : </td><td><%=phoneNo%></td>
	</tr>
	<tr>
		<td>이메일 : </td><td><%=email%></td>
	</tr>
	<tr>
		<td>관심 정보 : </td><td><%if(interest != null){for(String i : interest) {%><%=i +" "%><%}}%></td>
	</tr>
	</tr>
		<td>메일링 여부 : </td> <td><%=mail %></td>
	</tr>
	</table>
	</div>
	</section>
</body>
</html>