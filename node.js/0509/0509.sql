-- 데이터베이스 확인
show databases;

-- 데이터베이스 생성
create database 데이터베이스이름;

-- 데이터베이스 삭제
drop database 데이터베이스이름;

-- 데이터베이스 사용
use 데이터베이스이름;

-- 실습
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

-- usertbl 테이블에서 name이 김태연인 데이터의 birthyear보다 크거나 같은 birthyear를 가진 모든 컬럼 조회
select birthyear from usertbl where name = '김태연';
select * from usertbl where birthyear >= 1989;
-- 위 문제를 서브쿼리를 이용해서 해결
select * from usertbl where birthyear >= (select birthyear from usertbl where name ='김태연');

-- usertbl테이블에서 addr이 광주인 데이터와 birthyear가 같은 데이터의 모든 컬럼 조회
select birthyear from usertbl where addr ='광주';
select * from usertbl where birthyear = 1994 or birthyear = 1991;

-- 에러 : 서브 쿼리가 리턴하는 데이터가 2개 이상이라서 단일 행 연산자 사용 불가
select * from usertbl where birthyear = (select birthyear from usertbl where addr='광주');
-- 다중 행 연산자
-- = : in, != : not in
select * from usertbl where birthyear in (select birthyear from usertbl where addr='광주');

-- <. <=, >, >= : ALL이나 ANY와 함께 사용
-- ALL이면 전체가 되는 것이고 ANU는 아무가나 하나
-- MIN, MAX로 대체 가능

-- 최소값보다 작은 것
select * from usertbl where birthyear < ALL(select birthyear from usertbl where addr='광주');

-- usertbl 테이블의 name과 birthyear를 조회하는데 birthyear의 오름차순으로 정렬해서 출력
select name, birthyear from usertbl order by birthyear;
select name, birthyear from usertbl order by 2;

-- usertbl테이블의 name과 birthyear를 조회하는데 birthyear의 오름차순으로 정렬해서 출력하는데 
-- birthyear의 값이 같으면 name의 내림차순으로 정렬
select name, birthyear from usertbl order by birthyear asc, name desc;

-- usertbl 테이블에서 addr의 값을 중복되지 않게 조회
select distinct addr from usertbl;

-- usertbl 테이블에서 birthyear가 가장 큰 5개의 데이터를 조회
select * from usertbl order by birthyear desc limit 0, 10;

-- usertbl 테이블에서 birthyear가 다섯번째로 큰 데이터부터 나머지 모든 데이터를 조회
select * from usertbl order by birthyear desc limit 5;

-- usertbl 테이블의 데이터 개수 조회
select count(userid) from buytbl;
select count(*) from buytbl;

-- buytbl의 평균 amount 조회
select avg(amount) from buytbl;

-- buytbl 테이블에서 userid별 평균 amount 조회
select userid, avg(amount) from buytbl group by userid;
select * from buytbl;
-- buytbl 테이블에서 userid의 개수가 3번이상 등장한 데이터의 userid를 조회  
select userid from buytbl group by userid having count(userid) >= 3;

-- buytbl테이블에서 price가 30이상인 데이터가 두번 이상 등장하는 데이터의 userid를 조회
select userid from buytbl where price >= 30 group by userid having count(userid) >=2;

-- mysql은 그룹화하지 않은 항목을 출력하면 첫번째 것 하나를 출력
select addr, name, count(*) from usertbl group by addr;

select userid, avg(price) from buytbl group by userid with rollup;

select addr as "주소" from usertbl order by 주소;
select usertbl.addr as "주소" from usertbl U order by 주소; -- 에러
select U.addr as "주소" from usertbl U order by 주소;

-- EMPLOYEE_SALARY테이블에서 SALARY의 값이 null이면 0, 아니면 원래의 값으로 조회
select ifnull(salary, 0) from employee_salary;