# 하나의 테이블을 가지고 CRUD 작업을 수행하는 Application  
## 1. 프로젝트 생성  
* build type: gradle  

* 의존성  
spring-devtoos  
spring-jpa  
mysql 또는 oracle  
thymeleaf  
spring-web
lombok

## 2. application.properties파일에 기본설정 추가  
```ini
server.port = 80

# 연결할 데이터베이스 설정 - MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/node?useUnicode=yes&characterEncoding=UTF-8&serverTimezone=UTC  
spring.datasource.username=root  
spring.datasource.password=1234

# 하이버네이트가 수행하는 sql을 콘솔에 출력  
spring.jpa.properties.hibernate.show_sql=true

# 포맷팅해서 보기 좋게 출력  
spring.jpa.properties.hibernate.format_sql=true

# 쿼리에 물음표로 출력하는 바인드 파라미터 출력  
logging.level.org.hibernate.type.descriptor.sql=trace

# ddl 옵션
spring.jpa.hibernate.ddl-auto=update

# 데이터베이스 플랫폼 설정  
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect  

spring.thymeleaf.cache=false
spring.devtools.livereload.enabled=true
```  

## 3. 부트스트랩의 사이드 바 적용  
### 1) 부트스트랩 사이드 바 다운로드  
https://startbootstrap.com/template/simple-sidebar  
* assets : 무겁고 자주 사용하지 않는 애들을 넣어둠  

* +) JavaScript Framework  
node와 express : web server를 만들 수 있도록 해주는 프레임워크  

jquery : Cross Browsing(한 번만 작성해서 모든 브라우저에서 동일한 컨텐츠를 사용할 수 있도록 하는 것)을 위한 프레임워크  

react, vue : SPA(Single Page Application) 구현을 위한 프레임워크  

bootstrap : 반응형 웹(디바이스 또는 화면의 크기에 상관없이 동일한 컨텐츠를 사용할 수 있도록 하는 것)을 쉽게 구현하기 위한 프레임워크  

d3js : 데이터 시각화를 위한 프레임워크  

electron : PC의 웹 브라우저에서 동작하는 애플리케이션 제작을 위한 프레임워크  

react-native, ionic : 스마트 디바이스용 애플리케이션 제작을 위한 프레임워크  

* +) Progressive Web : UI나 UX를 많이 사용하는 형태(스마트디바이스)로 와 유사하게 작업   

### 2) 다운로드 받은 파일을 압축 해제

### 3) 압축을 해제한 assets, css, js 디렉터리를 프로젝트의 resources/static 디렉터리에 복사  

### 4) templates 디렉터리에 공통된 레이아웃 파일을 위한 디렉터리를 생성 - layout

### 5) template/layout 디렉토리에 basic.html 파일을 추가하고 복사한 파일의 index.html파일의 내용을 전부 복사해서 붙여넣고 레이아웃 설정을 위해서 수정  
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<th:block th:fragment="setContent(content)">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Simple Sidebar - Start Bootstrap Template</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" th:href="@{assets/favicon.ico}" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link th:href="@{/css/styles.css}" rel="stylesheet" />
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script th:src="@{/js/scripts.js}"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>

<body>
<div class="d-flex" id="wrapper">
    <div class="border-end bg-white" id="sidebar-wrapper">
        <div class="sidebar-heading border-bottom bg-light">Start Bootstrap</div>
        <div class="list-group list-group-flush">
            <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Dashboard</a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Shortcuts</a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Overview</a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Events</a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Profile</a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Status</a>
        </div>
    </div>
        <!-- Page content wrapper-->
    <div id="page-content-wrapper">
        <!-- Top navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <div class="container-fluid">
                <button class="btn btn-primary" id="sidebarToggle">Toggle Menu</button>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                        <li class="nav-item active"><a class="nav-link" href="#!">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">Link</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
                            <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="#!">Action</a>
                                <a class="dropdown-item" href="#!">Another action</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#!">Something else here</a>

                                </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Page content-->
        <div class="container-fluid">
            <th:block th:replace = "${content}"></th:block>
        </div>
    </div>
