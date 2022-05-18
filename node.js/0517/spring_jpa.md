# Sprint JPA
## 1.Object Relational Mapping(ORM)
* 객체 지향 패러다임을 관계형 데이터베이스에 보존하는 기술  
* 객체 와 관계형 데이터베이스를 매핑해주는 것  
* 관계형 데이터베이스에서의 Table 을 객체 지향에서는 Class 개념으로 파악하고 Row(Record)를 하나의 Instance 의 개념으로 파악  
	외래키(관계 와 참조)의 경우는 참조를 이용해서 파악  
* ORM은 객체 지향 과 관계형 데이터베이스 사이의 변환 기법을 의미  
* 대다수의 객체 지향을 지원하는 언어에서는 ORM Framework 이 존재합니다.  

## 2.Java Persistence API(JPA)  
* Java ORM 의 API 표준 : 인터페이스  
* 실제 구현체로는 Hibernate, EclipseLink, DataNucleus, OpenJpa, TopLink 등 이 있으나 Hibernate를 가장 많이 사용  

JavaCode <-> Spring Data JPA <-> Hibernate <-> JDBC <-> Database  

### 1)장점
* 특정 데이터베이스에 종속되지 않음 - 데이터베이스 변경이 편리  
* 객체 지향적 프로그래밍  
* 생산성 향상  

### 2)단점
* 복잡한 쿼리 처리를 할 때 코드가 가독성이 떨어짐  
* 성능 저하 위험  
* 학습 시간이 오래 걸림  

### 3)용도  
* 데이터베이스 구조를 몰라도 되는 SI 업계에서는 SQL Mapper Framework 인 MyBatis를 많이 사용  
* 데이터베이스 구조를 알 수 있는 솔루션 업계에서는 ORM 인 JPA를 많이 사용하며 다른 언어에서도 대부분 ORM 사용을 권장  

### 4)구성 요소  
* Entity(개체)  
	- 데이터베이스의 테이블에 해당하는 class  
	- @Entity 으로 생성  

* Entity Manager Factory  
	- Entity Manager 클래스의 Instance를 관리하는 객체  

* Entity Manager  
	- Entity를 관리하기 위한 객체  
	- 데이터베이스 작업을 제공  

* Persistence Context  
	- Entity를 영구 저장하기 위한 객체  
	- 이 계층을 이용해서 작업을 수행하게 되면 버퍼링 이나 캐싱 같은 작업을 할 수 있어서 효율적  

## 3.JPA 실습  
### 1)프로젝트 생성  
* spring dev tools, lombok,  spring web, jpa, mysql driver, oracle driver

### 2)프로젝트 실행 - 에러 발생  
* jpa 가 포함된 프로젝트에서는 데이터베이스 접속 정보가 없으면 에러

### 3)데이터베이스 접속 정보 작성 - src/main/resources/application.properties 파일  

* 웹 서버 포트 설정  
server.port=80

* 데이터베이스 접속 정보  
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver  
spring.datasource.url=jdbc:mysql://localhost:3306/node?useUnicode=yes&characterEncoding=UTF-8&serverTimezone=UTC  
spring.datasource.username=root  
spring.datasource.password=1234  

### 4)프로젝트 실행 - 정보가 올바르다면 에러가 발생하지 않습니다.  

## 4.Entity 클래스 생성  
### 1)annotation  
* @Entity: 클래스 이름 위에 적어서 Entity로 선언 - 데이터베이스에서 테이블 과 매핑  
* @Table: 매핑할 테이블을 설정하는 것인데 생략하면 Entity 이름 과 동일한 테이블 과 매핑  

* @Id: 속성 위에 기재해서 기본키로 설정  
* @GeneratedValue: 속성 위에 기재해서 기본키 생성 방법을 설정  

* @Column: 컬럼 과 속성 매핑  

* @Lob: BLOB 나 CLOB 매핑 - BLOB는 파일의 내용을 바이트로 기록 할 때 CLOB는 문자열을 바이트로 기록할 때  

* @CreationTimestamp: 생성 시간 매핑  
* @UpdateTimestamp: 수정 시간 매핑  

