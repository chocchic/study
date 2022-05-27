# One To Many
## 1. 연관 관계와 관계형 데이터베이스  
* 관계형 데이터베이스에서는 1:1, 1:N(N:1), M:N의 관계를 이용해서 데이터가 서로 간에 어떤 관계를 가지고 있는지 표현  
    이 때 Primary Key와 Foreign Key를 이용해서 관계를 설정  
* JPA에서는 어노테이션과 방향성을 이용해서 표현하게 되는데, 데이터베이스에서는 방향성의 개념이 없습니다.  

1:1 -> @OneToOne  
1:N -> @OneToMany  
N:1 -> @ManyToOne  
N:M -> @ManyToMany  

* 방향성은 단방향과 양방향이 있습니다.  

### 1) 1:1관계  
* 한쪽 테이블의 하나의 행과 다른 쪽 테이블의 하나의 행이 매칭이 되는 경우  
* 이 경우는 양쪽 테이블의 기본키를 다른 테이블에 외래키로 추가해주어야 합니다.  
* JPA에서는 양쪽의 기본키에 @OneToOne을 설정하면 자동으로 추가해줍니다.  

### 2) 1:N관계  
* 회원과 게시글의 관계  
    한명의 회원은 여러개의 게시글을 작성할 수 있고, 하나읭 게시글은 한명의 회원이 작성해야 합니다.  
    관계형 데이터베이스에서는 회원의 기본키를 게시글의 외래키로 추가해야 합니다.  
    JPA에서는 회원의 기본키에 @OneToMany를 설정해도 되고 게시글 쪽의 외래키에 @ManyToOne을 설정해도 됩니다.  

### 3) M:N 관계  
* 회원과 상품의 관계  
    한 명의 회원은 여러 개의 다른 상품을 구매할 수 있습니다.  동일한 상품을 여러 명의 회원이 구매할 수 있습니다.  
    데이터베이스에서는 별도의 테이블을 만들어서 각 테이블의 기본기를 새로 만든 테이블의 외래키로 추가합니다.  
    JPA에서는 @ManyToMany로 설정은 가능하지만 @OneToMany나 @ManyToOne 2개로 분할해서 설정  

### 4) 회원, 게시글, 댓글의 관계  
    회원과 게시글은 1:N관계  
    게시글과 댓글은 1:N  
    회원과 댓글은 1:N  

## 2. Board 애플리케이션 생성  
### 1) 프로젝트 생성  
* 의존성 : Spring Boot Devtools, Lombok, Spring Web, Thymeleaf, Spring Data JPA, MySQL  
* boot 2.7.0버전  

### 2) build.gradle 파일에 시간 처리 관련 의존성을 추가  
```gradle
// https://mvnrepository.com/artifact/org.thymeleaf.extras/thymeleaf-extras-java8time
implementation group: 'org.thymeleaf.extras', name: 'thymeleaf-extras-java8time'
```  

### 3) application.properties파일에 기본 설정을 추가  
* 이전 프로젝트 것 thymeleaf만 빼고 복붙  
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

### 4) EntryPoint클래스에 데이터베이스 감시 설정 추가  
```java
@EnableJpaAuditing
@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

}
```  

### 5) 기본이 되는 속성들을 갖는 model.BaseEntity 생성  
```java
@EntityListeners(value= {AuditingEntityListener.class})
@MappedSuperclass
@Getter
abstract class BaseEntity {
	@CreatedDate
	@Column(name = "regdate", updatable = false)
	private LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(name = "moddate")
	private LocalDateTime modDate;
}
```  

## 3. Model 생성
### 1) 회원 정보를 저장할 Entity 생성 - model.Member  
* email(기본키), password, name  
```java
@Entity
@Table(name="tbl_member")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity{
	@Id
	private String email;
	private String password;
	private String name;
}
```  

### 2) 게시물 정보를 저장할 Board Entity 생성 - model.Board  
* 게시글 번호(기본키 자동 생성), 제목, 내용  
```java
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Board extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	private String title;
	private String content;
}
```

### 3) 댓글 정보를 저장할 Reply Entity를 생성 - model.Reply  
* 댓글 번호, 댓글 내용, 댓글 작성자
```java
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Reply extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rno;
	private String content;
	private String replyer;
}
```  
## 4. 관계 어노테이션의 세부 속성  
### 1) mappedBy : 양방향 연관관계를 만들 떄 매핑되는 엔티티의 필드를 연결  

