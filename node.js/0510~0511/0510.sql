use node;
create table Member(
	id varchar(100) primary key,
    name char(50) not null,
    nick varchar(100) unique,
    age int default 1,
    gender varchar(3) check(gender in ('남', '여'))
)engine=MyISAM default charset = utf8;
select * from member;

create table Board(
	bno int primary key auto_increment,
    title char(100) default '무제',
    content varchar(10000) default '냉무',
    regdate datetime,
    nick varchar(100) references member(nick) on delete cascade
)engine=InnoDB default charset = utf8;
select * from board;

-- 테이블 구조 확인
desc member;
desc board;

-- 제약조건확인
select * from infomation_scheam.table_constraints;

alter table member add emaii varchar(200);