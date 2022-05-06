# node _ express

## 1. 에러 처리
  1) 에러 발생 즉시 처리  
  ```javascript  
    res.status(상태 코드).수행할 코드
  ```  
      : 수행할 코드 부분은 매개변수가 4개가 되는데 error 객체, req 객체, next  
  
  2) 에러 별도 처리  
  ```javascript  
    app.use((err,req,res, next) =>  
      // 에러 처리 코드  
    }  
  ```  

## 2. req 객체 - request(클라이언트의 정보를 저장한 요청) 객체  
  * req.app : app 객체에 대한 참조  
  * req.body : body-parser가 만드는 요청의 내용을 해석한 객채(post 방식으로 전송된 데이터 중에서 file을 제외한 데이터 해석에 사용)  
  * req.params : 라우트 매개변수에 대한 정보가 저장된 객체(URL의 일부분을 파라미터로 사용하고자 하는 경우 사용)  
  * req.query : query string 해석에 사용(get 방식으로 전송된 데이터를 해석)


  * req.cookies : 쿠키 해석에 사용  
  * req.signedCookies : 서명된 쿠키 해석에 사용  


  * req.ip : 클라이언트의 ip  


  * req.get(해더 이름) : 헤더에 해당하는 데이터 리턴(API Key 해석에 많이 이용)  

## 3. res 객체 - response(응답 정보를 제공) 객체  
  * res.app : app 객체에 대한 참조  


  * res.cookie(키, 값, 옵션) : 쿠키를 설정하는 함수  
  * res.clearCookie(키, 값, 옵션) : 쿠키를 삭제하는 함수
    이전에는 보안 문제 때문에 사용하지 않는 것을 권장했지만 최근에는 사용자의 동석 추적등에 많이 이용  
 
 
  * res.send(출력할 내용) : 내용을 직접 출력
  * res.sendFile(파일 경로) : 파일을 출력


  * res.end() : 데이터 없이 응답을 전송  


  * res.render(뷰이름, 데이터) : 템플릿 엔진(spring에서의 thymeleaf나 velocity나 python의 django와 유사)을 이용해서 출력  
 
 
  * res.json(JSON 데이터) : JSON 형식으로 응답, 최근에 많이 사용  
  
  
  * res.redirect(리다이렉트할 URL) : URL로 리다이렉트  
  
  * res.status(상태코드)


  * **응답은 1번만 수행, 최근에 추세인 SPA(Single Page Application) 구현이나 다양한 디바이스 지원을 위한 Server를 구축하고자 하는 경우에는 json형태로 리턴하는 경우가 많습니다.**
 
## 4. 템플릿 엔진
  * HTML은 정적이기 때문에, 서버에서 생성하는 동적인 데이터를 가져다가 사용할 수 없다.  
    이를 위한 해결책으로는 ajax나 web socket같은 JavaScript를 이용한 전송방식이 있고, 서버의 데이터를 사용할 수 있도록 해주는 템플릿 엔진을 이용하는 방법이 있다.  
    
  * 서버의 데이터를 html파일에서 자바스크립트 코드없이 바로 출력하고자 하는 경우 사용  
  
  1) Jade  
    * Jade로 배포되었다가 저작권 문제로 Pug로 개명  
    * 공식 문서 : https://pugjs.org/api/getting-started.html  
    * html도 jade 문법으로 출력  
    * 설치 : npm install pug  
    * 설정  
  ```javascript  
    app.get('views', path.join(__dirname, '실제 pug파일이 위치할 디렉토리')  
    app.set('view engine', 'pug')  
  ```  
    * 라우팅 시 pug로 출력  
      res.render('출력할 뷰 이름') : 실제 pug 파일이 위치할 디렉토리에 뷰이름.html이나 뷰이름.pug파일을 이용해서 출력합니다.  
      render함수를 호출할 때 뒤에 데이터를 같이 전송할 수 있으며, pug를 이용한 출력 파일에서는 이 데이터를 사용할 수 있고, 변수 생성이나 제어문도 가능합니다.  
      
   2) jade를 이용한 데이터 출력
     * node 프로젝트 생성  
     * 필요한 라이브러리 설치  
       일반 설치 : express, morgan, session-file-store, multer, cookie-parser, express-session, dotenv, pug  
       ```
         npm i express morgan session-file-store multer cookie-parser express-session dotenv pug
       ```  
       개발용 설치 : nodemon  
       ```
         npm i --save-dev nodemon
       ```  
     * package.json 수정 : main 부분과 scripts부분 설정과 같게 수정  
     ```json
      "main": "index.js",
      "scripts": {
        "start": "nodemon index",
        "test": "echo \"Error: no test specified\" && exit 1"
      },
      ```
      