### 2) cascade : Entity의 상태변화를 전파시키는 옵션  
* PERSIST : 부모 Entity가 저장될 때 자식 Entity도 같이 저장  
* MERGE : 부모 Entity가 병합될 때 자식 Entity도 같이 병합  
* REMOVE : 부모 Entity가 삭제될 때 자식 Entity도 같이 삭제  
* REFRESH : 부모 Entity가 refresh될 때 자식 Entity도 같이 refresh  
* DETACH : 부모 Entity가 detach될 때 자식 Entity도 같이 detach  
* ALL : 부모 Entity의 모든 변화가 자식 Entity에 전파  

### 3) orphanRemoval  
* 고아 객체를 삭제해주는 옵션  
* Primary Key 값이 NULL인 데이터가 고아 객체(orphanRemoval) : Entity는 데이터베이스에 있었던 데이터이지만 실제로는 메모리에 존재하는 데이터  

### 4) fetch  
* 관련있는 Entity를 가져오는 시점을 설정하는 것인데 Eagar(바로 가져오기)와 Lazy(지연)가 있음  

## 5. ManyToOne 어노테이션  
* Board테이블과 Member테이블은 Board쪽에서 바라보면 N:1의 관계입니다.  
	객체지향에서 이런 관계를 표현하고자 할 때는 Board쪽에 Member를 참조할 수 있는 속성을 하나 추가하거나 Mebmer쪽에 Board를 포함할 수 있는 List나 배열같은 속성을 추가합니다.  
* 관계형 데이터베이스에서는 Member테이블의 기본키를 Board테이블에 외래키로 설정합니다.  
* Spring JPA에서는 속성 위에 @OneToMany나 @ManyToOne을 이용해서 설정을 합니다.  

### +) Build tool  
소스코드 -> 컴파일 작업(문법적인 오류가 있는지 확인)을 수행하게 되고 이 작업을 하고나면 자바의 경우는 중간 코드인 class 파일이 생성됩니다. -> build를 수행하는데 결과로는 실행 가능한 코드가 만들어집니다. -> Run(실행)  

* gradle(build.gradle에 설정), maven(pom.xml파일에 설정), jenkins(현업과 클라우드 환경에서 가장 많이 사용되는 build tool) : 설정 파일을 수정하면 rebuild를 해주는 것이 좋습니다. 의존성 설정을 수정한 경우에는 반드시 rebuild를 해주는 것이 좋습니다. 외부 라이브러리의 의존성을 설정해서 코드를 작성할 떄는 에러가 없었는데 실행을하면 ClassNotFoundExceptioon이 발생하는 경우가 있는데 이 경우가 대부분 rebuild를 하지 않아서 그렇습니디.  

### 1) Board Entity에 Member Entity를 참조할 수 있는 속성을 추가  
```java
	// Member Entity를 N:1관계로 참조
	@ManyToOne
	private Member member;
```  

### 2) ReplyEntity에 BoardEntity를 참조할 수 있는 속성을 추가  
```java
	@ManyToOne
	private Board board;
```  

### 3) 프로젝트 실행
### 4) 데이터베이스 확인  
```sql
desc tbl_member;
desc board;
desc reply;
```
* board테이블에는 member_기본키 컬럼이 추가되어 있고 reply테이블에는 board_기본키 컬럼이 추가되어 있어야 합니다.  

## 6. Repository 생성  
### 1) Member Entity를 위한 Repository 인터페이스 생성 - persistence.MemberRepository  
```java
public interface MemberRepository extends JpaRepository<Member, String> {
	
}
```  

### 2) Board Entity를 위한 Repository 인터페이스 생성 - persistence.BoardRepository  
```java
public interface BoardRepository extends JpaRepository<Board, Long> {

}
```  

### 3) Reply Entity를 위한 Repository 인터페이스 생성 - persistence.ReplyRepository  
```java
public interface ReplyRepository extends JpaRepository<Reply, Long> {
	
}
```  
## 7. Repository Test  
### 1) Repository의 작업을 Test하기 위한 클래스를 src/test/java디렉터리의 패키지에 생성 - RepoTest
```java
@SpringBootTest
public class RepoTest {

}
```  

