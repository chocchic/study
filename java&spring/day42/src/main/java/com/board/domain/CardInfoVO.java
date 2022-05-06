package com.board.domain;

import lombok.Data;

@Data
public class CardInfoVO {
	private String purchase_corp; //	String	매입 카드사 한글명
	private String purchase_corp_code; //	String	매입 카드사 코드
	private String issuer_corp; //	String	카드 발급사 한글명
	private String issuer_corp_code; //	String	카드 발급사 코드
	private String kakaopay_purchase_corp; //	String	카카오페이 매입사명
	private String kakaopay_purchase_corp_code; //	String	카카오페이 매입사 코드
	private String kakaopay_issuer_corp; //	String	카카오페이 발급사명
	private String kakaopay_issuer_corp_code; //	String	카카오페이 발급사 코드
	private String bin; //	String	카드 BIN
	private String card_type; //	String	카드 타입
	private String install_month; //	String	할부 개월 수
	private String approved_id; //	String	카드사 승인번호
	private String card_mid; //	String	카드사 가맹점 번호
	private String interest_free_install; //	String	무이자할부 여부(Y/N)
	private String card_item_code; //	String	카드 상품 코드
}
