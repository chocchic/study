package com.board.domain;

import lombok.Data;

@Data
public class AmountVO {
	private Integer total;	//Integer	전체 결제 금액
	private Integer tax_free;//	Integer	비과세 금액
	private Integer vat;	//Integer	부가세 금액
	private Integer point;	//Integer	사용한 포인트 금액
	private Integer discount;	//Integer	할인 금액
}
