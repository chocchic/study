package com.board.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.board.domain.KakaoPayApprovalVO;
import com.board.domain.KakaoPayReadyVO;

@Service
public class KakaoPayServiceImpl implements KakaoPayService {

	// HTTP RESP 요청해주는 기능을 가진 클래스: 빈으로 등록해서 얻어다 쓰자 
	@Autowired
	private RestTemplate restTemplate; 
	
	private KakaoPayReadyVO readyVO; 
	
	@Override
	public String kakaoPayReady() {
		System.out.println("ready service 메서드!!!");
		// 카카오 결제 준비 처리 요청 
		
		// #1. 요청시 필요한 헤더정보 준비 
		HttpHeaders headers = new HttpHeaders(); 
		headers.add("Authorization", "KakaoAK " + "내어플리케이션>앱키>Admin키 복붙");
		// 카카오 서버에게 난 JSON 형태로 응답받고 싶어~~ 라는 정보를 주는것.
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8");
		
		// #2. 카카오서버로 요청할때 보내줄 데이터 BODY 준비 
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME"); // 가맹점 코드(테스트는 TC0ONETIME로 사용) 
		params.add("partner_order_id", "202204070001"); // 우리사이트에서 생성한 주문 고유번호 
		params.add("partner_user_id", "pikachu"); 		// 결제하는 사용자 id 
		params.add("item_name", "chocochip cookies");	// 상품명 
		params.add("item_code", "P00123");				// 상품 고유번호 (pk)
		params.add("quantity", "1");					// 상품 수량 
		params.add("total_amount", "1000");				// 상품 총액 
		params.add("tax_free_amount", "0");				// 비과세 상품 금액 
		params.add("approval_url", "http://localhost:8080/kakao/kakaoPaySuccess"); // 결제 성공시 redirect할 주소 
		params.add("cancel_url", "http://localhost:8080/kakao/kakaoPayCancel"); // 결제 취소시 redirect할 주소 
		params.add("fail_url", "http://localhost:8080/kakao/kakaoPayFail"); // 결제 실패시 redirect할 주소 
		
		/// 헤더와 바디 합치기 
		HttpEntity<MultiValueMap<String, String>> body 
		= new HttpEntity<MultiValueMap<String,String>>(params, headers);
		
		// #3. 요청
		try {
			// 매개변수1 : 요청 주소 
			// 매개변수2 : 요청 headers + body
			// 매개변수3 : 응답받을 타입 
			readyVO =
			restTemplate.postForObject(new URI("https://kapi.kakao.com/v1/payment/ready"), body, KakaoPayReadyVO.class);
		
			System.out.println("readyVO: " + readyVO);
			
			// 응답으로 받은 redirect: 이동할 주소 리턴 
			return readyVO.getNext_redirect_pc_url(); 
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 위 에서 정상적으로 처리 안되면 이동할 주소 넣기 
		return "/mypage/cart"; //지금은 임의로 작성 
	}

	// 결제 승인 처리 메서드 
	@Override
	public KakaoPayApprovalVO kakaoPayApprove(String pg_token) {
		
		// 헤더 정보 
		HttpHeaders headers = new HttpHeaders(); 
		headers.add("Authorization", "KakaoAK " + "내어플리케이션>앱키>Admin키 복붙");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8");
		
		// 바디 파라미터 
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", readyVO.getTid());
		params.add("partner_order_id", "202204070001"); // 우리사이트에서 생성한 주문 고유번호 
		params.add("partner_user_id", "pikachu"); 		// 결제하는 사용자 id 
		params.add("pg_token", pg_token);
		params.add("total_amount", "1000");				// 상품 총액 
		
		/// 헤더와 바디 합치기 
		HttpEntity<MultiValueMap<String, String>> body 
		= new HttpEntity<MultiValueMap<String,String>>(params, headers);
		
		// 요청 
		try {
		
			KakaoPayApprovalVO approvalVO =
			restTemplate.postForObject(new URI("https://kapi.kakao.com/v1/payment/approve"),
					body, KakaoPayApprovalVO.class);
			
			System.out.println("approvalVO : " + approvalVO);
			
			return approvalVO; // 응답으로 받은 정보 리턴 
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;  // 위에 처리 안되면 null 리턴 
		
	}
	
	
}
