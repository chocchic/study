/**
 * 댓글용 JS 파일
 */

console.log("Reply JS 모듈..");

// 자바의 replyService 구현 클래스격
var replyService = (function(){
	
	// 댓글 등록처리 : reply = 추가할 댓글 정보, callback = 추가 후 실행할 함수
	function add(reply, callback){
		console.log("reply add!!");
		$.ajax({
			type:"post",
			url:"/replies/new",
			data: JSON.stringify(reply),
			contentType: "application/json;charset=utf-8",
			success: function(result,status, xhr){
				if(callback){
					callback();
				}
			},
			error:function(xhr, status, e){
				if(error){
					error(e);
				}
			}
		});//ajax
	}//add
	
	// 전체 댓글 가져오기
	function getList(param, callback, error){
		let bno = param.bno;
		let page = param.page || 1; // page가 없으면 1로 사용
		$.getJSON("/replies/pages/"+bno+"/"+page+".json", 
			function(data){
				if(callback){
					callback(data);
				}
			}).fail(function(xhr, status, e){
				if(error){
					error(e);
				}
		});
	}
	
	// 댓글 삭제
	function remove(rno, callback, error){
		$.ajax({
			type : "delete",
			url : "/replies/" + rno,
			success : function(result, status, xhr){
				if(callback){
					callback(data);
				}
			},error : function(xhr, status, e){
				if(error){
					error(e);
				}
			}
		});
	}
	
	//댓글 수정
	function update(reply, callback, error){
		console.log("update reply rno  : " + reply.rno);
		console.log("update reply reply: "+ reply.reply);
		$.ajax({
			type:"put",
			url:"/replies/" + reply.rno,
			data:JSON.stringify(reply),
			contentType : "application/json;charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},error : function(xhr, status, e){
				if(error){
					error(e);
				}
			}
		});
	}
	// 댓글 한개 조회
	function get(rno, callback, error){
		$.get("/replies/"+rno+".json", function(result){
			if(callback){
				callback(data);
			}
		}).fail(function(xhr, status, e){
			if(error){
				error(e);
			}
		});
	}
	
	// 시간 포맷 함수
	function displaytimeFormat(timeVal){
		// 오늘단 거는 시간, 그전에는 날짜 
		let today = new Date();			 // 오늘 날짜
		let diff = today.getTime() - timeVal; // 시간차 구하기
		let dateObj = new Date(timeVal); // JS Date 객체로 변경
		if(diff < (1000 * 60 * 60 * 24)){ // 24h보다 차이가 작다
			let hour = dateObj.getHours();
			let min = dateObj.getMinutes(); 
			let sec = dateObj.getSeconds();
			return (hour > 9 ? '' : '0') +hour+ ":" + (min > 9 ? '' : '0')+min+ ":" + (sec > 9 ? '' : '0')+sec;
		}else{
			let yy = dateObj.getFullYear();
			let mm = dateObj.getMonth() + 1;
			let dd = dateObj.getDate(); 
			return yy + "/" +(mm>9 ? "" : "0") + mm + "/" +(dd > 9 ? '' : '0') + dd;
		}
	}
	//console.log("replyService 즉시함수 호출")
	return {
		add:add,
		getList:getList,
		remove: remove,
		update:update,
		get:get,
		displaytimeFormat:displaytimeFormat
	}; // replyService 객체에 저장되는 값
})();