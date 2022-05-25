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