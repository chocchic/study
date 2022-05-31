# ManyToMany - 다 대 다 관계  
* 회원 <-> 게시글 <-> 댓글

한 명의 회원은 여러 개의 게시글을 작성하는 것이 가능  
한 명의 회원은 여러 개의 게시글을 작성하는 것이 가능  
한 개의 게시글에는 여러 개의 댓글이 작성될 수 있음  
**역은 성립하지 않음**  

* 영화 리뷰 작성  
    한 명의 회원은 여러 영화에 리뷰를 남길 수 있고, 하나의 영화에 여러 회원이 리뷰를 남길 수 있음  
    
    이런 경우 회원과 영화 사이의 관계는 M:N 관계(Many To Many)  

    관계형 데이터베이스에서는 이런 관계가 존재할 수 없습니다.  
    ORM에서는 ManyToMany관계를 만들어 낼 수는 있으나 **권장하는 방법은 아닙니다.**  

    이런 경우는 양쪽 테이블의 기본키를 외래키로 갖는 별도의 테이블을 생성해서 해결  

## 1. 영화 리뷰 프로젝트
* Entity 구조  
Movie, MovieImage(영화에 대한 이미지를 가지는 테이블인데, 하나의 영화에 0개 이상의 이미지를 가짐)  

Member

Review(Movie와 Member의 기본키를 외래키로 가짐)

한꺼번에 (하나의 테이블) 저장되어야 할 내용이지만 기본키를 설정하고 난 후 하나의 기본키에 여러개의 데이터가 매칭되는 경우라면 이 정보는 별도의 테이블로 분리시켜야 하는데, 이 때 분리된 테이블은 자신만의 기본키를 가져야 하고 분리되기 전 테이블의 기본키를 외래키로 가져야 합니다.  

영화 정보
  영화 일련 번호 - 기본키  
  영화 제목 - 일련번호 1개당 1개  
  주연 배우 - 0개 이상  
  영화 이미지 - 0개 이상  
  영화 줄거리 - 일련번호 1개당 1개  

테이블 1  
영화 일련번호  
영화 제목  
영화 줄거리  

테이블 2  
배우 번호 - 기본키  
영화 일련번호 - 영화 테이블에 대한 외래키  
주연 배우  

테이블 3  
영화 이미지 번호 - 기본키  
영화 일련번호 - 영화 테이블에 대한 외래키  
영화 이미지 - 파일을 저장할 때는 방식이 2가지. 내용을 저장하고자 하면 BLOB. 경로를 저장하고자 하면 경로. 근데 경로는 유일무이해야 함  

## 1. 프로젝트 생성
### 1) 프로젝트 생성 - movie  

### 2) application.properties에 설정을 추가  
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
spring.thymeleaf.cache=false
```  

### 3) EntryPoint 클래스에 데이터베이스의 변경사항을 감시하는 어노테이션 추가  
```java
@EnableJpaAuditing
@SpringBootApplication
public class MovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

}
```  

### 4) 기본패키지.model 패키지에 생성시간과 수정시간을 소유한 기본 Entity클래스 생성 - BaseEntity
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

## 2. Entity 설계  
### 1) 영화 정보 - Movie  
* 영화 번호와 제목  
```java
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Movie extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mno;
	
	private String title;
}
```

### 2) 영화 이미지 - MovieImage  
* 이미지 번호, uuid, 파일 이름, 파일 저장 경로(날짜), Movie로의 외래키  
```java
@Entity
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude="movie")
public class MovieImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inum;
	private String uuid;
	private String imgName;
	private String path;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Movie movie;
}
```  

### 3) 회원 - Member  
* 회원 구분 번호, email, pw, nickname  
```java
@Entity
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Table(name = "m_member") // member관련 테이블이 많으므로 이름 지정
public class Member extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mid;
	private String email;
	private String nickname;
	private String pw;
}
```  

### 4) 리뷰 - Review  
* 리뷰 번호, 영화에 대한 외래키, 회원에 대한 외래키, 평점, 내용  
```java
@Entity
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = {"movie", "member"})
public class Review extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String rnum;
	private float score;
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Movie movie;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
}
```  

## 3. Repository 작업  
### 1) Movie 엔티티 작업을 위한 Repository 인터페이스를 생성 - MovieRepository  

### 2) MovieImage 엔티티 작업을 위한 Repository 인터페이스를 생성 - MovieImageRepository  

### 3) Test 클래스를 만들어서 샘플 데이터를 삽입  
```java
	@Test
	@Transactional // 한번에 여러개의 데이터를 삽입하므로 모두 성공하거나 실패하도록 하기 위해 추가
	@Commit
	public void insertMovie() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Movie movie = Movie.builder().title("Movie..."+i).build();
			m.save(movie);
			
			int count = (int)(Math.random() * 5) + 1;
			for(int j = 0; j < count; j++) {
				MovieImage movieImg = MovieImage.builder().uuid(UUID.randomUUID().toString()).movie(movie).imgName("test"+j+".jpg").build();
				mm.save(movieImg);
			}
		});
	}
