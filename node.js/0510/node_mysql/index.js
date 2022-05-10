// 데이터베이스 접속 확인
const mysql=require("mysql")

// 접속 정보 생성 - 연결은 하지 않음
var connection = mysql.createConnection({
    host : '127.0.0.1',
    port : 3306,
    user : 'root',
    password : '1234',
    database : 'node'
})

// 데이터베이스 연결
connection.connect(function(err){
    if(err){
        console.log('mysql connection error');
        console.log(err)
    }
})

//console.log(connection)

// sql 실행 테스트
/*
connection.query('create table family(id int auto_increment, name varchar(20), primary key KeyboardEvent(id))engine=innodb')
connection.query('insert into family(name) values(?)', '을지문덕')
*/

/*
connection.query('select * from family', (err, results, fields)=>{
    // 에러객체에 내용이 있다면 에러 메시지를 출력하고 종료
    if(err){
        console.log(err);
        throw err;
    }

    // 결과를 출력
    for(var idx = 0; idx<results.length; idx++){
        console.log(results[idx].id + ':' + results[idx].name)
    }
})
*/

// 웹서버를 만들기 위한 라이브러리
const express = require('express')
//.env파일의 내용을 읽어서 precss.env의 속성으로 만들어주는 설정
const dotenv = require('dotenv')
dotenv.config()

// 서버 생성
const app = express()
app.set('port', process.env.PORT)


const morgan = require('morgan')
const compression = require('compression')
const path = require('path')
const mysql = require('mysql')
const cp = require('cookie-parser')
const session = require('session')
const multer = require('multer')
const dotenv = require('dotenv')
