<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>content</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<br />
	<h1 align="center"> Content </h1>
	<table width="300">
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
			<td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
		</tr>
		<tr>
			<td>수정일</td>
			<td><fmt:formatDate value="${board.updateDate}" pattern="yyyy-MM-dd HH:mm:SS"  /></td>
		</tr>
		<tr>
			<td colspan="2">
				<button class="btn" data-oper="modify" >수정</button>
				<button class="btn" data-oper="delete">삭제</button>
				<button class="btn" data-oper="list">리스트</button>
			</td>
		</tr>
	</table>
	<form action="/board/modify" method="get" >
		<input type="hidden" name="bno" value="${board.bno}" />
		<input type="hidden" name="pageNum" value="${cri.pageNum}" />
		<input type="hidden" name="listQty" value="${cri.listQty}" />
		<input type="hidden" name="type" value="${cri.type}" />
		<input type="hidden" name="keyword" value="${cri.keyword}" />
	</form>
	<br />
	<style>
		.inner {
			margin: 0 auto; 
			width: 70%;
		}
		.reply_container {
			margin: 0.5rem auto;
			padding: 1rem 1.5rem;
			display: flex;
			width: 80%;
			flex-direction: column; 
			background-color: #AAB9BD;
		}
		.reply_title {
			font-size: 1rem;
			font-weight: bold;
			margin: 0.5rem 0;
		}
		.reply_box {
			margin: 0.5rem 0; 
			width: 100%; 
		}
		textarea {
			width: 90%;		
		}
		.reply_list_container {
			margin: 0.5rem auto;
			padding: 1rem 1.5rem;
			display: flex;
			width: 80%;
			flex-direction: column; 
			background-color: #AAB9BD;
		}
		.reply_li {
			margin: 0.5rem 0; 
			display: flex; 
			flex-direction: row; 
			width: 100%; 
		}
		.reply_content {
			width: 100%; 
		}
		.replyer {
			font-weigth: bold;
			font-size: 0.8rem;
			width: 100%;
		}
		.reply {
			font-size: 0.8rem;
			width: 100%; 
		}
		.regdate {
			font-size: 0.6rem;
			width: 100%; 
		}
		.reply_btns {
			margin: 0.3rem 0;
		}
		.replyPaging {
			margin: 0.5rem auto;
			padding: 1rem 1.5rem;
			width: 80%;
			background-color: #AAB9BD;
			display: flex;
			justify-content: right;
		}
	</style>
	<div class="inner">
		<div class="reply_container">
			<div class="reply_title"> 댓글 </div>
			<div class="reply_box">
				<textarea rows="3"></textarea>
				<input type="text" placeholder="작성자"/>
				<button class="newReplyBtn">댓글 저장</button>
			</div>
		</div>
		<div class="reply_list_container">
			
		</div>
		
		<%-- #7. 댓글 페이지 번호 띄울 div 추가  --%>
		<div class="replyPaging">
		
		</div>
		
	</div>
	<br /><br />
	
	<script type="text/javascript" src="/resources/js/reply.js" ></script>
	<script type="text/javascript">
	$(document).ready(function(){
		
		let bnoVal = '<c:out value="${board.bno}" />';
		let list_container = $(".reply_list_container");
		showList(1);
		
		//전체 댓글 목록 가져와 뿌리기(댓글 페이지번호 주면서) 
		function showList(page){
			console.log("page : " + page); 
			// 전체 댓글 가져오는 js 함수 호출 
			//replyService.getList({bno:bnoVal, page:page||1}, function(data){ // data = 서버에서 전달해준 댓글 리스트 
			// #6. callback 함수 매개변수 수정, 안쪽 코드 수정/추가 
			replyService.getList({bno:bnoVal, page:page||1}, function(replyCount, data){ // replyCount, data 매개변수 2개로 수정  
				// #6. 추가 
				console.log("replyCount : " + replyCount);
				console.log("data : " + data);
			
			
				// 서버에서 댓글을 보내줬는지 확인 (댓글이 없는 본문은 안가져옴)
				if(data == null || data.length == 0){
					list_container.html("<div class='reply_li'>댓글이 없습니다</div>");
					return; // 아래는 실행할 필요없으니 강제 종료 
				}
				console.log(data);
				let str = ""; // 화면에 띄울 댓글 tr묶음 html을 만들어서 저장해놓을 변수 
				let len = data.length || 0; 

				// 댓글 목록 받아온 것 화면 띄우기위해 html 형태의 문자열로 구성 
				for(let i = 0; i < len; i++){
					let wid = data[i].lev * 2; 
					console.log("wid : " + wid);
					str += "<div class='reply_li' data-rno='"+data[i].rno+"' data-grp='"+data[i].grp+"' data-step='"+data[i].step+"' data-lev='"+data[i].lev+"' >";
					str += "<div class='reply_level' style='width:"+wid+"rem'></div>";
					str += "<div class='reply_content'>";
					// 리스트 분석용 데이터 출력 (나중에 지우기)
					str += "<div> rno: "+data[i].rno+", grp: "+data[i].grp+", step: "+data[i].step+", lev: "+data[i].lev+" </div>";
					
					str += "<div class='replyer'>"+data[i].replyer+"</div>";
					str += "<div class='reply'>"+data[i].reply+"</div>";
					str += "<div class='replyDate'>"+replyService.displayTimeFormat(data[i].replyDate)+"</div>";
					str += "<div class='reply_btns'><button class='addRepBtn'>답글</button>";
					str += "<button class='modifRepBtn'>수정</button></div></div></div>";
				}
				
				list_container.html(str); // list_container div 태그 안을 댓글목록만들어놓은 str로 변경 
				// #9. 댓글 페이징번호 띄우기 함수 호출 
				showReplyPaging(replyCount);
				
			});// replyService.getList
		}// showList
		
		// #8. 댓글 페이지 번호 출력 로직 추가 
		let pageNum = 1;  // 댓글 페이지 번호를 들고 있을 변수 
		let replyPaging = $(".replyPaging");  // 댓글 페이지번호 띄울 div 태그 가져오기 
		function showReplyPaging(replyCount) {
			let endNum = Math.ceil(pageNum / 10.0) * 10;  // 마지막 페이지 번호 (일단 10개로 보이게) 
			let startNum = endNum - 9; 	// 시작 페이지 번호 
			let prev = startNum != 1;  	// 이전버튼  보일지 여부 
			let next = false; 			// 다음버튼 일단 false
			if(endNum * 10 >= replyCount) { // 페이지 번호 10개에 보여줘야되는 댓글수보다 전체 댓글 수가 적거나 같으면 
				endNum = Math.ceil(replyCount / 10.0); // 마지막 페이지 번호를 전체 나오는 페이지 수로 변경 
			}
			if(endNum * 10 < replyCount) { // 지금 보는 마지막 페이지번호에 보여줄 댓글수 보다 전체 댓글 수가많으면  
				next = true; //next 보여주기 값을 변경 
			}
			// 페이지 번호 띄워줄 html 구성 
			let str = "<div class='replyPaing_content'>";
			if(prev) {
				str += "<a class='paging_link' href='"+(startNum-1)+"'> prev </a> ";
			}
			for(let i = startNum; i <= endNum; i++){
				str += "<a class='paging_link' href='"+i+"'>&nbsp; "+i+" &nbsp;</a>";
			}
			if(next){
				str += " <a class='paging_link' href='"+(endNum+1)+"'> next </a></div>";
			}
			
			replyPaging.html(str);
			// => showList에 showReplyPaging() 호출 추가 
		} 
		
		// #10. 댓글 페이지 번호 이벤트 등록 
		replyPaging.on("click", "a.paging_link", function(e){
			e.preventDefault();  // a 태그의 default기능(이동) 무시해라~	
			let pageLink = $(this).attr("href"); // 클릭한 a태그의 href 값 가져오기 (이동할 댓글페이지번호)
			console.log(pageLink); 
			pageNum = pageLink; // 밖에 만들어놓은 현재 페이지 변수에 덮어쓰기 
			showList(pageNum); 
		});
		
		// 새 댓글 등록
		$(".newReplyBtn").click(function(e){
			// 새 댓글 추가 처리 
			console.log("새 댓글 추가 버튼 클릭!!!!");
			let replyerTxt = $(this).prev().val(); 	// 작성자 input value
			let replyTxt = $(this).prev().prev().val(); // 댓글 textarea value
			// 댓글 추가 메서드 실행 
			replyService.add(
					{reply : replyTxt, replyer : replyerTxt, bno : bnoVal},
					function(result){
						alert("result : " + result);
						showList(1); // 댓글 추가후 화면에 댓글목록 새로 가져와 뿌리기 
					}
			);
		});
		// 댓글의 답글 추가 버튼 이벤트 처리 -> 답글 입력창 보여주기 
		$(".reply_list_container").on("click", "button.addRepBtn", function(e){
			console.log("답글 버튼 클릭!!!");
			let reply_li_tag = $(this).parent().parent().parent(); 
			let rno = reply_li_tag.data("rno");
			let grp = reply_li_tag.data("grp");
			let step = reply_li_tag.data("step");
			let lev = reply_li_tag.data("lev");
			let wid = lev*2;
			// 답글 입력할수있는 textarea, input, button 문자열로 구성 
			let str = "<div class='reply_li' data-rno='"+rno+"' data-grp='"+grp+"' data-step='"+step+"' data-lev='"+lev+"'>";
			str += "<div class='reply_level' style='width:"+wid+"rem'></div>";
			str += "<div class='reply_content'>";
			str += "<textarea></textarea><input type='text' placeholder='작성자' />";
			str += "<button class='addRepSubmit'>답글저장</button></div></div>";
			reply_li_tag.after(str);
		}); 
		// 답글 추가 처리 
		$(".reply_list_container").on("click", "button.addRepSubmit", function(e){
			console.log("답글 저장 버튼 클릭클릭!!");
			let reply_li = $(this).parent().parent();
			let rnoVal = reply_li.data("rno"); 
			console.log("rnoVal : " + rnoVal);
			let stepVal = reply_li.data("step"); 
			console.log("stepVal : " + stepVal);
			let levVal = reply_li.data("lev");
			console.log("levVal : " + levVal);
			let grpVal = reply_li.data("grp");
			console.log("grpVal : " + grpVal);
			let replyTxt = $(this).prev().prev().val(); 
			let replyerTxt = $(this).prev().val(); 
			
			// 댓글 추가 메서드 실행 
			replyService.add(
					{reply : replyTxt, replyer : replyerTxt, bno : bnoVal, rno : rnoVal, grp: grpVal, step: stepVal, lev: levVal},
					function(result){
						alert("result : " + result);
						showList(1); // 댓글 추가후 화면에 댓글목록 새로 가져와 뿌리기 
					}
			);
			
		});
		
		let li_content_clone = {}; 
		let reply_content = {}; 
		// 댓글 수정 버튼 이벤트 처리 -> 수정가능한 textarea 로 변경 
		$(".reply_list_container").on("click", "button.modifRepBtn", function(e){
			console.log("댓글 수정 버튼 클릭!!!");
			
			// 변경되야하는 reply_content 부분만 담기 
			reply_content = $(this).parent().parent();
			li_content_clone = reply_content.clone();  // 나중에 수정취소 누르면 되돌리기위해 복제해두기
			
			// 기존에 작성된 값들 꺼내기 
			let replyer = reply_content.find("div[class='replyer']").html();
			let reply = reply_content.find("div[class='reply']").html();
			let replyDate = reply_content.find("div[class='replyDate']").html();
			/*
			<div class="reply_content">
				<div class='replyer'><input type='text' value='"+replyer+"' disabled /></div>
				<div class='reply'><textarea>"+reply+"</textarea></div>
				<div class='replyDate'><input type='text' value='"+replyDate+"' disabled /></div>
				<div class='reply_btns'>
					<button class='modifRepSubmit'>수정확인</button>
					<button class='modifCancel'>취소</button>
				</div>
			</div>
			*/
			let str = "<div class='replyer'><input type='text' value='"+replyer+"' disabled /></div>";
			str += "<div class='reply'><textarea rows='3' class='modifTxt'>"+reply+"</textarea></div>";
			str += "<div class='replyDate'><input type='text' value='"+replyDate+"' disabled /></div>";
			str += "<div class='reply_btns'><button class='modifRepSubmit'>수정확인</button>";
			str += "<button class='deleteRep'>삭제</button>";
			str += "<button class='modifCancel'>취소</button></div>";
			reply_content.html(str);
			
		}); 
		// 수정 처리 이벤트 
		$(".reply_list_container").on("click", "button.modifRepSubmit", function(e){
			console.log("수정 확인 버튼 클릭!!!");
			let rnoVal = $(this).parent().parent().parent().data("rno");
			let modifTxt = $(this).parent().parent().find("textarea[class='modifTxt']").val();
			console.log("rno : " + rnoVal);
			console.log("modifTxt : " + modifTxt);
			
			// rno, reply
			replyService.update({
				rno : rnoVal, 
				reply : modifTxt
			}, function(result){
				alert("수정 완료!!!");
				//showList(1);
				// #11. 보던페이지로 이동 
				showList(pageNum);
			});
			
		});
		// 수정 취소 이벤트 
		$(".reply_list_container").on("click", "button.modifCancel", function(e){
			console.log("수정 취소 버튼 클릭!");
			reply_content.html(li_content_clone.children());
		});
		// 댓글 삭제 이벤트 
		$(".reply_list_container").on("click", "button.deleteRep", function(e){
			console.log("삭제 버튼 클릭!");
			let rnoVal = $(this).parent().parent().parent().data("rno");
			
			// 댓글 삭제 
			replyService.remove(rnoVal, 
				function(result){
					console.log(result); 
					if(result === "success"){
						alert("삭제 완료!!!"); 
						//showList(1); 
						// #11. 보던페이지로 이동 
						showList(pageNum);
					}
				},function(e){
					alert(e);	
			}); 
			
		});
		/*
			댓글 삭제하면 답글도 같이 삭제?? 
			유효성 검사 추가 
			+ 회원가입 합쳐야 
			- 작성자 입력 x, id 로 변경 
			- 모든페이지 : 로그인/로그아웃 
			- 회원 -> 게시판 게시판 -> 회원 
			- 게시글과 댓글은 로그인한사람만 작성가능
			- 비로그인으로 어떤페이지만 볼수있는지 
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
			
			if(operation === 'delete'){
				formObj.attr("action", "/board/delete"); 
			}else if(operation === 'list'){
				formObj.attr("action", "/board/list"); 
			}
			
			formObj.submit(); // 이동 
			
		}); 
	}); 
	</script>
	
	

</body>
</html>