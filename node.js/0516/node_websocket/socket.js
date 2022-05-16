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
        })
    })
};