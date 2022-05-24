## Back End와 Front End의 분리  
* Back End는 MySQL과 Spring Boot의 조합으로 만들어서 JSON 데이터를 전송  
* Front End는 react.js를 이용해서 구현  

## 1. REST API를 테스트할 수 있는 애플리케이션 설치 - postman  
* https://www.postman.com/downloads  

## 2. Back End Application  
### 1) 프로젝트 생성  
* 의존성 : devtools, web, jpa, lombok, mysql  

### 2) application.properties에 이전 프로젝트 것 thymeleaf만 빼고 복붙  
```ini
#server 의 port 설정
server.port = 80

#연결할 데이터베이스 설정 - MySQL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/node?useUnicode=yes&characterEncoding=UTF-8&serverTimezon=UTC
spring.datasource.username=root
spring.datasource.password=1234

#연결할 데이터베이스 설정 - Oracle
#spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
#spring.datasource.url=jdbc:oracle:thin:@192.168.10.4:1521:xe
#spring.datasource.username=system
#spring.datasource.password=oracle

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
logging.level.org.hibernate.type.descriptor.sql=trace

spring.devtools.livereload.enabled=true
```  

### 3) Spring JPA에서 데이터베이스 작업을 감시하기위한 어노테이션을 entrypoint클래스(시작점이 되는 클래스나 함수)에 추가
```java
@EnableJpaAuditing
@SpringBootApplication
public class ToDoBackEndApplication {
	public static void main(String[] args) {
		SpringApplication.run(ToDoBackEndApplication.class, args);
	}
}
```

### 4) model 패키지에 데이터 삽입시간과 수정시간을 가진 BaseEntity를 생성
```java
// 데이터베이스 작업을 감시하도록 설정
// Listener: 이벤트가 발생했을 때 처리하는 객체
@EntityListeners(value= {AuditingEntityListener.class})

// 테이블로 생성하지 않는 Entity를 생성하기 위한 설정
@MappedSuperclass


// 인스턴스를 만들 수 없는 클래스
public class BaseEntity {
	@CreatedDate
	@Column(name = "regdate", updatable = false)
	private LocalDateTime regDate;
	
	@CreatedDate
	@Column(name = "regdate", updatable = true)
	private LocalDateTime modDate;
}
```  

### 5) model패키지에 데이터 저장을 위한 ToDo엔티티 클래스를 생성  
```java
@Builder // 생성자를 사용하지 않고 인스턴스를 생성하고 속성이름을 setter처럼 사용하기 위한 어노테이션
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="todo")
@Entity
public class ToDoEntity extends BaseEntity{ // regDate와 modDate를 자동으로 상속
	@Id
	// 시스템이 랜덤하게 uuid로 기본키 값을 생성
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="systme-uuid", strategy="uuid")
	private String id;
	
	@Column(length = 100, nullable = false)
	private String userId;
	
	@Column(length = 500, nullable = false)
	private String title;
	
	@Column(nullable = false)
	private boolean done;	
	
}
```

### 6) 실행을 해서 테이블이 생성되는지 확인  

### 7) persistence 패키지에 ToDoEntity 작업을 위한 ToDoRepository 인터페이스를 생성하고 메서드 선언  
```java

```  

### 8) test 패키지에 ToDoRepository를 테스트할 수 있는 클래스를 생성하고 테스트
* 사용자의 

### +) 그냥 하다보면 프로그래머들이 알아야하는 단어들  
* Authentication(인증)  
* Authorization(인가)  
* Audit(감사 - 감시)  