</div>
</th:block>
</body>
</html>
```  

* +) Web Front End에 자신이 없는데 포트폴리오를 만들어야 하는 경우엔 bootstrap이나 jquery plugin으로 검색해서 사용하면 빠르게 디자인을 할 수 있습니다.  

## 4. 공통 디자인 확인  
### 1) HTML 출력을 처리할 Controller를 생성하고 시작 요청을 처리하는 메서드 추가  
```java
@Controller
public class MemoPageController {
	@GetMapping("/")
	public String main() {
		return "main";
	}
}
```  

### 2) templates 디렉터리에 main.html 파일을 추가하고 작성  
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<th:block th:replace="~{layout/basic::setContent(~{this::content})}">
	<th:block th:fragment="content">
		<h1>Memo Application</h1>
	</th:block>
</th:block>
```  

### 3) application 실행  

### 4) 브라우저에서 localhost:포트번호를 입력하고 확인  

### +) Template Method Pattern  
인터페이스나 추상 클래스의 구현할 메서드의 원형을 만들고 이를 implements해서 메서드의 내용을 만들어서 사용하는 방식  

인터페이스는 다른 개발자나 사용자와의 대화수단이 되고 클래스가 실제 내용을 구현할 본체가 되는 방식입니다.  

클래스를 상속받을 때(Sub Classing)는 상속을 하나만 하면 Ex를 추가해서 상속받은 클래스라는 것을 표현하고 인터페이스를 구현할 때는 일반적으로 뒤에 Impl을 추가합니다.  

## 5. 프로젝트 구조  
### 1) 기본 구조  
Thymeleaf Page <-> Controller(MemoPageController) <-> MemoServcie(Template Method Pattern 적용) <-> MemoRepository(영속성 작업)  

### 2) 화면 구성  
작업        URL             Method  수행할 기능             Redirect URL  
목록보기    /memo/list      GET     목록보기 - 페이징,검색  
삽입        /memo/register  GET     입력화면  
            /memo/register  POST    실제 삽입               /memo/list  
상세보기    /memo/read      GET     하나의 데이터 조회  
수정        /memo/modify    GET     수정/삭제 화면  
            /memo/modify    POST    실제 수정               /memo/read  
삭제        /memo/remove    POST    실제 삭제               /memo/list  

    삭제의 경우는 정말로 삭제를 할 것인지 아니면 삭제되었다고 표시만 했다가 출력할때 제외를 할 것인지에 대해서 고민  

### 3) DTO와 Entity  
* Entity : 데이터베이스와 연동하기 위한 클래스, 데이터베이스에 꼭 필요한 것들로만 구성되어야 합니다.  
    이 클래스는 Repository에서만 사용해야 합니다.  

* Entity는 사용자의 요청과 받느시 일치하지는 않는 문제가 발생하는데, 이런 경우에는 DTO 클래스를 별도로 만들어서 해결을 합니다.  
    Repository에서 넘겨준 데이터를 가지고 가공을 해서 Controller에게 전달하거나 전달받고 Controller는 DTO를 View에게 전송해서 출력하거나 사용을 합니다.  
    이렇게 Service에서 Controller로 데이터가 전달된다고 해서 이러한 클래스를 Data Transfer Object라고 합니다.  
    안드로이드같은 곳에서 이러한 DTO 패턴으로 만들어진 클래스만 화면 사이에 전달이 됩니다.  
    이 때 자바는 Serializable 인터페이스를 구현한 것을 DTO로 인식합니다.  

    프로그램에서 만든 데이터는 근본적으로 Main Memory에 존재하는 데이터입니다.  
    이 Main Memory의 데이터를 반영구적으로 저장하기 위해서 File에 기록을 하려면 Main Memory에서 File(Auxiliary Memory = 보조기억장치, HDD 또는 SSD)로 옮겨야 합니다.  
    이 때 이동이 가능한 인스턴스는 Serializable 인터페이스가 구현된 인스턴스만 File에 기록이 가능합니다.  
    이렇게 저장한 데이터는 반드시 Serializable 인터페이스가 구현된 인스턴스로 읽어야 합니다.  

* Entity의 생성시간과 수정시간  
    생성 시간과 수정 시간을 사용하고자 하는 경우 매번 현재 시간을 가져오는 것은 아주 많이 사용하는 공통된 로직입니다.  
    정적인 로직을 매번 수행하는 것을 자원의 낭비입니다.  
    JPA에서는 이를 Annotation으로 처리할 수 있도록 해주는데, Application의 Entry Point가 되는 클래스에 @EnableJpaAuditing이라는 어노테이션을 추가하고, Entity에 @CreatedData와 @LastModifiedDate를 추가한 속성을 만들면 됩니다.  

