<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>form page</h2>
	<form action="pro.jsp?test=10" method="post" > <!-- method 안적어주면 default가 get-->
		ID : <input type="text" name="id"/><br/>
		PW : <input type="password" name="pw"/><br/>
		좋아하는 동물 :
			<input type = "checkbox" name="pet" value="dog"/>개
			<input type = "checkbox" name="pet" value="cat"/>고양이
			<input type = "checkbox" name="pet" value="rep"/>파충류 <br/>
		<input type="submit" value="login"/>	
	</form>
</body>
</html>