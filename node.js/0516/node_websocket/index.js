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