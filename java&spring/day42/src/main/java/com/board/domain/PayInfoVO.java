package com.board.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PayInfoVO {
	private String imp_uid;
	private String merchant_uid;
	private String pay_method;
	private String paid_amount;
	private String name;
	private String pg_provider;
	private String buyer_name;
	private String buyer_tel;
	private String buyer_addr;
	private String buyer_postcode;
	private Timestamp paid_at;
	private String receipt_url;
	// DB 상황에 맞게 수정
}
