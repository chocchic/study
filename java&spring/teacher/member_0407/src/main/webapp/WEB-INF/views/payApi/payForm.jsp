<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>payForm</title>
	<!-- jQuery 라이브러리 추가  -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- iamport.payment.js 아임포트 라이브러리 추가 : 최신버전은 iamport doc에서 찾아보기  -->
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
</head>
<body>

	<button type="button" id="payBtn"> 결제 하기 </button>

	<script type="text/javascript">
	$(document).ready(function(){
		// 결제 준비 
		var IMP = window.IMP; // 생략 가능
	    IMP.init("import 콘솔>시스템>내정보가서 가맹점식별코드 복붙"); // 내정보에서 가져오기 
	    
	    // 결제 요청 : 결제하기 버튼 클릭시 요청 
	    $("#payBtn").click(function(){
	    	// IMP.request_pay(파라미터 객체{}, callback function(){}); 
	    	IMP.request_pay({ // parameter 
	    		pg: 'kakaopay',
	    		pay_method: 'card',
	    		merchant_uid: 'ORD_'+ new Date().getTime(),  // 임의로 만들기 
	    		name: '주문명 : 브라운시계', 
	    		amount: 1000, 
	    		buyer_name: '피카츄',
	    		buyer_tel: '010-1111-2222', 
	    		buyer_email: 'test@test.com', 
	    		buyer_addr: '서울시 서대문구....',
	    		buyer_postcode: '01001'
	    	}, function(rsp){
	    		console.log(rsp);
	    		if (rsp.success) {
	                // 결제 성공 시 로직,
	                console.log("결제 성공!!!");
	                // 응답 받은 정보를 DB 일정 데이터를 저장하겠다~~~  
	                let result = {
	                		imp_uid: rsp.imp_uid, 	// 아임포트 고유 결제 번호 
	                		merchant_uid: rsp.merchant_uid, // 우리사이트의 고유 주문번호
	                		pay_method: rsp.pay_method, 	// 결제 방법, card, kakaopay
	                		paid_amount: rsp.paid_amount,	// 결제 금액 
	                		name: rsp.name,					// 주문명 
	                		pg_provider: rsp.pg_provider, 	// 결제 승인시도한 PG사 
	                		buyer_name: rsp.buyer_name, 	// 주문자명 
	                		buyer_email: rsp.buyer_email, 	// 주문자 이메일 
	                		buyer_tel: rsp.buyer_tel, 		// 주문자 휴대폰번호 
	                		buyer_addr: rsp.buyer_addr,
	                		buyer_postcode: rsp.buyer_postcode, 
	                		//paid_at: rsp.paid_at, 
	                		paid_at: new Date().getTime(), 	// 결제 승인시각 
	                		receipt_url: rsp.receipt_url	// PG사에서 발행하는 거래 매출 전표 url
	                };
	                // DB 처리되게 하는 요청
	                $.ajax({
	                	url: '/payApi/insertPayInfo', 
	                	type: 'POST',
	                	data: JSON.stringify(result),
	                	contentType: 'application/json;charset=utf-8', 
	                	dataType: 'text',  // ajax 요청처리후 Controller에서 "success"같은 문자열 리턴해줄예정.. 
	                	success: function(data, status, xhr){
	                		alert("결제 처리 성공!!!"); 
	                		console.log(data);
	                	}, 
	                	error: function(xhr, status, e) {
	                		console.log(e);
	                	}
	                }); 
	                
	            } else {
	                // 결제 실패 시 로직,
	                console.log("결제 실패....");
	            }
	    		
	    	}); 
	    	
	    	
	    }); 
		
		
		
	}); 	
	
	
	</script>

</body>
</html>