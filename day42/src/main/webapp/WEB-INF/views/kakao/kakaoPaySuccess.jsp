<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>kakakoPaySuccess</title>
</head>
<body>

	<h1>카카오 페이 성공</h1>
	<%-- 결제 승인 요청 후 응답 받은 내용 확인차 view에 출력 --%>
	<p> 요청 고유 번호 : ${payInfo.aid}</p>
	<p> 결제 고유 번호 : ${payInfo.tid}</p>
	<p> 가맹점 코드 : ${payInfo.cid}</p>
	<p> 가맹점 주문 코드 : ${payInfo.partner_order_id}</p>
	<p> 가맹점 회원 번호 : ${payInfo.partner_user_id}</p>
	<p> 결제 수단 : ${payInfo.payment_method_type}</p>
	<p> 결제 금액 : ${payInfo.amount}</p>
	<p> 결제 상세 정보 (카드) : ${payInfo.card_Info }</p>
	<p> 상품이름 : ${payInfo.item_name }</p>
	<p> 상품코드 : ${payInfo.item_code }</p>
	<p> 상품수량 : ${payInfo.item_quantity }</p>
	<p> 결제 준비 요청 시간 : ${payInfo.created_at }</p>
	<p> 결제 승인 시간 : ${payInfo.approved_at }</p>
</body>
</html>