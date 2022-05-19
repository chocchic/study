# Thymeleaf
## 1. Spring Boot에서 화면 출력  
### 1) jsp(Java Servlet Pages) 사용  
* 설정을 추가해야만 사용 가능  
* 출력하는 파일의 확장자가 jsp로 고정  

### 2) Template Engine 이용 - 확장자 html로 서버의 데이터 출력 가능  
* Thymeleaf : Spring Boot에서 권장  
* Velocity 
* FreeMarker
* Mustache
* Groovy  

### 3) REST API를 구성하고 별도의 Front End Applicatioon을 만들어서 출력  
* web의 출력같은 경우는 javascript의 ajax나 react나 vue와 spa 라이브러리를 학습  
* 서버에 종속되지 않음, 서버의 프로그래밍 언어가 바뀌더라도 url과 data의 이름이 변경되지 않는다면 서버의 프로그래밍 언어를 변경하더라도 코드를 수정할 필요가 없음  
	BackEnd와 FrontEnd를 명확하게 분리하는 것이 가능  

## 2. Spring Boot Application을 생성  
* 의존성 : devtools, lombok, web, thymleaf  

## 3. Spring Boot Application에서 jsp 사용  
### 1) 의존성 추가 - mvnrepository.com에서 찾아서 작성  
* javax.servlet.jstl, org.apache.tomcat.embed.tomcat-embed-jasper  

* pom.xml이면 dependencies 태그 안에 추가  
```xml
    <!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.apache.tomcat.embed/tomcat-embed-jasper -->
	<dependency>
		<groupId>org.apache.tomcat.embed</groupId>
		<artifactId>tomcat-embed-jasper</artifactId>
		<version>10.0.21</version>
	</dependency>
```

* build.gradle이면 dependencies{...} 안에 추가  
```java
	// https://mvnrepository.com/artifact/org.apache.tomcat/tomcat-jasper
	implementation group: 'org.apache.tomcat', name: 'tomcat-jasper', version: '10.1.0-M15'
	// https://mvnrepository.com/artifact/javax.servlet/jstl
	implementation group: 'javax.servlet', name: 'jstl', version: '1.2'
```  

### 2) application.properties에 jsp 디렉터리 설정  
```ini
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp

spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.cache=false

spring.thymeleaf.view-names=thymeleaf/*
```  

### 3) 사용자의 요청을 view를 통해 출력하도록 해주는 Controller 클래스를 controller 패키지에 생성  
* PageController
```java
@Controller
public class PageController {
	@GetMapping("/")
	public String main(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("language", "Java");
		map.put("database","MySQL");
		map.put("framework","SpringBoot");
		map.put("IDE","sts");
		
		List<String> task = new ArrayList<>();
		task.add("Developer");
		task.add("Operator");
		task.add("DBA");
		task.add("DevOps");
		task.add("MLOps");
		
		model.addAttribute("map",map);
		model.addAttribute("list",task);
		return "main";
	}
}
```
### 4) STS에서는 jsp나 html 파일 생성 옵션이 없으므로 작성을 쉽게 하고자 하면 플러그인을 설치  
* (help) - (eclipse marketplace)를 실행한 후 java web으로 검색  
* Eclipse Enterprise Java and Web Developer를 설치  

### 5) src/main 디렉터리 안에 webapp 디렉터리를 만들고 그 안에 다시 WEB-INF 디렉터리를 만들고 그 안에 views 디렉터리를 생성한 후 main.jsp 파일을 만들어서 작성  
```html
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c로 시작하는 태그는 http://java.sun.com/jsp/jstl/core에서 해석한 내용으로 변경.
 여기서 uri는 웹 주소형태로 되어있지만 실제론 jstl.jar파일에서 해석 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Spring에서 JSP 출력하기</title>
</head>
<body>
	<div>
		<table>
			<tr>
				<th>언어</th>
				<th>데이터베이스</th>
				<th>프레임워크</th>
				<th>IDE</th>
			</tr>
			<tr>
			<td>${map.language}</td>
			<td>${map.database}</td>
			<td>${map.framework}</td>
			<td>${map.IDE}</td>
			</tr>
		</table>
	</div>
	<div>
		<table>
			<c:forEach items="${list}" var="task">
				<tr>
					<td>${task}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
```

### 6) 애플리케이션을 실행하고 브라우저에서 localhost:8080을 입력해서 확인  

## 4. Thymeleaf
* 템플릿 엔진(서버가 전달해준 데이터를 출력할 수 있도록 해주는 프레임워크) 중의 하나  

### 1) 장점  
* 확장자를 html로 사용할 수 있다는 것  
	jsp는 서버 사이드 렌더링을 거쳐서 html 형태로 출력되지만 템픞릿 엔진들은 이 과정이 필요없습니다.  

### 2) 도큐먼트  
	https://www.thymeleaf.org  

### 3) 작업을 수행할 때 th라는 헤더를 주로 사용  

## 5. Spring Dev Tools의 기능  
* Automatic Restart : 소스 코드를 수정하면 자동 재시작  
	개발 환경에서는 사용하지만 운영환경에서는 거의 사용하지 않음  

* Live Reload : 정적 자원ㅇ르 수정했을 때 새로고침 없이 적용  
	application.properties 파일에 spring.devtools.livereload.enabled=true 추가  
	크롬에서도 livereload 기능 사용이 가능한데 이 경우는 LiveReload라는 확장 프로그램을 크롬에ㅔ 설치해야합니다.  

