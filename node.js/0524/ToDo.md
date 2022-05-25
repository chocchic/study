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

### 4) model 패키지에 데이터 삽입시간과 수정시간을 가진 BaseEntity를 생성
```java
// 데이터베이스 작업을 감시하도록 설정
// Listener: 이벤트가 발생했을 때 처리하는 객체
@EntityListeners(value= {AuditingEntityListener.class})

// 테이블로 생성하지 않는 Entity를 생성하기 위한 설정
@MappedSuperclass


// 인스턴스를 만들 수 없는 클래스
public class BaseEntity {
	@CreatedDate
	@Column(name = "regdate", updatable = false)
	private LocalDateTime regDate;
	
	@CreatedDate
	@Column(name = "regdate", updatable = true)
	private LocalDateTime modDate;
}
```  

### 5) model패키지에 데이터 저장을 위한 ToDo엔티티 클래스를 생성  
```java
@Builder // 생성자를 사용하지 않고 인스턴스를 생성하고 속성이름을 setter처럼 사용하기 위한 어노테이션
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="todo")
@Entity
public class ToDoEntity extends BaseEntity{ // regDate와 modDate를 자동으로 상속
	@Id
	// 시스템이 랜덤하게 uuid로 기본키 값을 생성
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="systme-uuid", strategy="uuid")
	private String id;
	
	@Column(length = 100, nullable = false)
	private String userId;
	
	@Column(length = 500, nullable = false)
	private String title;
	
	@Column(nullable = false)
	private boolean done;	
	
}
```

### 6) 실행을 해서 테이블이 생성되는지 확인  

### 7) persistence 패키지에 ToDoEntity 작업을 위한 ToDoRepository 인터페이스를 생성하고 메서드 선언  
```java
public interface ToDoRepository extends JpaRepository<ToDoEntity, String>{
	//userID에 해당하는 ToDoEntity를 가져오는 메서드
	public List<ToDoEntity> findByUserId(String userid);
}
```  

### 8) test 패키지에 ToDoRepository를 테스트할 수 있는 클래스를 생성하고 테스트  
```java
@SpringBootTest
public class ToDoRepoTest {
	@Autowired
	private ToDoRepository t;
	
	// 삽입 확인
	//@Test
	public void testInsert() {
		ToDoEntity todo = ToDoEntity.builder().userId("choc").title("테스트용 제목").build();
		t.save(todo);
	}
	
	// 수정 확인
//	@Test
	public void testUpdate() {
		ToDoEntity todo = ToDoEntity.builder().id("4028b88180f449f10180f44a09910000").userId("choc").title("테스트용 제목 수정됨").build();
		t.save(todo);
	}
	
	// 기본키를 가지고 데이터를 조회
	@Test
	public void testDetail() {
		// 기본키를 가지고 데이터를 조회
		Optional<ToDoEntity> result = t.findById("4028b88180f449f10180f44a09910000");
		
		// 데이터가 존재할 때
		if(result.isPresent()) {
			System.out.println(result.get());
		}else {
			System.out.println("데이터가 존재하지 않습니다.");
		}
	}

	// 기본키가 아닌 것을 가지고 조회
	//@Test
	public void testList() {
		List<ToDoEntity> result = t.findByUserId("choc");
		//List<ToDoEntity> result = t.findByUserId("c");
		//데이터가 존재할 때
		if(result.size() > 0) {
			for(ToDoEntity e : result) {
				System.out.println(e);
			}
		}else {
			System.out.println("데이터가 존재하지 않습니다.");
		}
	}
	
	// 삭제는 기본키를 가지고 지우는 것과 Entity를 이용해서 지우는 2가지를 제공
	@Test
	public void testDelete() {
		t.deleteById("4028b88180f449f10180f44a09910000");
	}
}
```