### 2) Member테이블에 100개의 데이터 삽입  
```java
	@Autowired
	private MemberRepository m;
	
	@Test
	public void insertMember() {
		IntStream.rangeClosed(0, 99).forEach(i -> {
			Member member = Member.builder().email("choc"+i+"@aa.com").password("1234").name("촉촉한초코칩"+i).build();
			m.save(member);
		});
	}
```  

### 3) Board테이블에 100개의 데이터 삽입  
```java
	@Autowired
	private BoardRepository b;
	
	@Test
	public void insertBoards() {
		IntStream.rangeClosed(0,99).forEach(i ->{
			Member member = Member.builder().email("choc0@aa.com").build(); // 없는 이메일 입력시 오류
			Board board = Board.builder().title("제목..."+i).content("내용..."+i).member(member).build();
			b.save(board);
		});
	}
```  
### 4) Reply테이블에 100개의 데이터 삽입  
```java
	@Autowired
	private ReplyRepository r;
	
	@Test
	public void insertReplys() {
		IntStream.rangeClosed(0,99).forEach(i ->{
			// 0부터 99사이의 정수를 랜덤하게 생성해서 Board객체를 생성
			long bno = (long)(Math.random()*100);
			Board board = Board.builder().bno(bno).build();
			Reply reply = Reply.builder().content("댓글..."+i).board(board).build();
			r.save(reply);
		});
	}	
```  
* Identity를 적용하면 차근차근 올라가지만 AUTO는 어떻게 올라갈지 모름
	AUTO할 때 오라클에서는 sequence써서 하고 MySQL에서는 AUTO_increment  

## 7. Eager/Lazy Loading  
### +) Eager / Lazy
* 객체지향 프로그래밍에서 하나의 인스턴스가 다른 인스턴스를 네부에서 사용하고자 할 때 포함된 인스턴스의 생성시점을 생각해볼 이유가 있다.  
```java
class Super {
	public Sub sub;
	public void method(){
		// 사용하기 직전에 생성 : Lazy
		sub = new Sub();
		System.out.println(sub.cnt); // 그냥 하면 생성되지 않은 객체를 불러오기 떄문에 오류남
	}
	// 생성자에서 하위 인스턴스 생성 : Eager
	public Super(){
		sub = new Sub();
	}
}
class Sub {
	public int cnt;
}
Super super = new Super();
super.method();
```
* 생성자에서 생성(Eager) : 이미 생성된 인스턴스를 사용하기 때문에 속도가 빨라질 수 있지만 사용하기 전부터 가지고 있기 떄문에 오히려 메모리에 부담을 줄 수 있음  
* 사용하기 직전 생성(Lazy) : Eager의 장단점이 반대로 적용  

* 이와 동일한 원리가 클라이언트 / 서버에도 적용이 됩니다.  
	클라이언트 프로그램은 **사용가능성**이 중요  
	서버 프로그램은 **속도**나 **효율**이 중요  

### 1) 하나의 Board데이터를 가져오는 메서드 - RepoTest에서 수행  
```java
	// 하나의 board데이터를 조회하는 메서드
	@Test
	public void readBoard() {
		Optional<Board> result = b.findById(50L);
		// 데이터를 출력
		System.out.println(result);
		System.out.println(result.get().getMember());
	}
```  
-> 수행된 쿼리를 확인해보면 Left Outer Join을 이용해서 데이터를 가져오는 것을 알 수 있습니다.  

### 2) 하나의 Reply데이터를 가져오는 메서드 
```java
	// 하나의 reply데이터를 조회하는 메서드
	@Test
	public void readReply() {
		Optional<Reply> result = r.findById(850L);
		// 데이터를 출력
		System.out.println(result);
		System.out.println(result.get().getBoard());
	}
```
-> 수행된 쿼리를 확인해보면 Left Outer Joini을 2번 이용  

### 3) Eager Loading  
* 즉시 로딩이라고 번역을 하는데 데이터를 조회할 떄 관계가 있는 데이터를 바로 찾아오는 Loading을 뜻합니다.  
* 관계를 만들 때 fetch옵션을 생략하거나 직접 지정해서 사용합니다.  
* 연관된 데이터를 자주 사용하는 경우가 아니라면 권장하지 않습니다.  

### 4) Lazy Loading  
* 지연 로딩이라고 번역을 하는데 연관된 데이터를 필요할 때 찾아오는 방식  
* 관계를 설정할 때 fetch옵션에 FetchType.LAZY를 적용하면 됩니다.  

