# Node Socket
## 1. Socket
    * 통신을 할 수 있도록 해주는 NIC(Network Interface Card)를 추상화(프로그래밍에서 사용할 수 있도록 객체로 변환)한 것  

### 1) 소켓 프로그래밍의 분류  
    * 고수준 소켓 프로그래밍  
        직접 연결과 해제를 하지 않는 방식으로 웹 프로그래밍이 대표적인 고수준 소켓 프로그래밍의 대표적인 방식인데 구현이 쉽지만 효율이 떨어집니다.  

    * 저수준 소켓 프로그래밍(소켓 프로그래밍)  
        직접 소켓을 생성해서 통신을 하는 방식으로 구현이 어렵지만, 효율이 좋습니다.  

    * +) 
        예전에는 통합된 하나의 큰 서비스가 클라이언트에게 서비스를 제공했는데 최근에는 이렇게 통합된 큰 서비스를 만드는 것보다는 작은 마이크로 서비스를 만들어서 연동하는 형태의 서비스를 구현하는 경우가 많습니다.  

### 2) 프로토콜 종류  
    * TCP : 연결형 통신  
        요청 -> 제공하는 쪽에서 메타 데이터 전송  
    * UDP : 비연결형 통신  
        요청 -> 데이터 전송
        카카오 -> 카카오 서버 -> 구글 -> 카카오  

## 2. Web Socket  
### 1) HTTP, HTTPS  
    * Client와 server간 접속을 유지하지 않고 한번에 한 방향으로만 통신이 가능한 Half-Duplex(반 이중 - 어느 한 순간에는 수신이나 송신만 가능)  
    * Client와 Server간의 정보 유지를 위해서 Cookie와 Session의 개념을 학습합니다.  
    * 서로 간에 짧은 주기를 가지고 데이터를 자주 주고받아야 하는 경우에 성능 저하를 피할 수 없음  
    * 접속을 유지할 수없기 때문에 Client의 요청없이 Server가 클라이언트에게 데이터를 전송할 수 없음  
    이 부분은 ajax pooling 기법으로 해결  

### 2) HTML5에 새로 추가된 API  
    * HTML5의 로컬 저장소 : Web Storage, Web SQL, Indexed DB 같은 개념을 이용해서 웹 브라우저에 데이터를 저장하는 개념  
    * HTML5의 Web Socket : 클라이언트와 서버간의 Full-Duplex(전 이중 - 동시에 주고받는 것이 가능)  
    * HTML5의 Web Push : 서버가 클라이언트의 요청이 없어도 데이터를 전송하는 기능, Notification이나 SSE(Server Sent Event)이라고 합니다.  

### 3) 웹 소켓  
    * ws 프로토콜 사용  
    * 모든 브라우저가 웹 소켓을 지원하지는 않습니다.  
      IE 하위버전에서는 지원하지 않습니다.  

## 3. Node에서의 Web Socket  
    * Socket.IO 모듈을 이용하는 경우가 많이 이용하지만 ws 모듈을 이용하기도 하고 websocket 모듈도 기능을 제공함  

### 1) web socket 구현
    * 프로젝트 생성  

### 2) 필요한 패키지 설치  
    * web socket, express, morgan  
    * 개발용으로 nodemon  

### 3) 프로젝트에 index.html 파이릉 생성하고 작성
    ```html
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="utf-8"/>
            <title>Web Socket</title>
        </head>
        <body>
            소켓 연결 상태 : <span id = "status" ></span>
            <br/>
            메세지 : <ul id="message"></ul>
        </body>
    </html>
    ```

### 4) 프로젝트에 index.js파일을 추가하고 작성 - websocket 모듈을 이용한 웹소켓 구현 
    ```javascript
    var WebSocketServer = require('websocket').server;
    var http = require('http');
    var fs = require('fs');

    var server = http.createServer((req,res)=>{
        if(req.url == "/"){
            res.writeHead(200, {'Content-Type':'text/html'});
            res.end('Web Socket');
        }else if(req.url == "/index"){
            fs.readFile("index.html", (err,data)=>{
                res.writeHead(200, {'Content-Type':'text/html;charset=utf-8'});
                res.end(data);
            })
        }
    })

    // 서버 구동
    server.listen(8000, ()=>{
        console.log('Server is listening on port 8000');
    })
    ```  

### 5) 프로젝트를 실행하고 브라우저에 localhost:8000, localhost:8000/index 요청을 확인  

### 6) 빼먹음
    ```javascript
    ```  

### 7) index.html에 웹 소켓 사용을 위한 스크립트 코드 추가
    ```javascript
    // 브라우저의 웹 소켓 여부 확인
    if('WebSocket' in window){
        // DOM(Document Object model) 찾아오기
        var status = document.getElementById('status')
        var message = document.getElementById('message')

        // 웹 소켓 연결
        // IP는 서버의 IP를 적어야하고, 이름은 서버에서 만든 이름을 적어줘야 합니다.
        var ws = new WebSocket('ws://127.0.0.1:8000', 'example-echo');

        ws.addEventListener('open', (e)=>{
            status.innerHTML = '연결 성공!';
            for(var i = 0; i < 10; i++){
                // 웹소켓에게 전송
                ws.send('Hello '+ i);
            }
        });

        ws.addEventListener('message', (evt)=>{
            message.innerHTML += '<li>받은 메세지 : ' + evt.data + '</li>';
        })
    }
    ```  

## 4. Node에서의 Web Socket - websocket 모듈 이용  
### 1) 패키지를 추가 설치  
    cookie-parser, dotenv, express, express-session, morgan, nunjucks, ws  

### 2) 프로젝트에 라우팅 모듈화를 위한 routes 디렉토리를 생성  

### 3) routes 디렉토리에 index.js 파일을 생성하고 요청처리 코드를 작성  
    ```javascript
    const express = require('express')
const router = express.Router();

router.get('/',(req,res)=>{
    res.render('websocket');
});

module.exports = router;
    ```

### 4) 