# Repository
  저장소  
## 1. 종류  
  * File System : 일반 File System과 Haddop과 같은 분산 파일 시스템  
  * DataBase  
    * 로컬 데이터베이스 : 로컬에서만 접속이 가능한 데이터베이스로 대표적인 Local Database가 SQLite(Android, iOS, Web Brower에는 내장되어 있음)이고 모바일에서 자주 사용하는 데이터를 cacheing할 목적으로 많이 사용  
    * 서버 데이터베이스 : 여러 사용자가 데이터를 공유할 목적으로 로컬 컴퓨터 이외의 곳에 데이터를 보관하는 방식  
      - RDBMS(관계형 DB) : 테이블을 이용해서 데이터를 저장하는 방식으로 Oracle, MySQL(MariaDB), MSSQL, Tibero, HANA DB, Postgre SQL 등  
        공공기관에서는 국산을 선호하기 때문에 Tibero도 많이 사용.  
      - NoSQL : 테이블 구조를 이용하지 않는 DB로 여러 종류가 있는데 그 중에서 대중에게 가장 많이 알려진 NoSQL이 MongoDB  
        스마트폰 애플리케이션 제작에는 google의 FireBase를 많이 사용함.  
        노드 진영에서는 Mongo DB를 많이 사용
        
      - 최근에는 클라우드 기반의 DB도 많이 사용

## 2. 연동 방법
### 1) 언어 차원에서 제공하는 기본 라이브러리 이용  

### 2) framework 이용  
  SQL Mapper Framework : SQL과 로직을 처리하는 코드를 분리해서 사용하는 방식으로 MyBatis(iBatis)가 대표적  
  ORM(Object Relation Mapper) Framework : 하나의 객체와 하나의 record(row, tuple 등)를 매핑하는 방식으로 사용하는데, SQL없이 관계형 데이터베이스 사용이 가능  
  JAVA에ㅓ는 JPA가 표준으로 채택되었으며 다른 언어에도 이러한 프레임워크가 존재.  
  최근에는 ORM 방식으로 DB연동을 많이하고 SQL Mapper를 보조적인 역할을 수행하는 형태로 사용하는 경우가 많음.  
  
## 3. 데이터베이스 사용 준비
  * 데이터베이스 서버 준비  
  * 데이터베이스 접속 도구 : 서버를 직접 설치하면 제공되는 경우가 있음  

# MYSQL
## 1. 설치
### 1) Windows는 mysql site에 가서 installer 버전을 다운로드해서 설치
### 2) mac은 homebrew이용해서 설치
  homebrew설치(M1, Intel chip이냐에 따라서 다르게 설치)  
  brew install mysql : 설치
  brew services start mysql : mysql server 실행(root 비밀번호는 없음)  
  brew services stop mysql : mysql server 중지  
### 3) ubuntu linux  
  sudo apt install -y mysql-server  
### 4) docker같은 가상화 애플리케이션에 설치  
  docker pull mysql  
  서버 시작은 docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=비밀번호 -d -p 3306:3306 mysql:latest  
  비밀번호에 관리자 비밀번호 입력  
  클라우드 환경 개발이나 일반 애플리케이션 개발자가 되고자 하는 경우 추천  
## 2. MySQL 버전  
  * 5.X 버전과 최근의 8.X 버전으로 구분  
  * 8.X 버전은 처음부터 비밀번호가 암호화되어 있으며 기본 설정이 외부에서는 root 계정에 접근할 수 없도록 되어있음  
## 3. MySQL 접속  
  * 관리자 계정은 root이고 설치할 때 관리자 비밀번호를 설정하면 기본 포트는 3306번  
  * 처음부터 제공되는 DB는 mysql이 있습니다  
  * MySQL의 데이터베이스는 user보다 큰 개념으로 하나의 database를 만들면 여러 명의 user가 공유해서 사용할 수 있습니다  

## 4. 계정 생성
### 1) 계정 생성  
  create user '사용자 이름'@'%' identified by '비밀번호'  
  -> % 대신에 localhost를 사용하거나 ip를 넣으면 localhost나 ip에서만 접속이 가능  
### 2) 계정에 권한 부여  
  grant all privileges on *.* to '사용자이름'@'%'
### 3) 일반 비밀번호로 접속이 가능하도록 수정
  alter usere '사용자이름'@'%' identified with mysql_native_password by '비밀번호'
### 4) 변경 내용 적용
  flush privileges;
### 5) 계정 생성 실습
  create user 'user00'@'%' identified by 'user00';  
  grant all previleges on *.* 'user00'@'%';  
  alter user 'user00'@'%' identified with mysql_native_password by 'user00';  
  
  flush privileges;
  
  * mac에서 계정이 만들어졌는지 테스트  
    mysql -u user00 -p

## 5. 데이터베이스 관련 명령
  * mysql의 데이터는 데이터베이스 단위로 관리가 이루어집니다.
  * 하나의 데이터베이스는 여러 유저가 공유가능합니다.

  ### 1) 사용 가능한 데이터베이스 확인
    show databases;
    -> 제공되는 DB중에서 world와 test를 제외한 DB는 관리자가 사용하는 DB.  
      사용자의 데이터를 저장하는 용도로 사용하는 것은 바람직하지 않음  
  ### 2) DB 생성
    create database 데이터베이스이름;
  ### 3) DB 삭제
    drop database DB이름;
  ### 4) DB 사용
    use DB이름;
  ### 5) DB 생성 및 확인;
    create database node;
    show databases;

## 6. SQL(Structed Query Language)
  관계형 DB의 질의어

  ### 1) DDL(Data Definition Language - 정의어, DB관리자의 언어)  
    * Create : 구조 생성  
    * Alter : 구조 변경  
    * Drop : 구조 삭제  


    * Truncate : 테이블의 데이터 삭제  
    * Rename : 구조 이름 변경  

  ### 2) DML(Data Manipulation Language - 조작어, 개발자의 언어)  
    * Select : 조회, DQL로 분리하는 것이 요즘의 추세

    * Insert : 삽입  
    * Update : 데이터의 변경  
    * Delete : 데이터 삭제  

    * 4개의 구문을 줄여서 CRUD(Create, Read, Update, Delete)라고도 합니다.
  
  ### 3) DCL(Data Control language - 제어어, DB 관리자의 언어)
    Grant & Revoke : 권한을 부여하고 회수하는 명령
  
    * Commit & Rollback : 작업 내역을 DB에 반영하고 취소하는 명령 (Transaction Control Language로 분리를 하고 개발자의 언어)