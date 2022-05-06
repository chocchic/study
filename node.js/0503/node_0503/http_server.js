const http = require('http')

http.createServer((req, res) => {
    //요청이 왔을 때 수행할 내용
    res.writeHead(200, {'Content-Type':'text/html; charset=utf-8'})
    res.write('<h2>처음 만든 노드 웹 서버</h2>')
    res.end('<p>Hello</p>')
}).listen(9000, () => {
    console.log('서버 대기 중')
});