const express = require('express')
const morgan = require('morgan')
const path=require('path')
const multer = require('multer')
const fs = require('fs')

const app = express()
app.set('port', 7000)
app.use(morgan('dev'))

var bodyParser = require('body-parser')
app.use(bodyParser.json())

app.use(bodyParser.urlencoded({
    extended:true
}))

// 파일 다운로드
var util = require('util')
var mime = require('mime')
//img 디렉터리가 없으면 생성
try{
    fs.readdirSync('img')
}catch(err){
    console.err('img 디렉토리가 없어서 생성')
    fs.mkdirSync('img')
}

// 이미지 업로드 설정
const upload = multer({
    storage:multer.diskStorage({
        destination(req,file,done){
            done(null,'img/')
        },
        filename(req,file,done){
            const ext = path.extname(file.originalname)
            done(null, path.basename(file.originalname, ext)+Date.now()+ext)
        }
    }),
    limits:{fileSize:10*10*1024}
})
// 뷰 템블릿 엔진 : Spring의 Controller 또는 Router가 넘겨준 데이터를 출력하기 위한 뷰
// html에 서버의 데이터를 출력하기 위한 것
app.set('view engine', 'html')
app.engine('html', require('ejs').renderFile)

// MongoDB 연결
var MongoClient = require('mongodb').MongoClient
var db;
var databaseUrl = 'mongodb://localhost:27017/'

app.use((err,req,res,next)=>{
    console.error(err)
    res.status(500).send(err.message)
})

// 데이터 전체 가져오기
app.get('/item/all',(req,res,next)=>{
    MongoClient.connect(databaseUrl,(err,database)=>{
        db = database.db('node')
        db.collection('item').find().sort({'itemid':-1}).toArray((err, items)=>{
            res.json({'count':items.length,'list':items})
        });
    })
})

// 데이터 일부분 가져오기
app.get('/item/list',(req,res,next)=>{
    var start = 0;
    var cnt = 3;
    // skip은 시작하는 데이터의 인덱스와 유사
    // limit은 가져올 데이터 개수르 설정
    MongoClient.connect(databaseUrl,(err,database)=>{
        db = database.db('node');
        db.collection('item').find().sort({'itemid':-1}).skip(start).limit(cnt).toArray((err, items)=>{
            res.json({'count':items.length,'list':items})
        });
    })
})

// 데이터 1 가져오기
app.get('/item/detail',(req,res,next)=>{
    var itemid = 1;
    MongoClient.connect(databaseUrl,(err,database)=>{
        db = database.db('node');
        db.collection('item').findOne({'itemid':itemid}, (err, item)=>{
            res.json({'result':true,'item':item})
        });
    })
})

app.get('/item/insert', (req,res,next)=>{
    MongoClient.connect(databaseUrl,(err,database)=>{
        db = database.db('node');
        // 가장 큰 itemid를 찾아서 +1을 해서 itemid 생성
        db.collection('item').find({},{projection:{_id:0, itemid:1}}).sort({itemid:-1}).limit(1).toArray((err,result)=>{
            var itemid = 1;
            if(result[0] != null){
                itemid = itemid+1;
            }
            db.collection('item').insert({"itemid":itemid, "itemname":"사과", 
            "description":"달고 맛있어요. 제가 제일 좋아하는 과일입니다.", "price":5000, 
            "pictureurl":"apple.jpg"},(err,result)=>{
                res.json({"result":true});
            });
        });

    })
})

app.get('/item/delete', (req,res,next)=>{
    MongoClient.connect(databaseUrl,(err,database)=>{
        db = database.db('node');
        db.collection('item').deleteOne({itemid:2},(err,result)=>{
            res.json({"result":true});
        });
    });
})

app.get('/item/update', (req,res,next)=>{
    MongoClient.connect(databaseUrl,(err,database)=>{
        db = database.db('node');
        db.collection('item').updateOne({itemid:2},{$set: {itemname:"유기농 사과"}}, { upsert: true },(err,result)=>{
            res.json({"result": true});
        });
    });
})
// upsert : true -> update + insert , 없으면 insert기능 수행

app.listen(app.get('port'),()=>{
    console.log(app.get('port'),'번 포트에서 대기중')
})