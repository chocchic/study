package com.board.domain;

import lombok.Data;

@Data
public class CardInfoVO {

	private String purchase_corp, purchase_corp_code; 
	private String issuer_corp, issuer_corp_code; 
	private String kakaopay_purchase_corp, kakaopay_purchase_corp_code;
	private String kakaopay_issuer_corp, kakaopay_issuer_corp_code; 
	private String bin, card_type, install_month; 
	private String approved_id, card_mid, interest_free_install, card_item_code;
	
}
