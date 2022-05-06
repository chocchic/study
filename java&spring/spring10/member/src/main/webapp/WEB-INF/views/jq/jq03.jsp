<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h3> 입력값 확인</h3>
	<input type="text" name="id" id="id1"/>
	<button>confirm</button>
	
	<br/>
	<br/>
	<br/>
	<h3> 과목 선택 </h3>
	<select>
		<option>java</option>
		<option>html/css</option>
		<option>sql</option>
		<option>framework</option>
	</select>
	<span id="span1"></span>
	<br/>
	<button id="showBtn">show</button>
	<button id="hideBtn">hide</button>
	<br/>
	<img src="/resources/imgs/sample.jpg" width="200px" style="display: none"/>
	<script>
	// 동적으로 생성된 id나 class 인식안될 수도 있으니깐 뼈대는 미리 만들어놔야 한다
		$(document).ready(function(){
			$("select").change(function(){
				$("#span1").text("선택 : " + $(this).val());
				
			});
			$("#showBtn").click(function(){
				$("img").fadeIn();
			});
			$("#hideBtn").click(function(){
				$("img").fadeOut();
			});
			/*$("button").click(function(){
				let value = $("#id1").val();
				$("h3").text(value);
				$("#id1").val("");
				//alert(value);
			});*/
		});
	</script>
</body>
</html>