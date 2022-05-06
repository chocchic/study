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
	<h3> hello jquery! </h3>
	<h3 id="id1"> id jquery! </h3>
	<h3 class="cls1"> class jquery! </h3>
	
	<ul>
		<li>1111</li>
		<li>2222</li>
		<li>3333</li>
		<li>4444</li>
	</ul>
	
	<button class="btn">클릭</button>
	<img src="/resources/imgs/beach.jpg"  width="400"/>
	
	<script>
	$(document).ready(function(){
		$("h3").css("color", "red"); 
		$("#id1").css("color", "blue");
		$(".cls1").css("color", "green");
		
		$("li:nth-child(2n)").css("color", "magenta");
		$("li").attr("class", "test");
		$("li").addClass("abc");
		$("li").removeClass("test");
		
		$(".btn").click(function(){
			$("img").attr("src", "/resources/imgs/bebe.png");
		});
		
		
	});
	
	
	</script>
</body>
</html>