### 9) dto패키지에 ToDoEntity의 DTO클래스 생성  
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDTO {
	private String id;
	private String title;
	private boolean done;
	private LocalDateTime regdate;
	private LocalDateTime moddate;
	// ToDoEntity를 매개변수로 해서 ToDoDTO를 생성하는 생성자
	public ToDoDTO(final ToDoEntity todo) {
		this.id= todo.getId();
		this.title = todo.getTitle();
		this.done =todo.isDone();
		this.regdate = todo.getRegDate();
		this.moddate = todo.getModDate();
	}
	
	// DTO를 Entity로 변환해주는 매서드
	public static ToDoEntity toEntity(final ToDoDTO dto) {
		return ToDoEntity.builder().id(dto.getId()).title(dto.getTitle()).done(dto.isDone()).build();
	}
}
```  

### 10) dto패키지에 조회 후 출력을 위한 ResponseDTO클래스를 생성  
* 데이터 목록과 에러여부를 소유  
```java
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO <T> {
	private String error;
	private List<T> data;
}
```

### 11) 사용자의 요청을 처리할 메서드르 소유한 ToDoService 인터페이스를 생성하고 메서드 선언  
```java
public interface ToDoService {
	// 데이터 삽입
	public List<ToDoEntity> create(final ToDoEntity entity);
	// userid에 해당하는 데이터 조회
	public List<ToDoEntity> retrieve(final String userId);
	// id에 해당하는 데이터 조회
	public ToDoEntity detail(final String id);
	// 데이터 수정
	public List<ToDoEntity> update(final ToDoEntity entity);
	// 데이터 삭제
	public List<ToDoEntity> delete(final ToDoEntity entity);
	
	/*
	지난번에는 DTO와 Entityt의 변환을 Service에서 했습니다. 
	장점은 DTO와 Entity변환 작업을 Service에서 호출하기 대문에 자기한테 만들어져 있으면 코드를 읽기가 편합니다.
	단점은 Business Logic과 그렇지 않은 로직이 한 곳에 있어서 역할의 경계가 애매해지고 유지보수가 어려워질 수 있습니다.
	*/
}
```  

### 12) 사용자의 요청을 처리할 메서드를 구현한 ToDoServiceImpl클래스를 만들고 메서드 구현  
```java
@RequiredArgsConstructor
@Slf4j
public class ToDoServiceImpl implements ToDoService{
	
	private final ToDoRepository todoRepository;
	
	// 유효성검사 메서드 - 인터페이스에 만들어도 되고 ServiceImpl에 만들어도 되는데 인터페이스에 만들면 public으로 만들어야해서 외부에서 호출할 수 있게 됩니다.
	// 외부에서 호출할 수 없도록 할 때는 ServiceImpl에서 private으로 구현하고, 외부에서 호출 가능하도록하고자하면 Service인터페이스에서 default로 만들면됩니다.
	private void validate(final ToDoEntity entity) {
		if(entity== null) {
			log.warn("Entity is null");
			throw new RuntimeException("Entity cannot be null");
		}
		if(entity.getUserId() == null) {
			log.warn("Unknown User");
			throw new RuntimeException("Unknown User");
		}
	}
	
	@Override
	public List<ToDoEntity> create(ToDoEntity entity) {
		// 유효성 검사
		validate(entity);
		// 데이터 삽입
		todoRepository.save(entity);
		// 삽입한 유저의 데이터를 전부 조회해서 리턴
		return todoRepository.findByUserId(entity.getUserId());
	}

	@Override
	public List<ToDoEntity> retrieve(String userId) {
		// 유저의 데이터를 전부 조회해서 리턴
		return todoRepository.findByUserId(userId);
	}

	@Override
	public ToDoEntity detail(String id) {
		ToDoEntity todo = null;
		Optional<ToDoEntity> result = todoRepository.findById(id);
		if(result.isPresent()) {
			todo = result.get();
		}
		return todo;
	}

	@Override
	public List<ToDoEntity> update(ToDoEntity entity) {
		// 유효성 검사
		validate(entity);
		// 데이터가 데이터베이스상 존재 여부 확인
		ToDoEntity todo =detail(entity.getId());
		// 데이터가 존재하면 수정
		if(todo != null) {
			todoRepository.save(entity);
		}
		// 유저의 모든 데이터 리턴
		return todoRepository.findByUserId(entity.getUserId());
	}

