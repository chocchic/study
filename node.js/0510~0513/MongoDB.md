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
