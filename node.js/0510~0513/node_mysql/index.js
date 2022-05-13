/*

//데이터베이스 접속 확인
const mysql = require("mysql")

//접속 정보 생성 - 연결은 하지 않음
var connection = mysql.createConnection({
    host:'127.0.0.1',
    port:3306,
    user:'root',
    password:'1234',
    database:'node'
})

//데이터베이스 연결
connection.connect(function(err){
    if(err){
        console.log('mysql connection error');
        console.log(err)
    }
})

//console.log(connection)

//sql 실행 테스트


connection.query(
    'create table family(id int auto_increment, name varchar(20), primary key(id))engine=innodb default charset=utf8');
connection.query('insert into family(name) values (?)', '을지문덕');


connection.query('select * from family', (err, results, fields) => {
    //에러 객체에 내용이 있다면 에러 메시지를 출력하고 종료
    if(err){
        console.log(err);
        throw err;
    }

    //결과를 출력
    for(var idx = 0; idx < results.length; idx++){
        console.log(results[idx].id + ':' + results[idx].name);
    }
})

*/

//웹 서버를 만들기 위한 라이브러리
const express = require('express')

//.env 파일의 내용을 읽어서 process.env 의 속성으로 만들어 주는 설정
const dotenv = require("dotenv")
dotenv.config()

//서버 생성
const app = express()
app.set('port', process.env.PORT)

//일 단위 로그 파일 생성하기
const morgan = require('morgan')
const FileStreamRotator = require('file-stream-rotator')
const fs = require('fs')
const path = require('path')

//로그 파일을 저장할 디렉토리를 설정
//현재 디렉토리의 log 디렉토리
var logDirectory = path.join(__dirname, 'log')
//logDirectory가 없으면 생성
fs.existsSync(logDirectory) || fs.mkdirSync(logDirectory)

//로그 기록 옵션 설정
var accessLogStream = FileStreamRotator.getStream({
    date_format:'YYYYMMDD',
    filename: path.join(logDirectory, 'access-%DATE%.log'),
    frequency:'daily',
    verbose:false
})

//로그 기록 설정
app.use(morgan('combined', {stream:accessLogStream}))

//응답을 보낼 때 압축을 하기 위한 설정
//응답의 크기를 줄여서 트래픽을 줄이기 위한 목적
const compression = require('compression')
app.use(compression())

//post 방식의 파라미터를 읽을 수 있도록 설정
var bodyParser = require('body-parser')
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended:true
}));

//mysql 접속 옵션 생성
var options = {
    host:process.env.HOST,
    port:process.env.MYSQLPORT,
    user:process.env.USERID,
    password:process.env.PASSWORD,
    database:process.env.DATABASE
}

//세션을 MySQL에 저장
const cookieParser = require('cookie-parser')
const session = require('express-session')
const MySQLStore = require('express-mysql-session')(session)

app.use(
    session({
        secret:process.env.COOKIE_SECRET,
        resave:false,
        saveUninitialized:true,
        store:new MySQLStore(options)
    })
)

//파일 업로드 설정
const multer = require('multer')
//업로드할 디렉토리를 설정
try{
    fs.readdirSync('public/img')
}catch(error){
    console.error('img 디렉토리가 없어서 생성')
    fs.mkdirSync('public/img')
}

const upload = multer({
    storage:multer.diskStorage({
        destination(req, file, done){
            done(null, 'public/img/')
        },
        filename(req, file, done){
            const ext = path.extname(file.originalname);
            done(null, path.basename(file.originalname, ext) 
                + Date.now() + ext);
        }
    }),
    limits:{fileSize: 10 * 1024 * 1024}
})

//정적 파일(html, css, js, 기타 파일)의 위치 설정
//디렉토리가 만들어져 있어야 합니다.
app.use('/', express.static('public'))

//파일 다운로드를 위한 설정
var util = require('util')
var mime = require('mime')

//데이터베이스 접속
const mysql = require('mysql')
var connection = mysql.createConnection(options)
connection.connect(function(err){
    if(err){
        console.log('mysql connection error')
        console.log(err)
        throw err;
    }
})
// ORM을 사용한 DB접속
const {sequelize} = require('./models');
const {Item} = require('./models');
// 이 구문은 데이터베이스 접속 여부와는 아무런 상관이 없음
// 필요한 설정이 제대로 되어있는지만 확인 
// ORM은 
sequelize.sync({force:false}).then(()=>{
        console.log('DB 접속 성공')
    }).catch((err)=>{
        console.log(err)
    })
    
//기본 요청이 왔을 때 수행할 내용
app.get('/', (req, res, next) => {
    //res.send('MySQL Use')
    res.sendFile(path.join(__dirname,'index.html'))
})

