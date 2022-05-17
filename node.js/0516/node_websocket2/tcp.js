var http = require('http');
// TCP 통신을 위한 객체 생성
var client = require('net').Socket();
var fromServer = '서버가 보냅니다.!!';

// 서버에 접속
client.connect(9999, function() {
    console.log('Connected succesfully!!');
});

// 서버에서 데이터가 전송 된 경우
client.on('data', function(data) {
    console.log('Recieve data: ' + data);
    fromServer = data;
});

// 연결이 해제된 경우
client.on('close', function() {
    console.log('Connection closed');
    // Close the client socket completely
    client.destroy();
})

http.createServer((req,res)=>{
    client.write('안녕 방가',()=>{
        console.log('보내기 성공');
    })

    res.writeHead(200, {
        'Content-Type':'text/plain;charset=utf-8'
    })
    res.end(fromServer);
}).listen(1338,'127.0.0.1');
console.log('Server 구동 중');