### 5) Board Entity에 Lazy Loading 적용
```java
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Board extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	private String title;
	private String content;
	
	// Member Entity를 N:1관계로 참조
	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
}
```  
* 이전의 하나의 Board를 가져오는 메서드를 다시 테스트  
	Error  
	readBoard를 다시 실행하면 getMember()를 할 수 없어서 에러가 납니다. -> member데이터를 찾아오지 않음  
	=> 데이터를 가져올 때 트랜잭션을 적용하면 이 문제 해결 가능  

* 테스트하는 메서드위에 @Transactional을 추가하고 다시 테스트
```java
	@Test
	@Transactional
	public void readBoard() {
		Optional<Board> result = b.findById(50L);
		// 데이터를 출력
		System.out.println(result);
		System.out.println(result.get().getMember());
	}
```  
-> 이 경우에는 join을 하지 않고 Board의 데이터를 가져온 후 거기서 외래키의 값을 가지고 Member테이블의 데이터를 다시 조회합니다. ~> 서브쿼리의 형태로 동작하는 것을 확인할 수 있습니다.  

### 6) 연관관계에서 @ToString  
* ToString은 자신의 모든 속성을 toString을 호출해서 하나의 문자열로 만들어서 리턴하는 toString메서드를 자동 생성해주는 어노테이션입니다.
* Eager Loading을 사용하는 경우는 모든 데이터가 존재하기 떄문에 별 문제가 안되지만 Lazy Loading의 경우는 처음에 연관된 데이터가 존재하지 않기 때문에 문제가 발생할 가능성이 있음  
* @ToString(exclude="toString 만들 때 제외할 속성")을 이용해서 특정 속성을 제외할 수 있습니다.  

* Board에 적용  
```java
@ToString(exclude="member")
```

## 8. JPQL과 left outer join
* 게시글을 가져올 때 댓글의 수를 같이 가져오고자 하는 경우에는 하나의 entity로는 처리가 불가능  
	댓글의 수는 entity에 존재하지 않기 떄문입니다.  
	이런 경우에는 JPQL을 이용해서 해결 가능  

### 1) BoardRepository인터페이스에 Board데이터를 가져올 때 Member정보도 같이 가져오는 JPQL처리 메서드를 생성  
```java
	// Board테이블에서 데이터를 가져올 때 Member 정보도 같이 가져오는 메서드
	@Query("select b, w from Board b left join b.member w where b.bno = :bno")
	Object getBoardWithMember(Long bno);
```
### 2) RepoTest 클래스에서 앞에서 만든 메서드 테스트  
```java
@Test
	public void testReadWithWriter() {
		// 데이터 조회
		Object result = b.getBoardWithMember(40L);
		// JPQL의 결과가 Object인 경우는 Object[]로 강제 형변환해서 사용
		System.out.println((Object[])result);
	}
```  

### 3) BoardRepository인터페이스에 글 번호에 해당하는 게시글과 그에 해당하는 모든 댓글을 가져오는 메서드를 작성  
* Board테이블과 Reply테이블은 연관관계가 있으면 Reply테이블에 board라는 속성으로 관계가 설정되어 있습니다.  
	Board에서 바라볼 때는 1:N의 관계입니다.  
```java
// 하나의 글 번호를 가지고 게시글과 댓글을 모두 가져오는 메서드
	// 하나의 게시글에 여러 개의 댓글이 있으므로 리턴 타입은 List<Object[]>
	// 결과는 게시글, 댓글 + 게시글, 댓글의 형태로 리턴됩니다.
	@Query("select b,r from Board b left join Reply r on r.board = b where b.bno=:bno")
	List<Object[]> getBoardWithReply(@Param("bno") Long bno);
```  

### 4) RepoTest 클래스에서 앞에서 만든 메서드 테스트  
```java
	//@Test
	public void testGetBoardWithReply() {
		List<Object[]> result = b.getBoardWithReply(40L);
		for(Object[] ar : result) {
			System.out.println(Arrays.toString(ar));
		}
	}
```

### +) JPA MySQL 관련 오류  
계속 board_bno라는 애를 reply에서 참조를 제대로 못하고 있다는 오류가 났는데, 그냥 테이블자체 잘못 올라갔던 것이라 drop하고 다시 올렸더니 고쳐졌다  

