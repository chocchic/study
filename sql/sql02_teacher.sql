/*

1. 테이블 생성 : CREATE 
    CREATE TABLE [테이블명] (
        컬럼명 컬럼데이터타입 (옵션),
        컬럼명 컬럼데이터타입 (옵션), 
        ....
    );
    
    컬럼명 : 알파벳소문자붙혀서 
    데이터타입 : 반드시 명시    문자: varchar2(글자길이값), 숫자:number, 날짜: date 
    옵션 : 제약조건 contraint (생략가능) 
            부적절한 자료가 입력되는 것을 방지하기 위해서 여러가지 규칙을 적용해 놓는것
        
        not null    : null 값이 들어올 수 없다.  
        unique      : 중복요소 올 수 없다.  
        primary key : 중요함. not null + unique 형태로 
                    테이블당 한개는 있는 형태가 좋고, 테이블당 한개 컬럼만 pk 지정가능. 
                    레코드를 구분짓는 기준이 됨. 
        foreign key : 두개 테이블을 연결시키리때 다른테이블의 PK가 되는 컬럼을 
                    현재 테이블에 FK 로 지정 
        default     : 디폴트 값 지정 
        check       : 조건 체크등 검사하고 들어갈 수 있는
        
    * 테이블 생성시 주의사항 
        - 테이블 이름과 컬럼은 항상 알파벳으로 시작 
            A-Z 문자, 0-9숫자, $#_ 사용가능, 공백X 
        - 컬럼명 예약어 사용 불가 
        - 한 계정에서 테이블명 중복 불가 
        - 한 테이블 안에서 컬럼이름은 중복 불가, 다른테이블에서의 컬럼이름과 동일해도 무관. 
*/
create table test(
    id varchar2(50) primary key, 
    pw varchar2(50) not null,
    age number default 1, 
    reg date default sysdate
); 
-- 사용자가 보유한 테이블명 조회 
select table_name from user_tables;
-- 테이블 정보 확인 
desc test;
select * from test;

/*
2. 레코드 추가 : INSERT (not null 속성을 가진 컬럼에는 반드시 값을 넣어주기) 
    1) 모든 컬럼에 데이터 추가 (테이블명뒤에 컬럼명생략, 값순서 주의) 
    INSERT INTO [테이블명] VALUES( [값들을 쉼표구분자로 컬럼순서대로 나열] );
        default 옵션 적용 X 

    2) 원하는 컬럼만 데이터 추가 (컬럼명 순서는 마음대로, 대신 값은 작성한 컬럼명순서대로) 
    INSERT INTO [테이블명(컬럼명,컬럼명,..)] VALUES( [컬럼명작성한 순서대로 값들 나열] ); 
        default 옵션은 2)버전일 경우만 처리됨. 
*/
insert into test values('java', '1234', 20, sysdate); 
insert into test(pw, id, age, reg) values('java01', '1234', 10, sysdate); 
insert into test(id, pw) values('java01', '0000');

insert into test values('java02', '0000', 2, sysdate); 
insert into test values('java03', '0000', 10, sysdate);
insert into test values('java04', '0000', 30, sysdate);
insert into test values('java05', '1234', 40, sysdate);
insert into test values('java06', '0000', 50, sysdate);
insert into test values('java07', '1111', 10, sysdate);
insert into test values('java08', '1212', 10, sysdate);
insert into test values('java09', '0000', 100, sysdate);
insert into test values('java10', '0000', 5, sysdate);
commit;


select * from test;
select * from test 
where pw = '0000';
select * from test 
where age > 10;
select * from test 
order by id desc; 

/*
3. 레코드 수정 : UPDATE
    1) 일괄 수정 
        UPDATE [테이블명] SET [컬럼명=값]; 
        
    2) 레코드 조건 수정 
        UPDATE [테이블명] SET [컬럼명=값 (,컬럼명=값,...)] 
        WHERE [조건식]; 
*/
select * from test;
update test set pw='0000';  -- 모든 레코드의 pw값을 0000으로 바꾸기 
commit;

update test set pw='1234' 
where id='java01';
commit;

update test set pw='1111' 
where age = 10; 
commit;

insert into test values('java11', '1234', 10, sysdate);
select * from test;

/*
4. 레코드 삭제 : DELETE 
    1) 전체 레코드 삭제 
        DELETE FROM [테이블명];     : 테이블의 모든 데이터 삭제, 공간 반납은 안해줌 (테이블유지)
        TRUNCATE TABLE [테이블명]; : 테이블의 모든 데이터 삭제, 사용하던 공간도 반납 (테이블유지)
        
    2) 레코드 조건 삭제 
        DELETE FROM [테이블명] 
        WHERE [조건식]; 
*/
delete from test 
where id = '1234';
commit;
select * from test; 

--delete from test;  레코드 모두 날리기 
--truncate table test; 레코드 모두 날리고, 점유하던 공간도 반납 

