<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>modify</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<br />
	<h1 align="center"> 회원 정보 수정 폼 </h1>
 	<form action="/member/modify" method="post">
	 	<table>
			<tr>
				<td>아이디*</td>
				<td> ${member.id} </td>
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
				<td> ${member.name} </td>
			</tr>		
			<tr>
				<td>email</td>
				<td>
					<c:if test="${member.email != null}">
						<input type="text" name="email" value="${member.email}" />
					</c:if>
					<c:if test="${member.email == null}">
						<input type="text" name="email" />
					</c:if>
				</td>
			</tr>		
			<tr>
				<td>성별</td>
				<td>
					${member.gender} 
				</td>
			</tr>		
			<tr>
				<td>연령대</td> 
				<td>
					<c:if test="${member.age != null}">
						<input type="text" name="age" value="${member.age}" />
					</c:if>
					<c:if test="${member.age == null}">
						<input type="text" name="age" />
					</c:if>
				</td>
			</tr>		
			<tr>
				<td colspan="2">
					<input type="submit" value="수정" />
					<input type="reset" value="reset" />
					<input type="button" value="취소"  onclick="window.location='/member/mypage'" />
				</td>
			</tr>		
		</table>
		<%-- 스프링시큐리티 적용시, post 요청에는 CRSF 토큰 값 보내줘야함. --%>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
		
</body>
</html>