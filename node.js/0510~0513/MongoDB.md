# Mongo DB
## 1. NoSQL(Not Only SQL)  
    * SQL이 데이터베이스의 전부는 아니다.  
    * 느슨한 트랜잭션을 지원하고(ACID를 완전하게 보장하지 않음) 뛰어난 확장성과 성능을 가짐  

### 1) 종류  
    * Key Value DB  
    * Wide Columnar Store : Google의 Big Table이나 HBase, Cassandra 등이 대표적  
    * Document DB : 데이터를 하나의 문서로 취급하는 데이터베이스로 MongoDB가 대표적  
    * Graph DB  

## 2. MongoDB  
    * 가장 많이 사용되는 NoSQL 중 하나  
    * 내부 엔진은 C++로 만들어져 있고 인터페이스는 JavaScript를 사용합니다.  

### +) 
    중앙집중식 서버 <-> 모든 클라이언트가 전부 물려있는 형태  

    데이터1(1-10)   애플리케이션 서버 1  
                                        미들웨어 <-----------> 클라이언트  
    데이터2(6-15)   애플리케이션 서버 2  

## 3. 설치
### 1) mongodb server 설치 : 터미널에서 사용할 수 있는 접속도구도 같이 설치  
    * Window : MongoDB 사이트에서 다운받아 설치
    * Mac
        brew tap mongodb/brew
        brew install mongodb-community  
### 2) compass  
    * mongodb gUI 접속도구  
    * https://www.mongodb.com/try/download/compass에서 다운로드  

## 4. 서버 실행과 중지
### 1) Windows의 경우는 서비스(Service)에서 가능  

### 2) Mac의 경우는 brew services start mongodb-community로 시작하고 brew services stop mongodb-community로 중지  

### 3) 서버는 기본적으로 27017번 포트를 이용해서 실행합니다.  


## 5. 클라이언트 접속
### 1) 터미널 : mongo  
    * 자신이 설치한 mongodb 경로의 server\자신이 설치한버전\bin까지 들어가서 mongo를 실행해야 함
        ex ) > cd C:\Program Files\MongoDB\Server\5.0\bin
             > mongo
### 2) 접속 툴에서 사용가능  

## 6. MongoDB 구조
    DB > 컬렉션 > document

    * namespace : DB.컬렉션의 형태로 시작  

## 7. 비교  
    Database -> Database  
    Table(Relation) -> Collection  
    Row(Tuble, Record) -> Document  
    Column(Attribute, Field) -> Field  
    Index -> Index  
    Join -> Embedding & Linking  
    Select 구문의 결과로 관계형 데이터베이스는 Row의 집합을 리턴하지만 MongoDB는 Cursor 리턴  

## 8. MongoDB 사용 구조  
    Application <-> Driver <-> MongoDB 네트워크 프로토콜  <-> 스토리지 엔진 <-> 저장장치  

## 9. MongoDB JSONDocument(BSON - Binary jSON)  
### 1) 장점  
    * Light Weight(경량)  
    * Traveragle : 조회 속도가 빠름  
    * Efficient : 기본 데이터 타입으로 C언어의 Primitive Type을 사용하기 때문

### 2) 객체  
    { 속성 이름 : 값, 속성이름 : 값 ... }  
    * 속성 이름의 순서가 있음  
    * 동일한 속성 이름이 있고, 같은 값을 갖더라도 순서가 다르면 다른 문서  

### 3) 배열  
    [데이터, 데이터, ... ]  
    * 객체의 값으로 배열이나 객체 가능하고 배열의 데이터로 객체나 다른 배열 가능  

## 10. 관리 프로그램 : MongoDB Ops Manager  

## 11. 가져오기와 내보내기  
### 1) 가져오기 : 터미널에서 가져오기
    mongodbimport -db 데이터베이스이름 -collection 저장할 컬렉션 이름 < 파일경로  
### 2) 내보내기(백업) : 터미널에서 수행
    mongodbexport -db 데이터베이스이름 -collection 컬렉션 이름 --out 파일경로  

## 12. 작업 단위  
### 1) 데이터베이스  생성 및 사용  
    * 현재 데이터베이스 이름 확인  
        show dbs;
    * 데이터베이스 생성
        use 데이터베이스이름;  
        => 데이터 베이스가 없으면 생성됨  
    * 현재 사용중인 데이터베이스 확인  
        db  
    * 현재 사용중인 데이터베이스 삭제  
        db.dropDatabase()  
    
    * 데이터베이스 생성과 확인까지 되지만 실제로 데이터가 삽입되기 전까지 실제 생성된 것이 아님  
        show dbs; 해도 node가 보이지 않음  
    
        db.mycollection.insertOne({name:1}) // 데이터 1개 삽입  
        show dbs; // node 생성 완료  

### 2) Collection   
    **Document의 모임**  

    * 생성
        db.createCollection(컬렉션 이름)  
        컬렉션을 만들지 않고 사용하면 컬렉션을 만들어 줍니다.  

    * 현재 데이터베이스 내의 컬렉션 확인  
        show collections  

    * 컬렉션 제거  
        db.컬렉션이름.drop()  

    * 컬렉션 이름 변경  
        db.collection.renameCollection(변경할 이름)  
    
    * capped collection  
        처음 정해진 크기를 초과하게 되면 자동으로 가장 오래된 데이터를 삭제하는 컬렉션  
        db.createCollection(컬렉션이름, {capped:true, size:크기})  