## 9. 목록보기에 필요한 Repository 구현  
### 1) 필요한 데이터  
Board : 게시물 번호, 제목, 작성시간  
Member : 회원의 이름, 이메일  
Reply : 댓글의 수  
페이징도 구현  
### 2) BoardRepository 인터페이스에 메서드를 선언
```java
	// 목록보기를 위한 메서드  
	@Query(value="select b, w, count(r) "+
	"from Board b left join b.member w left join Reply r On r.board = b group by b", 
			countQuery="select count(b) from Board b")
	Page<Object[]> getBoardWithReplyCount(Pageable pageable);
```  

### 3) RepoTest에서 테스트
```java
	@Test
	public void testWithReplyCount() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Object[]> result = b.getBoardWithReplyCount(pageable);
		
		result.get().forEach(row ->{
			Object[] ar = (Object[])row;
			System.out.println(Arrays.toString(ar));
		});
	}
```  
-> 결과에서 0번 요소가 Board이고 1번 요소가 Member 2번 요소가 댓글의 개수  

### 4) BoardRepository인터페이스에 게시글 번호를 가지고 동일한 데ㅔ이터를 가져오는 메서드를 선언  
```java
// 게시글 번호를 가지고 데이터를 찾아오는 메서드 
	@Query(value="select b, w, count(r) "+
	"from Board b left join b.member w left join Reply r On r.board = b where b.bno = :bno")
	Object getBoardByBno(@Param("bno") Long bno);
```  

### 5) RepositoryTest 클래스에서 메서드 테스트  
```java
	@Test
	public void testByBno() {
		Object result = b.getBoardByBno(40L);
		Object[] ar = (Object[])result;
		System.out.println(Arrays.toString(ar));
	}
```  

## 10. CRUD작업을 위한 준비
### 1) BoardEntity에 해당하는 Board DTO클래스 생성  
```java
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	private Long bno;
	private String title;
	private String content;
	private LocalDateTime regdate;
	private LocalDateTime moddate;
	
	// 작성자 정보
	private String memberEmail;
	private String memberName;
	
	// 댓굴의 개수
	private int replyCount;
}
```  

### 2) Board Entity의 요청을 처리할 메서드의 원형을 소유할 BoardService인터페이스를 생성하고 DTO와 Entity사이의 변환을 수행해주는 메서드를 생성 - service.BoardService  
```java
public interface BoardService {
	// DTO를 Entity로 변환하는 메서드
	default Board dtoToEntity(BoardDTO dto) {
		Member member = Member.builder().email(dto.getMemberEmail()).build();
		Board board = Board.builder().bno(dto.getBno()).title(dto.getTitle()).content(dto.getContent()).member(member).build();
		
		return board;
	}
	
	// Entity를 DTO로 변환해주는 메서드
	default BoardDTO entitytoDTO(Board board, Member member, Long replyCount) {
		BoardDTO boardDTO = BoardDTO.builder().bno(board.getBno()).title(board.getTitle()).content(board.getContent())
				.regdate(board.getRegDate()).moddate(board.getModDate()).memberEmail(member.getEmail())
				.memberName(member.getName()).replyCount(replyCount.intValue()).build();
		return boardDTO;		
	}
}
```  

### 3) 사용자의 요청을 처리할 메서드를 구현하기위한 BoardServiceImpl 클래스를 생성 - service.BoardServiceImpl  
```java
@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{
	private final BoardRepository boardRepostiory;
}
```
## 11. 게시물 등록
### 1) BoardService 인터페이스에 메서드를 선언
```java
	// 게시물 등록을 위한 메서드
	Long register(BoardDTO dto);
```  

### 2) BoardServiceImpl에 메서드 구현  
```java
	@Override
	public Long register(BoardDTO dto) {
		log.info(dto);
		Board board = dtoToEntity(dto);
		boardRepostiory.save(board);
		return board.getBno();
	}
```  

### 3) Service 계층 테스트를 위한 클래스를 생성하고 작성한 메서드 테스트 - src/test/java의 ServiceTest  
```java
@SpringBootTest
public class ServiceTest {
	@Autowired
	private BoardService b;
	
	@Test
	public void testRegister() {
		BoardDTO dto = BoardDTO.builder().title("test").content("test content").memberEmail("choc1@aa.com").build();
		Long bno = b.register(dto);
		System.out.println("test reg : "+bno);
	}
}
```  
-> 없는 member의 email을 사용하면 오류  

