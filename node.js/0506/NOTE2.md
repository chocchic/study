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
* homebrew 설치(M1, Intel chip이냐에 따라서 다르게 설치)  

* 설치
```bash
  brew install mysql
```  

* mysql server 실행(root 비밀번호는 없음)  
```bash
  brew services start mysql
```  

* mysql server 중지  
```bash
  brew services stop mysql
```
### 3) ubuntu linux  
```bash
sudo apt install -y mysql-server  
```  

### 4) docker같은 가상화 애플리케이션에 설치  
* 설치
```bash
  docker pull mysql  
```  
* 서버 시작
```bash
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=비밀번호 -d -p 3306:3306 mysql:latest  
```  

* 비밀번호에 관리자 비밀번호 입력  
* 클라우드 환경 개발이나 일반 애플리케이션 개발자가 되고자 하는 경우 추천  

## 2. MySQL 버전  
* 5.X 버전과 최근의 8.X 버전으로 구분  
* 8.X 버전은 처음부터 비밀번호가 암호화되어 있으며 기본 설정이 외부에서는 root 계정에 접근할 수 없도록 되어있음  

## 3. MySQL 접속  
* 관리자 계정은 root이고 설치할 때 관리자 비밀번호를 설정하면 기본 포트는 3306번  
* 처음부터 제공되는 DB는 mysql이 있습니다  
* MySQL의 데이터베이스는 user보다 큰 개념으로 하나의 database를 만들면 여러 명의 user가 공유해서 사용할 수 있습니다  

## 4. 계정 생성
### 1) 계정 생성  
```bash
  create user '사용자 이름'@'%' identified by '비밀번호'  
```
-> % 대신에 localhost를 사용하거나 ip를 넣으면 localhost나 ip에서만 접속이 가능  

### 2) 계정에 권한 부여  
```bash
  grant all privileges on *.* to '사용자이름'@'%'
```  

### 3) 일반 비밀번호로 접속이 가능하도록 수정  
```bash
alter usere '사용자이름'@'%' identified with mysql_native_password by '비밀번호'
```  

### 4) 변경 내용 적용
```bash
flush privileges;
```  

### 5) 계정 생성 실습  
```bash
create user 'user00'@'%' identified by 'user00';  
grant all previleges on *.* 'user00'@'%';  
alter user 'user00'@'%' identified with mysql_native_password by 'user00';  

flush privileges;
```  

* mac에서 계정이 만들어졌는지 테스트  
```bash
mysql -u user00 -p
```

## 5. 데이터베이스 관련 명령
* mysql의 데이터는 데이터베이스 단위로 관리가 이루어집니다.
* 하나의 데이터베이스는 여러 유저가 공유가능합니다.

### 1) 사용 가능한 데이터베이스 확인
```bash
show databases;
```  

* 제공되는 DB중에서 world와 test를 제외한 DB는 관리자가 사용하는 DB.  
  사용자의 데이터를 저장하는 용도로 사용하는 것은 바람직하지 않음  

### 2) DB 생성  
```sql
  create database 데이터베이스이름;
```  

### 3) DB 삭제  
```sql
drop database DB이름;
```  

### 4) DB 사용  
```sql
use DB이름;
```  
### 5) DB 생성 및 확인  
```sql
create database node;
show databases;
```  

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
* Grant & Revoke : 권한을 부여하고 회수하는 명령  

* Commit & Rollback : 작업 내역을 DB에 반영하고 취소하는 명령 (Transaction Control Language로 분리를 하고 개발자의 언어)  

## 7.DQL - Select  
* 샘플 데이터 생성
```sql
show databases;

create database node;

show databases;

use node;

create table usertbl(
userid char(15) not null primary key,
name varchar(20) not null,
birthyear int not null, 
addr char(100),
mobile char(11),
mdate date)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

create table buytbl(
num int auto_increment primary key,
userid char(8) not null,
productname char(10),
groupname char(10),
price int not null,
amount int not null,
foreign key (userid) references usertbl(userid) on delete cascade)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

show tables;

insert into usertbl values('kty', '김태연',1989,'전주','01011111111', '1989-3-9');
insert into usertbl values('bsj', '배수지',1994,'광주','01022222222', '1994-10-10');
insert into usertbl values('ksh', '김설현',1995,'부천','01033333333', '1995-1-3');
insert into usertbl values('bjh', '배주현',1991,'대구','01044444444', '1991-3-29');
insert into usertbl values('ghr', '구하라',1991,'광주','01055555555', '1991-1-13');
insert into usertbl values('san', '산다라박',1984,'부산','01066666666', '1984-11-12');
insert into usertbl values('jsm', '전소미',2001,'캐나다','01077777777', '2001-3-9');
insert into usertbl values('lhl', '이효리',1979,'서울','01088888888', '1979-5-10');
insert into usertbl values('iyou', '아이유',1993,'서울','01099999999', '1993-5-19');
insert into usertbl values('ailee', '에일리',1989,'미국','01000000000', '1989-5-30');

insert into buytbl values(null, 'kty', '운동화', '잡화', 30, 2);
insert into buytbl values(null, 'kty', '노트북', '전자', 1000, 1);
insert into buytbl values(null, 'jsm', '운동화', '잡화', 30, 1);
insert into buytbl values(null, 'lhl', '모니터', '전자', 200, 1);
insert into buytbl values(null, 'bsj', '모니터', '전자', 200, 1);
insert into buytbl values(null, 'kty', '청바지', '잡화', 100, 1);
insert into buytbl values(null, 'lhl', '책', '서적', 15, 2);
insert into buytbl values(null, 'iyou', '책', '서적', 15, 7);
insert into buytbl values(null, 'iyou', '컴퓨터', '전자', 500, 1);
insert into buytbl values(null, 'bsj', '노트북', '전자', 1000, 1);
insert into buytbl values(null, 'bjh', '메모리', '전자', 50, 4);
insert into buytbl values(null, 'ailee', '운동화', '잡화', 30, 2);
insert into buytbl values(null, 'ghr', '운동화', '잡화', 30, 1);

commit;
```  

