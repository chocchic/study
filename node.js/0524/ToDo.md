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

### +) 그냥 하다보면 프로그래머들이 알아야하는 단어들  
* Authentication(인증)  
* Authorization(인가)  
* Audit(감사 - 감시)  