## 12. 게시물 목록 보기  
### 1) 목록보기 요청을 위한 DTO클래스를 생성 - dto.PageRequestDTO  
```java
@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
	// 현재페이지 번호
	private int page;
	// 페이지당 출력할 데이터 개수
	private int size;
	// 검색 항목
	private String type;
	// 검색할 데이터
	private String keyword;
	
	// 생성자
	public PageRequestDTO() {
		this.page = 1;
		this.size = 10;
	}
	
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page-1, size, size);
	}
}
```
### 2) 목록보기 응답을 위한 DTO클래스를 생성 - dto.PageResultDTO  
```java
@Data
public class PageResultDTO<DTO, EN> {
	//DTO리스트
	private List<DTO> dtoList;
	
	// 전체 페이지 개수
	private int totalPage;
	
	// 현재 페이지 번호
	private int page;
	
	// 출력할 페이지 번호 개수
	private int size;
	
	// 시작하는 페이지번호와 끝나는 페이지번호
	private int start, end;
	
	// 이전 페이지와 다음페이지 존재여부
	private boolean prev,next;
	
	// 페이지번호 목록
	private List<Integer> pageList;
	
	// 페이지 번호목록을 만들어주는 메서드
	private void makePageList(Pageable pageable) {
		this.page = pageable.getPageNumber();
		this.size = pageable.getPageSize();
		
		int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
		start = tempEnd - 9;
		prev = start > 1;
		
		end = totalPage > tempEnd ? tempEnd:totalPage;
		next= totalPage> tempEnd;
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}
	
	public PageResultDTO(Page <EN> result, Function<EN, DTO> fn) {
		dtoList = result.stream().map(fn).collect(Collectors.toList());
		totalPage = result.getTotalPages();
		makePageList(result.getPageable());		
	}
}
```  

### 3) BoardService 인터페이스에 목록보기 메서드 선언  
```java
	// 목록보기 메서드
	PageResultDTO<BoardDTO, Object[]> <BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
```  

### 4) BoardServiceImpl 클래스에 목록보기 메서드 구현  
```java
	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		log.info(pageRequestDTO);
		
		// Entity를 DTO로 변환해주는 함수 생성
		// Repository의 메서드의 결과가 Object[]인데 이 배열의 요소를 가지고 BoardDTO를 생성해서 출력해야 함
		Function<Object[], BoardDTO> fn = (en -> entitytoDTO((Board)en[0], (Member)en[1], (Long)en[2]));
		
		// 데이터를 조회 - bno의 내림차순 적용
		// 상황에 따라서는 regdate나 moddate로 정렬하는 경우도 있음
		Page<Object[]> result = boardRepostiory.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));
			
		return new PageResultDTO<>(result, fn);
	}
```  

### 5) ServiceTest클래스에 앞에서 만든 메서드를 테스트하는 코드를 추가하고 확인  
```java
	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
		PageResultDTO<BoardDTO, Object[]> result = b.getList(pageRequestDTO);
		
		for(BoardDTO boardDTO : result.getDtoList()) {
			System.out.println(boardDTO);
		}
	}
```

## 13. 게시물 상세보기  
### 1) BoardService 인터페이스에서 상세보기(하나의 데이터를 자세히 확인하는 작업)를 위한 메서드를 선언  
```java
	// 상세보기 메서드
	BoardDTO getBoard(Long bno);
```

### 2) BoardServiceImpl에서 상세보기를 위한 메서드 구현  
```java
	@Override
	public BoardDTO getBoard(Long bno) {
		// bno를 이용해서 하나의 데이터 가져오기
		// Board, Member, Long - 댓글 개수
		Object result = boardRepostiory.getBoardByBno(bno);
		Object[] ar = (Object[]) result;
		
		return entitytoDTO((Board)ar[0], (Member)ar[1], (Long)ar[2]);
	}
```  

### 3) ServiceTest 클래스에 상세보기를 확인하는 메서드를 생성하고 확인  
```java
	@Test
	public void testBoard() {
		BoardDTO dto = b.getBoard(40L);
		System.out.println("test board : " + dto);
	}
```  

