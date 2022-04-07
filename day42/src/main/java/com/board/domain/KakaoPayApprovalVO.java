package com.board.domain;

import java.util.Date;

import lombok.Data;

@Data
public class KakaoPayApprovalVO {
	private String aid; // 요청 고유번호
	private String tid; // 결제 고유 번호
	private String cid;	// 가맹점 코드
	private String sid; // 정기결제용 ID
	private String partner_order_id; 
	private String partner_user_id;
	private String payment_method_type;
	private AmountVO amount;
	private CardInfoVO card_Info;
	private String item_name;
	private String item_code;
	private Integer item_quantity;
	private Date created_at;
	private Date approved_at;
	private String payload;
}
