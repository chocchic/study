package com.chocchic.movie.dto;

import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResultDTO {
	private String fileName;
	private String uuid;
	private String uploadPath;
	
	// 이미지 경로를 리턴해주는메서드
	public String getImageURL() {
		try {
			// 파일에 한글이 있는 경우를 대비해서 UTF-8로 인코딩
			return URLEncoder.encode(uploadPath+"/"+uuid+fileName, "UTF-8");
		}catch(Exception e){
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return "";
	}
}