### 3) 관리 툴 사용
    db.enableFreeMonitoring()을 실행하고 나오는 URL을 웹 브라우저에 복사해서 실행  

### 4) 데이터베이스 상태 조회  
    * 현재 데이터베이스의 Collection 정보 확인 : db.getCollectionInfos()  
    * 호스트, 프로세스 아이디, Lock정도 확인 : db.serverStatus()  
    * 데이터베이스 정보 확인 : db.status()  

### 5) CappedCollection 동작확인  
    // CappedCollection 생성  
    db.createCollection('cappedCollection',{capped:true, size:10000})  

    // 데이터 1개 삽입  
    db.cappedCollection.insertOne({x:1})  

    // 데이터 확인  
    db.cappedCollection.find()  

    // 컬렉션 정보 확인  
    db.cappedCollection.stats()  

    // 여러 개의 데이터 삽입  
    for(var i = 0; i < 1000; i++){ db.cappedCollection.insertOne({x:i}) }  

    // 데이터 확인 - CappedCollection은 자신의 사이즈보다 더 많은 데이터를 저장하고자 하면 이전 데이터를 삭제 : 647 ~ 999 까지 저장  
    db.cappedCollection.find()  

## 13. 도큐먼트 삽입  
### 1) 함수  
    * insert : deprecated  
    * save : 데이터가 없으면 추가하고 있으면 수정  
    * insertOne, insertMany  

### 2) MongoDB에서의 데이터 삽입  
    * Collection에서는 _id라고 하는 ObjectId 타입의 기본키가 자동으로 생성됨  
    * 삽입할 때 _id를 설정하지 않으면 시스템이 자동으로 _id 값을 생성해서 삽입을 합니다.  
    * ObjectId를 생성해서 대입하면 insert는 중복된 데이터가 있으면 에러가 발생하지만 save는 수정을 합니다.  

### 3) 데이터 삽입  
    db.users.insert({name:"adam",age:25,gender:"man"})  
    db.users.find()  

# Node와 MongoDB 연동
## 1. MongoDB에서 샘플 데이터 생성  
    db.item.insert({'itemid':1, 'itemname':'레몬', 'price':500, 'description':'비타민 C가 풍부', 'pictureurl' : 'lemon.jpg'});  

## 2. 노드 프로젝트 생성  
    node_mongo로 생성  

## 3. 프로젝트에 디렉터리와 텍스트 파일 생성  
    * 이미지를 저장할 img 디렉터리를 생성하고 샘플 이미지 추가  
    * 뷰 파일을 저장할 views 디렉터리  
    * 수정된 날짜 및 시간을 저장할 update.txt 파일을 생성  
    * 필요한 라이브러리 설치  
        npm i express morgan multer mongodb ejs  

        npm i --save-dev nodemon  
    * package.json 수정
        매번 start추가하는 그것 그대로 

## 4. index.js 작성
    ```javascript
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

    app.listen(app.get('port'),()=>{
        console.log(app.get('port'),'번 포트에서 대기중')
    })
    ```  

## 5. index.js파일에 전체 데이터 가져오기 요청을 생성
    ```javascript
    // 데이터 전체 가져오기
    app.get('/item/all',(req,res,next)=>{
        MongoClient.connect(databaseUrl,(err,database)=>{
            db = database.db('node')
            db.collection('item').find().sort({'itemid':-1}).toArray((err, items)=>{
                res.json({'count':items.length,'list':items})
            });
        })
    })
    ```

## 6. index.js파일에 데이터 일부분 가져오기
    ```javascript
    app.get('/item/list',(req,res,next)=>{
    var start = 0;
    var cnt = 3;
    MongoClient.connect(databaseUrl,(err,database)=>{
            db = database.db('node');
            db.collection('item').find().sort({'itemid':-1}).skip(start).limit(cnt).toArray((err, items)=>{
                res.json({'count':items.length,'list':items})
            });
        })
    })
    ```  

## 7. index.js파일에 id=1인 데이터 하나 가져오기
    ```javascript
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
    ```

## 8. index.js파일에 id = 2인 데이터 삭제
    ```javascript
    app.get('/item/delete', (req,res,next)=>{
        MongoClient.connect(databaseUrl,(err,database)=>{
            db = database.db('node');
            db.collection('item').deleteOne({itemid:2},(err,result)=>{
                res.json({"result":true});
            });
        });
    });
    ```  

## 9. index.js 파일에 id=2인 데이터의 itemname을 유기농 사과로 update
    * upsert : true -> update + insert , 없으면 insert기능 수행
    ```javascript
    app.get('/item/update', (req,res,next)=>{
        MongoClient.connect(databaseUrl,(err,database)=>{
            db = database.db('node');
            db.collection('item').updateOne({itemid:2},{$set: {itemname:"유기농 사과"}}, { upsert: true },(err,result)=>{
                res.json({"result": true});
            });
        });
    });
    ```