* @Enumerated: enum(나열형 상수, 특정 값들만을 갖도록 한 것) 매핑  

* @Transient: 테이블 매핑 제외  

* Temporal: 날짜 타입 매핑  

* @CreateDate: Entity 가 생성되어 저장될 때 시간 자동 저장  
* @LastModifiedDate: Entity 가 수정되어 저장될 때 시간 자동 저장  

### 2)프로젝트의 기본 패키지 .entity 패키지에 Entity로 사용할 Memo 클래스를 생성하고 작성  
```java
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
```
### 3)application.properties 파일에 데이터베이스 연동 설정을 추가  
* 하이버네이트가 수행하는 sql을 콘솔에 출력  
spring.jpa.properties.hibernate.show_sql=true  

* 포맷팅해서 보기 좋게 출력  
spring.jpa.properties.hibernate.format_sql=true  

* 쿼리에 물음표로 출력하는 바인드 파라미터 출력  
logging.level.org.hibernate.type.descriptor.sql=trace  

* ddl 옵션  
spring.jpa.hibernate.ddl-auto=update  

* 데이터베이스 플랫폼 설정  
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect  

* **properties쪽 코드 복붙시 뒤에 공백이 있는지 확인**
### 4)spring.jpa.hibernate.ddl-auto 옵션  
* none을 설정하면 아무것도 실행하지 않음, 데이터베이스에서 DDL을 수행해야 합니다. 기본값  

* create-drop: 데이터베이스 사용을 위해서 연결을 할 때 테이블을 drop 하고 create를 수행  
In-Memory DB의 기본값으로 테스트 할 때 이 방식이 사용됩니다.  
전자 정부 프레임워크가 만들어주는 샘플 프로젝트도 이 방식을 사용합니다.  

* create: 테이블을 생성  

* update: 구조가 변경된 경우 적용  

* validate: 테이블을 생성하지는 않고 정상 매핑 되는지 확인  

대부분의 경우는 none으로 설정해서 테이블을 직접 생성하는 경우가 많습니다.  
스테이징 환경이나 운영환경에서는 validate를 사용하기도 합니다.  

### 5) 빼먹음
```java
	package kr.co.adamsoft.entity;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;

	import lombok.AllArgsConstructor;
	import lombok.Builder;
	import lombok.Getter;
	import lombok.NoArgsConstructor;
	import lombok.ToString;

	//데이터베이스의 테이블 과 연동
	@Entity
	//테이블 이름 설정
	@Table(name="tbl_memo")

	//toString메서드 생성
	@ToString
	// get 메서드 생성
	@Getter
	// build라는 메서드를 이용해서 매개변수가 없는 생성자(default constructor)를 이용해서 인스턴스를 생성하고 속성을 호출해서 값을 설정하도록 해주는 설정
	@Builder
	// 모든 속성을 매개변수로 받는 생성자
	@AllArgsConstructor
	// 매개변수가 없는 생성자
	@NoArgsConstructor
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
```  

## 5. JpaRepository 인터페이스  
* Entity와 연결된 테이블의 CRUD 작업을 위한 인터페이스  
* 기본적인 메서드는 이미 구현되어 있음  
* 계층  
Repository <- CrudRepository <- PagingAndSortRepository <- JpaRepository  

* 인터페이스를 생성할 때는 JpaRepository <Entity Class 클래스 이름, Entity의 Primary Key의 자료형>을 extends해서 인터페이스를 생성하면 되는데, 이 작업만으로 Entity Class에 CRUD 작업을 할 수 있는 상태가 됩니다.  

* Memo Entity에 대한 데이터베이스 작업을 수행하는 Interface를 생성 - repository.MemoRepository  
	```java
		// Memo Entity에 대한 작업을 수행하기 위한 Repository 인터페이스
		public interface MemoRepository extends JpaRepository<Memo, Long>{

		}
	```
	* 위 인터페이스에서는 insert나 update를 위한 save 그리고 id를 가지고 데이터를 조회하는 findById와 전체 데이터를 조회하는 findAll 그리고 데이터 개수를 확인하는 count 그리고 delete, deleteById와 같은 메서드가 구현되어 있습니다.  