* Entity를 테이블로 생성하지 않도록 하고자 하는 경우에는 @MappedSuperclass를 추가  

## 6. 자동으로 처리되는 날짜 / 시간을 위한 작업  
### 1) Entry Point(애플리케이션이 시작되는 지점 - main메서드가 소유한 클래스)  가 되는 클래스에 annotation추가  
```java
// JPA 관련된 작업을 감시
@EnableJpaAuditing
@SpringBootApplication
public class BootIoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootIoApplication.class, args);
	}

}
```  

### +) Inheritance(상속 - SubClassing) : 하위 클래스가 상위 클래스의 모든 것을 물려받는 것  
    Super Class <----- Sub Class  
    상속을 구현하는 방법  
        여러 클래스들을 구현하다가 공통된 부분이 있으면 상위 클래스를 만들어서 상속을 하는 구조를 만듭니다.  상위 클래스 만들고 하위 클래스를 생성하는 것은 경험이 많은 경우 가능  
        자주 사용이 될만한 상위 클래스들을 미리 만들어서 제공하는 것을 Development Kit 또는 Framework라고 합니다.  

### 2) 빼먹음
```java
// 공통된 속성을 가진 Entity

// 테이블로 생성할 필요가 없음
@MappedSuperclass
// 데이터베이스 작업을 감시
@EntityListeners(value= {AuditingEntityListener.class})
@Getter
//abstract : 인스턴스를 생성할 수 없도록 해주는 클래스로 상속을 통해서만 사용이 가능
public class BaseEntity {
	// 생성한 시간을 저장하는데 컬럼 이름은 regdate이고 수정할 수 없도록 생성  
	@CreatedDate
	@Column(name="regdate", updatable=false)
	private LocalDateTime regDate;
	
	// 수정한 시간을 저장하는데 컬럼이름은 moddate이고 수정할 수 없도록 생성
	@LastModifiedDate
	@Column(name="moddate", updatable=false)
	private LocalDateTime modDate;
}
```  

### 3) 애플리케이션에서 사용할 Memo Entity 생성 - model.Memo
* 
```java
@Entity
@Table(name="memo")

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Memo extends BaseEntity{
	/*
	// 시스템이 생성해주는 랜덤한 문자열
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String id;
	*/
	// gno 값을 데이터베이스의 auto_increment나 sequence를 이용해서 생성
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long gno;
	
	@Column(length=100, nullable=false)
	private String title;
	
	@Column(length=1500, nullable=false)
	private String content;
	
	@Column(length=100, nullable = false)
	private String writer;
	
	// title을 변경해주는 메서드
	public void changeTitle(String title) {
		this.title = title;
	}
	
	// content를 변경해주는 메서드
	public void changetContent(String content) {
		this.content = content;
	}
}
```

### 4) 애플리케이션을 실행해서 테이블이 만들어지는지 확인  

## 7. Persistency 작업 (Querydsl 사용을 위해)  
### 1) Memo Entity를 사용하기위한 Repository 클래스를 생성 - persistency.MemoRepository  

### +) 
```java
    public List<String> method(){
        List<String> result = new ArrayList<>();

        // 처리내용 ...

        return result;
    }

    public String method(){
        String result = null;

        // 처리내용 ...

        return result;
    }
```
    -> return 타입에 맞춰 메서드를 여럭개 만들어야 함. => Optional 사용  

