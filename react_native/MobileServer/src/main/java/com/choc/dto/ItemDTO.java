package com.choc.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
	private String error;
	
	private Long itemid;
	private String itemname;
	private Integer price;
	private String description;
	//업로드 된 이미지 경로를 저장
	private String pictureurl;
	//파일을 받아오기 위한 변수
	private MultipartFile image;
	//Entity 를 만들 때 는 Entity를 외래키로 추가하지만 화면 입출력할 때는 필요한 데이터만 선언
	private String email;
	private String name;
}