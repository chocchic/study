//ws 모듈을 이용한 웹 소켓
/*
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
        ws.interval = setInterval(() => {
            if(ws.readyState === ws.OPEN){
                ws.send('서버에서 클라이언트에게 메시지를 전송합니다.');
            }
        }, 3000)

    })
};
*/
/*
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

        socket.on('disconnect', () => {
            clearInterval(socket.interval);
        });

        socket.on('reply', (data) => {
            console.log(data);
        })

        socket.interval = setInterval(()=>{
            //emit 은 강제로 이벤트를 발생시키는 것입니다.
            //news 라는 이벤트를 안녕이라는 파라미터로 발생
            socket.emit('news', '안녕');
        }, 3000)

    })
};
*/

// socket.io 모듈 가져오기
const SocketIO = require('socket.io');
// 서버를 생성해서 다른 곳에서 사용할 수 있도록 설정
module.exports = (server) => {
    //웹 소켓 서버 생성
    const io = SocketIO(server, {path:'/socket.io', transport:['websocket']});

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

        // 클라이언트에서 linesend라는 이벤트가 발생하면 연결된 모든클라이언트에게 linesend_toclient 이벤트를 발생시킴
        socket.on('linesend',(data)=>{
            socket.broadcast.emit('linesend_toclient',data);
        });
    })
};