### 1) Spring의 Text 클래스를 생성해서 Repository 테스트  
* Test에서 작업하는 테이블의 In-Memory DB입니다.  
* src/test 디렉터리에 클래스를 만들고 주입하는 테스트
```java
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
```  

### 2) 데이터 삽입 테스트 - 테스트 클래스에 작성하고 테스트  
```java
	@Test
	public void testInsert() {
		// 정수 스트림(여러 개의 데이터를 순회하면서 사용할 수 있도록 만든 데이터의 통로) 생성
		// forEach는 데이터를 순회하면서 작업을 수행하는 메서드
		// forEach의 매개변수는 매개변수가 1개이고 리턴이 없는 람다가 매개변수
		// 이 때 매개변수는 스트림의 데이터가 순서대로 주입됩니다. 
		
		// List <Integer> list = new ArrayList<>();
		// for(int i = 1; i< 101; i++) { list.add(i);}
		// for(Integer i : list){Memo memo = new Memo(); memo.setMemoText("Sample..."+i); memoRepository.save(memo);}
		// 이 코드와 같은 의미 
		IntStream.rangeClosed(1, 100).forEach(i -> {
			// builder()는 매개변수가 없는 생성자를 이용해서 객체를 생성하고 속성 이름에 값을 바로 대입해서 객체를 생성해주는 메서드 입니다.
			Memo memo = Memo.builder().memoText("Sample..."+i).build();
			memoRepository.save(memo);
		});
	}
```  
* 테스트를 실행한 후 콘솔에서 insert 구문이 수행되는지 확인  

### 3) 테이블의 전체 조회
```java
	@Test
	public void testAllSelect() {
		//테이블의 전체 데이터 가져오기
		List<Memo> list = memoRepository.findAll();
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
```  

### 4) 테이블에 기본키를 가지고 조회
```java
	@Test
	public void testSelectOne() {
		// 기본키를 가지고 데이터 가져오기
		// 존재하는 경우는 데이터가 출력
		System.out.println("Select One");
		Optional<Memo> memo = memoRepository.findById(1L);
		System.out.println(memo);

		// 존재하지 않는 경우는 optional.empty
		memo = memoRepository.findById(300L);
		System.out.println(memo);
	}
```  

* 없는 데이터는 optional.empty라고만 나온다  
<img src="./optional.jpg">

### 5) 데이터 수정
```java
	@Test
	public void testUpdate() {
		//mno가 100이고 memoText가 UpdateText인 인스턴스 생성
		Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
		
		// save의 호출
		// mno의 값이 없으면 삽입이 되고 있으면 수정
		System.out.println(memoRepository.save(memo));
		
		System.out.println(memoRepository.findById(100L));
	}
```

### 6) 데이터 삭제  
```java
	@Test
	public void testDelete() {
		// 기본키 값을 가지고 데이터를 삭제 
		memoRepository.deleteById(100L);
		
		System.out.println(memoRepository.findById(100L)); //Optional.empty가 뜬다.
	}
```  

### 7) 페이징과 정렬  
* RDBMS들은 페이징을 처리하는 방법이 제각각입니다.  
	오라클의 경우는 inline view나 FETCH & OFFSET을 이용해서 처리하고, MySQL은 limit을 이용해서 처리  
* JPA에서는 Pageable이라는 인터페이스를 이용해서 처리  
	PageRequest.of(int page, int size) : 페이지 번호와 개수를 설정해서 Pagable객체를 만들고 이 객체를 findAll에 적용해서 페이징을 구현. 이 메서드를 오버로딩이 되어있는데 정렬을 위한 Sort 객체를 추가로 매개변수를 받을 수 있음  
* 테스트 메서드를 작성하고 확인  
```java
	@Test
	public void testPaging() {
		Pageable page = PageRequest.of(0, 10);
		Page<Memo> list = memoRepository.findAll(page);
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
```  

