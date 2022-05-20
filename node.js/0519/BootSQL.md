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

### 5) MemoRepotest 클래스에 데이터 삽입을 위한 테스트 메서드
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