```  

### 4) Member 엔티티를 위한 Repository 인터페이스 생성 - MemberRepository  

### 5) Test 클래스에 Member Entity에 데이터를 삽입하는 테스트  
```java
	@Autowired
	private MemberRepository mem;
	
	@Test
	@Transactional
	@Commit
	public void insertMember() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Member member = Member.builder().email("test"+i+"@aaa.com").pw("1234").nickname("tt"+i).build();
			mem.save(member);
		});
		
	}
```  

### 6) Review엔티티를 위한 Repository인터페이스 생성 - ReviewRepository  

### 7) Test 클래스에 Review Entity에 데이터를 삽입하는 테스트  
```java
	@Autowired
	private ReviewRepository r;
	
	@Test
	@Transactional
	@Commit
	public void insert() {
		IntStream.range(1, 200).forEach(i -> {
			// 존재하는 영화 번호
			Long mno = (long)(Math.random() * 100) + 1;
			// 회원 번호
			Long mid = (long)(Math.random() * 100) + 1;
			
			Movie movie = Movie.builder().mno(mno).build();
			Member member = Member.builder().mid(mid).build();
			
			Review movieReview = Review.builder().movie(movie).member(member).score((int)(Math.random() * 5) + 1).text("영화리뷰..."+i).build();
			
			r.save(movieReview);
					
		});
	}
```  

### 8) 영화 목록을 출력하기 위한 메서드를 MovieRepository인터페이스에 추가  

### 9) 영화 목록을 가져오는 메서드
```java
	@Test
	public void testListPage() {
		PageRequest pageRequest = PageRequest.of(0,10,Sort.by(Sort.Direction.DESC,"mno"));
		Page<Object []> result = m.getListPage(pageRequest);
		for(Object[] obj : result.getContent()) {
			System.out.println(Arrays.toString(obj));
		}
	}
```  

### 10) 영화 1개의 모든 이미지와 평균 평점 및 리뷰 개수 가져오기  
```java
	@Query("select m, max(mi), avg(coalesce(r.grade,0)), count(distinct r) from Movie m "
			+"left outer join MovieImage mi on mi.movie = m " 
			+"left outer join Review r on r.movie = m where m.mno = :mno group by mi")
	Page<Object []> getMovieAll(@Param("mno") Long mno);
```  

### 11) 영화 1개의 모든 이미지와 평균 평점 및 리뷰개수 가져오는 메서드 테스트  
```java
	@Test
	public void testGetMovieWithAll() {
		List<Object[]> result = m.getMovieAll(92L);
		for(Object[] objs : result) {
			System.out.println(Arrays.toString(objs));
		}
	}
