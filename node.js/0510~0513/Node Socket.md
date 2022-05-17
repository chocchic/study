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

### 6) index.js 파일에 웹 소켓 서버 구현 코드를 추가  
    ```javascript
    //웹 소켓 서버 구현
    wsServer = new WebSocketServer({
        httpServer:server,
        autoAcceptConnections:false
    });
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

### 4) 웹 소켓 로직을 위한 socket.js 파일을 생성하고 작성  
    ```javascript
    const WebSocket = require('ws');

    module.exports = (server) => {
        //웹 소켓 서버 생성
        const wss = new WebSocket.Server({server});

        //클라이언트가 접속을 하면
        wss.on('connection', (ws, req)=>{
            //클라이언트의 IP 확인
            const ip = req.headers['x-forwarded-for'] 
                || req.connection.remoteAddress;
            console.log('새로운 클라이언트 접속:' + ip);

            ws.on('message', (message) => {
                console.log("클라이언트에게 받은 메시지:", message);
            });

            ws.on('close', () => {
                console.log("클라이언트 접속 종료:", ip);
                //타이머 종료
                clearInterval(ws.interval);
            });

            //타이머를 이용해서 클라이언트에게 주기적으로 메시지를 전송
            ws.interval = setInteval(() => {
                if(ws.readyState === ws.OPEN){
                    ws.send('서버에서 클라이언트에게 메시지를 전송합니다.');
                }
            }, 3000)

        })
    };
    ```  

### 5) 프로젝트의 index.js 파일을 수정  
    ```javascript
    // ws 모듈을 이용한 웹 소켓 구현 - 서버에서 일정한 주기를 가지고 메세지 전송
    const express = require('express');
    const path = require('path');
    const morgan = require('morgan');
    const cookieParser = require('cookie-parser');
    const session = require('express-session');
    const nunjucks = require('nunjucks');
    const dotenv = require('dotenv');

    dotenv.config();

    const webSocket = require('./socket');
    // 파일 이름을 생략하면 index.js입니다
    const indexRouter = require('./routes');

    const app = express();
    app.set('port', 8001);
    // 뷰 템플릿(서버의 데이터를 출력할 수 있는 html 파일) 설정
    app.set('view engine', 'html');
    nunjucks.configure('views',{
        express:app,
        watch:true
    })

    app.use(morgan('dev'));
    app.use(express.static(path.join(__dirname, 'public')));

    app.use(express.json());
    app.use(express.urlencoded({extended:false}));

    app.use(session({
        resave:false,
        saveUninitialized:false,
        secret:'websocket',
        cookie:{
            httpOnly:true,
            secure:false
        }
    }))

    // /로 시작하는 요청은 indexRouter가 처리
    app.use('/', indexRouter);

    const server = app.listen(app.get('port'), () =>{
        console.log(app.get('port'), '번 포트에서 대기 중');
    })

    webSocket(server);
    ```

### 6) 프로젝트에 views디렉토리를 생성하고 websocket.html을 생성한 후 작성  
    ```html
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="utf-8"/>
            <title>Web Socket</title>
        </head>
        <body>
            <div>
                검사를 이용해서 console과 network를 확인하세요.
            </div>
        </body>
        <script>
            const webSocket = new WebSocket("ws://127.0.0.1:8001");
            webSocket.addEventListener("open", ()=>{
                console.log("웹 소켓 서버와 연결");
            });
            webSocket.addEventListener('message',(evt)=>{
                console.log(evt.data);
                webSocket.send("클라이언트가 서버에게 보내는 메세지");
            })
        </script>
    </html>
    ```  

    * +) 로컬에서 접속했을 때 ip주소는 ipv4는 127.0.0.1, ipv6는 0000:0000:0000:0001 => :::1

## 5. node에서 웹 소켓 구현 - socket.IO모듈  
### 1) 설치  
    npm i socket.io  

### 2) 프로젝트의 socket.js 파일을 수정 - 없으면 생성  
    ```javascript
    const SocketIO = require('socket.io');
    module.exports = (server)=>{
        // 웹 소켓 서버 생성
        const io = SocketIO(server, {path:'/socket.io'});

        // 클라이언트가 접속을 하면
        io.on('connection', (socket)=>{
            // 클라이언트의 IP 확인
            const req = socket.request;
            const ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;
            console.log('새로운 클라이언트 접속 : ' + ip);

            socket.on('disconnect',() =>{
                clearInterval(socket.interval);
            });

            socket.on('reply', (data)=>{
                console.log(data);
            });
            var i = 0;
            socket.interval = setInterval(()=>{
                // emit은 강제로 이벤트를 발생시키는 것
                // news라는 이벤트를 안녕이라는 파라미터로 발생
                socket.emit('news','안녕');
            },3000);

        })
    };
    ```  
### 3) websocket.html 파일 수정  
    ```html
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="utf-8"/>
            <title>Web Socket</title>
        </head>
        <body>
            <div>
                검사를 이용해서 console과 network를 확인하세요.
            </div>
        </body>
        <script src="/socket.io/socket.io.js"/>
        <script>
            const socket = io.connect('http://localhost:8001', {
                path:'/socket.io',
                transports:['websocket']
            })

            socket.on('news', (data)=>{
                console.log(data);
                socket.emit('reply', 'Hello socket.io');
            })
        </script>
    </html>
    ```  

### +) 채팅
    서버가 접속한 클라이언트 정보를 모두 가지고 있다가 클라이언트가 메세지를 전송하면 그 메세지를 모든 클라이언트에게 전송해서 출력하면 채팅  
### +) 
    다른 곳에서 작성된 노드 프로젝트를 가져왔을 때에는 npm init만 해주면 OK  

## 6. 채팅 구현  
### 1) socket.js 파일을 수정
    ```javascript
    // socket.io 모듈 가져오기
    const SocketIO = require('socket.io');
    // 서버를 생성해서 다른 곳에서 사용할 수 있도록 설정
    module.exports = (server) => {
        //웹 소켓 서버 생성
        const io = SocketIO(server, {path:'/socket.io'});

        //클라이언트가 접속을 하면
        io.on('connection', (socket)=>{
            //클라이언트의 IP 확인
            const req = socket.request;
            const ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;
            
            console.log('새로운 클라이언트 접속:' + ip);
            // 접속을 해제했을 때 처리 - 타이머 종료
            socket.on('disconnect', () => {
                clearInterval(socket.interval);
            });

            // reply 이벤트가 발생했을 때 처리 - 사용자가 보내는 reply 이벤트
            // 원래 존재하는 이벤트가 아님
            socket.on('reply', (data) => {
                console.log(data);
            })

            // 타이머 생성 - 3초마다 강제로 news라는 이벤트를 발생
            socket.interval = setInterval(()=>{
                //emit 은 강제로 이벤트를 발생시키는 것입니다.
                //news 라는 이벤트를 안녕이라는 파라미터로 발생
                socket.emit('news', '안녕');
            }, 3000)

            // 클라이언트가 메세지를 전송하면
            socket.on('message',(data)=>{
                // 모든 클라이언트에게 메세지 전송
                io.sockets.emit('message', data);
            });
        })
    };
    ```
    
    * 클라이언트가 메세지를 전송한 것을 받아오는 부분만 추가

### 2) websocket.html 수정
    ```javascript
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8" />
            <title>웹 소켓</title>

            <!-- 모바일 웹 페이지 생성시 옵션 설정 -->
            <meta name="viewport" content="width=device-width, initial-scale=1"/>

            <!-- jquery mobile 설정 -->
            <link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css"/>
            <!-- jquery mobile은 기본적으로 single page application을 제작 내부 코드는 ajax로 동작-->
            <script src="https://code.jquery.com/jquery-1.8.2.min.js"></script>
            <script src="https://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js"></script>
            <!-- 소켓 설정 -->
            <script src="/socket.io/socket.io.js"></script>
            <script>
                $(document).ready(()=>{
                    // 웹 소켓 생성
                    var socket = io.connect('http://localhost:8001');

                    // 소켓 서버로부터 message 이벤트가 오면
                    socket.on('message', function(data){
                        // 받은 메세지를 이용해서 출력할 내용을 생성
                        var output = '';
                        output += '<li>';
                        output += '<h3>' + data.name + '</h3>';
                        output += '<p>'+data.message+'</p>';
                        output += '<p>'+data.date+'</p>';
                        output += '</li>';

                        // 메세지 출력
                        $(output).prependTo('#content'); // content앞에 가져다 붙이고
                        $('#content').listview('refresh'); // content 새로고침
                    });

                    // 버튼 눌렀을 때 메세지 전송
                    $('button').click(()=>{
                        socket.emit('message',{
                            name:$('#name').val(),
                            message:$('#message').val(),
                            date: new Date().toUTCString()
                        });
                        $('#message').val('');
                    })
                })
            </script>
        </head>
        <body>
            <!--data-role은 jquery mobile에서 사용하는 출력 영역의 역할을 설정하는 속성 -->
            <div data-role='page'>
                <div data-role="header">
                    <h1>socket.io chatting</h1>
                </div>
                <div data-role="content">
                    <h3>별명</h3>
                    <input id="name" />
                    <a data-role="button" href="#chatpage">채팅 시작</a>
                </div>
            </div>
            <div data-role="page" id="chatpage">
                <div data-role="header">
                    <h1>socket.io chatting</h1>
                </div>
                <div data-role="content">
                    <input id="message"/>
                    <button>전송</button>
                    <ul id="content" data-role="listview" data-inset="true">

                    </ul>
                </div>
            </div>
        </body>
    </html>
    ```  

## 7. 전자 칠판 구현  
### 1) 프로젝트에 public 디렉터리를 생성 - 정적인 데이터 저장이 목적  

### 2) websocket.html 파일 수정

### public 디렉터리에 images 디렉터리를 생성하고 blackboard.jpg 파일을 추가

### 4) public 디렉터리에 stylesheet 디렉터리를 생성하고 style.css파일을 추가한 후 작성
    ```css
    body{
        margin:0px;
    }

    #cv{
        width: 860px;
        height: 645px;
        background-color: url('../images/blackboard.jpg');
    }
    ```

### 5) websocket.html파일을 수정
    * 캔버스 아레에 메뉴와 버튼을 추가
    ```html
    <div class="menu"></div>
    <button id="clear"></button>
    ```

    * css도 수정
    ```css
    body{
        margin:0px;
    }

    #cv{
        width: 860px;
        height: 645px;
        background-image: url('../images/blackboard.jpg');
        float: left;
    }

    .menu{
        float: left;
    }
    button{
        width:100px;
        height: 50px;
    }
    ```  

### 6) public 디렉터리에 js 디렉터리를 생성하고 board.js 파일을 추가한후 작성  
    ```javascript
    var ctx;
    $(()=>{
        ctx = $('#cv').get(0).getContext('2d');
        // 기본 설정
        shape.setShape();

        $('#cv').bind('mousedown', draw.start);
        $('#cv').bind('mousemove', draw.move);
        $('#cv').bind('mouseup', draw.end);
        $('#clear').bind('click', draw.clear);
        
    })

    var shape = {
        color:'white',
        width:3,
        setShape : function(color,width){
            if(color != null){
                this.color = color;
            }
            if(width != null){
                this.width = width;
            }
            ctx.strokeStyle=this.color;
            ctx.lineWidth = this.width;
        }
    }

    var draw = {
        drawing : null,
        start : function(e){
            this.drawing=true;
            ctx.beginPath();
            ctx.moveTo(e.pageX, e.pageY);
        },
        move : function(e){
            if(this.drawing){
                ctx.lineTo(e.pageX,e.pageY);
                ctx.stroke();
            }
        },
        end : function(){
            this.drawing=false;
        },
        clear:function(){
            ctx.clearRect(0,0,cv.width,cv.height);
        }
    }
    ```  
### 7) 현재까지 작성한 후 실행해서 마우스로 선이 그려지고 버튼을 누르면 삭제되는지 확인

### 8) websocket.html 파일에 메뉴 영역에 색상변경, 펜 두께, 펜 모양 메뉴를 추가
    ```html
    <div class="menu">
        <button id="clear"></button>
        <fieldset>
            <legend>색상 변경</legend>
            <select id="pen_color">
                <option></option>
            </select>
        </fieldset>
        <fieldset>
            <legend>두께</legend>
            <select id="pen_width">
                
            </select>
        </fieldset>
        <fieldset id="pen_shape">
            <legend>모양</legend>
            
        </fieldset>
    </div>
    ```

### 9) style.css파일에 추가한 요소  style.css파일에 추가
    ```css
    .menu{
        float: left;
    }
    button{
        width:100px;
        height: 50px;
    }

    #cv_pen{
        width:100px;
        height: 50px;
        float: left;
        background-image:url('images/blackboard.jpg');
    }
    fieldset{
        width:100px;
        height: 60px;
        float: left;
    }

    #pen_shape{
        position: absolute;
        top:10px;
        left:700px;
        color:white;
    }
    ```  


### 10) board.js 파일에 초기화를 위한 코드를 추가 - $(function(){ '여기에 추가' })  
    ```javascript
       //색상 선택 select 설정
    for(var key in color_map){
        $('#pen_color').append('<option value=' + color_map[key].value + '>' + color_map[key].name + '</option>');
    }

    //두께 선택 select 설정
    for(var i = 1 ; i < 16 ; i++){
        $('#pen_width').append('<option value=' + i + '>' + i + '</option>');
    }
    ```  

### 11) board.js파일의 shape객체에 select 값이 변경되었을 때 호출될 함수 추가
    ```javascript
    //색 두께 변경 메서드
    change : function() {
        // jquery에서 select에서 선택된 항목을 찾을 때 사용
        var color = $('#pen_color option:selected').val();
        var width = $('#pen_width option:selected').val();
        shape.setShape(color, width);
    },
    ```  
 
### 12) board.js파일에 select 값이 변경될때 호출할 함수 설정  - $(function(){ '여기에 추가' })
    ```javascript
        //select에 이벤트 연결
    $('select').bind('change', shape.change);
    ```  

### 13) index.js 파일에 소켓 서버 설정 코드 추가
    ```javascript
    const io = require('socket.io')(server)
    ```  
    * 근데 이거 추가하면 socket 오류남. 주석처리
    * require할때 ('모듈이름')의 경우와 ('모듈이름')(객체나열)의 차이는 객체 지향 언어의 관점에서 보면
    앞의 경우는 Default Constructor를 호출해서 만든 객체를 리턴받는 것이고 뒤의 경우는 Default Constructor가 아닌 Constructor를 이용해서 생성한 객체를 리턴받는 경우입니다.  
### 14) websocket.html 파일에 socket.io.js파일을 사용할 수 있도록 링크 추가
    ```html
    <script src="/socket.io/socket.io.js"></script>
    ```
    * 스크립트의 순서가 중요!! 어떤 스크립트를 먼저 사용하는지에 맞춰서 추가

### 15) board.js파일에 전역변수 선언  
    ```javascript
    var socket;
    ```
    ctx 근처에 선언
### 16) board.js파일에 소켓 생성 코드를 추가 - $(function(){ '여기에 추가' })  
    ```javascript
    socket = io.connect('http://' + window.location.host);
    ```  

### 17) board.js 파일에 msg 객체 생성
    ```javascript
    var msg = {
        line:{
            send:function(type,x,y){
                socket.emit('linesend',{
                    'type': type,
                    'x': x,
                    'y': y,
                    'color': shape.color,
                    'width': shape.width
                });
            }
        }
    }
    ```

### 18) board.js 파일의 draw 객체 수정  
    ```javascript
    var draw = {
        drawing : null,
        start : function(e){
            this.drawing=true;
            ctx.beginPath();
            ctx.moveTo(e.pageX, e.pageY);
            msg.line.send('start', e.pageX,e.pageY);
        },
        move : function(e){
            if(this.drawing){
                ctx.lineTo(e.pageX,e.pageY);
                ctx.stroke();
                msg.line.send('move',e.pageX,e.pageY);
            }
        },
        end : function(){
            this.drawing=false;
            msg.line.send('end');
        },
        clear:function(){
            ctx.clearRect(0,0,cv.width,cv.height);
            shape.setShape(); // clear하면 현재 붓의 모양까지 날아가니깐 다시 설정
            msg.line.send('clear');
        }
    }
    ```  

### +) 통신 방식의 분류
    TCP & UDP
    Simplex (단방향 통신)  
    Half Duplex(반 이중 : 동시에 주고 받을 수는 없는 방식)  
    Full Duplex(전 이중 : 동시에 주고 받는 것이 가능한 방식)  

    Unicast : 1대1 통신 (PtoP)  
    Multicast : 1 대 group 통신  
    Broadcast : 방송, 모든 곳에서 전송  
    Anycast : 아무 곳이나 하나에게 전송  

### 19) socket.js 파일에 메세지를 받았을 때 메세지를전송하는 코드를 작성
    ```javascript
    // 클라이언트에서 linesend라는 이벤트가 발생하면 연결된 모든클라이언트에게 linesend_toclient 이벤트를 발생시킴
        socket.on('linesend',(data)=>{
            socket.broadcast.emit('linesend_toclient',data);
        });
    ```  

### 20) board.js

### 21) board.js


## 8. Node의 Socket 프로그래밍  
    * TCP와 UDP 통신 모두 가능  
### 1) TCP  
    * 연결형 프로토콜을 이용한 통신  
    * 요청하는 쪽에서 서버에게 요청을 하면 서버는 클라이언트에게 메타 데이터(데이터에 대한 정보)를 전송하고, 클라이언트는 이 메타데이터를 읽고 전송 가능 여부와 데이터에 대한 정보를 파악해서 서버에게 실제 데이터 전송을 요청.  
        서버는 실제 데이터를 전송하고, 전송받은 쪽에서는 이에 대한 응답을 합니다.  
    * 신뢰성이 높은 통신 방식이지만 실제 데이터 이외에도 다른 데이터를 많이 전송하기 때문에 효율은 떨어질 수 있습니다.  

### 2) UDP  
    * 비 연결형 통신으로 일방적으로 데이터를 전송하고 종료하는 방식  
    * 신뢰성이 낮지만 효율이 좋습니다.  
    * DNS(Domain Name Service), APNS(Apple Push Notification Service), FCM(Google의 Firebase Cloud Messaging)등은 UDP입니다.  

## 9. Java와 Node간의 UDP통신
### 1) Java 프로젝트를 생성해서 main을 가진 클래스 생성  
    ```java
    import java.net.DatagramPacket;
    import java.net.DatagramSocket;
    import java.net.InetAddress;

    public class UDPServer {

    public static void main(String[] args) {
        try {         
            //UDP 소켓 클래스 생성
            DatagramSocket dsoc = new DatagramSocket(4445);
            //데이터를 저장하기 위한 바이트 배열 생성
            byte [] data = new byte[65536];
            while(true) {
                System.out.println("받을 준비 완료");
                //데이터를 전송받기 위한 준비가 완료 됨 - 데이터를 전송받아야 다음으로 넘어감
                DatagramPacket dp = new DatagramPacket(data, data.length);
                dsoc.receive(dp);
                
                //보낸 곳 주소 확인
                System.out.println("보낸 곳:" + dp.getAddress().getHostAddress());
                //전송 받은 데이터 확인
                String utf8String = 
                    new String(new String(dp.getData()).trim().getBytes("UTF-8"));
                System.out.println("받은 메시지:" + utf8String);   
                
                String msg = "message";
                // 보낸 곳의 주소
                InetAddress address = dp.getAddress();
                int port = dp.getPort();
                dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address,port);
                
                // 데이터 전송
                dsoc.send(dp);
                
            }
        }catch(Exception e) {
            System.out.println("소켓 통신 에러");
            System.out.println(e.getLocalizedMessage());
        }
    }

    }
    ```  

### 2) node프로젝트에 udp.js파일을 생성하고 작성  
    ```javascript
        var http = require('http')
    // UDP 통신을 위한 객체 생성
    var client = require('dgram').createSocket('udp4');
    var message = '보낼 데이터 : node에서 보냄.';


    // 메세지를 받았을 때 수행할 내용 
    client.on('message', (msg, rinfo)=>{
        message = msg;
    });

    // 에러가 발생했을 때 수행할 내용
    client.on('error', (err)=>{
        console.log('error', err);
    })

    http.createServer((req,res)=>{
        var data = new Buffer.from('Client Buffer : 안녕안녕 노드로부터'); // 그냥 buffer쓰면 뭐라고 warning남
        client.send(data,0,data.length, 4445, 'localhost'); // java에서의 포트번호와 같아야함

        res.writeHead(200, {
            'Content-Type':'text/plain;charset=utf-8'
        })

        res.end(message);
    }).listen(1338,'127.0.0.1');
    console.log('Server 구동 중');
    ```

### 3) 서로 다른 프로그래밍 언어간에 문자열을 주고 받을 때는 인코딩에 주의를 해야합니다.