### 8) 데이터 정렬  
* Sort.by(컬럼이름).정렬방법()을 이용해서 Sort객체를 생성하고 Pageable을 생성해서 적용  
* 여러 개의 정렬 조건을 생성하고자 하면 첫 번째 Sort객체를 생성하고 .and(두번째 Sort객체)를 이용하면 됩니다.  
* 테스트 메서드를 작성하고 확인  
```java
	@Test
	public void testSort() {
		// mno의 내림차순 정렬
		Sort sort = Sort.by("mno").descending();
		Pageable page = PageRequest.of(1, 10, sort);
		Page<Memo> list = memoRepository.findAll(page);
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
```

## 7. JpaRepository의 Query 방법  
### 1) 종류  
* 기본적으로 제공되는 메서드를 활용  
* Query Method : 이름 자체가 쿼리 구문인 메서드  
* @Query : 직접 쿼리를 작성하는 방식으로 Native SQL 사용 가능  
* Querydsl : 동적 쿼리 생성  

## 8. Query Method  
* 메서드의 이름 자체가 질의문이 되는 기능  
* https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods  

### 1) 생성 방법  
작업(find, delete 등) + By + 속성이름(매개변수)  
-> 속성을 매개변수로 받아서 작업을 수행  

* Item Entity에서 num을 가지고 조회  
	findByNum(자료형 num);  

### 2) 리턴 타입  
	* 조회의 경우는 List나 배열  
	* Pageable을 매개변수로 사용하면 Page<Entity>  
	* 조회가 아닌 작업은 void  

### 3) 키워드  
Distinct  
	: findDistinctByLastname - Lastname으로 조회하는데 중복 제거  
And  
	: findByLastnameAndFirstname - Lastname과 Firstname이 모두 일치  
Or  
Is, Equals  
Between  
LessThan  
LessThanEqual  
GraterThan  
GraterThanEqual  
기타 등등

### 4) Memo Entity에서 mno값이 70에서 80사이인 데이터를 조회  
* MemoRepository 인터페이스에 메서드를 선언  
```java
// Memo Entity에 대한 작업을 수행하기 위한 Repository 인터페이스
public interface MemoRepository extends JpaRepository<Memo, Long>{
	// mno값이 70에서 80사이인 데이터를 조회
	List<Memo> findByMnoBetween(Long first, Long second);
}
```  

* Test 클래스에 메서드를 생성해서 확인  
```java
@Test
public void betweenTest() {
	List<Memo> list = memoRepository.findByMnoBetween(70L, 80L);
	for(Memo m : list) {
		System.out.println(m);
	}
}
```  

### 5) Memo Entity에서 mno값이 70에서 80사이인 데이터를 내림차순 조회  
* MemoRepository 인터페이스에 메서드를 선언  
```java
	// mno값이 70에서 80사이인 데이터를 mno를 내림차순 정렬하여 조회
	List<Memo> findByMnoBetweenOrderByMnoDesc(Long first, Long second);
```  

* Test 클래스에 메서드를 생성해서 확인  
```java
@Test
public void betweenOrderTest() {
	List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
	for(Memo m : list) {
		System.out.println(m);
	}
}
```  

### 6) MemoEntity에서 10부터 50사이의 데이터를 조회하는데 두번째 페이지부터 10개만 mno의 내림차순 정렬해서 조회  
* MemoRepository 인터페이스에 메서드를 선언  
```java
	// mno값이 from에서 to사이인 데이터를 조회 - 페이징 적용
	List<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);
```  

* Test 클래스에 메서드를 생성해서 확인  
```java
@Test
public void betweenPagingTest() {
	//mno의 내림차순 정렬 후 2페이지부터 10개를 가져오는 Pageable 객체 생성
	Pageable page = PageRequest.of(1, 10, Sort.by("mno").descending());
	List<Memo> list = memoRepository.findByMnoBetween(10L, 50L, page);
	for(Memo memo : list) {
		System.out.println(memo);
	}
}
```  

### 7) MemoEntity에서 mno가 10보다 작은 데이터 삭제
* MemoRepository 인터페이스에 메서드를 선언  
```java
	// mno가 매개변수보다 작은 데이터 삭제
	void deleteByMnoLessThan(Long num);
```  

