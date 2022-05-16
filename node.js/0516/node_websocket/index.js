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

dotenv.config();

const webSocket = require('./socket');
//파일 이름을 생략하면 index.js 입니다.
const indexRouter = require('./routes')

const app = express();
app.set('port', 8001);
//뷰 템플릿(서버의 데이터를 출력할 수 있는 html 파일) 설정
app.set('view engine', "html");
nunjucks.configure('views', {
    express:app,
    watch:true
});

app.use(morgan('dev'));

app.use(express.static(path.join(__dirname, 'public')));

app.use(express.json());
app.use(express.urlencoded({extended:false}))

app.use(cookieParser('websocket'));

app.use(session({
    resave:false,
    saveUninitialized:false,
    secret:'websocket',
    cookie:{
        httpOnly:true,
        secure:false
    }
}))

// /로 시작하는 요청은 indexRouter 가 처리
app.use('/', indexRouter);

const server = app.listen(app.get('port'), () => {
    console.log(app.get('port'), '번 포트에서 대기 중');
})

webSocket(server);