/*
5. 테이블 정보 수정 : ALTER
    1) 컬럼 추가 ALTER - ADD
        ALTER TABLE [테이블명] ADD ( [컬럼명 데이터타입 (옵션)] ); 
        
        * 컬럼과 컬럼사이에 추가 불가, 항상 맨 뒤에 추가 
        * 테이블에 이미 데이터가 들어가 있을때는 not null 옵션 줄수 없다. default 가능. 
        
    2) 컬럼 타입 변경 ALTER - MODIFY
        ALTER TABLE [테이블명] MODIFY ( [컬럼명 데이터타입 (옵션)] ); 
        
        * 컬럼변경시 고려사항 
        - 해당 컬럼의 크기를 늘리 수는 있지만 줄이지는 못함, 기존 데이터 훼손 우려. 
        - 해당 컬럼의 NULL 값만 가지고 있거나 테이블에 아무 레코드가 없으면 컬럼의 크기를 줄일 수 있다. 
        - 해당 컬럼의 NULL 값만을 가지고 있으면 데이터 유형을 변경할 수 있다. 
        - 해당 컬럼의 DEFAULT 값을 바꾸면 변경 이후에 발생하는 레코드 삽입에만 영향을 준다. 이전은 그대로 
        - 해당 컬럼에 NULL값이 없을 경우에만 NOT NULL 제약조건 추가 가능. 
        
    3) 컬럼 이름 변경 ALTER - RENAME
        ALTER TABLE [테이블명] RENAME COLUMN [구 컬럼명] TO [새 컬럼명]; 
        
    4) 컬럼 삭제 ALTER - DROP
        ALTER TABLE [테이블명] DROP COLUMN [컬럼명]; 

        * 한번에 한개의 컬럼만 삭제 가능, 삭제후 테이블에 한개이상의 컬럼이 남아있어야함. 
        * 삭제되면 복구 불가능 
*/
select * from test;
-- 컬럼 추가
alter table test add(name varchar2(50));
commit;
-- 컬럼 타입 변경
alter table test modify(name number); 
-- 컬럼 이름 변경 
alter table test rename column name to nickname;
commit;
-- 컬럼 삭제 
alter table test drop column nickname;

/*
6. 테이블 삭제 : DROP 
    : 테이블 자체를 삭제 
    
    DROP TABLE [테이블명]; 
*/
-- drop table test;

/*
7. 예명(별칭) : ALIAS 
    : 조회된 결과에 일종의 별명(alias) 을 부여해서 컬럼 레이블(명)을 변경할 수 있다. 
    실제 테이블의 컬럼명이 변경되지 않고, 검색시 표에 예명으로 한번 붙는다. 
    
        SELECT [컬럼명] [예명], [컬럼명] [예명] FROM [테이블명] [예명];
        SELECT [컬럼명] as [예명], [컬럼명] as [예명] FROM [테이블명];

        * 예명은 컬러명 바로 뒤에 작성
        * 컬럼명과 예명 사이에 AS, as 키워드를 사용할 수도 있다. 
        * 예명은 쌍따옴표로 묶어서 표현하면 예명에 공백이나 특수문자를 포함시킬 수 있고, 
                대소문자 구분하게 할 수도 있다. -> 쌍따옴표 생략할 수도 있다.  
        * FROM절에 테이블 예명을 설정하면, SELECT 문장에서 테이블명 대신 사용 가능.  
*/
select id, pw from test;
select id as "당신의 아이디", pw as 비밀번호 from test as t;
select t.id "당신의 아이디", t.pw 비밀번호 from test t;

/*
8. 트랜젝션 Transaction 
    데이터 처리의 한 단위 
    트랜젝션 : 이전 커밋이 일어난 후 ~ 다음 커밋 전까지의 작업 

9. 집계함수 
    1) 종류 
        COUNT   : 데이터의 개수 
        SUM     : 데이터의 총합
        AVG     : 데이터의 평균값
        MAX     : 데이터중 가장 큰값 
        MIN     : 데이터중 가장 작은값 
        ...
        
        * 하나또는 여러행을 주고, 하나의 결과값을 반환하는 형태 
*/
select count(*) from test;
select empno, count(*) from emp;
select count(*), count(comm) from emp; 
select sum(comm) from emp; 
select avg(comm) from emp; -- null을 제외한 값들의 평균 
select avg(nvl(comm, 0)) from emp; -- null도 카운트해서 전체 평균 
select max(hiredate) from emp; -- 가장 최신 
select min(hiredate) from emp; -- 가장 오래된 
select * from emp;

/*
10. GROUP BY : 특정 조건으로 세부적인 그룹화 

    GROUP BY [그룹핑 기준] 
    
    * WHERE 절 뒤, ORDER BY 전. 
*/
select deptno, count(*) from emp 
--where 
group by deptno
order by 2 desc; 

select * from emp;
-- job을 기준으로, 각 업무에 해당하는 사원수, 업무별 평균 급여,
--  업무별 최고 급여, 최소 급여, 급여합계 
select job, count(*) "인원수", round(avg(sal),2) "평균 급여",
max(sal) "최고 급여", min(sal) "최저급여",
sum(sal) "급여 합계"
from emp
group by job;