### 1) Select의 구조  
5  SELECT : 열단위 추출을 위한 컬럼이나 연산식을 나열 - 별명 부여할 수 있음, 필수  
1  FROM : 테이블 이름 나열하는데 다른 이름을 부여할 수 있음 - 필수  
2  WHERE : 행 단위 추출을 위한 조건 나열   
3  GROUP BY : 그룹화하기 위한 컬럼이나 연산식을 나열 - 그룹 함수 생성 시점  
4  HAVING : 그룹화한 후 행 단위 추출을 위한 조건 나열  
6  ORDER BY : 정렬할 컬럼이나 연산식 나열  
7  LIMIT : 추출할 행의 시작 인덱스와 개수 나열 가능(오라클은 이 옵션이 없어서 inline view를 최근의 버전의 다른 옵션을 이용해서 이 부분을 해결)  

### 2) 테이블의 전체 데이터 확인  
```sql
select * from 테이블이름;  
```  
접속 도구에서는 블럭을 잡아서 실행하므로 ;을 입력하지 않아도 됩니다.  
콘솔 환경이라면 반드시 입력해주어야 합니다.  
프로그래밍 언어에서 embedded sql의 형태로 많이 사용하는데, 이 경우 ;이 있으면 에러가 나는 경우가 많습니다.  
;을 생략하고 입력하는 것이 일반적입니다.  

* usertbl 테이블과 buytbl테이블을 조회  
```sql
select * from usertbl;
select * from buytbl;
```  

Oracle은 테이블이름과 컬럼 이름을 대문자로 사용하는데 MySQL은 소문자로 사용합니다.  
Oracle은 SQL 조건절을 제외한 경운 대소문자 구분을 하지 않지만 MySQL은 대소문자 구분을 하는 경우가 많습니다.   

### 3) 특정 컬럼이나 연산식 출력  
```sql
select 컬럼이름 이나 연산식 나열 from 테이블이름;
```  
-> 연산식을 사용하게 되면 연산식은 컬럼으로 만들어지는 것이 아니고 출력할 때 연산을 수행해서 출력합니다.
-> 별명을 붙이고자 할 때는 공백이나 as를 붙이고 별명을 입력하면 됩니다.
별명에 공백이 있는 경우에는 별명을 반드시 ' '로 감싸야 합니다.
-> 테이블을 만들 때는 컬럼 이름을 길게 하고 사용을 할 때 별명을 이용해서 사용하는 경우가 많습니다.

* buytbl 테이블에서 nu, userid, amount*price 결과를 조회  
```sql
select num userid, amount * price from buytbl;
select num as 번호, userid as 아이디, amount * price as 금액 from buytbl;
```  

### 4) 조건 검색  
* where에 조건을 설정해서 행 단위 추출을 수행  
* 연산자  
> >= < <=  
= !=  

between A and B(not between A and B) : A와 B 사이, 반드시 B의 값이 A보다 크거나 같아야합니다.  
in(not in) : 여러 개의 데이터를 괄호 안에 나열해서 그 중 하나오 ㅏ잉ㄹ치하는 데이터 조회  
is null(is not null) : null 데이터 조회  
and : 그리고  
or : 또는(and의 우선순위가 or보다 높음)  

and는 앞의 조건이 false이면 뒤의 조건을 확인하지 않고 or는 앞의 조건이 true이면 뒤의 조건을 확인하지 않음  

3의 배수면서 4의 배수인 데이터 조회  
데이터 % 3 == 0 and 데이터 % 4 == 0  
데이터 % 4 == 0 and 데이터 % 3 == 0  

3의 배수는 아니면서 4의 배수도 아닌 데이터 조회  
데이터 % 3 != 0 and 데이터 % 4 != 0  
데이터 % 4 != 0 and 데이터 % 3 != 0  

like(not like) : 패턴 검색  
_ : 하나의 문자
% : 글자수 없는 문자

* 데이터베이스에서는 문자도 크기 비교가 가능  
* 날짜도 숫자로 인식  

* usertbl 테이블에서  name이 김태연인 데이터 조회  
```sql
select * from usertbl where name ='김태연';
```

* usertbl 테이블에서 birthyear가 1990보다 크고 addr이 서울인 데이터 조회  
```sql
select * from usertbl where birthyear > 1990 and addr='서울';
```  

* usertbl 테이블에서 birthyear가 1990에서 1993인 데이터 조회  
```sql
select * from usertbl where birthyear between 1990 and 1993;
select * from usertbl where birthyear >= 1990 and birthyear <= 1993;
```  

* usertbl 테이블에서 name에 라가 포함된 데이터 조회  
```sql
select * from usertbl where name like '%라%';
```  

* usertbl 테이블에서 name이 배로 시작하는 데이터 조회  
```sql
select * from usertbl where name like '배%';
```  

* usertbl 테이블에서 name이 4글자인 데이터 조회  
```sql
select * from usertbl where name like '____';
```  