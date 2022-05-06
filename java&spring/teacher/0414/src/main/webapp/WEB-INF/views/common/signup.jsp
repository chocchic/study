<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br />
	<h1 align="center"> 회원 가입 </h1>
	<form action="/common/signup" method="POST">
		<table>
			<tr>
				<td>권한</td> <%-- 약식으로 일반회원, 관리자 가입 라디오로 분리 (실제로는 일반회원가입하고 관리자가 등급 올려주는것이 좋다) --%>
				<td>
					<input type="radio" name="au" value="member" checked="checked" /> 일반회원 
					<input type="radio" name="au" value="admin" /> 관리자
				</td>
			</tr>		
			<tr>
				<td>아이디*</td>
				<td><input type="text" name="id" /></td>
			</tr>		
			<tr>
				<td>비밀번호*</td>
				<td><input type="password" name="pw" /></td>
			</tr>		
			<tr>
				<td>비밀번호 확인*</td>
				<td><input type="password" name="pwch" /></td>
			</tr>		
			<tr>
				<td>이름*</td>
				<td><input type="text" name="name" /></td>
			</tr>		
			<tr>
				<td>email</td>
				<td><input type="text" name="email" /></td>
			</tr>		
			<tr>
				<td>성별</td>
				<td>
					<input type="radio" name="gender" value="male" checked /> 남
					<input type="radio" name="gender" value="female" /> 여
				</td>
			</tr>		
			<tr>
				<td>연령대</td>
				<td><input type="text" name="age" /></td>
			</tr>		
			<tr>
				<td colspan="2">
					<input type="submit" value="가입" />
					<input type="reset" value="reset" />
					<input type="button" value="취소"  onclick="window.location='/common/main'" />
				</td>
			</tr>		
		</table>
		<%-- 스프링시큐리티 적용시, post 요청에는 CRSF 토큰 값 보내줘야함. --%>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	
</body>
</html>