* Test 클래스에 메서드를 생성해서 확인  
```java
	// 삭제하는 작업이므로 트랜젝션을 설정해주어야 함 
	@Test
	@Commit
	@Transactional
	public void deleteByMno() {
		memoRepository.deleteByMnoLessThan(10L);
	}
``` 
## 9. @Query
* JPA Repository가 제공하는 메서드들은 복잡한 SQL에 해당하는 구문을 만들어내기 어려움  
* JPQL이라는 특별한 모양의 구문을 이용해서 처리  
* insert, delete, update작업은 @Modifying이라는 어노테이션과 함게 작성해서 수행  
* 예 - Memo Entity의 모든 데이터를 mno순으로 내림차순 정렬해서 리턴  
```java
	@Query("select m from Memo m order by m.mno desc")
	public List<Memo> getListDesc();
```  

* 파라미터를 바인딩하고자 하는 경우에는 @Param 자료형 이름의 형태로 메서드의 매개변수로 만들고 JPQL을 작성할 때 #{이름} 또는 :이름 또는 ?인덱스의 형태로 만들면 됩니다.  

### 1) mno값을 가지고 데이터를 조회해서 memoText를 수정하는 작업  
* MemoRepository 인터페이스에 필요한 메서드 생성  
	대소문자를 구분하므로 쿼리의 대소문자 주의  
```java
	// mno을 가지고 데이터를 찾아서 memoText를 수정하는 메서드
	@Query("update Memo m set m.memoText = :memoText where m.mno = :mno")
	//@Query에서 수행하는 문장이 select가 아닐 때 원본에 반영하도록 하기 위해서 설정
	@Modifying
	// 이 메서드 안에서 이루어지는 모든 작업은 하나의 트랜젝션으로 수행하도록 해주는 annotation
	// Repository에 적용하지 않고 대부분은 Service나 Controller에 적용
	@Transactional
	int updateMemoText(@Param("mno")Long mno, @Param("memoText")String memoText);
	
	@Query("update Memo m set m.memoText =:#{#mmm.memoText} where m.mno =:#{#mmm.mno}")
	@Modifying
	@Transactional
	int updateMemoText(@Param("mmm")Memo memo);
```

* Test 클래스에서 두 개의 메서드 호출  
```java
	@Test
	public void testUpdateQuery() {
		// 수정하는 메서드 호출
		System.out.println(memoRepository.updateMemoText(11L, "반갑습니다."));
		System.out.println(memoRepository.updateMemoText(Memo.builder().mno(12L).memoText("dkd안녕하세요").build()));
	}
```  

### 2) 조회  
* 조회를 할 때 Paging처리를 할 것이라면 countQuery라는 속성에 데이터 개수를 구하는 JPQL이 추가되어야 합니다.  

* mno와 Pageable을 매개변수로 받아서 mno보다 큰 mno를 가진 데이터에 페이징을 적용해서 리턴받는 메서드를 MemoRepository에 생성  
```java
	// mno보다 큰 mno를 가진 데이터를 조회
	@Query("select m from Memo m where m.mno > :mno")
	Page<Memo> getListWithQuery(@Param("mno")Long mno, Pageable pageable);
```  

* Test 클래스에서 위 메서드 호출  
```java
	@Test
	public void testSelectQuery() {
		Pageable pageable = PageRequest.of(0,10,Sort.by("mno").descending());
		Page<Memo> page = memoRepository.getListWithQuery(50L, pageable);
		for(Memo m : page) {
			System.out.println(m);
		}
	}
```  

* +) 
	사용자의 요청 하나당 서비스의 메서드는 1개인 것을 권장  
	회원가입을 요청했는데 회원가입도 처리하고 다른 작업도 부수적으로 처리  

	서비스1 - 회원가입  
	서비스2 - 회원가입  
	서비스3 - 다른 작업  

### 3) Object 타입 리턴
* select 구문의 경우 Entity타입으로 리턴을 하게 되는데 불필요한 속성까지 호출되는 경우가 있습니다.  
	또한 JOIN이나 GROUP BY 등을 수행하게 되면 Entity 타입이 만들어져 있지 않을 가능성이 높습니다.  