	@Override
	public List<ToDoEntity> delete(ToDoEntity entity) {
		// 유효성 검사
		validate(entity);
		// 데이터가 데이터베이스상 존재 여부 확인
		ToDoEntity todo = detail(entity.getId());
		// 데이터가 존재하면 수정
		if(todo != null) {
			todoRepository.delete(entity);
//			todoRepository.deleteById(entity.getId()); // id로 지우기도 가능
		}
		// 유저의 모든 데이터 리턴
		return todoRepository.findByUserId(entity.getUserId());
	}

}
```
* java에서 final은 다른 객체로 대체할 수 없다라는 의미인데 변수에 처음에 데이터를 대입하고 변경할 계획이 없다면 반드시 붙여줄 것  
	final과 다른언어의 const를 사용하는 것은 중요한 프로그래밍 테크닉 중 하나입니다.  

### 13) URL과 Service 매핑을 위한 ToDoController클래스를 생성하고 요청 처리 메서드를 작성  
```java
//데이터를 리턴하기 위한 Controller를 만들기 위한 어노테이션
@RestController
//공통된 URL 작성 - localhost:포트번호/todo/
@RequestMapping("todo")
public class ToDoController {
	@Autowired
	private ToDoService toDoService;
	
	//데이터 삽입
	@PostMapping
	public ResponseEntity<?> createToDo(@RequestBody ToDoDTO dto){
		try {
			//회원 정보를 만들 수 없어서 임시로 회원 아이디 설정
			String temporaryUserId = "temporary-user";
			//DTO를 Entity로 변환
			ToDoEntity entity = ToDoDTO.toEntity(dto);
			entity.setId(null);
			entity.setUserId(temporaryUserId);
			//서비스의 삽입을 호출하고 결과를 저장
			List<ToDoEntity> entities = toDoService.create(entity);
			
			//ToDoEntity의 List를 ToDoDTO의 List로 변환
			//entities.stream() 는 List를 Stream으로 변환
			
			//map은 Stream의 모든 요소를 순서대로 매개변수로 대입된 함수를 적용해서 리턴한 값들을 가지고
			//스트림을 만들어주는 메서드
			//클래스이름::메서드 이름의 형태로 대입을 해야하는데 new는 생성자를 이용하겠다는 의미입니다.
			
			//collect는 Stream을 배열이나 List, Set, Map으로 변환해주는 메서드입니다.
			//map으로 나온 결과를 List로 변환한 것 입니다.
			
			List<ToDoDTO> list = entities.stream().map(ToDoDTO::new).collect(Collectors.toList());
			//응답 객체를 생성
			ResponseDTO<ToDoDTO> response = ResponseDTO.<ToDoDTO>builder().data(list).build();
			//정상 응답 객체를 만든 후 본문은 response로 설정
			return ResponseEntity.ok().body(response);
			
		}catch(Exception e) {
			//예외가 발생하면 에러 메시지를 리턴
			String error = e.getMessage();
			ResponseDTO<ToDoDTO> response = ResponseDTO.<ToDoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	//데이터 조회
	@GetMapping
	public ResponseEntity<?> retrieveToDoList(){
		String temporaryUserId = "temporary-user";
		List<ToDoEntity> entities = toDoService.retrieve(temporaryUserId);
		//ToDoEntity의 List를 ToDoDTO의 List로 변환
		List<ToDoDTO> list = entities.stream().map(ToDoDTO::new).collect(Collectors.toList());
		//응답 객체를 생성
		ResponseDTO<ToDoDTO> response = ResponseDTO.<ToDoDTO>builder().data(list).build();
		//정상 응답 객체를 만든 후 본문은 response로 설정
		return ResponseEntity.ok().body(response);
		
	}
	
	//데이터 수정
	@PutMapping
	public ResponseEntity<?> updateToDo(@RequestBody ToDoDTO dto){
		String temporaryUserId = "temporary-user";
		
		ToDoEntity entity = ToDoDTO.toEntity(dto);
		entity.setUserId(temporaryUserId);
				
		List<ToDoEntity> entities = toDoService.update(entity);
		//ToDoEntity의 List를 ToDoDTO의 List로 변환
		List<ToDoDTO> list = entities.stream().map(ToDoDTO::new).collect(Collectors.toList());
		//응답 객체를 생성
		ResponseDTO<ToDoDTO> response = ResponseDTO.<ToDoDTO>builder().data(list).build();
		//정상 응답 객체를 만든 후 본문은 response로 설정
		return ResponseEntity.ok().body(response);
		
	}
	
	//데이터 삭제
	@DeleteMapping
	public ResponseEntity<?> deleteToDo(@RequestBody ToDoDTO dto){
		String temporaryUserId = "temporary-user";
		
		ToDoEntity entity = ToDoDTO.toEntity(dto);
		entity.setUserId(temporaryUserId);
				
		List<ToDoEntity> entities = toDoService.delete(entity);
		//ToDoEntity의 List를 ToDoDTO의 List로 변환
		List<ToDoDTO> list = entities.stream().map(ToDoDTO::new).collect(Collectors.toList());
		
		//응답 객체를 생성
		ResponseDTO<ToDoDTO> response = ResponseDTO.<ToDoDTO>builder().data(list).build();
		//정상 응답 객체를 만든 후 본문은 response로 설정
		return ResponseEntity.ok().body(response);
		
	}
}
```

### 14)PostMan 프로그램을 이용해서 테스트  
* 데이터 삽입 테스트: post를 선택하고 url은 http://localhost:포트번호/todo  
	body에 체크하고 raw 와 json을 선택한 후 데이터를 입력  
```json
{
    "title":"새로운 메모"
}
```
* 데이터 조회 테스트: get을 선택하고 url은 http://localhost:포트번호/todo  

* 데이터 수정 테스트: put를 선택하고 url은 http://localhost:포트번호/todo  
	body에 체크하고 raw 와 json을 선택한 후 데이터를 입력  
```json
{
    "id":"아이디 - 데이터베이스에서 확인해서 복사",
    "title":"수정한 메모",
    "done":true
}
```  

* 데이터 삭제 테스트: delete를 선택하고 url은 http://localhost:포트번호/todo  
	body에 체크하고 raw 와 json을 선택한 후 데이터를 입력  
```json
{
    "id":"아이디 - 데이터베이스에서 확인해서 복사"
}
```  

## 3.FrontEnd Application  
### 1)SPA - Single Page Application  
* 한 번 로딩하면 사용자가 임의로 새로 고침하지 않는 이상 페이지를 새로 로딩하지 않는 애플리케이션  
* 웹에서는 React.js 나 Vue.js를 이용해서 구현하는 경우가 많습니다.  

### 2)React.js를 사용하기 위한 개발 환경  
* node.js를 설치  
* IDE - vscode  

### 3)react 프로젝트 생성  
* 터미널에서 npx create-react-app 앱이름을 입력  
* npx create-react-app todo-front-end  

* 실행은 npm start  
```shell
cd todo-front-end
npm start
```  
* 브라우저에서는 localhost:3000 으로 확인  

### 4)vscode 에서 생성한 프로젝트 열기  
* src 디렉토리의 App.js가 Entry Point  

### 5)UI 개발을 편리하게 해주는 material-ui 패키지를 설치  
* https://mui.com  
* 설치  
```shell
npm install --save --legacy-peer-deps @material-ui/core
npm install --save --legacy-peer-deps @material-ui/icons
```

### 6)src 디렉토리에 ToDo.js 파일을 추가하고 작성  
```javascript
import React from "react"

class ToDo extends React.Component{
    render(){
        return(
            <div className="ToDo">
                <input type="checkbox" 
                    id="todo0" name="todo0" value="todo0"/>
                <label for="todo0">ToDo 컴포넌트 만들기</label>
            </div>
        )
    }
}

export default ToDo;
```  

### 7)App.js 파일을 수정  
```javascript
import logo from './logo.svg';
import React from "react";
import ToDo from "./ToDo";
import './App.css';

function App() {
  return (
    <div className="App">
      <ToDo/>
    </div>
  );
}

export default App;
```  

### 8) 목록보기  
* ToDo.js파일 수정  
```javascript
import React from "react"

class ToDo extends React.Component{
    constructor(props){
        super(props);
        this.state = {item:props.item};
    }

    render(){
        return(
            <div className="ToDo">
                <input type="checkbox" 
                    id={this.state.item.id} name={this.state.item.id} value={this.state.item.done}/>
                <label id={this.state.item.id}>{this.state.item.title}</label>
            </div>
        )
    }
}

export default ToDo;
```  

* App.js파일을 수정
```javascript
import logo from './logo.svg';
import React from "react";
import ToDo from "./ToDo";
import './App.css';

class App extends React.Component{
  constructor(props){
      super(props);
      this.state = {item:{id:0, title:"Hello React", done:true}};
  }

  render(){
    return(
      <div className='App'>
        <ToDo item={this.state.item}/>
      </div>
    );
  }
}

export default App;
```  

* 브라우저 화면에 변경된 내용이 적용되는지 확인  

* App.js를 수정해서 배열을 출력  

* Todo.js에 빼먹음
```javascript
import React from "react"

import{
    ListItem,
    ListItemText,
    InputBase,
    Checkbox
}from "@material-ui/core";

class ToDo extends React.Component{
    constructor(props){
        super(props);
        this.state = {item:props.item};
    }

    render(){
        const item = this.state.item;
        return(
            <ListItem>
                <Checkbox checked={item.done}/>
                <ListItemText>
                    <InputBase
                        inputProps={{"aria-label":"naked"}}
                        type="text"
                        id={item.id}
                        name={item.name}
                        value={item.title}
                        multiline={true}
                        fullWidth={true}
                        />
                </ListItemText>
            </ListItem>
        )
    }
}

export default ToDo;
```

* App.js파일을 수정 - UI 개선 (material-ui 활용)  
```javascript
import logo from './logo.svg';
import React from "react";
import ToDo from "./ToDo";
import AddToDo from "./AddToDO";

import './App.css';
import {Paper, List, Container} from "@material-ui/core";

class App extends React.Component{
  constructor(props){
      super(props);
      this.state = {items:[
      {id:0, title:"Hello React", done:true},
      {id:1, title:"Hello React2", done:false},
      {id:2, title:"Hello React", done:true}]};
  }

  render(){
    var todoItems= this.state.items.length > 0 && (
      <Paper style={{margin:16}}>
        <List>
          {this.state.items.map((item)=>{
            return <ToDo item = {item}/>
          })};
        </List>
      </Paper>
    )

    return(
      <div className='App'>
        <Container maxWidth="md">
          <AddToDo/>
          <div className="ToDoList">{todoItems}</div>
        </Container>
      </div>
    );
  }
}

export default App;
```  

* 브라우저에서 데이터 목록위에 추가화면이 출력되는지 확인  

* App.js파일을 수정해서 데이터 추가 함수를 생성하고 AddToDo.js에게 함수를 넘겨줍니다.  
```javascript
import logo from './logo.svg';
import React from "react";
import ToDo from "./ToDo";
import AddToDo from "./AddToDO";

import './App.css';
import {Paper, List, Container} from "@material-ui/core";

class App extends React.Component{
  constructor(props){
      super(props);
      this.state = {items:[
      {id:0, title:"Hello React", done:true},
      {id:1, title:"Hello React2", done:false},
      {id:2, title:"Hello React", done:true}]};
  }
  add = (item) =>{
    // 데이터 배열 가져오기
    const thisItems = this.state.items;
    // 새로운 item의 id 설정
    item.id = "ID-"+thisItems.length;
    // done 설정
    item.done = false;
    // 배열에 추가
    thisItems.push(item);
    // 원본 데이터 변경
    this.setState({items:thisItems});
  }
  render(){
    var todoItems= this.state.items.length > 0 && (
      <Paper style={{margin:16}}>
        <List>
          {this.state.items.map((item)=>{
            return <ToDo item = {item}/>
          })};
        </List>
      </Paper>
    )

    return(
      <div className='App'>
        <Container maxWidth="md">
          <AddToDo add={this.add}/>
          <div className="ToDoList">{todoItems}</div>
        </Container>
      </div>
    );
  }
}

export default App;
```  

* AddToDo.js 파일을 수정 - 이벤트 처리  
```javascript
import React from "react";

import { TextField, Paper, Button, Grid } from "@material-ui/core";

class AddToDo extends React.Component{
    constructor(props){
        super(props);
        this.state = {item:{title:""}};
        this.add = props.add;
    }

    // Input의 내용이 변경될 때 호출될 함수
    onInputChange = (e) =>{
        // 입력한 내용을 title에 대체
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({item:thisItem});
    }

    // + 버튼을 눌렀을 때 호출될 함수
    onButtonClick = () =>{
        this.add(this.state.item);
        this.setState({item:{title:""}});
    }

    // Enter키를 눌렀을 때 호출될 함수
    enterKeyEventHandler = (e)=>{
        if(e.key === "Enter"){
            this.onButtonClick();
        }
    }

    render(){
        return(
            <Paper style={{margin:16,padding:16}}>
                <Grid container>
                    <Grid xs={11} item style={{paddingRight:16}}>
                        <TextField placeholder="텍스트를 입력하세요" fullWidth 
                        onChange={this.onInputChange} value={this.state.item.title} 
                        onKeyPress={this.enterKeyEventHandler}/>
                    </Grid>
                    <Grid xs={1} md={1} item>
                        <Button fullWidth color="secondary" variant="outllined" 
                        onClick={this.onButtonClick}>
                            +
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        )
    }
}

export default AddToDo;
```

* 브라우저에서 내용을 입력하고 Enter를 누르거나 +버튼을 눌러서 데이터가 삽입되는지 확인

* 빼먹음
```javascript
import React from "react"

import{
    ListItem,
    ListItemText,
    InputBase,
    Checkbox,
    ListItemSecondaryAction,
    IconButton
}from "@material-ui/core";

import DeleteOutlined  from "@material-ui/icons/DeleteForeverOutlined";
class ToDo extends React.Component{
    constructor(props){
        super(props);
        this.state = {item:props.item};
    }

    render(){
        const item = this.state.item;
        return(
            <ListItem>
                <Checkbox checked={item.done}/>
                <ListItemText>
                    <InputBase
                        inputProps={{"aria-label":"naked"}}
                        type="text"
                        id={item.id}
                        name={item.name}
                        value={item.title}
                        multiline={true}
                        fullWidth={true}
                        />
                </ListItemText>
                <ListItemSecondaryAction>
                    <IconButton aria-label="Delete ToDo">
                        <DeleteOutlined/>
                    </IconButton>
                </ListItemSecondaryAction>
            </ListItem>
        )
    }
}

export default ToDo;
```  

* 브라우저에서 데이터 오른쪽에 삭제 아이콘이 생겼는지 확인  

* App.js파일에 삭제 아이콘을 눌렀을 때 호출될 함수를 작성하고 이를 ToDo에게 전달  
```javascript
import logo from './logo.svg';
import React from "react";
import ToDo from "./ToDo";
import AddToDo from "./AddToDO";

import './App.css';
import {Paper, List, Container} from "@material-ui/core";

class App extends React.Component{
  constructor(props){
      super(props);
      this.state = {items:[
      {id:0, title:"Hello React", done:true},
      {id:1, title:"Hello React2", done:false},
      {id:2, title:"Hello React", done:true}]};
  }
  add = (item) =>{
    // 데이터 배열 가져오기
    const thisItems = this.state.items;
    // 새로운 item의 id 설정
    item.id = "ID-"+thisItems.length;
    // done 설정
    item.done = false;
    // 배열에 추가
    thisItems.push(item);
    // 원본 데이터 변경
    this.setState({items:thisItems});
  }

  delete = (item)=>{
    const thisItems = this.state.item;
    const newItems = thisItems.filter((e)=>e.id !== item.id);
    this.setState({items:newItems}, ()=>{
      console.log("데이터 삭제");
    });
  }

  render(){
    var todoItems= this.state.items.length > 0 && (
      <Paper style={{margin:16}}>
        <List>
          {this.state.items.map((item)=>{
            return <ToDo item = {item} key={item.id} delete={this.delete}/>
          })};
        </List>
      </Paper>
    )

    return(
      <div className='App'>
        <Container maxWidth="md">
          <AddToDo add={this.add}/>
          <div className="ToDoList">{todoItems}</div>
        </Container>
      </div>
    );
  }
}

export default App;
```  

* ToDo.js에 구현
```javascript
import React from "react"

import{
    ListItem,
    ListItemText,
    InputBase,
    Checkbox,
    ListItemSecondaryAction,
    IconButton
}from "@material-ui/core";

import DeleteOutlined  from "@material-ui/icons/DeleteForeverOutlined";
class ToDo extends React.Component{
    constructor(props){
        super(props);
        this.state = {item:props.item};
        this.delete = props.delete;
    }
    // 삭제 이벤트 처리를 위한 함수
    /*
    함수 만드는 방법
    1.
    function 이름(매개변수){
        내용
    }

    2. 
    var 또는 let,const 이름 = function(매개변수){
        내용
    }
    3. 에로우 함수 : 메모리 효율때문에 많이 사용
    (매개변수) => {
        내용
    }

    javascript에서 변수 만들 때 
    var 변수이름 = 값; 해도 되지만
    변수이름 = 값; 해도 됨. 대신 이 경우는 global됨
    */
    deleteEventHandler = ()=>{
        this.delete(this.state.item);
    }
    render(){
        const item = this.state.item;
        return(
            <ListItem>
                <Checkbox checked={item.done}/>
                <ListItemText>
                    <InputBase
                        inputProps={{"aria-label":"naked"}}
                        type="text"
                        id={item.id}
                        name={item.name}
                        value={item.title}
                        multiline={true}
                        fullWidth={true}
                        />
                </ListItemText>
                <ListItemSecondaryAction>
                    <IconButton aria-label="Delete ToDo" onClick={this.deleteEventHandler}>
                        <DeleteOutlined/>
                    </IconButton>
                </ListItemSecondaryAction>
            </ListItem>
        )
    }
}

export default ToDo;
```  
=> 브라우저에서 데이터가 삭제되는지 확인  

* App.js에서 직접 생성한 데이터 삭제  
```javascript
import logo from './logo.svg';
import React from "react";
import ToDo from "./ToDo";
import AddToDo from "./AddToDO";

import './App.css';
import {Paper, List, Container} from "@material-ui/core";

class App extends React.Component{
  constructor(props){
      super(props);
      this.state = {items:[]};
  }
  add = (item) =>{
    // 데이터 배열 가져오기
    const thisItems = this.state.items;
    // 새로운 item의 id 설정
    item.id = "ID-"+thisItems.length;
    // done 설정
    item.done = false;
    // 배열에 추가
    thisItems.push(item);
    // 원본 데이터 변경
    this.setState({items:thisItems});
  }

  delete = (item)=>{
    const thisItems = this.state.items;
    const newItems = thisItems.filter((e)=>e.id !== item.id);
    this.setState({items:newItems}, ()=>{
      console.log("데이터 삭제");
    });
  }

  render(){
    var todoItems= this.state.items.length > 0 && (
      <Paper style={{margin:16}}>
        <List>
          {this.state.items.map((item)=>{
            return <ToDo item = {item} key={item.id} delete={this.delete}/>
          })};
        </List>
      </Paper>
    )

    return(
      <div className='App'>
        <Container maxWidth="md">
          <AddToDo add={this.add}/>
          <div className="ToDoList">{todoItems}</div>
        </Container>
      </div>
    );
  }
}

export default App;
```

### 11) ToDo 수정  
* 요구사항  
	ToDo 컴포넌트 readonly 플래그를 이요해서 이 플래그가 true면 수정이 불가능하고 false인 경우 수정이 가능하도록 설정  
	사용자가 아이템의 title을 클릭하면 input field가 수정할 수 있는 상태인 readonly가 false인 상태로 변경되도록 할 것  
	사용자가 Enter를 누르면 readonly가 true인 상태로 변환  
	체크를 클릭하면 item.done의 값을 토글  


## 4. 서비스 통합  
### 1) CORS(Cross-Origin Resource Sharing)  
* 웹 서비스를 만들면 리소스를 제공하는 곳의 도메인(포트번호까지)과 요청하는 곳의 도메인이 같아야만 요청을 허락합니다.  

* FrontEnd의 App.js파일의 컴포넌트에 데이터를 요청하는 코드를 추가  
```javascript
import logo from './logo.svg';
import React from "react";
import ToDo from "./ToDo";
import AddToDo from "./AddToDO";

import './App.css';
import {Paper, List, Container} from "@material-ui/core";

class App extends React.Component{
  constructor(props){
      super(props);
      this.state = {items:[]};
  }

  // 컴포넌트가 마운트된 후 호출되는 함수
  componentDidMount(){
    const requestoptions={
      method:'GET',
      headers:{'Content-Type':'application/json'}
    };

    fetch('http://localhost/todo', requestoptions).then((response)=>response.json())
    .then((response)=>{
      this.setState({
        items:response.data
      });
    },(error)=>{
      this.setState({
        error
      });
    })
  }

  add = (item) =>{
    // 데이터 배열 가져오기
    const thisItems = this.state.items;
    // 새로운 item의 id 설정
    item.id = "ID-"+thisItems.length;
    // done 설정
    item.done = false;
    // 배열에 추가
    thisItems.push(item);
    // 원본 데이터 변경
    this.setState({items:thisItems});
  }

  delete = (item)=>{
    const thisItems = this.state.items;
    const newItems = thisItems.filter((e)=>e.id !== item.id);
    this.setState({items:newItems}, ()=>{
      console.log("데이터 삭제");
    });
  }

  render(){
    var todoItems= this.state.items.length > 0 && (
      <Paper style={{margin:16}}>
        <List>
          {this.state.items.map((item)=>{
              return <ToDo item = {item} key={item.id} delete={this.delete}/>
            })
          }
        </List>
      </Paper>
    )

    return(
      <div className='App'>
        <Container maxWidth="md">
          <AddToDo add={this.add}/>
          <div className="ToDoList">{todoItems}</div>
        </Container>
      </div>
    );
  }
}

export default App;
```  

* 브라우저의 검사 창을 확인 - 에러발생  

* Spring에서는 환경 설정 클래스를 추가해서 해결 - 기본패키지.config.WebMVCconfig  
```java
```  
=> 브라우저에서 새로고침을 하게되면 에러가 없어집니다.  


### 2) 자바스크립트로 데이터 요청  
* ajax : 콜백을 이용하는 방법  
```javascript
var oReq = new XMLHttpReqeust();
oReq.open("GET", "http://localhost/todo");
oReq.send();
oReq.addEventListener("load", (e)=>{
	// 내부처리
})
```  

* ajax : Promise를 이용하는 방법  
```javascript
function exampleFunction(){
	return new Promise((resolve, reject)=>{
		var oReq = new XMLHttpRequest();
		oReq.open("GET", "http://localhost/todo");
		oReq.onload= function(){
			// 성공했을 때 수행 내용
		}
		oReq.onerror= function(){
			// 에러가 발생했을 때 수행 내용
		}
		oReq.onsend= function(){
			// 아직 도착하지 않았을 때 수행 내용
		}
	})
}

exampleFunction()
	.then((r)=>{/* 완료되었을 때 수행할 내용*/})
	.catch((e)=>{/*실패했을때 수행할 내용*/});
```  

* fetch api
```javascript
// 파라미터가 없을 때 
fetch("url").then(response =>{
	// 성공했을 때 수행할 내용
}).catch(e =>{
	// 실패했을 때 수행할 내용
})

//파라미터 있을 때
파라미터 이름 = {method:전송방식, headers:[헤더데이터], body:JSON.stringfy(데이터)};
fetch("url", 파라미터 이름).then(response =>{
	// 성공했을 때 수행할 내용
}).catch(e =>{
	// 실패했을 때 수행할 내용
});
```  

### 3) Front-End Application 수정  
* Back - End URL을 저장할 환경 설정 파일을 src 디렉터리에 추가 - src/app-config.js
```javascript
let backendHost;
const hostname=window && window.location && window.location.hostname;

if(hostname == 'localhost'){
    backendHost = "http://localhost";
}

export const API_BASE_URL = `${backendHost}`
```  

* BackEnd에서 데이터를 가져우는 함수를 구현한 파일을 생성 - src/service/Api-Service.js
```javascript
import { API_BASE_URL } from "../app-config";

export function call(api, method, request){
    let options = {
        headers:new Headers({
            'Content-Type':"application/json"
        }),
        url:API_BASE_URL + api,
        method:method
    };

    if(request){
        options.body = JSON.stringify(request);
    }

    return fetch(options.url, options).then((response)=>
        response.json().then((json)=>{
            if(!response.ok){
                return Promise.reject(json);
            }
            return json;
        }))
}
```  

* app.js파일의 내용 수정 - 삽입, 삭제, 조회기능 적용  
```javascript

```
=> 브라우저에서 데이터 삽입 및 삭제를 확인  

* 수정을 구현하기 위해서 app.js를 수정
```javascript
```

* ToDo.js 수정  빼먹음