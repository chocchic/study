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
	<form action="/member/signup" method="POST">
		<table>
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
					<input type="button" value="취소"  onclick="window.location='/member/main'" />
				</td>
			</tr>		
		</table>
	</form>
	
</body>
</html>