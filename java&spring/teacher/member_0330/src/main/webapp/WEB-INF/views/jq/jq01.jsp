<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>jq01</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<button>btnJS</button>
	<button>btnJQuery</button>

	<script>
		// script 
		function test() {
			alert("test!!!!");
		}
		// jquery
		$(document).ready(function(){
			$("button").click(function(){
				alert("jquery test!!!!!!");
			});
		});
		
	
	
	
	</script>
</body>
</html>