<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>upload form</title>
</head>
<body>
	
	<h2> upload form </h2>
	<form action="/upload/uploadPro" method="post" enctype="multipart/form-data">
		MSG : <input type="text" name="msg" /> <br />
		File : <input type="file" name="img" /> <br />
		<input type="submit" value="전송" />
	</form>





</body>
</html>