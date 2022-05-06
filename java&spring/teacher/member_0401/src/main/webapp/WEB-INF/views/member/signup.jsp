<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
	<!--  jquery,ajax 사용하기위해 라이브러리 CDN 추가  -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<br />
	<h1 align="center"> 회원 가입 </h1>
	<form action="/member/signup" method="POST" name="inputForm" onsubmit="return check()">
		<table>
			<tr>
				<td>아이디*</td>
				<td><input type="text" name="id" id="id" /></td>
			</tr>		
			<tr>
				<td>아이디 사용가능 여부</td>
				<td><input type="text" id="idCheckResult" disabled /></td>
			</tr>		
			<tr>
				<td>아이디 사용여부 확인 버튼</td>
				<td><input type="button" value="아이디 중복 확인" onclick="openIdAvail(this.form)" /></td>
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
	
	<script type="text/javascript">
		// jquery 
		$(document).ready(function(){
			// id속성값이 'id'인 태그에 변화가 있을때 함수 실행해라
			$("#id").change(function(){ 
				let idVal = $("#id").val(); 
				//console.log(idVal);
				// ajax로 id 체크 요청하고, 결과 받아서 화면에 결과 뿌리기 
				$.ajax({
					type : "post",
					url : "/member/ajaxIdAvail",
					data :{id : idVal}, 
					success : function(result){
						console.log("요청 성공!!"); 
						console.log("result : " + result); 
						// 아이디사용가능여부 결과 input태그에 value값을 
						// 서버에서 돌려받은 문자열로 지정 
						$("#idCheckResult").val(result); 
					},
					error : function(e){
						console.log("에러 발생!!"); 
						console.log(e);
					}
				});// ajax
			});// change
			
			
		});// ready
	
	
		// 유효성 검사 
		function check() {
			let inputs = document.inputForm; 
			//console.log(inputs);
			if(!inputs.id.value){
				alert("아이디를 입력하세요");
				return false;
			}
			if(!inputs.pw.value){
				alert("비밀번호를 입력하세요"); 
				return false;
			}
			if(!inputs.pwch.value){
				alert("비밀번호 확인란을 입력하세요"); 
				return false;
			}
			if(!inputs.name.value){
				alert("이름을 입력하세요"); 
				return false;			 			
			}
			if(inputs.pw.value != inputs.pwch.value){
				alert("비밀번호와 확인란을 동일하게 입력하세요."); 
				return false
			}
		}
 		// 아이디 중복확인 팝업 
 		function openIdAvail(inputForm) { // <-- this.form 받아주기 
			if(inputForm.id.value == "" || !inputForm.id.value){
				alert("아이디를 입력하세요"); 
				return;  // 함수 강제 종료
			}
 			// 팝업으로 띄울 요청 경로 만들기 
 			let url = "/member/idAvail?id=" + inputForm.id.value; 
 			// 팝업 띄우기 
 			open(url, "idAvail", "toolbar=no, location=no, status=no, menubar=no, resizable=no, width=300, height=200"); 
		}
	</script>
	
	
	
	
	
	
	
	
</body>
</html>