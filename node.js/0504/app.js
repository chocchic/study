//모듈 가져오기
const express = require('express')
//파일 경로를 사용하기 위한 모듈
const path = require('path')

//.env 파일의 내용을 process.env.속성 으로 사용할 수 있도록 설정
const dotenv = require('dotenv')
dotenv.config()

//express 인스턴스를 생성
const app = express()

//포트 설정
app.set('port', process.env.PORT)

//쿠키를 req.cookies 로 사용할 수 있도록 설정
const cookieParser = require('cookie-parser')
app.use(cookieParser(process.env.COOKIE_SECRET))

//정적인 파일의 요청 경로를 설정
app.use('/', express.static('public'))


//로그 기록을 위한 모듈 가져오기
const morgan = require('morgan')
const FileStreamRotator = require('file-stream-rotator')
const fs = require('fs')

//로그가 저장될 디렉토리 경로 생성
var logDirectory = path.join(__dirname, 'log')

//로그 기록을 위한 내용 - 일단위로 로그 파일 생성

//logDirectory가 있으면 다음으로 넘어가고 없으면 생성
fs.existsSync(logDirectory) || fs.mkdirSync(logDirectory)

//로그 생성 옵션
var accessLogStream = FileStreamRotator.getStream({
    date_format:'YYYYMMDD',
    filename:path.join(logDirectory, 'access-%DATE%.log'),
    frequency:'daily',
    verbose:false
})

app.use(morgan('combined', {stream:accessLogStream}))

//데이터를 압축해서 전송하기 위한 설정
var compression = require('compression');
app.use(compression());

//세션 사용을 위한 모듈 가져오기
const session = require('express-session')

const FileStore = require('session-file-store')(session)
app.use(
    session({
        secret:'keyboard',
        resave:false,
        saveUninitialized:true,
        store:new FileStore()
    })
)

app.get('/session', (req, res) => {
    if(!req.session.num){
        req.session.num = 1
    }else{
        req.session.num += 1
    }
    console.log(req.session.num)
    res.send('Views:' + req.session.num)
})

//파일 업로드 설정
const multer = require('multer')

//업로드할 디렉토리를 생성
//학원에서 연습할 때는 대부분 프로젝트 안에 파일을 업로드 하지만
//실무에서는 별도의 스토리지 서버(amazon 의 s3 서버 등)를 이용해서 업로드
try{
    fs.readdirSync('uploads')
}catch(error){
    console.error('업로드할 디렉토리가 없어서 생성')
    fs.mkdirSync('uploads')
}

const upload = multer({
    storage:multer.diskStorage({
        destination(req, file, done){
            //업로드할 디렉토리 설정
            done(null, 'uploads/')
        },
        filename(req, file, done){
            //파일명 생성
            const ext = path.extname(file.originalname);
            done(null, path.basename(file.originalname, ext) + 
                Date.now() + ext)
        }
    })
})

//사용자 요청 처리
/*
//별다른 경로 입력을 하지 않은 경우 현재 디렉토리의 index.html을 출력
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, '/index.html'))
})
*/

//라우팅 모듈화
//라우팅 내용이 작성된 모듈을 전부 읽어오기
//index.js 는 파일 이름을 작성할 필요가 없습니다.
const indexRouter = require('./routes');
const userRouter = require('./routes/user');
const boardRouter = require('./routes/board');

app.use('/', indexRouter)
app.use('/user', userRouter)
app.use('/board', boardRouter)

//하나의 파일을 업로드 하기 위한 요청 처리 구문
app.get('/singlefileupload', (req, res) => {
    res.sendFile(path.join(__dirname, 'singlefileupload.html'))
})

app.post('/singlefileupload', upload.single('image'), (req, res) => {
    //파일
    console.log(req.file);
    //파일이 아닌 파라미터
    console.log(req.body.title)

    res.send("OK")
})

//여러 개의 파일을 하나의 파라미터로 업로드 하기 위한 요청 처리 구문
app.get('/multifileupload', (req, res) => {
    res.sendFile(path.join(__dirname, 'multifileupload.html'))
})

app.post('/multifileupload', upload.array('image'), (req, res) => {
    console.log(req.files)
    console.log(req.body.title)
    res.send('OK')
})

//여러 개의 파일을 하나의 파라미터로 업로드 하기 위한 요청 처리 구문
app.get('/multiparamupload', (req, res) => {
    res.sendFile(path.join(__dirname, 'multiparamupload.html'))
})

app.post('/multiparamupload', upload.fields([{name:'image1'}, {name:'image2'}]),
(req, res) => {
    console.log(req.files)
    console.log(req.body.title)
    res.send("OK")
})


//서버 구동
app.listen(app.get('port'), () => {
    console.log(app.get('port'), '번 포트에서 대기 중')
})