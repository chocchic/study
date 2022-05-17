package kr.co.adamsoft.entity.Memo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//데이터베이스의 테이블 과 연동
@Entity
//테이블 이름 설정
@Table(name="tbl_memo")
public class Memo {
	//기본키 연결
	@Id
	//기본키는 설정하는 데이터베이스 옵션에 따라 자동 생성 - Hibernate 가 결정
	//MySQL 연결하면 auto_increment, Oracle 이면 Sequence 사용
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long mno;
	
	@Column(length=200, nullable=false)
	private String memoText;
}