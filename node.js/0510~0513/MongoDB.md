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
