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