```  

### 12) 특정 영화에 대한 모든 리뷰를 가져오는 메서드를 ReviewRepository 인터페이스에 생성  
```java
	// 영화 정보를 가지고 리뷰 목록을 가져오는 메서드
	// 영화 정보를 자세히 출력할 때 필요
	List<Review> findByMovie(Movie movie);
```  

### 13) 리뷰 목록을 가져오는 메서드 테스트  
```java
	@Test
	public void testGetMovieReviews() {
		Movie movie = Movie.builder().mno(9L).build();
		List<Review> list = r.findByMovie(movie);
		
		for(Review review : list){
			System.out.println(review);
		}
	}
```  

### 14) 특정 영화에 대한 모든 리뷰를 가져오는 메서드를 수정
```java
	// EntityGraph로 속성에 해당하는 데이터는 EAGER로 처리해서 바로 가져옵니다.
	@EntityGraph(attributePaths = {"member"}, type=EntityGraphType.FETCH)
	List<Review> findByMovie(Movie movie);
```  

### 15) 테스트
```java
	@Test
	public void testGetMovieReviews() {
		Movie movie = Movie.builder().mno(9L).build();
		List<Review> list = r.findByMovie(movie);
		for(Review review : list){
			System.out.println(review.getMember().getEmail());
		}
	}
```  

### 16) 회원이 삭제될 때 회원이 작성한 댓글도 삭제하기 위해서 ReviewRepository인터페이스에 회원정보를 가지고 댓글을 삭제하는 메서드를 선언  
```java
	// 회원 정보를 가지고 삭제하는 메서드
	void deleteByMember(Member member);
```  

### 17) 회원 정보를 이용해서 삭제하는 메서드
```java
	// 2개의 삭제 구문을 사용하므로 @Transactional와 @Commit을 이용해서 동시에 수행되거나 아니면 하나도 되지 않도록 처리를 해주어야 합니다.
	@Test
	@Transactional
	@Commit
	public void testDeleteMember() {
		Long mid = 100L;
		Member member = Member.builder().mid(mid).build();
		r.deleteByMember(member);
		mem.deleteById(mid);
	}
```  

### 18) 회원 정보를 가지고 삭제하는 메서드를 수정  
* 리뷰가 많으면 많을 수록 그 수만큼 리뷰를 지우는 쿼리를 사용 - 아래 코드는 delete구문이 한 번만 수행됩니다.  
```java
	@Modifying
	@Query("delete from Review mr where mr.member = :member")
	void deleteByMember(@Param("member")Member member);
```  

## 4. 파일 업로드 처리  
### 1) 파일 업로드 처리 방법  
* Servlet 3.0에서부터 제공하는 자체적인 파일 업로드 라이브러리를 이용  
* 별도의 외부 라이브러리 이용(commons-fileupload 등)  
* WAS(Web Application Server - Tomcat이 대표적인 WAS)가 아닌 환경에서 Spring boot를 실행하거나 실행하는 WAS의 버전이 낮을 때는 별도의 라이브러리를 이용해서 파일 업로드를 처리  

### 2) 이미지 파일 미리보기  
* 자바스크립트를 이용해서 업로드하기전에 미리보기  
* 서버에 업로드한 후 미리보기  

### 3) spring boot에서의 업로드 처리  
* application.properties에 설정만 하면 됨  
```ini
# 파일 업로드설정
spring.servlet.multipart.enabled=true
spring.servlet.multipart.location=파일 업로드할 절대 경로
spring.servlet.multipart.max-request-size=30MB
spring.servlet.multipart.max-file-size=10MB
```  

### 4) View를 출력하기 위한 Controller를 생성하고 요청을 처리하는 메서드를 작성 - UploadTestController  
```java
@Controller
public class UploadTestController {
	@GetMapping("/uploadex")
	public void uploadex() {
		
	}
}
```  

### 5) REST API를 위한 Controller를 생성하고 파일 업로드 요청을 처리하는 메서드를 작성 - UploadController  
```java
@RestController
@Log4j2
public class UploadController {
	@PostMapping(value="/uploadajax")
	public void uploadFile(MultipartFile[] uploadFiles) {
        for (MultipartFile uploadFile : uploadFiles) {
            //실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            log.info("fileName: " + fileName);
		}
	}
}
```  

### 6) templates디렉터리에 uploadex.html파일을 추가하고 작성  
```java
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>
	<input name ="uploadFiles" id="uploadFiles" type="file" accept="image/*" multiple/>
	<button class ="uploadBtn">업로드</button>
	<img id="img" width="200" height="200" border="1"/>