### 2) querydsl을 사용하기 위한 설정 - build.gradle에서 수행  
```gradle
plugins {
   id 'org.springframework.boot' version '2.5.5'
   id 'io.spring.dependency-management' version '1.0.11.RELEASE'
   id 'java'
   
   id 'com.ewerk.gradle.plugins.querydsl' version '1.0.10'
   
}

group = 'io.github.chocchic'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

configurations {
   compileOnly {
      extendsFrom annotationProcessor
   }
}

repositories {
   mavenCentral()
}

dependencies {
   implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
   implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
   implementation 'org.springframework.boot:spring-boot-starter-web'
   compileOnly 'org.projectlombok:lombok'
   developmentOnly 'org.springframework.boot:spring-boot-devtools'
   runtimeOnly 'com.oracle.database.jdbc:ojdbc8'
   runtimeOnly 'mysql:mysql-connector-java'
   annotationProcessor 'org.projectlombok:lombok'
   testImplementation 'org.springframework.boot:spring-boot-starter-test'
   implementation group: 'org.thymeleaf.extras', name: 'thymeleaf-extras-java8time'
   
   testImplementation 'org.springframework.boot:spring-boot-starter-test'
   
   // QueryDSL
    implementation 'com.querydsl:querydsl-jpa'
    implementation 'com.querydsl:querydsl-apt'
    implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.1'
}

// querydsl 추가 시작
def querydslDir = "$buildDir/generated/querydsl"

querydsl {
    jpa = true
    querydslSourcesDir = querydslDir
}
sourceSets {
    main.java.srcDir querydslDir
}

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
    querydsl.extendsFrom compileClasspath
}

compileQuerydsl {
    options.annotationProcessorPath = configurations.querydsl
}


tasks.named('test') {
   useJUnitPlatform()
}

```
* boot 2.6.x 버전 이상 하려면 구글링해서 따라하기  
### 3) MemoRepository에 QuerydslPredicateExecutor 인터페이스를 extends  
```java
public interface MemoRepository extends JpaRepository<Memo, Long>, QuerydslPredicateExecutor<Memo>{

}
```  

### 4) src/test/java의 기본 패키지에 테스트용 클래스를 생성 - MemoRepotest  
```java
@SpringBootTest
public class MemoRepotest {

	@Autowired
	private MemoRepository m;
	
}
```  

### 5) MemoRepotest 클래스에 데이터 삽입을 하는 테스트 메서드를 작성하고 확인  
```java
@Test
public void insertMemo() {
    // 300개의 정수 모임을 생성하고 순회
    IntStream.rangeClosed(1, 300).forEach(i ->{
        // 데이터 생성
        Memo memo = Memo.builder().title("title_"+i).content("content_"+i).writer("writer_"+i).build();
        // 데이터 삽입
        m.save(memo);
    });
    /*
    select * from memo;
    DB에서 위 구문으로 확인
        */
    
}
```
* 데이터가 한번에 300개씩 들어가기 때문에 DB에서 삽입된게 확인된다면 반드시 @Test를 주석처리하고 다른 코드 수행  

* JPARepository를 extends(상속)하면 전체 데이터 가져오기(페이징, 정렬), 기본키를 이용해서 조회하기, 삽입, 삭제(기본키를 조건으로 해서 수정하는 경우), 수정(기본키를 조건으로 해서 수정하는 경우)은 구현할 필요가 없습니다.  

* 기본키가 아닌 속성으로 조회하는데 일치하는 조건인 경우는 Repository에 메서드 이름을 findBy컬럼 이름 또는 컬럼이름연산자 형태로 만들고 필요한 매개변수를 갖는 메서드를 선언하면 됩니다.  

### 6) 

## 8. DTO 작업  
* Entity는 데이터베이스 관련 작업을 위한 클래스입니다.  
    Entity는 우리가 관리하는 클래스가 아니고 Spring JPA의 Entity Manager가 관리하는 객체입니다.  
    수명주기(인스턴스가 만들어지는 시점과 소멸되는 시점)를 우리가 알 수 없습니다.  

* 화면에 출력하고 클라이언트에서 넘겨준 파라미터를 사용하는 경우 이 데이터는 일시적으로 필요한 데이터입니다. 이러한 데이터를 Entity 클래스를 이용해서 관리하면 자원의 낭비가 발생할 수 있습니다. Service와 Controller와 View에서 사용하는 별도의 데이터 클래스가 필요  

* 별도의 데이터 클래스(DTO)를 만들면 유사한 코드를 중복으로 개발하게 되고 Entity와 DTO 사이의 변환을 위한 메서드가 필요하게 됩니다.  

### 1) dto 패키지에 memoDTO 클래스를 생성하고 작성
```java
// Controller와 Service 사이의 데이터 전달을 위한 클래스
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoDTO {	
	private Long gno;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
}
```  

### 2) 사용자의 요청을 처리하는 메서드이 원형을 갖는 Service 인터페이스 생성 - service.MemoService  
```java
// Controller와 Service 사이의 데이터 전달을 위한 클래스
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoDTO {	
	private Long gno;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
}
```

