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


