// 전체 데이터 가져오기 요청을 처리하는 라우팅 함수
app.get('/item/all', async(req,res, next)=>{
    try{
        // 전체 데이터와 전체 데이터 개수를 가져와서 출력
        var list = await Item.findAll(); // 조회한 데이터 저장할 배열 원래 쿼리를 썼을 때 초기값 : []
        var count = await Item.count(); // 조회한 데이터 개수를 저장할 변수 : 0
        res.json({'count':count, 'list':list})
    }catch(err){
        console.log(err);
    }
    /*
    // 전체 데이터 가져오는 query
    connection.query('select * from goods order by itemid desc', (err, results,fields)=>{
        //에러가 발생했을 때
        if(err){
            throw err;
        }
        list = results;
        //데이터 개수 가져오기
        connection.query('select count(*) cnt from goods', (err, results, fields)=>{
            //에러가 발생했을 때
            if(err){
                throw err;
            }

            //읽어온 데이터 개수를 count에 저장
            count = results[0].cnt;
            //json출력
            res.json({'count': count, 'list':list})
        })
    })
    */
})

// 데이터 일부분 가져오기
app.get('/item/list', async(req, res,next) =>{
    // 파라미터 가져오기 : 일부분 가져오는 경우는 데이터개수와 페이지 번호
    // get 방식에서 pageno와 count 파라미터 가져오기
    const pageno = req.query.pageno;
    const count = req.query.count;

    // 일부분 가져오기 위한 변수를 선언
    var start = 0;
    var size = 5;

    if(count != undefined){
        size = parseInt(count);
    }
    if(pageno != undefined){
        start = (parseInt(pageno) -1) *size;
    }
    try{
        // start부터 size만큼 가져오기
        var list = await Item.findAll({
            offset:start,
            limit:size,
            order:[
                ['itemid','DESC']
            ]
        });
        var cnt = await Item.count();
        res.json({'count':cnt, 'list':list})
    }catch{
        console.log(Err)
    }
    /*
    var list = [];
    connection.query('select * from goods order by itemid limit ?, ?', [start, size], (err,results, fields) =>{
        list = results;

        connection.query('select count(*) cnt from goods', (err, results, fields) =>{
            res.json({'count':results[0].cnt, 'list':list})
        })
        
    })
    */
})
// 상세보기 - 데이터 1개와서 리턴
app.get('/item/detail', async(req, res,next) =>{
    // 1개의 데이터를 찾아오기 위한 primary key 값 가져오기
    var itemid = req.query.itemid;
    if(itemid == undefined){
        res.json({'result':false});
    }
    try{
        var item = await Item.findOne({
            where:{itemid:itemid}
        });
        res.json({'result':true, 'item':item});
    }catch(err){
        console.log(err);
        res.json({'result':false});
    }
    /*
    connection.query('select * from goods where itemid=?', itemid,(err, results, fields)=>{
        if(err){
            throw err;
        }
        if(results.length == 0){
            res.json({'result':false});
        }else{
            res.json({'result':true, 'item': results[0]})
        }
    })
    */
})
// 데이터 삽입 화면 출력 요청
app.get('/item/insert', (req, res, next)=>{
    // public 디렉토리에 있는 insert.html파일을 비동기적으로 읽어서 에러가 발생하면 에러 내용을 err에 저장
    // 그렇지 않으면 읽은 내용을 data에 저장
    fs.readFile('public/insert.html',(err,data)=>{
        // 문자열로 전송
        res.end(data); // utf-8을 안먹여주면 다 깨져서 보임
    })
})
function getNow(){
     // 삽입하는 날짜(현재 날짜 및 시간)를 생성
     var date = new Date();

     var year = date.getFullYear();
     var month = date.getMonth()+1;
     month = month >= 10 ? month : '0' + month;
     var day = date.getDate();
     day = day >= 10 ? month : '0'+day;
     var hour = date.getHours();
     hour = hour >= 10 ? month : '0'+hour;
     var min = date.getMinutes();
     min = min >= 10 ? month : '0'+min;
     var sec = date.getSeconds();
     sec = sec >= 10 ? month : '0'+sec;

     return year+'-'+month+'-'+day+' '+hour+':'+min+':'+sec;
}

