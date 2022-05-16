// ws 모듈을이용한 web socket
/*
const WebSocket = require('ws');
module.exports = (server)=>{
    // 웹 소켓 서버 생성
    const wss = new WebSocket.Server({server});

    // 클라이언트가 접속을 하면
    wss.on('connection', (ws, req)=>{
        // 클라이언트의 IP 확인
        const ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;
        console.log('새로운 클라이언트 접속 : ' + ip);

        ws.on('message',(msg)=>{
            console.log('클라이언트에게 받은 메세지 : ', msg);
        });

        ws.on('ip',()=>{
            console.log('클라이언트에게 받은 메세지 : ');
            // 타이머 종료
            clearInterval(ws.interval);
        });

        // 타이머를 이용해서 클라이언트에게 주기적으로 메세지를 전송
        ws.interval = setInterval(()=>{
            if(ws.readyState === ws.OPEN){
                ws.send('서버에서 클라이언트에게 메세지를 전송합니다.');
            }
        }, 3000);
    })
};
*/

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

        socket.interval = setInterval(()=>{
            // emit은 강제로 이벤트를 발생시키는 것
            // news라는 이벤트를 안녕이라는 파라미터로 발생
            socket.emit('news','안녕');
        },3000);

    })
};