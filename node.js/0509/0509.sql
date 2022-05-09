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
select * from usertbl where birthyear >= (select birthyear from usertbl where name ='김태연');

select birthyear from usertbl where name = '김태연';
select * from usertbl where birthyear >= 1989;