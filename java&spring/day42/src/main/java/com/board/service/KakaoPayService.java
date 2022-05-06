package com.board.service;

import com.board.domain.KakaoPayApprovalVO;

public interface KakaoPayService {
	// 결제 준비 처리
	public String kakaoPayReady();
		
	// 결제 승인 요청
	public KakaoPayApprovalVO kakaoPayApprove(String pg_token);
}
