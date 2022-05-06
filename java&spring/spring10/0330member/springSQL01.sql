select sysdate from dual;

-- resultType="string"
select id from test;
select id from test where id = 'java';

-- resultType="int"
select age from test;

-- resutlType="date" / "java.util.Timestamp"
select reg from test;

-- resultType="com.test.model.TestDTO"
select * from test;

select count(*) from test;
select max(age) from test;
select * from test;

select * from test where id='spring03';

update test set pw='1234',age=33 where id='spring01';

-- 전체
select count(*) from test;
select count(*) from test where age='10';

-- id,pw체크
select COUNT(*) from test where id ='spring01' and pw='1234';
-- id 사용유무 체크
select count(*) from test where id='spring01';

-- id, pw, age가 일치하는 레코드 수
select COUNT(*) from test where id ='spring01' and pw='1234' and age=33;

--update test set pw=#{pw}, age=#{age} where id=#{id};

select count(*) from test where id in ('spring01','spring02','spring03','spring','spring100');
select * from test where id in ('spring01','spring02','spring03','spring','spring100');

select * from test where id like '%s';


select count(*) from test;
-- 레코드 전체 개수를 insert의 age 값으로 넣어줘 보자.
--insert into test values(#{id}, #{pw},#{age},sysdate);

-- 게시판 table
create table board(
    bno number,
    title varchar2(100) not null,
    content varchar2(2000) not null,
    writer varchar2(50) not null,
    regdate date default sysdate,
    updatedate date default sysdate
);

--primary key bno에 지정
-- ALTER TABEL 테이블 명 ADD CONSTRAINT 제약조건명 제약조건;
-- 제약조건은 PRIMARY KEY (컬럼명)
alter table board add constraint pk_board primary key (bno);
desc board;

-- 게시판 글 고유번호로 사용할 시퀀스 
-- cache 안사용하려면 뒤에 no cache라는 옵션
create sequence board_seq;

insert into board values(board_seq.nextval, '테스트제목2', '테스트내용2','java',sysdate,sysdate);
commit;
select * from board;