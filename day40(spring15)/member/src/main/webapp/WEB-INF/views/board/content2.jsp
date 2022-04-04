<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>content</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<br />
	<h1 align="center"> Content </h1>
	<table>
		<tr>
			<td>글번호</td>
			<td>${board.bno}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${board.title}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.writer}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea rows="10" cols="30" disabled>${board.content}</textarea></td>
		</tr>
		<tr>
			<td>작성일</td>
			<td><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
		</tr>
		<tr>
			<td>수정일</td>
			<td><fmt:formatDate value="${board.updateDate}" pattern="yyyy-MM-dd HH:mm:SS"  /></td>
		</tr>
		<tr>
			<td colspan="2">
				<button class="btn" data-oper="modify">수정</button>
				<button class="btn" data-oper="delete">삭제</button>
				<button class="btn" data-oper="list">리스트</button>
			</td>
		</tr>
	</table>
	<form action="/board/modify" method="get" role="form">
		<input type="hidden" name ="bno" value="${board.bno}">
		<input type="hidden" name ="pageNum" value="${cri.pageNum}">
		<input type="hidden" name ="listQty" value="${cri.listQty}">
		<input type="hidden" name="type" value="${cri.type}"/>
		<input type="hidden" name="keyword" value="${cri.keyword}"/>
	</form>
	<br/>
	
	<table width="300px">
		<tr align="left">
			<td><b>댓글 목록</b></td>
		</tr>
	</table>
	<table class="reply" width="300px">
		<tr data-rno ="12">
			<td align="left">작성자<br/>목이너무아파<br/><font size="0.5rem">2022-04-01</font></td>
			
		</tr>
	</table>
	
	<script type="text/javascript" src="/resources/js/reply.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		//console.log(replyService);
		// replyService.add(reply 객체, function(){요청 성공시 실행할 함수}, function(){에러시 실행할 함수});
		
		// 댓글 등록
		let bnoVal = '<c:out value="${board.bno}"/>';
		let replyTable=$(".reply");
		showList(1);
		// 전체 댓글 목록 가져와 뿌리기 (댓글 페이지 번호 주면서)
		function showList(page){
			replyService.getList({bno:bnoVal, page:1}, function(data){ // data = 서버에서 전달해준 댓글 리스트
				// 서버에서 댓글을 보내줬는지 확인 (댓글이 없는 본문은 안가져옴)
				if(data == null || data.length == 0){
					replyTable.html("");
					return; // 아래는 실행할 필요 없으니 강제 종료
				}
			let str = ""; // 화멘여 띄울 댓글 tr 묶음 html을 만들어서 저장해놓을 변수
			let len = data.length || 0;
			
				// let data.length || 0;
				for(let i= 0,len=data.length||0; i < len;i++){
					str += "<tr data-rno ='" + i + "'>";
					str += "<td align='left'><b>"+data[i].replyer+"</b><br/>";
					str += data[i].reply+"<br/><font size='0.5rem'>"+replyService.displaytimeFormat(data[i].updateDate)+"</font></td></tr>";
				}
				replyTable.html(str); // 테이블에 tr묶음들 모은 문자열 str 배치
				
			});//replyService.getList
		}// show
		
		/*
		replyService.add(
			{reply:"js text", replyer : "jstest", bno : bnoVal},
			function(result){
				alert("result : " + result);
			}
		);
		*/
		/*
		// 댓글 전체 가져오기
		replyService.getList({bno:bnoVal, page:1}, function(data){
			// let data.length || 0;
			for(let i= 0,len=data.length||0; i < len;i++){
				console.log(data[i]);
			}
		});
		*/
		//댓글 삭제
		/*
		replyService.remove(15, function(result){
			console.log(result);
			if(result === "success"){
				alert("삭제완료!!");
			}
		},function(e){
			alert(e);
		});*/
		
		// 댓글 수정
		/*
		replyService.update({
			rno : 6, bno : bnoVal, reply: "수정 ajax 댓글"
		}, function(result){
			alert("수정완료!");
		});
		*/
		/*
		// 댓글 한개 조회
		replyService.get(5,function(result){
			console.log(result);
		});
		*/
	});
	</script>
	<script type="text/javascript">
		$(document).ready(function(){
			// 숨김 폼태그 가져오기
			let formObj = $("form");
			$("button.btn").click(function(e){
				e.preventDefault();
				let operation = $(this).data("oper");
				//console.log(operation);
				
				if(operation === 'delete'){
					formObj.attr("action","/board/delete");
				}else if(operation === 'list'){
					formObj.attr("action", "/board/list");
				}
				formObj.submit(); // 이동
			});
		});
	</script>
</body>
</html>