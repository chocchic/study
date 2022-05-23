package io.github.chocchic.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import lombok.Data;

// 재활용을 위해서 템플릿을 적용
// DTO는 변환할 DTO클래스가 대입되고, EN은 Entity와 DTO사이의 변환을 수행해 줄 함수
// Java에서는 Generic이라고 하고 객체 지향에서는 템플릿 프로그래밍이라고 합니다.
// 알고리즘을 구현하거나 프레임워크를 만들 때 중요
@Data
public class PageResponseDTO <DTO, EN> {
	// DTO의 List
	private List<DTO> dtoList;
	
	// Page 객체와 함수를 적용해서 List로 변환해주는 메서드
	public PageResponseDTO(Page<EN> result, Function<EN, DTO> fn) {
		dtoList = result.stream().map(fn).collect(Collectors.toList());
	}
}