### 3) Service 인터페이스의 메서드를 구현한 ServiceImpl 클래스를 생성 - service.MemoServiceImpl  
```java
// Controller와 Service 사이의 데이터 전달을 위한 클래스
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoDTO {	
	private Long gno;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
}
```  

## 9. 데이터 삽입  
### 1) MemoService 인터페이스에 데이터 삽입을 위한 메서드 선언  
```java
// 데이터 삽입을 위한 메서드 : 삽입된 메모의 gno값을 리턴
	public Long insertMemo(MemoDTO dto);
```  

### 2) MemoServiceImpl 클래스에 데이터 삽입을 위한 메서드를 구현  
```java
	@Override
	public Long insertMemo(MemoDTO dto) {
		log.info("==데이터 삽입==");
		log.info(dto);
		
		// DTO를 Entity로 변환
		Memo memo = dtoToEntity(dto);
		
		// 데이터 저장하고 저장한 내용을 memo에 닫시 기록
		m.save(memo);
		
		return memo.getGno();
	}
```  

### 3) 데이터 삽입 테스트  
* Service를 테스트하기 위한 테스트 클래스를 만들고 테스트 - src/test/MemoServiceTest
```java
@SpringBootTest
public class MemoServiceTest {
	@Autowired
	private MemoService m;
	
	@Test
	public void testInsert() {
		MemoDTO dto = MemoDTO.builder().title("데이터삽입테스트1").content("insert success").writer("Service").build();
		System.out.println(m.insertMemo(dto));
		// 데이터베이스에 가서 select * from memo order by gno desc;로 확인
	}
}
```  

* +) 
    Controller <- Service <- Repository  
    IoC(Inversion of Control) : 제어의 역전(역흐름) - 기본적으로 프레임워크가 클래스를 만들서 제공하고 개발자가 그 클래스의 인스턴스를 만들어서 사용하는데 클래스를 개발자가 만들고 그 클래스의 인스턴스를 프레임워크가 만들어서 수명주기를 관리하는 것  
    DI(Dependency Injection) : 의존성 주입 - 내부에서 사용할 요소를 외부에서 주입받는 것, 밖에서 만들어서 그냥 받음  
    AoP(Aspect of Programming) : 관점 지향 프로그래밍 - Business Logic과 Common Concern을 분리해서 Common Conern을 별도로 작성하고 컴파일이나 빌드를 할 때 주입시키는 형태로 프로그래밍  

## 10. 목록 보기 구현  
* 고려 사항
    - 페이징 처리 여부  
    - 페이징 방식 처리  
        페이지 번호 이용(개수가 고정이거나 select를 이용해서 설정)  
        스크롤 이용(화면의 크기를 알아야하고, 화면의 크기를 가지고 처음에 가졍로 데이터 개수를 설정할 수 있어야 합니다.)  

* 페이징 처리를 하게 되면 요청을 할 때 필요한 데이터가 존재  
    페이지 번호와 데이터 개수가 필요  

* 검색을 구현하고자 하는 경우에 필요한 데이터
    검색 항목과 키워드

* 목록 보기를 하기위해서는 별도의 DTO클래스가 필요  

### 1) 요청 관련 DTO : 페이징 적용 - PageRequestDTO  
```java
@Builder
@Data
@AllArgsConstructor
public class PageRequestDTO {
	// 페이지 번호
	private int page;
	// 한 페이지에 출력될 데이터 개수
	private int size;
	
	// 생성자 - 인스턴스 변수를 초기화
	public PageRequestDTO() {
		this.page = 1;
		this.size = 10;
	}
	
	// Pageable(Spring Boot JPA에서 Page 단위 검색을 위한 클래스) 객체를 생성해주는 메서드
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page-1, size, sort);
	}
}
```  

### 2) 응답관련 DTO : Page 객체를 받아서 List로 변환해서 저장  
```java
// 재활용을 위해서 템플릿을 적용
// DTO는 변환할 DTO클래스가 대입되고, EN은 Entity와 DTO사이의 변환을 수행해 줄 함수
// Java에서는 Generic이라고 하고 객체 지향에서는 템플릿 프로그래밍이라고 합니다.
// 알고리즘을 구현하거나 프레임워크를 만들 때 중요
@Data
public class PageResponseDTO <DTO, EN> {
	// DTO의 List
	private List<DTO> dtoList;
	
	// Page 객체와 함수를 적용해서 List로 변환해주는 메서드
	public PageResponseDTO(Page<EN> result, Function<EN, DTO> fn) {
		dtoList = result.stream().map(fn).collect(Collectors.toList());
	}
}
```  

