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
