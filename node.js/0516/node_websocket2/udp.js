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
    var data = new Buffer.from('Client Buffer : 안녕안녕 노드로부터');
    client.send(data,0,data.length, 4445, 'localhost'); // java에서의 포트번호와 같아야함

    res.writeHead(200, {
        'Content-Type':'text/plain;charset=utf-8'
    })

    res.end(message);
}).listen(1338,'127.0.0.1');
console.log('Server 구동 중');