/*
11. HAVING 절 
    WHERE 절에는 집계함수 사용 불가. 
    집계함수로 조건 비교할때 사용 
*/
-- job별로 직원수가 3명이상인 job과 인원수 출력 
select job, count(empno) "직원수" 
from emp 
--where count(*) > 3
group by job
having count(empno) >= 3;
-- 전체 월급이 5000을 초과하는 JOB에 대해, 
-- job과 월 급여 합계를 조회, 
-- 단, job이 'SALESMAN' 는 제외하고 월 급여 합계로 내림차순 정렬 
select job, sum(sal) "급여 합계" 
from emp
where job != 'SALESMAN'     -- 집계함수로 조건사용 불가 
group by job                -- 반드시 컬럼명이와야, 예명X
having sum(sal) > 5000 
order by sum(sal) desc;

/*
12. 서브 쿼리 
    : 쿼리문 안에 쿼리문을 작성하는 형태 
    
    SELECT * FROM [테이블명];
    
    SELECT * FROM ( 서브쿼리문 ); 
    SELECT * FROM ( 서브쿼리문( 서브쿼리 ) ); 
*/
select empno, sal from (select empno, ename, sal from emp); 
select * 
from (
    select empno, ename, job 
    from emp
    
) where sal > 3000; -- 에러, 서브쿼리에 sal 이 없어서 외부에서 조건검사 불가 

select * from emp;
-- 사원번호 7900인 직원의 직무와 같은 직무를 가진 사람의 이름과 직무 출력 
select empno, ename, job 
from emp
where job = (select job from emp where empno = 7900); 

/*
13. 조인 JOIN 
    : 둘 이상의 테이블을 연결하여 데이터를 검색하는 방법 
    두 테이블에 적어도 한개의 컬럼은 서로 공유하는 형태여야 한다. 
    
    test  a,b,c,d,e
    정규화 : 하나의 테이블이 여러개의 테이블로 나눠져서 조각으로 분리하는 기술. 
    정규화로 여러테이블에 흩어져 있는 데이터들을 조합해서 가져오는 기술이 조인이다. 

    [Oracle Join]
    select a.col1, b.col1 from table1 a, table2 b where a.col2=b.col2; 
    [ANSI Join]
    select a.col1, b.col1 from table1 a [inner] join table2 b on a.col2=b.col2;
    
    1) EQUI Join  등가조인 
        
*/
select * from emp e, dept d
where d.deptno = e.deptno; 
-- empno, ename, dname 출력 
-- Oracle 
select empno, ename, dname 
from emp e, dept d
where e.deptno = d.deptno; 
-- ANSI 
select e.empno, e.ename, d.dname 
from emp e join dept d
on e.deptno = d.deptno; 

-- 학생테이블과 교수테이블을 join 하여 
-- 학생이름과 지도교수이름을 출력 (profno) 
SELECT s.name "학생이름" , p.name "지도교수이름"
from student s, professor p 
where s.profno = p.profno; 

/*
14. SELF Join 
    조인하고 싶은 데이터가 하나의 테이블에 다 들어있는 경우 
*/
-- 직원번호, 직원이름과 해당 직원의 상사의 번호와 이름 출력 
select e1.empno, e1.ename, e1.mgr, e2.ename 
from emp e1, emp e2 
where e1.mgr = e2.empno; 

/*
15. 시퀀스 SEQUENCE
    : 유일(unique)한 값을 생성해주는 오라클 객체 
    시퀀스를 생성하면 순차적으로 증가하는 컬럼을 자동적으로 생성 할 수 있고, 
    주로 PK로 사용을 많이 한다. 
    number 타입의 pk 값을 생성할때 사용. 
    시퀀스는 테이블과는 독립적으로 저장되고 생성된다. 

    1) 시퀀스 생성 
        
        CREATE SEQUENCE [시퀀스명] (옵션); 
        
        * 옵션 : 생략가능 
        START WITH n : 시퀀스의 시작값을 지정. 
        INCREMENT BY n : 증가값 지정. DEFAULT 1 
        MAXVALUE : 최대값 
        MINVALUE : 최소값 
        CYCLE / NOCYCLE : 최대값 도달시 순환 여부 
        CACHE n / NOCACHE : 시퀀스 생성속도를 개선하기 위해 캐싱여부 지정.  

        * 시퀀스명 : 테이블명_seq   seq_테이블명 

    2) 시퀀스 사용 
        시퀀스명.CURRVAL    : 현재까지 생성된 번호. 
        시퀀스명.NEXTVAL    : 다음번호 
        
    3) 시퀀스 삭제 
        DROP SEQUENCE [시퀀스명]; 
*/
create sequence test_seq nocache;
commit;
select test_seq.nextval from dual;
select test_seq.currval from dual;
select * from test;

insert into test values('pikachu', '1111', test_seq.nextval, sysdate); 

drop sequence test_seq;

-- 문제1. student(학생), department(학과), professor(교수) 테이블 join 하여 
--      학생이름, 학생의학과이름(dname), 지도교수이름 출력  
--       단, 학생의 학과는 DEPTNO1을 기준으로 찾기 
-- 문제2. student 테이블을 조회하여 1전공이 101번인 학생들의 이름과 
--      각 학생들의 지도교수이름을 출력하세요. 












