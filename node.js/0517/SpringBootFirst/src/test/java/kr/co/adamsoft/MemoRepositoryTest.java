package kr.co.adamsoft;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// 스프링 부트 테스트 클래스를 의미하는 어노테이션
@SpringBootTest
public class MemoRepositoryTest {

	@Autowired
	MemoRepository memoRepository;
	
	@Test
	public void testDependency() {
		System.out.println(memoRepository);
	}
}
