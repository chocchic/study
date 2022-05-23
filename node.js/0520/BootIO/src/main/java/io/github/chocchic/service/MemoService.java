package io.github.chocchic.service;

import io.github.chocchic.dto.MemoDTO;
import io.github.chocchic.dto.PageRequestDTO;
import io.github.chocchic.dto.PageResponseDTO;
import io.github.chocchic.model.Memo;

public interface MemoService {
	// DTO객체를 Entity로 변환해주는 메서드
	// 이 메서드는 클라이언트 정보를 가지고 데이터베이스에 변환을 수행하기 위해 사용
	default Memo dtoToEntity(MemoDTO dto) {
		Memo memo = Memo.builder().gno(dto.getGno()).title(dto.getTitle()).content(dto.getContent()).writer(dto.getWriter()).build();
		
		return memo;
	}
	
	// Entity 객체를 DTO 객체로 변환해주는 메서드
	// 조회를 할 때 사용
	default MemoDTO entityToDTO(Memo memo) {
		MemoDTO dto = MemoDTO.builder().gno(memo.getGno()).title(memo.getTitle()).content(memo.getContent()).writer(memo.getWriter()).regDate(memo.getRegDate()).modDate(memo.getModDate()).build();
		
		return dto;
	}
	
	// 데이터 삽입을 위한 메서드 : 삽입된 메모의 gno값을 리턴
	public Long insertMemo(MemoDTO dto);
	// 목록보기를 위한 메서드
	PageResponseDTO<MemoDTO, Memo> getList(PageRequestDTO requestDTO);
}
