package io.github.chocchic.service;

import org.springframework.stereotype.Service;

import io.github.chocchic.dto.MemoDTO;
import io.github.chocchic.model.Memo;
import io.github.chocchic.persistence.MemoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2 // 로그 기록을 위한 어노테이션 - log.레벨(메세지)를 이용해서 로그를 출력하는 것이 가능
@Service
// 생성자를 이용해서 주입받기 위한 어노테이션 
@RequiredArgsConstructor
// @Autowired로 이용해서 주입받으면 주입받는 시점을 예측하기 어렵기 때문에 비추천
public class MemoServiceImpl implements MemoService {
	// 주입받기 위한 Repository - 생성자에서 주입받기 위해서는 final로 만들어져야 합니다.
	private final MemoRepository m;

	@Override
	public Long insertMemo(MemoDTO dto) {
		log.info("==데이터 삽입==");
		log.info(dto);
		
		// DTO를 Entity로 변환
		Memo memo = dtoToEntity(dto);
		
		// 데이터 저장하고 저장한 내용을 memo에 닫시 기록
		m.save(memo);
		
		return memo.getGno();
	}
}
