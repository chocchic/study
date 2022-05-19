# Thymeleaf
## 1. Spring Boot에서 화면 출력  
### 1) jsp(Java Servlet Pages) 사용  
* 설정을 추가해야만 사용 가능  

### 2) Template Engine 이용  
* Thymeleaf : Spring Boot에서 권장  
* Velocity 
* FreeMarker
* Mustache
* Groovy  

### 3) REST API를 구성하고 별도의 Front End Applicatioon을 만들어서 출력  

## 2. Spring Boot Application을 생성  
* 의존성 : devtools, lombok, web, thymleaf  

## 3. Spring Boot Application에서 jsp 사용  
### 1) 의존성 추가  
* javax.servlet.jstl, org.apache.tomcat.embed.tomcat-embed-jasper  

* pom.xml이면 dependencies 태그 안에 추가  
```xml
    <!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.apache.tomcat/tomcat-jasper -->
    <dependency>
        <groupId>org.apache.tomcat</groupId>
        <artifactId>tomcat-jasper</artifactId>
        <version>10.1.0-M15</version>
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
