package io.github.chocchic;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.chocchic.model.Memo;
import io.github.chocchic.persistence.MemoRepository;

@SpringBootTest
public class MemoRepotest {

	@Autowired
	private MemoRepository m;
	
	@Test
	public void insertMemo() {
		// 300개의 정수 모임을 생성하고 순회
		IntStream.rangeClosed(1, 300).forEach(i ->{
			// 데이터 생성
			Memo memo = Memo.builder().title("title_"+i).content("content_"+i).writer("writer_"+i).build();
			// 데이터 삽입
			m.save(memo);
		});
	}
}
