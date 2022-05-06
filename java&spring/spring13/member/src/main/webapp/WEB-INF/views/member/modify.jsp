<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Form</title>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%-- 비로그인인 경우 --%>
	<c:if test="${sessionScope.memId ==null}">
		<script type="text/javascript">
			alert("로그인 후 사용 가능합니다.");
			window.location.href="/member/login";
		</script>
	</c:if>
	
	<c:if test="${sessionScope.memId!=null}">
		<br/>
		<h1 align ="center">회원정보 수정</h1>
		<form action="/member/modify" method="post">
		<table>
				<tr>
					<td>id * </td>
					<td>${member.id}</td>
				</tr>
				<tr>
					<td>pw *</td>
					<td><input type="password" name="pw" value="${member.pw}"/></td>
				</tr>
					<tr>
					<td>pw check *</td>
					<td><input type="password" name="pwch"/></td>
				</tr>
				<tr>
					<td>name *</td>
					<td>${member.name}</td>
				</tr>
				<tr>
					<td>email</td>
					<td><c:if test="${member.email!=null}">
							<input type="text" name="email" value="${member.email}"/>	
						</c:if>
						<c:if test="${member.email==null}">
							<input type="text" name="email"/>
						</c:if></td>
				</tr>
				<tr>
					<td>gender</td>
					<td>${member.gender}</td>
				</tr>
				<tr>
					<td>age</td> <%--분기처리 --%>
					<td>
						<c:if test="${member.age!=null}">
							<input type="text" name="age" value="${member.age}"/>	
						</c:if>
						<c:if test="${member.age==null}">
							<input type="text" name="age"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정"/>
						<input type="reset" value="재작성"/>
						<input type="button" value="취소" onclick="window.location='/member/mypage'"/>
					</td> 
				</tr>
			</table>	
		</form>
	</c:if>
</body>
</html>