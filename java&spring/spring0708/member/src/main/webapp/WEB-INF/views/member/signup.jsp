<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<br/>
	<h1 align="center">Sign up</h1>
	<form action ="/member/signup" method="post">
		<table>
			<tr>
				<td>id * </td>
				<td><input type="text" name="id"/></td>
			</tr>
			<tr>
				<td>pw *</td>
				<td><input type="password" name="pw"/></td>
			</tr>
				<tr>
				<td>pw check *</td>
				<td><input type="password" name="pwch"/></td>
			</tr>
			<tr>
				<td>name *</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>email</td>
				<td><input type="text" name="email"/></td>
			</tr>
			<tr>
				<td>gender</td>
				<td><input type="radio" name="gender" value="male" checked/>남
				<input type="radio" name="gender" value = "female"/>여
				</td>
			</tr>
			<tr>
				<td>age</td>
				<td><input type="text" name="age"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="가입"/>
					<input type="reset" value="재작성"/>
					<input type="button" value="취소" onclick="window.location='/member/main'"/>
				</td> 
			</tr>
		</table>	
	</form>
</body>
</html>