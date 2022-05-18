package kr.co.adamsoft;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.adamsoft.entity.Memo;

// Memo Entity에 대한 작업을 수행하기 위한 Repository 인터페이스
public interface MemoRepository extends JpaRepository<Memo, Long>{

}
