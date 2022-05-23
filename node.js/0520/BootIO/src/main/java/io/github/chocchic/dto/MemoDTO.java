package io.github.chocchic.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Controller 와 Service 사이의 데이터 전달을 위한 클래스
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoDTO {
	private Long gno;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
}