</body>
<!-- integerity는 소스코드가 조작되었는지 확인하기 위한 해시값이고, crossorigin은 동일한 도메인이 아닐 때 코드를 공유할 수 있도록 해주는 속성 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>
<script>
	document.getElementById("uploadFiles").addEventListener("change", (e)=>{
		// 이벤트를 처리할 때 this는 이벤트가 발생한 객체
		// 여기서는 document.getElementByID("uploadFiles")입니다.
		readURL(e.target);
	});
	function readURL(input){
		if(input.files && input.files[0]){
			console.log("이미지 인식!!");
			var filename = input.files[0].name;
			var reader = new FileReader();
			
			reader.addEventListener("load", function(e){
				document.getElementById("img").src = e.target.result;
			});
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	$('.uploadBtn').click(function(){
		// 파일 전송을 위한 FormData 생성
        var formData = new FormData();
        var inputFile = $("input[type='file']");
        var files = inputFile[0].files;
        if(files.length < 1){
            alert("업로드할 파일을 선택하지 않으셨습니다.");
            return;
        }
        for (var i = 0; i < files.length; i++) {
            console.log(files[i]);
            formData.append("uploadFiles", files[i]);
        }
        $.ajax({
        	url:"/uploadajax",
        	processData:false,
        	contentType:false,
        	data:formData,
        	type:"POST",
        	dataType:"json",
        	sucess:function(result){
        		console.log(result);
        	},
        	error:function(jqXHR, textstatus, errorThrown){
        		console.log(textstatus);
        	}
        })
	})
</script>
</html>
```

### 7) 실행하고 파일을 업로드했을 때 콘솔에 파일이름이 출력되는지 확인  

### 8) application.properties파일에 업로드를 위한 디렉터리를 변수로 추가  
```ini
com.chocchic.upload.path=C:\\Users\\SAMSUNG\\Desktop\\java\\chocchic.github.io\\node.js\\movie\\data
```  

### 9) UploadController 클래스에 위의 변수를 읽는 부분을 생성  
```java
	// application.properties파일에 있는 com.chocchic.upload.path라는 속성에 설정된 값 가져오기
	@Value("${com.chocchic.upload.path}")
	private String uploadPath;
```
-> 외부로 노출되면 안되는 문자열이나 개발환경과 운영환경에서 다르게 사용될 가능성이 있는 문자열은 설정파일이나 데이터베이스에 기록해두고 불러들이는 것이 좋습니다.  

* 소스코드 -> 컴파일 -> 빌드 -> 실행
	소스코드를 변경하면 이 모든 과정을 다시 수행해야 함. 소스코드가 아닌 파일의 문자열이나 데이터베이스가 변경된 경우에는 다시 실행하거나 파일의 내용을 다시 읽기만 하면 됩니다.  

### 10) UploadController 클래스에 디렉터리를 생성해주는 사용자 정의 메서드를 추가
```java
	private String makeFolder() {
		// 오늘 날짜를 문자열로 가져옴
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// /문자열을 파일의 경로 구분자로 변경
		String realUploadPath = str.replace("//",File.separator);
		// uploadPath와 realUploadPath를 합쳐서 파일 객체를 생성
		File uploadPathDir = new File(uploadPath, realUploadPath);
		// 이 파일이 존재하지 않는다면 디렉터리를 생성
		if(uploadPathDir.exists() == false) {
			uploadPathDir.mkdirs();
		}
		// 디렉터리 이름을 리턴
		return realUploadPath;
	}
```  

### 11) UploadController클래스의 파일 업로드 처리 메서드를 수정
```java

```  

### 12) 브라우저에서 파일을 업로드하고 컴퓨터에 오늘 날짜로 디렉터리가 생성되고 그안에 파일이 업로드 되었는지 확인
* 파일이름은 원본이름앞에 uuid가 추가되어있어야합니다.  

### 13) 업로드 결과를 반환하기 위한 UploadResultDTO클래스를 생성  
* 파일이름, uuid, 업로드 경로를 소유  
```java
@Data
@AllArgsConstructor
public class UploadResultDTO {
	private String fileName;
	private String uuid;
	private String uploadPath;
	
	// 이미지 경로를 리턴해주는메서드
	public String getImageURL() {
		try {
			// 파일에 한글이 있는 경우를 대비해서 UTF-8로 인코딩
			return URLEncoder.encode(uploadPath+"/"+uuid+fileName, "UTF-8");
		}catch(Exception e){
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return "";
	}
}
```  

* 자바 예외 처리는 자신이 만들지 않은 자원을 사용할 때 강제합니다.  

### 14) 뺴먹음
```java
```

### 15) 브라우저에서 파일을 업로드하고 확인 - 콘솔에서 에러가 없어집니다.  

### 16) 업로드한 이미지를 화면에서 출력하기 위해서 UploadController클래스에 이미지를 다운로드 요청을 처리하는 메서드를 생성  
```java
	// 파일의 내용을 전송하는 요청을 처리해주는 메서드
	@GetMapping("/display")
	public ResponseEntity<byte []> getFile(String filename){
		ResponseEntity<byte []> result = null;
		try {
			// 파일의 이름을 가지고 파일 경로를 생성
			File file = new File(uploadPath + File.separator + URLDecoder.decode(filename, "UTF-8"));
			// Header는 데이터의 종류가 어떤 것인지 알려주기 위한 용도
			HttpHeaders header = new HttpHeaders();
			// Content-Type에 이 파일의 종류가 무엇인지 설정을 해줍니다.
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		}catch(Exception e){
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return result;
	}
```

### 17) uploadex.html 파일에 이미지 출력영역을 생성
```html
	<div class = "uploadResult"></div>
```  

### 18) upload.html파일에 업로드된 이미지를 호출하는 함수를 스크립트에 작성  
```javascript
	function showUploadedImages(ar){
		var divArea = $('.uploadResult');
		for(var i = 0; i < ar.length; i++){
			divArea.append("<img src='display?filename="+ ar[i].imageURL + "'>");
		}	
	}
```  

### 19) upload.html파일의 파일 업로드 요청함수 안에 업로드된 이미지를 호출하는 함수를 호출하는 구문을 추가  
```javascript
	function showUploadedImages(ar){
		var divArea = $('.uploadResult');
		for(var i = 0; i < ar.length; i++){
			divArea.append("<img src='display?filename="+ ar[i].imageURL + "'>");
		}	
	}
```  

## 설정파일
### YAML(Yaml Ain't Markup Language)  
	문자열을 표현한느 방법중의 하나로 인간이 알아보기 쉬워서 최근에 많이 사용되는 포맷  
	Sprint Boot에서는 properties파일 대신에 yml파일을 만들어서 사용해도 됩니다.  

### properties파일  
```ini
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/node?useUnicode=yes&characterEncoding=UTF-8&serverTimezon=UTC
spring.datasource.username=root
spring.datasource.password=1234
```  

### yml 파일
```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    password: 1234
    url: jdbc:mysql://localhost:3306/node?useUnicode=yes&characterEncoding=UTF-8&serverTimezon=UTC
    username: root
```  

### Micro Service 도입  
Spring MVC, 전자 정부 프레임워크 -> Spring Boot  
Maven -> gradle
xml이나 properties -> yaml  