* CURRENT_DATE나 CURRENT_TIME, CURRENT_TIMESTAMP와 같은 속성을 이용해서 현재 시간을 구할 수 있음.  

* MemoRepository 인터페이스에 mno와 memoText 그리고 현재 날짜를 조회하는 메서드를 생성  
```java
	// mno보다 큰 mno를 가진 데이터를 조회
	@Query("select m.mno, m.memoText, CURRENT_DATE from Memo m where m.mno > :mno")
	Page<Object[]> getListWithQueryObject(@Param("mno")Long mno, Pageable pageable);
```

* Text 클래스에 메서드를 만들어서 확인
```java
	@Test
	public void testSelectObjectQuery() {
		Pageable pageable = PageRequest.of(0,10,Sort.by("mno").descending());
		//Page<Memo> page = memoRepository.getListWithQueryObject(50L, pageable); // Memo에는 CURRENT_DATE를 담을 공간이 없으므로 error
		Page<Object[]> page = memoRepository.getListWithQueryObject(50L, pageable);
		for(Object[] m : page) {
			System.out.println(Arrays.toString(m));
		}
	}
```  

=> Object타입의 배열로 리턴했으므로 속성 하나 하나를 사용할 때는 원래의 자료형으로 강제 형변환을 해서 사용  

### 4) NativeSQL
* 순수한 SQL 문장을 사용하기 위한 것으로 이 경우에는 nativeQuery 속성에 true를 설정해야 합니다.  
  리턴되는 데이터 타입은 Object[]입니다.  

* MemoRepository 인터페이스에 nativeQuery사용을 위한 메서드 생성  
```java
	//nativeQuery사용
	@Query(value="select * from tbl_memo where mno > 0", nativeQuery=true)
	List<Object[]> getNativeResult();
```

* Test 클래스에 테스트용 메서드를 만들어서 확인  
```java
	@Test
	public void testNativeQuery() {
		List<Object[]> list = memoRepository.getNativeResult();
		for(Object[] ar : list) {
			System.out.println(Arrays.toString(ar));
		}
	}
```

## +) 최근 프로그래밍 언어의 데이터 타입에 대한 추세  
데이터의 자료형을 mutable(수정 가능한)과 immutable(수정 불가능한)의 형태로 구분하고 다시 null이 가능한 자료형과 그렇지 않은 자료형으로 구분합니다.  
이전에는 null을 구분하는 자료형이 없어서 직접 생성한 객체가 아닌 API가 생성해준 객체를 사용할 때는 null인지 확인하고 사용했는데 이러다보니 null을 확인하는 코드가 너무 많이 필요했습니다.  

```java
참조형 = api();  
if(참조형 != null){
	수행할 작업;
}else{
	// null일때 수행할 작업;
}
```
자바에서는 이 구분을 Optional이라는 것으로 합니다.  

Memo memo = API(); 이경우는 memo의 null 체크를 할 필요가 없음.  
Optional<Memo> memo = API(); 이 경우는 memo가 null일 수도 있음.  
그리고 Memo를 사용할 때는 get메서드가 있습니다.  

## lombok 설치  
### 1) https://projectlombok.org/download에서 파일 다운로드
### 2) 터미널에서 java -jar '다운로드 파일 경로'를 실행하면 IDE선택창이 나오는데 여기서 적용할 IDE를 선택하고 install을 한 후 IDE를 다시 시작하고 프로젝트를 rebuild하면 됩니다.  
* cmd키고 다운받은 lombok.jar파일을 드래그해서 올리면 자동으로 경로를 설정해줍니다.  
* 재시작한후 (project) - (clean)을 실행하면 됩니다.  

## Java
* Big Data Platform에서 많이 사용되는 Hadoop의 Echo System의 많은 부분이 Java로 개발되어 있습니다.  
* 최근에는 Java를 플랫폼으로 많이 사용합니다.  
	Jython, Kotlin 등이 JVM 기반의 언어입니다.  
