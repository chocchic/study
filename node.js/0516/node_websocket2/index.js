//websocket 모듈을 이용한 웹 소켓 구현 - echo
/*
var WebSocketServer = require('websocket').server;
var http = require('http');
var fs = require('fs');

var server = http.createServer((req, res) => {
    if(req.url == "/"){
        res.writeHead(200, {'Content-Type':'text/html'});
        res.end('Web Socket')
    }else if(req.url == "/index"){
        fs.readFile("index.html", (error, data) => {
            res.writeHead(200, {'Content-Type':'text/html;charset=utf-8'});
            res.end(data);
        })
    }


})

//서버 구동
server.listen(8000, function(){
    console.log('Server is listening on port 8000');
})

//웹 소켓 서버 구현
wsServer = new WebSocketServer({
    httpServer:server,
    autoAcceptConnections:false
});

//클라이언트에서 연결 요청이 오면
wsServer.on('request', (request) => {
    //클라이언트 와 example-echo 라는 이름으로 연결
    var connection = request.accept('example-echo', request.origin);

    //연결된 클라이언트에서 메시지가 오면
    connection.on('message', (message) => {
        //텍스트 데이터라면
        if(message.type === 'utf8'){
            //메시지 출력
            console.log('받은 메시지:' + message.utf8Data);
            //받은 메시지를 클라이언트에게 전송
            connection.sendUTF(message.utf8Data);
        }
        //일반 파일 데이터라면
        else if(message.type == 'binary'){
            connection.sendUTF(message.binaryData);
        }

        connection.on('close', (reasonCode, description) => {
            console.log('Peer ' + connection.remoteAddress + 
            ' disconnected.')
        })
    });
})

*/

//ws 모듈을 이용한 웹 소켓 구현 - 서버에서 일정한 주기를 가지고 메시지 전송
const express = require('express');
const path = require('path');
const morgan = require('morgan');
const cookieParser = require('cookie-parser');
const session = require('express-session');
const nunjucks = require('nunjucks');
const dotenv = require('dotenv');

// .env파일을읽어서 process.env의 속성으로 등록
dotenv.config();

// 현재 디렉토리의 socket.js파일을 읽어서 exports된 내용을 저장
const webSocket = require('./socket');
//파일 이름을 생략하면 index.js 입니다.
//routes/index.js
const indexRouter = require('./routes')

// 노드에서 자신의 프로젝트에 있는 js파일을 require할 때 주의할 점은 디렉터리 안에 있는 index.js를 가져올 떄는 파일 이름을 작성하지 않아도 됨.

// express 웹 서버 생성
const app = express();

// 서버 포트 설정
app.set('port', process.env.PORT || 8001);

//뷰 템플릿(서버의 데이터를 출력할 수 있는 html 파일) 설정
app.set('view engine', "html");
nunjucks.configure('views', {
    express:app,
    watch:true
});

// 로그를 기록 - 메모리에 기록
// 서버 프로그래밍에서는 로그를 반 영구적인 장치 또는 다른 컴퓨터에 전송하는 것이 매우 중요
app.use(morgan('dev'));

// 정적 파일의 경로 설정
// 정적 파일의 내용을 프로젝트가 실행될 때 메모리에 적재해서 사용
app.use(express.static(path.join(__dirname, 'public')));

// post 방식으로 전송된 파라미터를 읽기위한 설정
app.use(express.json());
app.use(express.urlencoded({extended:false}))

// 쿠키를 사용하도록 설정
// 문자열을 이용해서 암호화를 수행하기 위해 문자열을 포함
app.use(cookieParser(process.env.COOKIE_SECRET));

// 세션을 사용하도록 설정 - 메모리에 세션을 저장
// 클라이언트의 개수가 많아지거나 클러이언트의 정보를 많이 저장하는 경우는 데이터베이스나 파일에 저장하고 사용하는 것을 고려
app.use(session({
    resave:false,
    saveUninitialized:false,
    secret: process.env.COOKIE_SECRET,
    cookie:{
        httpOnly:true,
        secure:false
    }
}))
// 라우팅 모듈화
// routing은 클라이언트의 요청 주소를 파악해서 처리하는 코드로 분기하는 것
// 웹 서비스의 규모가 ㅋ커지거나 서로 다른 도메인 작업을 한다면 가독성을 높이기 위해서 별도의 모듈로 작성하는 것을 권장

// /로 시작하는 요청은 indexRouter 가 처리
app.use('/', indexRouter);

// 서버 실행
const server = app.listen(app.get('port'), () => {
    console.log(app.get('port'), '번 포트에서 대기 중');
})

// 웹 소켓 생성
webSocket(server);

// socket.io 파일에 있는 exports된 함수 중에서 express 서버를 매개변수로 받는 함수를 호출
// exports된 내용이 하나의 객체일 때는 ()를 추가할 필요가 없는데, 객체가 아니고 함수일 때는 매개변수를 대입해주어야 합니다.
const io = require('socket.io')(server)