* Property Defaults : 캐싱 기능을 중지시킬 수 있음  
	개발을 할 때 캐싱 기능으로 인해서 이전 내용이 보여지는 부분을 제거  

## 6. Thymeleaf 출력  
### 1) application.properties 설정 내용을 수정
* 기존 뷰 설정 제거  
```ini
server.port=80
#spring.mvc.view.prefix=/WEB-INF/views/
#spring.mvc.view.suffix=.jsp
#spring.thymeleaf.prefix=classpath:/templates/
#spring.thymeleaf.suffix=.html
#spring.thymeleaf.cache=false
#spring.thymeleaf.view-names=thymeleaf/*
spring.thymeleaf.cache=false
spring.devtools.livereload.enabled=true
```

### 2) PageController의 기본 요청 처리 메서드 화인
* url이 /이고, 리턴하는 문자열이 main인지 확인  

### 3) src/main/resources 디렉터리 안에 template 디렉터리에 main.html 파일을 생성  
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thymeleaf</title>
</head>
<body>
	<h1>Thymeleaf출력하기</h1>
</body>
</html>
```  

### 4) 애플리케이션을 시작하고(이미 시작된 경우는재시작하지 않아도 됩니다) 웹 브라우저에 localhost:자신이 설정중인 포트로 확인합니다.

## 7. Thymeleaf에서 데이터 출력  
* Scala Data : 하나의 값으로 만들어진 데이터  
* Vectro Data : Map이나 VO같은 형태나 List, 배열  
* 포맷 설정  
* 조건에 따른 분기  

### 1) 하나의 데이터나 Map이나 VO의 속성 출력  
```html
[[${데이터}]]
```

### 2) 반복문
```html
th:each = "임시변수:${목록}
```  
-> 이 경우는 자동으로 state 객체가 생성되는데 이 객체 안에 인덱스, 카운트, 홀수/짝수 등 지정 가능  

### 3) 분기문  
* th:if ~ unless 이용  
* th:switch와 th:case 이용  
* 삼항 연산자 사용 가능  

### 4) 데이터 출력 실습  
* 기본패키지.domain에 PersonVO 생성
* PageController의 요청 처리 메서드 수정  
```java
@GetMapping("/")
	public String main(Model model) {
		// Scala Data 생성
		String name = "chocchic";
		
		// 개인 취향 차. new로 해도 됨
		// 하나의 Instance 생성
		PersonVO person = PersonVO.builder().num(1L).name(name).nickname("촉촉한초코칩").birthTime(LocalDateTime.now()).build();
		// 여러개 데이터 생성
		// 1부터 20까지의 정수 스트림을 생성해서 LongStream으로 변환한 후
		// map(변환)ToObj(게겣로 변환 - 매개변수가 1개이고 1개의 객체를 리턴하는 람다를 매개변수로 사용)
		// 람다의 매개변수는 스트림의 데이터가 순서대로 들어오고 리턴하는 객체들을 모아서 list로 리턴
		List<PersonVO> list = IntStream.rangeClosed(1, 20).asLongStream().mapToObj(i -> {
			PersonVO temp = PersonVO.builder().num(i).name(name+"_"+i).nickname("촉촉한초코칩_"+i).birthTime(LocalDateTime.now()).build();
			return temp;
		}).collect(Collectors.toList());
		
		// 생성한 데이터를 request 객체에 저장
		model.addAttribute("name", name);
		model.addAttribute("person", person);
		model.addAttribute("list", list);
		
		// 여기서 리턴하는 문자열은 view의 이름입니다.
		return "main";
	}
```
* main.html을 수정  
```html
	<ul>
 		<li th:each="vo : ${list}">
			객체 : [[${vo}]] <br/>
			번호 : [[${vo.num}]] 
			이름 : [[${vo.name}]]
			별명 : [[${vo.nickname}]]
			태어난날 : [[${vo.birthTime}]]
		</li>
	</ul>
```
* main.html을 수정  
```html
	<!-- 인덱스와 데이터 함께 출력 -->
	<ul>
		<li th:each="vo,state : ${list}">
			[[${state.index}]] -- [[${vo.nickname}]]
		</li>
	</ul>
```
* main.html을 수정  
```html
	<!-- num이 3의 배수인 데이터만 출력 -->
	<ul>
		<li th:each ="vo, status : ${list}" th:if="${vo.num %3 ==0}">
			[[${vo.nickname}]]		
		</li>
	</ul>
```

* main.html을 수정  
```html
	<!-- num이 3의 배수인 경우는 name을 출력하고 그렇지 않은 경우네느 nickname을 출력 -->
	<ul>
		<li th:each ="vo, status : ${list}">
			<p th:if="${vo.num %3 ==0}" th:text="${vo.name}"/>
			<p th:unless="${vo.num%3==0}" th:text="${vo.nickname}"/>			
		</li>
	</ul>
```
### 5) 자바스크립트에서 데이터를 사용
```javascript
<script th:inline="javascript">
	let 변수명 = [[${데이터}]]
```

### 6) 링크 처리  
* href 속성에 @{}를 이용해서 설정  

### +) 포트 충돌이 계속 되는 경우
	포트를 사용중인 프로세스를 확인하기 위해 cmd에서 netstat -ano 명령어로 확인할 수 있고, 
	taskkill /pid 프로세스ID /F로 프로세스 강제 종료핧 수 있습니다.