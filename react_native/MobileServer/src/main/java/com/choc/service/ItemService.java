package com.choc.service;

import com.choc.dto.ItemDTO;
import com.choc.dto.PageRequestItemDTO;
import com.choc.dto.PageResponseItemDTO;
import com.choc.model.Item;
import com.choc.model.Member;

public interface ItemService {
	// 아이템 등록
	public Long registerItem(ItemDTO dto);
	// 하나의 아이템 가져오기
	public ItemDTO getItem(ItemDTO dto);
	// 아이템 수정
	public Long updateItem(ItemDTO dto);
	// 아이템 삭제
	public Long deleteItem(ItemDTO dto);
	
	// 페이지 단위로 데이터가져오기
	public PageResponseItemDTO getList(PageRequestItemDTO dto);
	
	// DTO를 Entity로 변환해주는 메서드
	public default Item dtoToEntity(ItemDTO dto) {
		Item item = Item.builder().itemid(dto.getItemid()).itemname(dto.getItemname()).price(dto.getPrice())
				.description(dto.getDescription()).pictureurl(dto.getPictureurl())
				.member(Member.builder().email(dto.getEmail()).build()).build();
		return item;
	}
	
	// Entity를 DTO로 변환해주는 메서드
	public default ItemDTO entitytoDto(Item item) {
		ItemDTO dto = ItemDTO.builder().itemid(item.getItemid()).itemname(item.getItemname()).price(item.getPrice())
				.description(item.getDescription()).pictureurl(item.getPictureurl()).email(item.getMember().getEmail()).build();
		return dto;
	}
}