### 3) Service 인터페이스에 목록보기를 위한 메서드를 선언  
리턴타입 getList(매개변수) -> 리턴타입 getList(PageRequsetDTO) -> PageResponseDTO<MemoDTO, Memo>getList(PageRequsetDTO);  
```java
// 목록보기를 위한 메서드
PageResponseDTO<MemoDTO, Memo>getList(PageRequestDTO requestDTO);
```  

### 4) ServiceImpl 클래스에 목록보기 메서드를 구현  
```java
@Override
	public PageResponseDTO<MemoDTO, Memo> getList(PageRequestDTO requestDTO) {
		// 정렬조건을 적용해서 페이징 객체를 생성
		Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
		Page<Memo> result = m.findAll(pageable);
		
		// Entity를 DTO로 변환해주는 함수 설정
		Function<Memo, MemoDTO> fn = (entity -> entityToDTO(entity));
		// 결과 리턴
		return new PageResponseDTO<MemoDTO, Memo>(result, fn);
	}
```  

### 5) Test클래스에서 메서드 테스트  
```java
    // 목록 보기 테스트
	@Test
	public void testList() {
		PageRequestDTO pr = PageRequestDTO.builder().page(1).size(10).build();
		PageResponseDTO<MemoDTO, Memo> resultDto = m.getList(pr);
		for(MemoDTO mm : resultDto.getDtoList()) {
			System.out.println(mm);
		}
	}
```  

### 6) 페이징 처리시 필요한 요소  
* 페이지 번호 목록을 화면에 출력하기 위해서는 시작하는 페이지 번호, 끝나는 페이지 번호 , 이전 페이지 여부, 다음 페이지 여부, 현재 페이지 번호가 필요  

* 끝나는 페이지 번호 = (int)(Math.ceil(현재 페이지 번호 / (double)페이지 번호 출력 개수)) * 페이지 번호 출력 개수;  

* 현재 페이지 번호가 5이고 페이지 번호 출력 개수가 10이라면?  
    (int)(Math.ceil(5/(double)10)) * 10 = 1 * 10 = 10  

* 시작하는 페이지 번호 = 끝나는 페이지 번호 - (페이지 번호 출력 개수 - 1);  

* Page ~ 빼먹음 - PageRespnseDTO에 추가
```java
// Page 객체와 함수를 적용해서 List로 변환해주는 메서드
	public PageResponseDTO(Page<EN> result, Function<EN, DTO> fn) {
		// 출력할 데이터 목록 생성
		dtoList = result.stream().map(fn).collect(Collectors.toList());
		// 페이지 번호 목록 생성
		totalPage = result.getTotalPages();
		makePageList(result.getPageable());
	}
	
	// 전체 페이지 개수
	private int totalPage;
	
	// 현재 페이지 번호
	private int page;
	
	// 출력할 페이지 번호 개수
	private int size;
	
	// 이전 페이지 목록 여부
	private boolean prev;
	// 다음 페이지 목록 여부
	private boolean next;
	
	// 시작하는 페이지 번호
	private int start;
	// 끝나는 페이지 번호
	private int end;
	
	// 출력할 페이지 번호 등록
	private List<Integer> pageList;
	
	// 출력핧 페이지 번호를 계산하는 메서드
	private void makePageList(Pageable pageable) {
		this.page = pageable.getPageNumber() + 1;
		this.size = pageable.getPageSize();
		
		int tempEnd = (int)(Math.ceil(page/ (double)size)) * 10;
		start = tempEnd - 9;
		prev = start > 1;
		end = totalPage > tempEnd ? tempEnd : totalPage;
		next = totalPage > tempEnd;
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}
```  

### 7) 테스트 클래스에서 메서드를 생성해서 테스트

### 8) 빼먹음
```java
@Controller
// 로그 기록을 편리하게 할 수 있도록 해주는 어노테이션
@Log4j2
// 인스턴스 변수의 주입을 생성자에서 자동으로 처리하도록 해주는 어노테이션
@RequiredArgsConstructor
public class MemoPageController {
	private final MemoService m;
	
	// 요청이 오면 templates 디렉터리에 있는 main.html을 출력
	@GetMapping("/")
	public String main() {
		// redirect할 때는 View의 이름을 적는 것이 아니고 요청을 적어야합니다.
		return "redirect:/memo/list";
	}
	
	// 목록보기 요청을 처리
	@GetMapping("/memo/list")
	public void list(PageRequestDTO pr, Model model) {
		log.info("목록보기");
		model.addAttribute("result", m.getList(pr));
	}
}
```

