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
	<h3>hello jq!</h3>
	<h3 id="id1">id jq</h3>
	<h3 class="cls1">class jq</h3>
	<ul>
		<li>1</li>
		<li>2</li>
		<li>3</li>
		<li>4</li>
	</ul>
	<button class="btn">클릭</button>
	<img src="/resources/imgs/sample.jpg" widht="400px"/>
	
	<script>
		$(document).ready(function(){
			$("h3").css("color","red");
			$("h3#id1").css("color","blue");
			$("h3.cls1").css("color","green");
			
			$("li:nth-child(2n)").css("color","magenta");
			$("li").attr("id","test");
			$("li").addClass("abc");
			$("li").removeClass("test");
			
			$(".btn").click(function(){
				$("img").attr("src","/resources/imgs/sample2.jpg")
			});
		});
	</script>
</body>
</html>