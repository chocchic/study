/*
    데이터 조회 : SELECT 
    SELECT [컬럼명 또는 표현식] FROM [테이블명 또는 뷰명]; 
    전체 조회 : * , 원하는 컬럼만 조회 : 컬럼명  , 구분자로 나열 
*/
select * from emp;
select empno, ename from emp; 

-- 표현식(리터럴상수)은 컬럼 이름이외에 출력하기를 원하는 내용을 작성하여 출력가능. 
--  select 뒤에 '' 묶어서 사용. 
--  데이터는 '' 묶기, 별칭(alias)은 "" 묶기 
select name, 'good morning' "HELLO" from professor;

select * from dept;
select dname "Department Name", ', it''s deptno : ' "test", deptno "Department Number" from dept;

-- 컬럼 별칭 (Column alias) : 컬럼명 뒤에 ""로 묶어서 별칭 달수 있다. (select 문 실행할때만 적용)
select * from emp;
select empno "employee number", ename "employee name", mgr "manager" from emp; 

select deptno from emp;
-- 중복값 제거하고 select 해오기 
-- 주의 : 1개 컬럼앞에 키워드를 작성해도 나열된 모든 컬럼을 기준으로 중복검사를함.
select DISTINCT deptno from emp;
select DISTINCT deptno, ename from emp;

-- 연결연산자 : || : 컬럼 붙혀서 출력 
select ename, job from emp; 
select ename || ' ' || job from emp; 

