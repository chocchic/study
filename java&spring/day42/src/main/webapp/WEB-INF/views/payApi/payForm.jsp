<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>payForm</title>
<!-- jQuery 라이브러리 추가 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- iamport.payment.js 아임포트 스크립트 라이브러리 추가 -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
</head>
<body>
	<h1>결제하기</h1>
	
	<button type ="button" id="payBtn">결제하기</button>
	
</body>
<script type="text/javascript">
	$(document).ready(function(){
	    var IMP = window.IMP; // 생략 가능
	    IMP.init("imp62297080"); // 예: imp00000000, 내 정보에서 가져오기
	    
	    //결제 요청 : 결제하기 버튼 클릭시 ㅣ요청
	    $("#payBtn").click(function(){
	    	//IMP.request_pay(파라미터 객체{}, callback function(){});
	    	IMP.request_pay({
	    		pg: 'kakaopay',
	    		pay_method: 'card',
	    		merchant_uid: 'ORD_' + new Date().getTime(), // 고유 주문번호, 임의로 만들기
	    		name : '주문명 : 브라운 시계',
	    		amount: 1000,
	    		buyer_name:'pikachu',
	    		buyer_tel:'010-1111-1234',
	    		buyer_email: 'test@test.com',
	    		buyer_addr:'서울특별시 서대문구 ...',
	    		buyer_postcode: '20202' ,
	    		m_redirect_url: 'http://localhost:8080/payApi/payForm'
	    	}, function(rsp){
	    		if(rsp.success){
	    			// 결제 성공시 로직.
	    			console.log("결제 성공!");
	    			let result={
	    					imp_uid:rsp.imp_uid, // 아임포트 고유 결제 번호
	    					mechant_uid: rsp.merchant_uid, // 우리 사이트의 고유 주문번호
	    					pay_method: rsp.pay_method, // 결제 방법, card, kakaopay
	    					paid_amount: rsp.paid_amount,// 결제 금액
	    					pg_provider: rsp.pg_provider, // 결제 승인시도한 PG사
	    					buyer_name: rsp.buyer_name, //주문자명
	    					buyer_email: rsp.buyer_email, // 주문자 이메일 
	    					buyer_tel: rsp.buyer_tel, // 주문자 번호 
	    					buyer_addr: rsp.buyer_addr,
	    					buyer_postcode: rsp.buyer_postcode,
	    					//paid_at: rsp.paid_at,
	    					paid_at: new Date().getTime(), // 결제 승인 시각
	    					receipt_url:rsp.receipt_url //PG사에서 발행하는 거래 매출 전표 url
	    					
	    			};
	    			$.ajax({
	    				url:'/payApi/insertPayInfo',
	    				type: 'POST',
	    				data: JSON.stringify(result),
	    				contentType: 'application/json;charset=utf-8',
	    				dataType:'text', //ajax 요청처리후 controller에서 "success"같은 문자열 리턴해줄 예정
	    				success: function(data, status, url){
	    					alert("결제 처리 성공!");
	    					console.log(data);
	    				},error: function(xhr, status, e){
	    					console.log(e);
	    				}
	    				
	    			})
	    		}else{
	    			// 결제 실패시 로직
	    			console.log("결제 실패..");
	    			console.log("emsg: " + rsp.error_msg);
	    		}
	    	});
	    });

	});
</script>
</html>