package com.board.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.board.domain.KakaoPayApprovalVO;
import com.board.domain.KakaoPayReadyVO;

@Service
public class KakaoPayServiceImpl implements KakaoPayService {
	
	// HTTP REST 요청해주는 기능을 가진 클래스 : 빈으로 등록해서 얻어다 씀
	@Autowired
	private RestTemplate restTemplate;
	private KakaoPayReadyVO readyVO;
	
	@Override
	public String kakaoPayReady() {
		System.out.println("ready service method");
		// 카카오 결제 준비 처리 요청
		
		// #1. 요청시 필요한 헤더정보 준비
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization","KakaoAK "+"395f6291ce611687cdf212033eb48ca5");
		
		// 카캌오 서버에게 난 JSON 형태로 응답받고 싶어라는 정보를 줌
		headers.add("Accept",MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8");
		// #2. 카카오 서버로 요청할 때 보내줄 데이터 BODY 준비
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME"); //가맹점 코드(테스트는 TC0ONETIME로 사용)
		params.add("partner_order_id","202204070001"); 	// 우리 사이트에서 생성한 주문 고유 번호
		params.add("partner_user_id", "pikachu"); 		// 결재하는 사용자 id
		params.add("item_name", "cola"); 				// 상품명
		params.add("item_code", "P00123"); 				// 상품코드
		params.add("item_quantity", "10"); 					//상품 수량
		params.add("total_amount", "1000");				// 상품 총액
		params.add("tax_free_amount", "0");				// 비과세 상품 금액
		params.add("approval_url", "http://localhost:8080/kakao/kakaoPaySuccess");// 결제 성공시 redirect할 주소
		params.add("cancel_url", "http://localhost:8080/kakao/kakaoPayCancel");	// 결제 취소시 redirect할 주소
		params.add("fail_url", "http://localhost:8080/kakao/kakaoPayFail");	// 결제 실패시 redirect할 주소
		
		// 헤더와 바디 합치기
		HttpEntity<MultiValueMap<String, String>> body=  new HttpEntity<MultiValueMap<String,String>>(params,headers);
		
		// #3. 요청 
		// 매개변수 1 : 요청 주소
		// 매개변수 2 : 요청 headers + body
		// 매개변수 3 : 응답받을 type
		try {
			readyVO = restTemplate.postForObject(new URI("https://kapi.kakao.com/v1/payment/ready"), body, KakaoPayReadyVO.class);
			
			System.out.println("readyVO : "+ readyVO);
			
			// 응답으로 받은 redirect: 이동할 주소 리턴
			return readyVO.getNext_redirect_pc_url();
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 결과받아와서 리턴 -> controller에서 redirect: 이동할 주소 리턴
		
		// 위에서 정상적으로 처리 안될시 돌아갈 페이지 처리
		return "/mypage/cart";
	}

	// 결제 승인 처리 메서드
	@Override
	public KakaoPayApprovalVO kakaoPayApprove(String pg_token) {
		// 헤더 정보
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization","KakaoAK "+"395f6291ce611687cdf212033eb48ca5");
		headers.add("Accept",MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8");
		
		// 바디 파라미터
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", readyVO.getTid());
		params.add("partner_order_id","202204070001"); 	// 우리 사이트에서 생성한 주문 고유 번호
		params.add("partner_user_id", "pikachu"); 		// 결재하는 사용자 id
		params.add("pg_token", pg_token);
		
		// 헤더와 바디 합치기
		HttpEntity<MultiValueMap<String, String>> body=  new HttpEntity<MultiValueMap<String,String>>(params,headers);
		
		// 요청
		try {
			KakaoPayApprovalVO approvalVO =
			restTemplate.postForObject(new URI("https://kapi.kakao.com/v1/payment/approve"), body, KakaoPayApprovalVO.class);
			
			System.out.println("approvalVO : "+ approvalVO);
			return approvalVO; // 응답으로 받은 정보 리턴
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
