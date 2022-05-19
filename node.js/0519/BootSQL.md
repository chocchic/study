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

### 3) application 실행  

### 4) 브라우저에서 localhost:포트번호를 입력하고 확인  