## 14. 게시물 삭제  
* 삭제를 할 때는 실제 삭제할 것인지 아니면 삭제되었다는 표시를 할 것인지를 고민해야 합니다.  
* Board테이블에서 게시글을 지울 때 Reply테이블에서 게시글에 해당하는 데이터도 삭제  

### 1) ReplyRepository 인터페이스에 게시글 번호로 삭제하는 메서드를 생성  
```java
public interface ReplyRepository extends JpaRepository<Reply, Long> {
	// 게시글 번호를 이용해서 삭제하는 메서드 
	@Modifying
	@Query("delete from Reply r where r.board.bno = :bno")
	public void deleteByBno(@Param("bno") Long bno);
}
```  

### 2) BoardService에 게시글을 삭제하는 메서드를 선언  
```java
	// 게시글 삭제 메서드
	void removeWithReplies(Long bno);
```

### 3) BoardServiceImpl클래스에 게시글을 삭제하는 메서드를 구현  
```java
	private final ReplyRepository replyRepo;

	// 이 메서드 안의 작업은 하나의 트랜젝션으로 처리해달라고 요청
	@Transactional
	@Override
	public void removeWithReplies(Long bno) {
		// 댓글 삭제
		replyRepo.deleteByBno(bno);
		// 게시글 삭제
		boardRepostiory.deleteById(bno);
	}
```  
-> @Transactional 생략시 오류나므로 조심할 것  

### 4) ServiceTest 클래스에 테스트 코드를 작성하고 테스트  
```java
	@Test
	public void testDelete() {
		Long bno = 2L;
		b.removeWithReplies(bno);
	}
```  
### +) 
```java
Scanner sc = new Scanner(System.in);
while(true){
	try{
		String x = sc.nextLine();
		int n = sc.nextInt();
	}catch(Exception e){
		continue;
	}	
}
```  

## 15. 데이터 수정  
* Board Entity에 getter는 있으나 Setter는 없으므로 수정이 안됨  
### 1) Board Entity에 title과 content를 수정할 수 있는 메서드를 추가  
```java
// title을 수정하는 메서드
	public void changeTitle(String title) {
		this.title = title;
	}
	
	// content을 수정하는 메서드
	public void changeContent(String content) {
		this.content = content;
	}
```

### 2) BoardService인터페이스에 게시글 수정을 위한 메서드를 선언  
```java
	// 게시글 수정 메서드
	void modifyBoard(BoardDTO boardDTO);
```  

### 3) BoardServiceImpl클래스에 게시글 수정을 위한 메서드 생성  
```java
	@Transactional
	@Override
	public void modifyBoard(BoardDTO boardDTO) {
		// 데이터의 존재 여부를 확인
		Optional<Board> board = boardRepostiory.findById(boardDTO.getBno());
		if(board.isPresent()) {
			board.get().changeTitle(boardDTO.getTitle());
			board.get().changeContent(boardDTO.getContent());
			
			boardRepostiory.save(board.get());
		}
	}
```

### 4) ServiceTest 클래스에 테스트 코드를 작성하고 테스트  
```java
	@Test
	public void testModifyBoard() {
		BoardDTO boardDTO = BoardDTO.builder().bno(3L).title("제목을 수정").content("내용을 수정").build();
		b.modifyBoard(boardDTO);
	}
```  



## 16. Controller와 Veiw 계층  
### 1) 공통된 디자인 적용을위한 설정 - bootstrap의 simple sidebar 디자인 적용  
* 공통된 디자인을 적용하기 위한 css 파일이나 js파일은 static 디렉터리에 위치시켜야합니다.  
* 이전에 사용했던 assets, css, js 디렉터리를 src/main/resources디렉터리 안의 static 디렉터리에 복사  
* 이전에 공통된 메뉴를 위해 만들었던 basic.html파일을 src/main/resources디렉터리안의 template 디렉터리에 layout 디렉터리를 만들고 복사  

### 2) Controller역할을 수행할 BoardController 클래스를 생성 
```java
@Controller
@Log4j2
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	
}
```  

### 3) 목록보기 처리  
* 목록보기 요청을 처리할 메서드를 BoardController 클래스에 생성  
```java
	// 목록보기 요청을 처리할 메서드
	@GetMapping({"/","/board/list"})
	public String list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("목록보기 요청"+pageRequestDTO);
		model.addAttribute("result",boardService.getList(pageRequestDTO));
		
		return "/board/list";
	}
```

