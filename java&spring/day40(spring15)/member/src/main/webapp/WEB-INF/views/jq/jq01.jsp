<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JQ 01</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<button onclick="test()">btnJS</button>
<button> btnJQ</button>
<script type="text/javascript">
	//script
	function test(){
		alert("test!!");
	}
	
	$(document).ready(function(){
		$("button").click(function(){
			alert("jquery test!");
		});
	});
	
</script>
</body>
</html>