package com.board.domain;

import java.util.Date;

import lombok.Data;

// 카카오 결제 준비에서 요청하고 응답받을 데이터들의 VO 
@Data
public class KakaoPayReadyVO {

	private String tid;						// 결제 고유 번호 
	private String next_redirect_app_url;	// 모바일 앱인경우 결제 페이지 redirect url 
	private String next_redirect_mobile_url; // 모바일 웹인경우 
	private String next_redirect_pc_url; 	// PC웹일 경우 요청 메세지 보내기위한 사용자 정보 입력화면
											// redirect url 
	private Date created_at; 
	
}