* templates 디렉터리에 board디렉터리를 생성하고 list.html파일을 생성하고 작성  
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<th:block th:replace="~{/layout/basic :: setContent(~{this::content} )}">
   <th:block th:fragment="content">
      <h1 class="mt-4">
         Board List Page 
         <span><a th:href="@{/board/register}">
         	<button type="button" class="btn btn-outline-primary">REGISTER</button>
         </a></span>
      </h1>
      <form action="/board/list" method="get" id="searchForm">
         <div class="input-group">
            <input type="hidden" name="page" value="1">
            <div class="input-group-prepend">
               <select class="custom-select" name="type">
                  <option th:selected="${pageRequestDTO.type == null}">-------</option>
                  <option value="t" th:selected="${pageRequestDTO.type =='t'}">제목</option>
                  <option value="c" th:selected="${pageRequestDTO.type =='c'}">내용</option>
                  <option value="w" th:selected="${pageRequestDTO.type =='w'}">작성자</option>
                  <option value="tc" th:selected="${pageRequestDTO.type =='tc'}">제목+내용</option>
                  <option value="tcw" th:selected="${pageRequestDTO.type =='tcw'}">ALL</option>
               </select>
            </div>
            <input class="form-control" name="keyword"
               th:value="${pageRequestDTO.keyword}">
            <div class="input-group-append" id="button-addon4">
               <button class="btn btn-outline-secondary btn-search" type="button">Search</button>
               <button class="btn btn-outline-secondary btn-clear" type="button">Clear</button>
            </div>
         </div>
      </form>
      <table class="table table-striped">
         <thead>
            <tr>
               <th scope="col">#</th>
               <th scope="col">Title</th>
               <th scope="col">Writer</th>
               <th scope="col">Regdate</th>
            </tr>
         </thead>
         <tbody>
            <tr th:each="dto : ${result.dtoList}">
               <th scope="row">[[${dto.bno}]]</th>
               <td><a th:href="@{/board/read(bno = ${dto.bno},
                    page= ${result.page},
                    type=${pageRequestDTO.type} ,
                    keyword = ${pageRequestDTO.keyword})}">[[${dto.title}]]--------[<b th:text="${dto.replyCount}"></b>]
               </a></td>
               <td>[[${dto.memberName}]] <small>[[${dto.memberEmail}]]</small>
               </td>
               <td>[[${#temporals.format(dto.regdate, 'yyyy/MM/dd')}]]</td>
            </tr>
         </tbody>
      </table>
      <ul class="pagination h-100 justify-content-center align-items-center">
         <li class="page-item " th:if="${result.prev}"><a
            class="page-link"
            th:href="@{/board/list(page= ${result.start-1},
                    type=${pageRequestDTO.type} ,
                    keyword = ${pageRequestDTO.keyword} ) }"
            tabindex="-1">Previous</a></li>
         <li th:class=" 'page-item ' + ${result.page == page?'active':''} "
            th:each="page: ${result.pageList}"><a class="page-link"
            th:href="@{/board/list(page = ${page} ,
                   type=${pageRequestDTO.type} ,
                   keyword = ${pageRequestDTO.keyword}  )}">
               [[${page}]] </a></li>
         <li class="page-item" th:if="${result.next}"><a
            class="page-link"
            th:href="@{/board/list(page= ${result.end + 1} ,
                    type=${pageRequestDTO.type} ,
                    keyword = ${pageRequestDTO.keyword} )}">Next</a>
         </li>
      </ul>
      <div class="modal" tabindex="-1" role="dialog">
         <div class="modal-dialog" role="document">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title">Modal title</h5>
                  <button type="button" class="close" data-dismiss="modal"
                     aria-label="Close">
                     <span aria-hidden="true">&times;</span>
                  </button>
               </div>
               <div class="modal-body">
                  <p>[[${msg}]]</p>
               </div>
               <div class="modal-footer">
                  <button type="button" class="btn btn-secondary"
                     data-dismiss="modal">Close</button>
               </div>
            </div>
         </div>
      </div>
<script th:inline="javascript">
    var msg = [[${msg}]];
    console.log(msg);

    if(msg){
      $(".modal").modal();
    }
    var searchForm = $("#searchForm");
    $('.btn-search').click(function(e){
      searchForm.submit();
    });

    $('.btn-clear').click(function(e){
      searchForm.empty().submit();
    });
</script>
```