<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<h2 align="center"> 회원 가입 폼 </h2>
	<form action="signupPro.jsp" method="post">
		<table>
			<tr>
				<td>ID *</td>
				<td><input type="text" name="id" /></td>
			</tr>
			<tr>
				<td>Password *</td>
				<td><input type="password" name="pw" /></td>
			</tr>
			<tr>
				<td>Email *</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td>
					<input type="radio" name="gender" value="male"  checked /> 남
					<input type="radio" name="gender" value="female" /> 여
				</td>
			</tr>
			<tr>
				<td>Age</td>
				<td>
					<input type="text" name="age"  /> 
				</td>
			</tr>
			<tr>
				<td>Hobbies</td>
				<td>
					<input type="checkbox" name="music" value="music" /> Music
					<input type="checkbox" name="sports" value="sports" /> Sports
					<input type="checkbox" name="movies" value="movies" /> Movies
					<input type="checkbox" name="travel" value="travel" /> Travel
				</td>
			</tr>
			<tr>
				<td>Job</td>
				<td>
					<select name="job">
						<option value="employer" selected >Employer</option>
						<option value="employee">Employee</option>
						<option value="teacher">Teacher</option>
						<option value="student">Student</option>
						<option value="freelancer">Freelancer</option>
						<option value="etc">Etc</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Bio</td>
				<td>
					<textarea name="bio" rows="10" cols="30"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="가입" />
					<input type="reset" value="재작성" />
				</td>
			</tr>

		
		</table>
	</form>
	
	
	
</body>
</html>