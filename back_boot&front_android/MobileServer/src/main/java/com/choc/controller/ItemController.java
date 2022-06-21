package com.choc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choc.dto.ItemDTO;
import com.choc.dto.PageRequestItemDTO;
import com.choc.dto.PageResponseItemDTO;
import com.choc.service.ItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class ItemController {
	private final ItemService itemService;
	
	//페이지 단위로 데이터 가져오기
	@GetMapping("list")
	public ResponseEntity<?> getList(PageRequestItemDTO dto){
		PageResponseItemDTO response = null;
		try {
			response = itemService.getList(dto);
		}catch(Exception e) {
			response = new PageResponseItemDTO();
			response.setError(e.getMessage());
		}
		return ResponseEntity.ok().body(response);
	}
	
	//데이터 삽입
	@PostMapping("register")
	public ResponseEntity<?> registerItem(ItemDTO dto){
		ItemDTO response = null;
		try {
			Long itemid = itemService.registerItem(dto);
			response = ItemDTO.builder().itemid(itemid).build();
		}catch(Exception e) {
			response = ItemDTO.builder().error(e.getMessage()).build();
		}
		
		return ResponseEntity.ok().body(response);
	}
	
	//데이터 1개 가져오기
	@GetMapping("get")
	public ResponseEntity<?> getItem(ItemDTO dto){
		ItemDTO response = null;
		try {
			response = itemService.getItem(dto);
		}catch(Exception e) {
			response = ItemDTO.builder().error(e.getMessage()).build();
		}
		
		return ResponseEntity.ok().body(response);
	}
	
	//데이터 삭제
	@PostMapping("delete")
	public ResponseEntity<?> deleteItem(ItemDTO dto){
		ItemDTO response = null;
		try {
			Long itemid = itemService.deleteItem(dto);
			response = ItemDTO.builder().itemid(itemid).build();
		}catch(Exception e) {
			response = ItemDTO.builder().error(e.getMessage()).build();
		}
		
		return ResponseEntity.ok().body(response);
	}
	
	// 데이터 수정
	@PostMapping("update")
	public ResponseEntity<?> updateItem(ItemDTO dto){
		ItemDTO response = null;
		try {
			Long itemid = itemService.updateItem(dto);
			response = ItemDTO.builder().itemid(itemid).build();
		}catch (Exception e) {
			response = ItemDTO.builder().error(e.getMessage()).build();
		}
		return ResponseEntity.ok().body(response);
	}
	
	// 마지막 수정시간을 전송
	@GetMapping("updatedate")
	public ResponseEntity<?> updateDateItem(){
		String updatedate = itemService.updatedate();
		// 별도의 DTO가 없어서 Map을 이용해서 저장
		Map<String, String> map = new HashMap<>();
		map.put("updatedate", updatedate);
		return ResponseEntity.ok().body(updatedate);
	}
}