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