### 9) templates 디렉터리 안에 
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<th:block th:replace="~{layout/basic::setContent(~{this::content})}">
	<th:block th:fragment="content">
		<h1>Memo Application</h1>
		<table class ="table table-striped">
			<thread>
				<tr>
					<th scope="col">#</th>
					<th scope="col">title</th>
					<th scope="col">writer</th>
					<th scope="col">Date</th>
				</tr>
			</thread>
			<tbody>
				<tr th:each="dto:${result.dtoList}">
					<th scope="row">[[${dto.gno}]]</th>
					<td>[[${dto.title}]]</td>
					<td>[[${dto.writer}]]</td>
					<td>[[${#temporals.format(dto.regDate, 'yyyy/MM/dd hh:mm:ss')}]]</td>
				</tr>
			</tbody>
		</table>
		<!-- 부트스트랩은 모양을 만들 때 class 이름을 가지고 모양을 생성하는데 pagination은 페이지 번호를 만들기 위한 클래스-->
		<ul class ="pagination h-100 justify-content">
            <!-- 이전을 만들기 위한 부분 result.prev가 true라면 prev를 출력-->
			<li class="page-item" th:if="${result.prev}">
                <!--thymeleaf에서는 link를 설정할 때 @를 포함해서 만들고 여기에 파라미터를 만들 때는 (파라미터 이름 = 값)으로 만듭니다. @{/memo/list(page=${result.start-1})는 a href='/memo/list?page=값의 형태가 됩니다.-->
				<a class="page-link" th:href="@{/memo/list(page=${result.start-1})}" tableindex="-1">prev</a>
			</li>
            <!--페이지 번호 리스트를 순회하면서 출력을 하는데 현재 페이지 번호와 출력하는 페이지 번호가 같으면 active클래스를 추가해서 활성화 시켜주고 링크는 해제합니다.-->
			<li th:class="'page-item'+${result.page==page?'active':''}" th:each="page:${result.pageList}">
				<a class="page-link" th:href="@{/memo/list(page=${page})}">[[${page}]]</a>
			</li>
			<li class="page-item" th:if="${result.next}">
				<a class="page-link" th:href="@{/memo/list(page=${result.end+1})}">next</a>
			</li>
		</ul>
	</th:block>
```  
* th:class 사용시 주의할 점  
    class의 속성값을 string처럼 주므로 base 클래스 뒤에 공백 하나가 있어야함.  
    th:class 대신 th:classappend사용 가능  
        class="base" th:classappend="${condition ? 'condition-true' : 'condition-false'}"  

* 페이지 번호 목록을 출력  
    현재 페이지가 1이라면 1~10, 현재 페이지가 5라면 1~10, 현재 페이지가 11이라면 11~20
    => 필요한 데이터 : 현재 페이지 번호, 전체 페이지 개수, 페이지 번호 출력 개수  
    시작하는 번호 : 끝나는 번호 - (페이지 번호 출력 개수 -1)  
    끝나는 번호 : 시작하는 번호 + (페이지 번호 출력 개수 -1)  
    끝나는 번호는 전체 페이지 개수보다 클 수 없음  

## 11. 데이터 삽입 처리  
* 데이터 삽입 과정 : 데이터 삽입 요청을 하면 데이터를 입력할 수 있는 화면으로 이동하고 데이터 입력이 끝난 후 데이터 삽입 요청을 하면 데이터 삽입을 수행  
* 단순한 화면 이동이나 데이터 출력은 GET방식으로 이루어지고, 나머지 작업은 대부분 POST(PUT, DELETE)방식으로 처리합니다.  
* 삽입, 삭제, 수정 작업이 완료되면 웹의 경우 페이지 이동은 redirect를 이용합니다.  
    forward : URL을 변경하지 않고 View파일을 출력, 새로고침을 하게되면 이전 요청이 다시 발생합니다.  
    redirect : URL을 변경, 새로고침을 하면 현재 요청이 다시 발생합니다.  

### 1) 