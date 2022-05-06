/**
 * 댓글처리용 JS 파일 
 */
console.log("Reply JS 모듈....");

// 자바의 replyService 구현 클래스격
var replyService = (function(){
	
	// 댓글 등록 처리 
	// reply = 추가할 댓글 정보, callback = 추가 후 실행할 함수, error = 에러발생시 실행할 함수
	function add(reply, callback, error) {
		console.log("reply add!!!!!"); 
		console.log(reply);
		$.ajax({
			type: "post",
			url: "/replies/new",
			data: JSON.stringify(reply),
			contentType: "application/json;charset=utf-8", 
			success : function(result, status, xhr) {
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, e) {
				if(error){
					error(e);
				}
			}//error
		});//ajax
	}//add
	
	// 전체 댓글 가져오기 
	function getList(param, callback, error) {
		let bno = param.bno;
		let page = param.page || 1;  // page가 없으면 1로 사용 
		$.getJSON("/replies/pages/"+bno+"/"+page+".json", 
			function(data){
				if(callback){
					//callback(data); 
					// #5. 콜백 수정 : list, count 두개 꺼내 매개변수로 추가 : 댓글 개수와 목록 가져오겠다 
					callback(data.replyCount, data.list); 
				}
			})
		.fail(function(xhr, status, e){
			if(error){
				error(e);
			}
		}); 
	}
	
	// 댓글 삭제 
	function remove(rno, callback, error) {
		$.ajax({
			type : "delete",
			url : "/replies/" + rno,
			success : function(result, status, xhr){
				if(callback){
					callback(result); 
				}
			},
			error : function(xhr, status, e){
				if(error){
					error(e); 
				}
			}
		}); 
	}
	
	// 댓글 수정 
	function update(reply, callback, error){
		console.log("update reply rno : " + reply.rno); 
		console.log("update reply reply : " + reply.reply); 
		$.ajax({
			type : "put",
			url : "/replies/" + reply.rno, 
			data : JSON.stringify(reply),
			contentType : "application/json;charset=utf-8", 
			success : function(result, status, xhr){
				if(callback){
					callback(result); 
				}
			},
			error : function(xhr, status, e){
				if(error){
					error(e);
				}
			}
		}); 
	}
	
	// 댓글 한개 조회 
	function get(rno, callback, error){
		$.get("/replies/" + rno + ".json", function(result){
			if(callback){
				callback(result);
			}
		}).fail(function(xhr, status, e){
			if(error){
				error(e);
			}
		}); 
	}
	
	// 시간 포맷팅 함수 
	function displayTimeFormat(timeVal) {
		// 오늘단거는 시간, 그전에는 날짜 
		let today = new Date(); 				// 오늘 날짜 
		let diff = today.getTime() - timeVal; 	// 시간차 구하기 
		let dateObj = new Date(timeVal); // JS Date 객체로 변경 
		if(diff < (1000 * 60 * 60 * 24)) { // 24h 보다 작으면 
			let hh = dateObj.getHours(); 
			let mi = dateObj.getMinutes(); 
			let ss = dateObj.getSeconds(); 
			return (hh > 9 ? "" : "0") + hh + ":" 
				+ (mi > 9 ? "" : "0") + mi + ":" 
				+ (ss > 9 ? "" : "0") + ss; 
		}else{
			let yy = dateObj.getFullYear(); 
			let mm = dateObj.getMonth() + 1; 
			let dd = dateObj.getDate(); 
			return yy + "/" + (mm > 9 ? "" : "0") + mm + "/" + (dd > 9 ? "" : "0") + dd; 
		}
	}
	
	return {
		add : add,
		getList : getList,
		remove : remove,
		update : update, 
		get : get, 
		displayTimeFormat : displayTimeFormat
	};  // replyService 객체에 함수들 저장 
})(); 





