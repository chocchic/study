package io.github.chocchic.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import io.github.chocchic.model.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long>, QuerydslPredicateExecutor<Memo>{

}
