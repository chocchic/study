<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Main</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<br />
	<h1 align="center"> 메인 페이지 </h1>
	
	<%-- 로그아웃 상태 : security 태그 사용 --%>
	<sec:authorize access="isAnonymous()"> 
		<table>
			<tr>
				<td>
					로그인을 원하시면 버튼을 누르세요. <br />
					<button onclick="window.location='/common/login'">로그인</button>
				</td>
				<td rowspan="2">
					<button onclick="window.location='/board/list'">게시판</button>
				</td>
			</tr>
			<tr>
				<td>
					<a href="/common/signup">회원가입</a>
				</td>
			</tr>
		</table>
	</sec:authorize> 
	
	<%-- 로그인 상태 : security 태그 사용 --%>
	<sec:authorize access="isAuthenticated()"> 
		<table>
			<tr>
				<td>
					<button onclick="window.location='/board/list'">게시판</button>
				</td>
			</tr>
			<tr>
				<td>
					<button onclick="window.location='/member/mypage'">마이페이지</button>
				</td>
			</tr>
			<tr>
				<td>
					<form action="/logout" method="post">
						<%-- post 요청에는 CRSF 토큰 값 보내줘야함. --%>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<button>로그아웃</button>
					</form>
				</td>
			</tr>
		</table>
		<br />
		<table>
			<tr>
				<td>principal</td>
				<td><sec:authentication property="principal"/> </td>
			</tr>
			<tr>
				<td>MemberVO</td>
				<td><sec:authentication property="principal.member"/> </td>
				<%-- principal = UserDetailsService = 리턴된 CustomUser 
					 principal.member = CustomUser의 MemberVO member 변수의 getMember() 호출되어 member객체돌려줌. 
				 --%>
			</tr>
			<tr>
				<td>사용자이름</td>
				<td><sec:authentication property="principal.member.name"/> </td>
			</tr>
			<tr>
				<td>사용자 아이디</td>
				<td><sec:authentication property="principal.username"/> </td>
				<%-- 스프링시큐리티로 로그인시 id는 username이라 칭함, 로그인폼에서도 name속성값이 username임 --%>
			</tr>
			<tr>
				<td>권한 리스트 </td>
				<td><sec:authentication property="principal.member.authList"/> </td>
			</tr>
		</table>
	</sec:authorize> 
	
	<br /><br /><br /><br />
	<div align="center">
		<img src="/resources/imgs/beach.jpg" width="500px" />
	</div>
	
	
	<script>
	$(document).ready(function(){
		let msg = "${msg}"; 
		checkResult(msg); // alert띄울지 함수호출 
		
		history.replaceState({}, null, null); // history 기록 조작 
		
		function checkResult(msg) {
			
			if(msg === "" || history.state){
				return; 
			}
			
			if(msg == "success"){
				alert("회원가입 성공!!");
			}
		}// checkResult
		
		
	}); 
	</script>

</body>
</html>