// 데이터 삽입 요청
app.post('/item/insert',upload.single('pictureurl'), async(req,res, next)=>{
    // 클라이언트가 전송한 데이터를 가져오기
    const itemname = req.body.itemname;
    const description = req.body.description;
    const price = req.body.price;

    // 파일 파라미터 읽기
    var pictureurl;
    if(req.file){
        pictureurl = req.file.filename;
    }else{
        pictureurl = "default.jpg"
    }

    // 가장 큰 itmeid를 조회해서 다음 itmeid를 생성
    var itemid = 1;
    try{
        var x = await Item.max('itemid');
        itemid = x + 1;
    }catch(err){
        console.log(err);
    }
    /*
    connection.query('select max(itemid) maxid from goods',(err, results, fields)=>{
        if(err){
            throw err;
        }  
        var itemid;
        if(results.length > 0){
            itemid = results[0].maxid+1
        }else{
            itemid = 1;
        }
    */  
        var update = getNow();
        
        Item.create({
            itemid:itemid,
            itemname:itemname,
            price:price,
            description:description,
            pictureurl:pictureurl,
            updatedate:update
        })
        /* 
        connection.query('insert into goods(itemid, itemname, price, description, pictureurl, updatedate) values(?, ?, ?, ?, ?, ?)',
        [itemid, itemname, price, description, pictureurl,update], (err, results, fields)=>{
           if(results.affectedRows == 1){
        */
            // 데이터를 삽입한 시간을 update.txt에 기록
            const writeStream = fs.createWriteStream('./update.txt')
            writeStream.write(update)
            writeStream.end();
            res.json({'result':true})
        /*  }else{
                res.json({'result':false})
            }
        })
    })
    */
})
app.post('/item/delete', async(req, res, next)=>{
    // 파라미터 읽어오기 : 삭제는 기본키만을 읽어옵니다.
    // 클라이언트에서는 itemid라는 이름으로 itemid를 post방식으로 전송
    const itemid = req.body.itemid;
    // 삭제하는 날짜(현재 날짜 및 시간)을 생성
    var update = getNow();
    try{
        var item = await Item.destroy({
            where:{itemid:itemid}
        });
        // 데이터를 삭제한 시간을 update.txt에 기록
        const writeStream = fs.createWriteStream('./update.txt')
        writeStream.write(update);
        writeStream.end();
        res.json({"result":true});
    }catch(err){
        console.log(err);
        res.json("result", false);
    }
    
    /*
    connection.query('delete from goods where itemid = ?', [itemid], (err,results,next)=>{
        // 에러 내용확인
        if(err){
            console.log(err)
        }
        if(results.affectedRows >= 0){
            // 데이터를 삭제한 시간을 update.txt에 기록
            const writeStream = fs.createWriteStream('./update.txt')
            writeStream.write(update);
            writeStream.end();
            res.json({"result":true});
        }else{
            res.json("result", false);
        }
    })
    */
})

// 수정화면 요청 처리
app.get("/item/update", (req,res,next)=>{
    fs.readFile('public/update.html', (err,data)=>{
        res.end(data);
    })
})

// 수정 요청 처리
app.post("/item/update", upload.single('pictureurl'), async(req,res,next)=>{
    // 파라미터 읽어오기
    const itemid= req.body.itemid;
    const itemname = req.body.itemname;
    const description = req.body.description;
    const price = req.body.price;
    var pictureurl;
    if(req.file){
        pictureurl = req.file.filename;
    }else{
        pictureurl = req.body.oldpictureurl;
    }
    var update = getNow();
    try{
        var item = await Item.update({
            itemname:itemname,
            description:description,
            price:price,
            pictureurl:pictureurl,
            updatedate:update,
        },{where:{itemid:itemid}});
        // 데이터를 수정한 시간을 update.txt에 기록
        const writeStream = fs.createWriteStream('./update.txt')
        writeStream.write(update);
        writeStream.end();
        res.json({"result":true});
    }catch(err){
        res.json("result", false);
    }
    /*
    connection.query('update goods set itemname=?, price=?, description=?, pictureurl=?, updatedate=? where itemid=?', 
    [itemname, price, description, pictureurl, update, itemid], (err,results,next)=>{
        if(err) console.log(err);
        if(results.affectedRows >= 0){
            // 데이터를 수정한 시간을 update.txt에 기록
            const writeStream = fs.createWriteStream('./update.txt')
            writeStream.write(update);
            writeStream.end();
            res.json({"result":true});
        }else{
            res.json("result", false);
        }
    })
    */
})

// 마지막 업데이트 한 시간을 전송
app.get('/item/date', (req, res, next)=>{
    fs.readFile('.update.txt', (err, data)=>{
        res.json({'data':data.toString()});
    })
})

// 이미지 다운로드 구현 
// 최근에 클라이언트에서 서버로 데이터 1개를 보내야하는 경웅
// 파라미터 형태보다는 url 마지막에 작성하는 경우가 많습니다.
app.get('/img/:fileid', (req,res,next)=>{
    var fileid = req.params.fileid;

    // 다운받을 파일 경로를 설정 (절대경로로)
    var file ='C:\\Users\\SAMSUNG\\Desktop\\java\\chocchic.github.io\\node.js\\0510~0512\\node_mysql\\public\\img' + fileid;
    // 타입 설정
    var mimetype = mime.lookup(fileid);

    res.setHeader('Content-disposition', 'attachment; filename='+fileid);
    res.setHeader('Content-type', mimetype);
    var filestream = fs.createReadStream(file);
    filestream.pipe(res);
})

app.listen(app.get('port'), ()=>{
    console.log(app.get('port'), '에서 서버 대기 중');
});

