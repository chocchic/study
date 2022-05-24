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
public interface ToDoRepository extends JpaRepository<ToDoEntity, String>{
	//userID에 해당하는 ToDoEntity를 가져오는 메서드
	public List<ToDoEntity> findByUserId(String userid);
}
```  

### 8) test 패키지에 ToDoRepository를 테스트할 수 있는 클래스를 생성하고 테스트
* 사용자의 
```java
@SpringBootTest
public class ToDoRepoTest {
	@Autowired
	private ToDoRepository t;
	
	// 삽입 확인
	//@Test
	public void testInsert() {
		ToDoEntity todo = ToDoEntity.builder().userId("choc").title("테스트용 제목").build();
		t.save(todo);
	}
	
	// 수정 확인
//	@Test
	public void testUpdate() {
		ToDoEntity todo = ToDoEntity.builder().id("4028b88180f449f10180f44a09910000").userId("choc").title("테스트용 제목 수정됨").build();
		t.save(todo);
	}
	
	// 기본키를 가지고 데이터를 조회
	@Test
	public void testDetail() {
		// 기본키를 가지고 데이터를 조회
		Optional<ToDoEntity> result = t.findById("4028b88180f449f10180f44a09910000");
		
		// 데이터가 존재할 때
		if(result.isPresent()) {
			System.out.println(result.get());
		}else {
			System.out.println("데이터가 존재하지 않습니다.");
		}
	}

	// 기본키가 아닌 것을 가지고 조회
	//@Test
	public void testList() {
		List<ToDoEntity> result = t.findByUserId("choc");
		//List<ToDoEntity> result = t.findByUserId("c");
		//데이터가 존재할 때
		if(result.size() > 0) {
			for(ToDoEntity e : result) {
				System.out.println(e);
			}
		}else {
			System.out.println("데이터가 존재하지 않습니다.");
		}
	}
	
	// 삭제는 기본키를 가지고 지우는 것과 Entity를 이용해서 지우는 2가지를 제공
	@Test
	public void testDelete() {
		t.deleteById("4028b88180f449f10180f44a09910000");
	}
}
```

### 9) dto패키지에 ToDoEntity의 DTO클래스 생성  
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDTO {
	private String id;
	private String title;
	private boolean done;
	private LocalDateTime regdate;
	private LocalDateTime moddate;
	// ToDoEntity를 매개변수로 해서 ToDoDTO를 생성하는 생성자
	public ToDoDTO(final ToDoEntity todo) {
		this.id= todo.getId();
		this.title = todo.getTitle();
		this.done =todo.isDone();
		this.regdate = todo.getRegDate();
		this.moddate = todo.getModDate();
	}
	
	// DTO를 Entity로 변환해주는 매서드
	public static ToDoEntity toEntity(final ToDoDTO dto) {
		return ToDoEntity.builder().id(dto.getId()).title(dto.getTitle()).done(dto.isDone()).build();
	}
}
```  

### 10) dto패키지에 조회 후 출력을 위한 ResponseDTO클래스를 생성  
* 데이터 목록과 에러여부를 소유  
```java
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO <T> {
	private String error;
	private List<T> data;
}
```

### 11) 사용자의 요청을 처리할 메서드르 소유한 ToDoService 인터페이스를 생성하고 메서드 선언  
```java
public interface ToDoService {
	// 데이터 삽입
	public List<ToDoEntity> create(final ToDoEntity entity);
	// userid에 해당하는 데이터 조회
	public List<ToDoEntity> retrieve(final String userId);
	// id에 해당하는 데이터 조회
	public ToDoEntity detail(final String id);
	// 데이터 수정
	public List<ToDoEntity> update(final ToDoEntity entity);
	// 데이터 삭제
	public List<ToDoEntity> delete(final ToDoEntity entity);
	
	/*
	지난번에는 DTO와 Entityt의 변환을 Service에서 했습니다. 
	장점은 DTO와 Entity변환 작업을 Service에서 호출하기 대문에 자기한테 만들어져 있으면 코드를 읽기가 편합니다.
	단점은 Business Logic과 그렇지 않은 로직이 한 곳에 있어서 역할의 경계가 애매해지고 유지보수가 어려워질 수 있습니다.
	*/
}
```  

### 12) 사용자의 요청을 처리할 메서드를 구현한 ToDoServiceImpl클래스를 만들고 메서드 구현  
```java
@RequiredArgsConstructor
@Slf4j
public class ToDoServiceImpl implements ToDoService{
	
	private final ToDoRepository todoRepository;
	
	// 유효성검사 메서드 - 인터페이스에 만들어도 되고 ServiceImpl에 만들어도 되는데 인터페이스에 만들면 public으로 만들어야해서 외부에서 호출할 수 있게 됩니다.
	// 외부에서 호출할 수 없도록 할 때는 ServiceImpl에서 private으로 구현하고, 외부에서 호출 가능하도록하고자하면 Service인터페이스에서 default로 만들면됩니다.
	private void validate(final ToDoEntity entity) {
		if(entity== null) {
			log.warn("Entity is null");
			throw new RuntimeException("Entity cannot be null");
		}
		if(entity.getUserId() == null) {
			log.warn("Unknown User");
			throw new RuntimeException("Unknown User");
		}
	}
	
	@Override
	public List<ToDoEntity> create(ToDoEntity entity) {
		// 유효성 검사
		validate(entity);
		// 데이터 삽입
		todoRepository.save(entity);
		// 삽입한 유저의 데이터를 전부 조회해서 리턴
		return todoRepository.findByUserId(entity.getUserId());
	}

	@Override
	public List<ToDoEntity> retrieve(String userId) {
		// 유저의 데이터를 전부 조회해서 리턴
		return todoRepository.findByUserId(userId);
	}

	@Override
	public ToDoEntity detail(String id) {
		ToDoEntity todo = null;
		Optional<ToDoEntity> result = todoRepository.findById(id);
		if(result.isPresent()) {
			todo = result.get();
		}
		return todo;
	}

	@Override
	public List<ToDoEntity> update(ToDoEntity entity) {
		// 유효성 검사
		validate(entity);
		// 데이터가 데이터베이스상 존재 여부 확인
		ToDoEntity todo =detail(entity.getId());
		// 데이터가 존재하면 수정
		if(todo != null) {
			todoRepository.save(entity);
		}
		// 유저의 모든 데이터 리턴
		return todoRepository.findByUserId(entity.getUserId());
	}

	@Override
	public List<ToDoEntity> delete(ToDoEntity entity) {
		// 유효성 검사
		validate(entity);
		// 데이터가 데이터베이스상 존재 여부 확인
		ToDoEntity todo = detail(entity.getId());
		// 데이터가 존재하면 수정
		if(todo != null) {
			todoRepository.delete(entity);
//			todoRepository.deleteById(entity.getId()); // id로 지우기도 가능
		}
		// 유저의 모든 데이터 리턴
		return todoRepository.findByUserId(entity.getUserId());
	}

}
```
* java에서 final은 다른 객체로 대체할 수 없다라는 의미인데 변수에 처음에 데이터를 대입하고 변경할 계획이 없다면 반드시 붙여줄 것  
	final과 다른언어의 const를 사용하는 것은 중요한 프로그래밍 테크닉 중 하나입니다.  

### 13) URL과 Service 매핑을 위한 ToDoController클래스를 생성하고 요청 처리 메서드를 작성  
```java

```

### +) 그냥 하다보면 프로그래머들이 알아야하는 단어들  
* Authentication(인증)  
* Authorization(인가)  
* Audit(감사 - 감시)  