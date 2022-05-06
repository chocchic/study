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
	<h3> 입력값 확인 </h3>
	<input type="text" name="id" id="id1" />
	<button>확인</button>
	
	<br />
	<br />
	<br />
	<h3> 가장 좋아하는 과목 선택 </h3>
	<select>
		<option>java</option>
		<option>html/css</option>
		<option>sql</option>
		<option>framework</option>
	</select>
	<span id="span1"></span>
	<br /><br /><br /><br />
	
	<button id="showBtn">show</button>
	<button id="hideBtn">hide</button>
	<img src="/resources/imgs/bebe.png" width="300" />

	<script>
	$(document).ready(function(){
		$("select").change(function(){
			$("#span1").text("선택 : " + $(this).val());
		}); 
		
		$("#showBtn").click(function(){
			$("img").fadeIn(); 
		});
		$("#hideBtn").click(function(){
			$("img").slideUp(); 
		});
		
		
		
		
		/*
		$("button").click(function(){
			let value = $("#id1").val();
			$("h3").text(value);
			$("#id1").val("");
		});
		*/
		
	});
	</script>


</body>
</html>