select * from student;
-- Student 테이블에서 모든학생의 이름과 ID, 체중을 아래와 같은 형태로 출력. 
-- 예 ) James Seo's ID : 75true, WEIGHT is 72Kg
select name || '''s ID : ' || id || ', WEIGHT is ' || weight || 'Kg' from student;

/*
    원하는 조건만 골라내기 : WHERE절 
    SELECT [컬럼명 또는 표현식] FROM [테이블명 또는 뷰명] WHERE [조건]; 
    조건은 일반 산수에 사용되는 조건 사용.  
*/
select * from emp;
select * from emp where empno = 7900; 
-- sal이 1500이상인 직원의 empno, ename, job 가져오기 
select empno, ename, job, sal from emp where sal >= 1500;
-- ename이 SMITH인 사람의 empno, ename, sal 를 emp테이블에서 가져오기 
select empno, ename, sal from emp where ename = 'SMITH';
-- ename이 FORD인 사람의 empno, ename, hiredate 를 emp테이블에서 가져오기 
select empno, ename, hiredate from emp where ename = 'FORD';
-- hiredate가 80/12/17 인 직원의 empno, ename, job, hiredate 가져오기 
select empno, ename, job, hiredate from emp 
--where hiredate = '80/12/17';
--where hiredate = '80-12-17';
--where hiredate = '1980/12/17';
where hiredate = '80/12/17'; -- 유닉스 계열 일-월-년  17/DEC/80
-- 검색의 조건에서는 숫자 외에는 꼭 홑따옴표로 묶어서 표시하자. 문자 대소문자 구분 

-- 기본산술 연산자 사용 : + - * / 
select ename, sal from emp where deptno = 10; 
select ename, sal+1000 from emp where deptno = 10; 
select ename, sal*0.1 "tax" from emp where deptno = 10; 

-- 비교연산자 

select empno, ename, sal from emp where sal >= 3000; 
select empno, ename, sal from emp where ename >= 'R'; 
select empno, ename, deptno from emp where hiredate >= '82/01/01'; 
-- 숫자 between and 
select empno, ename, sal from emp where sal >= 2000 and sal <= 3000; -- && 
select empno, ename, sal from emp where sal between 2000 and 3000; 
-- 문자 between and
select ename from emp order by ename; 
select ename from emp where ename between 'JAMES' and 'MARTIN';

-- IN 연산자 
select empno, ename, deptno from emp 
where deptno in (10,20); 
select * from emp;
-- LIKE 연산자 : % _ 두개의 기호와 같이 사용 
--      % : 글자수 제한 없고(0개포함) 어떤 글자가 와도 상관없다.
--      _ : 글자수 한글자 뜻하며, 어떤 글자이던 상관없다. 
--      WHERE 컬럼명 LIKE '%_를 이용한 패턴';
--      값% : 값으로 시작하는 
--      %값 : 값으로 끝나는 
--      %값% : 값을 포함하는
--      _값 : 두번째가 값인것 
--      __값 : 세번째가 값인것 
-- 숫자 
select empno, ename, sal from emp 
where sal like '1%'; 
-- 문자 
select empno, ename from emp 
where ename like 'A%';
-- 날짜 
select empno, ename, hiredate from emp 
where hiredate like '80%';
-- 입사월이 12월인 직원들 
select empno, ename, hiredate from emp 
where hiredate like '___12%';
select empno, ename from emp 
where ename like '%T%';
select empno, ename from emp 
where ename like '%N';

-- IS NULL / IS NOT NULL  : 값이 무엇인지 모를경우 
-- NULL은 어떤값이지 모른다는 뜻의 데이터 종류이다. 
-- NULL은 = 같다라는 비교연산자로 비교할 수없다.    == null : is null , != null : is not null 
select * from emp; 
select * from emp 
where comm is null;
select * from emp 
where comm is not null;

-- AND OR : 검색할때 WHERE절 조건식에서 사용 : AND가 OR 보다 우선순위가 높다 
-- AND  : 검색조건이 모두 만족할 때 
-- OR   : 조건중 하나라도 만족할 때 
select empno, ename, hiredate, sal from emp 
where hiredate >= '82/01/01' and sal >= 2000; 
select empno, ename, hiredate, sal from emp 
where hiredate >= '82/01/01' or sal >= 2000; 
-- sal 1000이상 이며, comm < 1000 이하 또는 없는사람들 
select * from emp 
where sal >= 1000 and (comm < 1000 or comm is null);

-- 정렬 : ORDER BY 기준컬럼명 또는 컬럼번호 (옵션) 
--      오름차순 : ASC (default) 
--      내림차순 : DESC
--      SQL 문에서 가장 마지막에 적어야함. 
select * from emp;
select hiredate,ename from emp; 
select * from emp order by ename asc; 
select * from emp order by ename desc; 
-- sal >= 1500, 이름기준 오름차순 
select * from emp 
where sal >= 1500 
order by sal;
select * from emp 
order by deptno asc, sal desc;

select empno, ename, sal, deptno from emp 
order by 4, 3 desc;

/*
-- 집합 연산자 
--  UNION       : 합집합   : 두집합의 결과 합쳐, 중복값 제거하고 정렬 
--  UNION ALL   : 합집합+  : 두집합의 결과 합쳐, 중복제거 안하고 정렬 안함 
--  INTERSECT   : 교집합   : 두집합의 중복되는 값만 가져옴, 정렬. 
--  MINUS       : 차집합   : 두집합의 차집합 결과를 가져옴, 정렬.(집합 작성 순서 중요) 
    * 연산자 사용시 주의사항 
        두 집합의 select 절에 오는 컬럼수가 동일해야함. 
        두 집합의 select 절에 오는 컬럼의 데이터 타입이 동일해야함
        두 집합의 컬럼명은 달라도 상관없음 
*/    
select * from student;
select * from professor;
--Student : STUDNO NAME ID GRADE JUMIN BIRTHDAY TEL HEIGHT WEIGHT DEPTNO1 DEPTNO2 PROFNO
--Professor : PROFNO NAME ID POSITION PAY HIREDATE BONUS DEPTNO EMAIL HPAGE
-- 합집합 
select studno, name, deptno1, 1 
from student where deptno1 = 101 
union 
select profno, name, deptno, 2 
from professor where deptno = 101; 

select studno, name, deptno1, 1 
from student where deptno1 = 101 
union all 
select profno, name, deptno, 2 
from professor where deptno = 101; 

-- 중복 비교  
select studno, name, deptno1, deptno2
from student
where deptno1 = 101
union 
select studno, name, deptno1, deptno2
from student
where deptno2 = 201;

-- intersect : 교집합 
select studno,  name 
from student where deptno1 = 101
intersect
select studno, name 
from student where deptno2 = 201;

-- minus : 차집합 
select empno, ename, sal 
from emp 
minus
select empno, ename, sal 
from emp 
where sal > 2500; 

/*
    ## SQL 함수 ## 
    단일행 함수 : 한번에 한개의 행에 대한 처리를 한번 해주는 함수 : 한개행 -> 한개의 결과 
    복수행 함수 : 한번에 여러개의 행에 대한 처리를 한번 해주는 함수 : 여러개행 -> 한개의 결과 
    
    1) 단일행 함수 
        문자함수, 숫자함수, 날짜함수, 일반함수, 변환함수 
        #1. 문자 함수 
            LOWER('문자')         : 소문자로 변환 
            UPPER('문자')         : 대문자로 변환 
            LENGTH('문자')        : 문자 길이 출력 
            CONCAT('문자','문자') : 문자열 연결 
            SUBSTR('문자', idx1, 개수) : 문자를 idx1 ~ 개수만큼 잘라서 줌. index는 1부터시작 
            LPAD('original문자', length, '삽입문자') 
                        : original에 왼쪽부분에 length 길이가 될때까지 삽입문자를 추가해줌. 
                          'love', 6, '*' -> **love
            RPAD('original문자', length, '삽입문자') 
                        : original에 오른쪽부분에 삽입문자를 추가 
            LTRIM('org문자', '삭제할문자')
                        : org문자에 왼쪽에 있는 삭제할 문자 삭제. '*love', '*' -> love
            RTRIM('org문자', '삭제할문자') 
                        : org문자에 오른쪽에 있는 삭제할 문자 삭제.
        
            REPLACE('org', 'old', 'new')
                        : org문자열안에 old부분을 new로 교체 
            INSTR('문자', '특정문자') : 문자안의 특정문자의 위치 알려줌. 위치 1 ~ 
                        'abcd', 'a' --> 1 
*/
select * from emp;
select ename, LOWER(ename) from emp; 
select ename, length(ename) from emp;
select ename, job, concat(ename, job) from emp;
select substr('abcd', 3, 2) "3,2" from dual;
select substr('abcde', -3, 2) "-3,2" from dual;
select * from student;
select name, jumin, substr(jumin, 1, 6) from student;
-- jumin 컬럼을 이용해서, 학생이름과 태어난 월일, 생일 하루전 날짜 출력, deptno1 = 101 
select name, jumin, substr(jumin, 3, 4) "birthday", LPAD(substr(jumin, 3, 4) - 1,4,'0') "d-1" 
from student where deptno1 = 101;
--LPAD('original문자', length, '삽입문자') 
-- emp테이블에서 ename이름의 첫 두글자를 *로 표시하기 
select ename, replace(ename, SUBSTR(ename, 1, 2), '**') from emp;
select '피카츄' "org", substr('피카츄', 1, 2) from dual;
-- 피**  : REPLACE('org', 'old', 'new') SUBSTR('문자', idx1, 개수)
select '피카츄' "org", replace('피카츄',substr('피카츄', 2, LENGTH('피카츄') - 1) , '**')
from dual;

/*
    2) 숫자 함수 : 입력되는 값이 숫자인 함수들을 말함 
        ROUND(숫자, 소수점수) : 반올림 : 12.345, 2 --> 12.35
        TRUNC(숫자, 소수점수) : 숫자에서 주어진 소수점수만큼 자리 유지하고 뒤는 버림 12.345, 2 -> 13.34
        MOD(숫자1, 숫자2)  : 숫자1을 숫자2로 나눈 나머지값 (자바의 %) 12,10 -> 2 
        CEIL(숫자)        : 올림 
        FLOOR(숫자)       : 내림 
        POWER(숫자1, 숫자2) : 숫자1의 숫자2승   3,2 -> 9
*/
SELECT round(12.545, 0) from dual;
select trunc(12.345, 1) from dual;

/*
    3) 날짜 관련 함수 
        # 날짜 계산 
        날짜 + 숫자 = 날짜  ex) 3월 8일  + 3 = 3월 11일
        날짜 - 숫자 = 날짜 
        날짜 - 날짜 = 숫자  ex) 3월 8일 - 3월 5일 = 3
        # 날짜 함수 
        SYSDATE         : 시스템의 현재 날짜와 시간 
        MONTHS_BETWEEN  : 두 날짜 사이의 개월수 
        ADD_MONTHS      : 주어진 날짜에 개월 더함 
        NEXT_DAY        : 돌아오는 날짜 
        LAST_DAY        : 해당 달의 마지막 날짜
        ROUND           : 주어진 날짜 반올림
        TRUNC           : 주어진 날짜 버림
*/
select sysdate from dual;

select MONTHS_BETWEEN(SYSDATE, '22/04/29') from dual;
select MONTHS_BETWEEN('22/04/29', '22/03/29') from dual;
select add_months(sysdate, 1) from dual;
select sysdate, next_day('22/03/29', '수') from dual;

select sysdate, round(sysdate), trunc(sysdate) from dual;

/*
    3) 형변환 함수 
        # 오라클 데이터 타입 
        varchar2(n) : n 만큼의 변하는 길이의 문자 저장. 최대 4000바이트 
        number(p, s) : 숫자 저장, p 전체자릿수 1~38, s 소수점자리수 -84~127자리까지. ()생략가능 
        date        : 날짜 
        
        char(n)     : n 만큼의 고정길이 문자 저장. 최대 2000바이트 
        long        : 가변길이 문자저장 2GB
        clob        : 가변길이 문자저장 4GB
        raw(n)      : 원시 2진 데이터 200byte
        long raw(n) : 원시 2진 데이터 2GB
        bfile       : 외부파일에 저장된 데이터로 최대 4GB 
        
        # 자동 형변환
            select 2 + '2' from dual; 
        # 강제 형변환 
            * 함수 *
            TO_CHAR(숫자)     : 숫자 -> 문자, 날짜 -> 문자 
            TO_NUMBER('문자') : 문자 -> 숫자 
            TO_DATE('날짜형문자') : 문자 -> 날짜 
            
        ### TO_CHAR(날짜, '패턴') :  날짜 -> 문자 
            * 날짜 표현하는 방법 *
                연도 : YYYY / YY / YEAR
                월 : MM (03) / MONTH (MARCH) / MON (MAR)
                일 : DD (08) / DAY 요일 / DDTH 몇번째 날인지 
                시간 : HH24 (24시간) / HH (12시간) 
                분 : MI 
                초 : SS 
*/
select sysdate, 
to_char(sysdate, 'YYYY') "YYYY", 
to_char(sysdate, 'RRRR') "RRRR",
to_char(sysdate, 'YY') "YY",
to_char(sysdate, 'YEAR') "YEAR"
from dual;

select sysdate, 
to_char(sysdate, 'MM') "MM", 
to_char(sysdate, 'MON') "MON",
to_char(sysdate, 'MONTH') "MONTH"
from dual;

select sysdate, 
to_char(sysdate, 'DD') "DD", 
to_char(sysdate, 'DAY') "DAY",
to_char(sysdate, 'DDTH') "DDTH"
from dual;

select sysdate, to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') from dual;

/*
        ### TO_CHAR(숫자, '패턴') : 숫자 -> 문자 
        * 오라클 표현식 *
        9   9의 개수만큼 자릿수를 표현 
        0   빈자리를 0으로 채움
        $   $표시 붙혀서 표시 
        .   소수점 이하 표시 
        ,   천단위 구분기호 표시 
*/
select to_char(1234, '99999') from dual;
select to_char(1234, '09999') from dual;
select to_char(1234, '$9999') from dual;
select to_char(1234, '9999.99') from dual;
select to_char(1234567, '9,999,999') from dual;

-- emp 테이블을 조회하여 이름이 'ALLEN'인 사원의 사번과 이름과 연봉을 출력 
--      (sal*12)+comm 계산, 천단위 구분기호 표시 
select empno, ename, to_char((sal*12) + comm, '999,999') "SALARY" 
from emp 
where ename = 'ALLEN'; 
-- professor 테이블을 조회, 201부서 교수들의 이름, 급여, 보너스, 연봉(pay*12)+bonus 출력 
select name, pay, bonus, (pay*12)+bonus "SALARY" 
from professor 
where deptno = 201; -- 일단 NULL은 무시, 뒤에서배울 함수 이용하여 해결가능 

-- emp 테이블을 조회, comm 값을 가지고 있는 사람들의 
-- empno, ename, hiredate, 총 연봉, 15%인상된연봉을 출력 
-- 단, 연봉 = (sal*12)+comm 으로 계싼, 15% 연상된것도 총연봉의 15% 인상된값. 연봉에 $표시 추가. 
select empno, ename, hiredate, to_char((sal*12)+comm, '$999,999') "연봉", 
to_char(((sal*12)+comm)* 1.15, '$999,999') "연봉+15%" 
from emp 
where comm is not null; 

-- to_number('문자') : 문자 -> 숫자 
select to_number('1') from dual;
select to_number('A') from dual;
-- to_date('날짜형태의문자') : 문자 -> 날짜 
select to_date('2022/03/10') from dual;
select to_date('2022-03-10') from dual;
select to_date('22/03/10') from dual;
select to_date('22-03-10') from dual;
select to_date('220310') from dual;

/*
    4) 일반함수 
        # NVL(컬럼명, default값) : null값을 만나면 default 값으로 치환해서 처리
        # NVL2(컬럼1, 컬럼2, 컬럼3) : 컬럼1이 null이 아니면 컬럼2, 
                                    컬럼1이 null 이면 컬럼3
*/
-- emp 테이블 comm이 null이 아니면 sal+comm, comm이 null이면 sal
select sal, comm, nvl2(comm, sal+comm, sal) "result"
from emp;

select comm, nvl2(comm, 'Y', 'N') "결과" 
from emp; 

select ename, comm, NVL(comm, 0) 
from emp;

-- professor 테이블을 조회, 201부서 교수들의 이름, 급여, 보너스, 연봉(pay*12)+bonus 출력 
select name, pay, nvl(bonus,0), (pay*12)+NVL(bonus,0) "SALARY" 
from professor 
where deptno = 201; -- 일단 NULL은 무시, 뒤에서배울 함수 이용하여 해결가능 

/*
        # DECODE() : if(삼항연산자)를 오라클 sql로 가져온 함수, 오라클에서만 존재하는 함수 
        #타입1. DECODE(A, B, '1', null) : 마지막 null은 생략 가능 A와 B가 같으면 '1', 아니면 null 
        #타입2. DECODE(A, B, '1', '0') : A와 B가 같으면 '1', 다르면 '0' 으로 
        #타입3. DECODE(A, B, '1', 'C', '0') : A와 B가 같으면 '1', A와 C가 같으면 '0'
        #타입4. DECODE(A, B, DECODE(C, D, '1', null)) : 마지막 null은 생략가능
                        A가 B일 경우, C와D 가 같으면 '1'
        #타입5. DECODE(A, B, DECODE(C, D, '1', '2'))
        #타입5. DECODE(A, B, DECODE(C, D, '1', '2'), '3')
*/
select empno, ename, deptno, decode(deptno, 10, 'NEW YORK') "LOC" 
from emp;
select empno, ename, deptno, decode(deptno, 10, 'NEW YORK', 'ETC') "LOC" 
from emp;
select empno, ename, deptno, decode(deptno, 10, 'NEW YORK', 20, 'DALLAS') "LOC"
from emp;
select empno, ename, deptno, mgr, decode(deptno, 20, DECODE(mgr, 7566, 'hahaha')) "LOC"
from emp;








































-------------------------------------------------------------------------------------
/*
    [ 문자함수 ]
    문제 2-4 : emp 테이블에서, 제공된 이미지(2-4)와 같이 20번 부서에 소속된 직원들의 이름과 
            2 ~ 3번째 글자만 '-'으로 변경해서 출력하세요.
select ename,replace(ename, substr(ename, 2, 2), '--') "REPLACE" 
from emp 
where deptno = 20; 
    문제 2-5 : Student 테이블에서, 제공된 이미지(2-5)와 같이 1전공(depto1)이 101번인 학생들의 
            이름과 주민번호를 출력하되, 주민번호의 뒤 7자리는 '-'과 '/'로 표시되게 출력하세요. 
select name, jumin, replace(jumin, substr(jumin, 7, 7), '-/-/-/-') "REPLACE" 
from student
where deptno1 = 101
    문제 2-6 : Student 테이블에서 제공된 이미지(2-6)와 같이 1전공이 102인 학생들의 
            이름과 전화번호, 전화번호에서 앞자리 부분만 '*'처리하여 출력하세요. 
            단, 모든 앞자리는 3자리로 간주. 
select name, tel, replace(tel, substr(tel, 5,3), '***') "REPLACE" 
from student 
where deptno1 = 102; 
    문제 2-7 : Student 테이블에서, 제공된 이미지(2-7)와 같이 1전공이 101번인 학과 학생들의 
            이름과 전화번호와 전화번호에서 뒷자리를 *로 표시해서 출력하세요. 
select name, tel, replace(tel,substr(tel, instr(tel, '-')+1, 4) ,'****') "REPLACE" 
from student 
where deptno1 = 101; 
    [to_char]   
    문제 2-8 : Student 테이블의 birthday 컬럼을 사용하여 생일이 1월인 학생의 
            학생번호(studno)와 이름, birthday를 제공된 이미지(2-8) 과 같이 출력하세요. 
select studno, name, TO_CHAR(birthday, 'YYYY-MM-DD HH24:MI:SS') "BIRTHDAY" 
from student
where TO_CHAR(birthday, 'MM') = '01'; 
    문제 2-9 : emp 테이블의 hiredate 컬럼을 사용하여 입사일이 1,2,3월인 사람들의 
            사원번호(empno), 이름(ename), 입사일을 제공된 이미지(2-9)와 같이 출력하세요. 
select empno, ename, TO_CHAR(hiredate, 'YYYY-MM-DD HH24:MI:SS') "HIREDATE" 
from emp
where TO_CHAR(hiredate, 'MM') in ('01','02','03') ; 
*/
-------------------------------------------------------------------------------------







