const http = require('http')
//파일을 비동기적으로 읽기 위한 모듈 가져오기
const fs = require('fs').promises

http.createServer(async(req, res) => {
    try{
        const data = await
        //파일의 내용을 읽어서 data에 저장
        fs.readFile('./main.html');
        //res.writeHead(200, {'Content-Type':'text/html; charset=utf-8'})
        //쿠키 저장
        res.writeHead(200, {'Set-Cookie':'mycookie=value'})
        console.log("쿠키:" + req.headers.cookie)
        //data 출력
        res.end(data)

    }catch(err){

    }
}).listen(9000, ()=>{
    console.log('서버 대기 중')
})