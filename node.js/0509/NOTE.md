# MySQL  
## 1. Sub Query  
  * 다른 SQL절에 포함된 SQL  
    일반적으로 select 구문에서 많이 사용하는데 insert 구문이나 delete, update구문에서도 사용가능  

### 1) 종류
  * subquery가 리턴되는 행의 개수에 따른 분류
    단일 행 서브 쿼리 : 서브 쿼리가 리턴하는 행의 개수가 0개이거나 1개인 경우  
    다중 행 서브 쿼리 : 서브 쿼리가 리턴하는 행의 개수가 0개 이상인 경우, 2개 이상의 행을 리턴한다면 단일 행 연산자(=,!=,>,>=,<,<=)를 직접 사용 안됨  
    이런 경우는 다중 행 연산자(in, not, in, all, any 등)와 사용해야 합니다.

  * 서브쿼리가 작성된 위치에 다른 분류
    from 절에 사용된 서브쿼리 : Inline View
    where절에 사용된 서브쿼리 : SubQuery

### 2) 서브 쿼리는 메인쿼리보다 먼저 한번만 수행되고 먼저 수행해야 하기때문에 ( )로 감싸게 됩니다.

### 3) 일반 서브쿼리로 해결할 수 없는 상황은 from절에서 2개 이상의 테이블 이름이 사용되는데, select 절에서 2개 테이블의 컬럼을 출력해야 하는 경우는 서브쿼리로 해결할 수 없고, join을 이용해야합니다.  

### 4) usertbl 테이블에서 name이 김태연인 데이터의 birthyear보다 크거나 갘은 birthyear를 가진 데이터의 모든 정보  
```sql  
-- usertbl 테이블에서 name이 김태연인 데이터의 birthyear보다 크거나 같은 birthyear를 가진 모든 컬럼 조회
select birthyear from usertbl where name = '김태연';
select * from usertbl where birthyear >= 1989;
-- 위 문제를 서브쿼리를 이용해서 해결
select * from usertbl where birthyear >= (select birthyear from usertbl where name ='김태연');
```

### 5) usertbl테이블에서 addr이 광주인 데이터와 birthyear가 같은 데이터의 모든 컬럼 조회  
```sql  
-- usertbl테이블에서 addr이 광주인 데이터와 birthyear가 같은 데이터의 모든 컬럼 조회
select birthyear from usertbl where addr ='광주';
select * from usertbl where birthyear = 1994 or birthyear = 1991;
-- 에러 : 서브 쿼리가 리턴하는 데이터가 2개 이상이라서 단일 행 연산자 사용 불가
select * from usertbl where birthyear = (select birthyear from usertbl where addr='광주');

-- 해결 : 다중 행 연산자( = : in, != : not in )
select * from usertbl where birthyear in (select birthyear from usertbl where addr='광주');

-- <. <=, >, >= : ALL이나 ANY와 함께 사용
-- ALL이면 전체가 되는 것이고 ANU는 아무가나 하나
-- MIN, MAX로 대체 가능

-- 예 : 최소값보다 작은 것
select * from usertbl where birthyear < ALL(select birthyear from usertbl where addr='광주');
```

## 2. 정렬  
  * order by 절에서 수행  
  * order by 컬럼 이름이나 연산식 또는 select에서 사용한 컬럼의 인덱스 [asc | desc ]. 다음 정렬 조건 ... ;  
  * asc는 오름차순이고, desc는 내림차순인데 asc는 기본값이라서 생략가능  
  * 여러 개의 정렬 조건을 작성하게 되면 앞의 정렬 조건의 값이 같은 경우 뒤의 조건이 적용  
  * 관계형 DB의 행과 열의 순서는 없다는 특징을 가지고 있으므로 여러 개의 행을 조회할 때 나오는 순서는 예측할 수 없으므로, 2개 이상의 행이 반환될 것 같으면 정렬을 해주는 것이 좋습니다.  

### 1) usertbl 테이블의 name과 birthyear를 조회하는데 birthyear의 오름차순으로 정렬해서 출력  
```sql  
select name, birthyear from usertbl order by birthyear asc;
select name, birthyear from usertbl order by 2; -- asc는 생략가능
```  

### 2) usertbl테이블의 name과 birthyear를 조회하는데 birthyear의 오름차순으로 정렬해서 출력하는데 birthyear의 값이 같으면 name의 내림차순으로 정렬  
``` sql
select name, birthyear from usertbl order by birthyear asc, name desc;
```

## 3. distinct  
  select 절에서 맨 앞에 1번만 작성할 수 있음  
  select 절의 나열된 모든 컬럼의 값이 일치하는 경우만 조회  
### 1) usertbl테이블에서 addr의 값을 중복되지 않게 조회  
```sql  
select distinct addr from usertbl;  
```  

## 4. 페이징, TOP-N  
  select 구문의 마지막 절에 limit 시작 위치번호, 데이터개수를 추가해주면 됩니다.  
  숫자를 한개만 입력하면 시작위치 번호부터 입력한 수자 개수만큼 조회합니다.  
  시작 인덱스는 0부터  

### 1) usertbl 테이블에서 birthyear가 가장 큰 5개의 데이터를 조회  
```sql
select * from usertbl order by birthyear desc;
```

### 2) usertbl 테이블에서 birthyear가 다섯번째로 큰 데이터부터 나머지 모든 데이터를 조회(birthyear를 내림차순 정렬후 처음부터 5개의 데이터만 조회)  
```sql  
select * from usertbl order by birthyear desc limit 5;
```

## 5. 데이터 그룹화  
### 1) 그룹 함수  
  sum  
  avg  
  min  
  max  
  count  

  stddev  
  var_samp

  * 함수들의 매개변수는 컬럼이나 연산식이 되는데 count에서는 * 가능  
  * 함수들은 null인 데이터는 제외하고 집계를 수행  

### 2) group by 절
  컬럼이나 연산식으로 그룹화하고자 할 때 사용  
  2개 이상 그룹화 가능  
  이 절이 수행된 이후 그룹 함수를 사용할 수 있습니다.  
  from과 where절에서는 그룹함수를 사용할 수 없습니다.  

### 3) having 절
  group by 다음에 작성하는 절로 group by 이후에 행 단위 추출을 위한 조건을 설정합니다.  
  그룹함수를 사용하는 것이 일반적입니다.  
  데이터베이스 종류에 따라서는 group by 없이도 사용할 수 있습니다.  
  having절을 잘못 사용하면 비효율적인 sql구문이 만들어질 수도 있습니다.  
 
### 4) 실습
  * usertbl 테이블의 데이터 개수 조회  
```sql  
select count(userid) from buytbl;
select count(*) from buytbl;
```  

  * buytbl의 평균 amount 조회  
```sql  
select avg(amount) from buytbl;
```  

  * buytbl 테이블에서 userid별 평균 amount 조회  
```sql  
select userid, avg(amount) from buytbl group by userid; -- amount함수를 사용하면 그 항목이 어느 값인지에 대한 컬럼은 자동으로 나오지 않으므로 해당 컬럼 값도 같이 찍어줘야함.
```  

  * buytbl 테이블에서 userid의 개수가 3번이상 등장한 데이터의 userid를 조회  
```sql
select userid from buytbl group by userid having count(userid) >= 3;
```  
**집계함수는 from과 where에는 사용불가능하므로 having에서만 사용할 수 있다.**  

  * 비효율적인 having의 사용 : where절에 사용가능한 조건은 where절에 사용  
    buytbl테이블에서 price가 30이상인 데이터가 두번 이상 등장하는 데이터의 userid를 조회  
    ```sql  
    select userid from buytbl where price >= 30 group by userid having count(userid) >=2;
    ```  

  * group by절에서 그룹화를 하게되면 그룹함수와 group by에서 그룹화 하지 않은 컬럼을 같이 출력 못함.  
    mysql은 그룹화하지 않은 항목을 출력하면 첫번째 것 하나만 출력  
    ```sql  
    select addr, name, count(*) from usertbl group by addr;
    ```  

### 5) 전체 총계나 중간 소계를 조회 - with rollup이용  
```sql
select userid, avg(price) from buytbl group by userid, with rollup;
```

## 6. alias
  * 열 이름이나 연산식에 별명을 사용할 수 있는데, 열 이름이나 연산식 뒤에 공백을 하나주고 별명을 입력하면 됩니다.  
    한글을 사용하거나 공백이 있는 경우는 " "로 묶어주어야 합니다.  
    별명 앞에 as를 추가해도 됩니다. 이 형태는 별명이라서 select 절 이후에 사용되는 order by에서 사용이 가능합니다.  

  * from절에서 테이블 이름 뒤에 다름 이름을 부여할 수 있는데, 이 경우는 별명이 아니라 다른 이름으로 변경하는 것입니다.  
    from절에서 테이블 이름에 다른 이름을 부여하면 그 이후 절에서는 그 이름만 사용해야 합니다.  

  * order by절에서 주소 대신 addr로 작성해도 됩니다.  
  ```sql  
  select addr as "주소" from usertbl order by 주소;
  ```  

  * select절에서 U 대신에 usertbl을 사용하면 에러가 발생  
  ```sql
  select U.addr as "주소" from usertbl U order by 주소;
  ```  

## 7. 함수
  매개변수를 받아서 작업을 수행한 후 리턴을 해주는 개체  
  그룹 함수가 아닌 함수는 컬럼을 매개변수로 대입하면 컬럼의 각 행에 작업을 수행한 후 결과를 모아서 컬럼으로 리턴  

### 1) 제어 흐름 함수
  * DB에서는 null과 다른 종류의 데이터가 산술 연산을 하게되면 결과가 null  
  * null관련 함수  
    - ifnull(수식1, 수식2) : 수식1이 null이 아니면 수식1리턴, 수식1이 null이면 수식2가 리턴  
    - nullif(수식1, 수식2) : 수식1과 수식2가 같으면 null을 리턴하고 다르면 수식1을 리턴  

  * EMPLOYEE_SALARY테이블에서 SALARY의 값이 null이면 0, 아니면 원래의 값으로 조회  
  ```sql
  select ifnull(salary, 0) from employee_salary;
  ```  
### 2) 문자열 함수
  * 좌우 공백 제거 : trim  
  * 대소문자 변환 : upper, lower  

  * 분할 : substring(문자열, 시작위치, 길이) - 문자열에서 시작 위치부터 길이만큼 잘라서 리턴  
    관계형 데이터베이스는 하나의 컬럼에 여러 개의 값을 저장할 수 없습니다.  

    사진파일 이름 - a.png|b.png|c.png
    , 로 구분 : csv (Comma-Separated-Values)
  
### 3) 숫자 함수
  * 반올림, 올림, 버림 함수 : CELIING, FLOOR, ROUND  

### 4) 날짜 관련 함수
  * 날짜 연산 함수 : addtime(날짜, 시간), subtime(날짜, 시간)  
    adddate(날짜, 날짜 차이), subdate(날짜, 날짜차이)  
  
  * 현재 날짜 및 시간
    current_date(), currnet_time(), now(), current_timestamp()  

  * 문자열을 날짜로 변환 : 일반적이니 날짜 포맷의 문자열은 날짜로 인식  
    str_to_date(날짜 형식의 문자열, 날짜 포맷)  

## 8. 데이터 삽입  
  insert into 테이블이름(컬럼이름 나열) values(값을 나열);  
  테이블을 만들 때 작성한 컬럼의 순서대로 모든 값을 입력할 때는 컬럼 이름을 나열하지 않아도 됩니다.  
  auto_increment가 적용된 컬럶에 값을 대입하지 않으면 일련번호가 대입됩니다.  
  default가 설정된 경우는 값을 대입하지 않으면 default 값이 대입됩니다.  
  auto_increment나 default가 설정되지 않은 경우 값을 대입하지 않으면 null이 대입됩니다.  

  * usertbl테이블에 userid가 kjn이고 name은 제니, birthyear는 1996, addr은 서울, mobile은 01012341234, mdate는 1996-01-16인 데이터를 삽입
  ```sql
  insert into usertbl(userid, name, birthyear, addr, mobile, mdate) values('kjn', '제니', 1996, '서울', '01012341234', '1996-01-16');
  ```  

  * 여러 개의 데이터를 한꺼번에 삽입 가능  
    insert into 테이블이름(컬럼 이름 나열) values(값1, 값2, ... ), (값1, 값2, ... ), ... ,(값1, 값2, ... );  
    값은 컬럼에 해당하는 값들을 나열  
  
  * subquery를 이용한 추가  
    insert into 테이블이름(컬럼 이름 나열)  
    select 구문  

## 9. 데이터 수정  
  update 테이블이름 set 컬럼이름=수정할 값, ... [where 조건];
  where절이 없으면 테이블의 모든 데이터를 수정  
  where절이 있으면 그 조건에 해당하는 데이터만 수정  

  * usertbl 테이블에서 userid가 kjn인 데이터의 name을 김제니로 수정
  ```sql
  update usertbl set name='김제니' where userid='kjn';
  ```

## 10. 데이터 삭제
  delete from 테이블 이름 [where 조건식];

  * usertbl 테이블에서 userid가 kjn인 데이터 삭제  
  ```sql
  delete from usertbl where userid='kjn';
  ```  

## 11. join  
  * 2개의 테이블을 합쳐서 하나의 테이블을 만드는  작업

### 1) cartesian product - cross join  
  * 2개의 테이블의 모든 조합을 만들어내는 것  
  * 행의 개수는 양쪽 테이블의 행의 개수를 곱한 것과 같고 열의 개수는 양쪽 테이블의 열의 개수를 더한 것과 같음.  
  * from절에 ,로 구분해서 테이블 이름을 2개 나열하거나 테이블이름 사이에 ,대신에 cross join이라고 입력하면 됩니다.

  * usertbl테이블과 buytbl테이블의 cross join  
  ```sql
  select * from usertbl, buytbl;
  select * from usertbl cross join buytbl;
  ```

### 2) Inner Join
  양쪽 테이블의 동일한 의미를 갖는 2개의 컬럼에서 값이 같은 데이터만 합쳐 새로운 테이블을 만드는 join  
  실제 구문 상에서는 자료형만 동일하면 되고, 컬럼의 이름은 아무 상관없음  
  외래키로 설정된 컬럼을 가지고하는 것을 권장  
  컬럼값을 비교할 때 =로 비교하면 equi join이라고 하고, =가 아닌 연산자로 비교하면 non equi join이라고 합니다.  

  조인 조건은 where절에 기술하기도 하고 on을 이용해서 기술할 수도 있고 natural join을 이용할 수도 있는데, natural jpin을 사용할려면 양쪽 테이블의 컬럼이름이 같아야 합니다.  

  * usertbl테이블과 buytbl테이블에는 userid라는 공통된 컬럼이 존재 -> 이 컬럼을 가지고 Inner Join(Equi Join) 수행  
  ```sql
  select * from usertbl, buytbl where usertbl.userid = buytbl.userid;
  select * from usertbl inner join buytbl on usertbl.userid = buytbl.userid;
  select * from usertbl natural join buytbl;
  ```  

### 3) outer join  
  한쪽 테이블에만 존재하는 데이터도 조인에 참여하는 것  
  inner join이라는 예약어 대신에 left outer join, right outer join, full outer join을 사용하면 됩니다.  

  * usertbl 테이블에는 존재하지만 buytbl에는 존재하지 않는 데이터도 join에 참여  
  ```sql
  select * from usertbl left outer join buytbl on usertbl.userid = buytbl.userid;
  ```  

### 4) self join
  동일한 테이블끼리 join  
  이런 경우는 하나의 테이블에 동일한 의미를 갖는 2개의 컬럼이 존재하는 경우 수행  
  하나의 테이블의 사원id가 존재하고, 관리자의 사원id가 존재하고 name이 있을 때, 특정 사원의 관리자 name을 알고자 하는 경우  
  이와 유사한 경우가 SNS에서 친구 테이블의 경우, 회원id와 친구id가 같은 테이블에 존재하는데, 내 친구의 친구를 찾고자 할 때  

  * 실습을 위해 필요한 쿼리
  ```sql 
  CREATE TABLE empTbl (emp CHAR(3), manager CHAR(3), empTel VARCHAR(8));
  INSERT INTO empTbl VALUES(N'나사장',NULL,'0000');
  INSERT INTO empTbl VALUES(N'김재무',N'나사장','2222');
  INSERT INTO empTbl VALUES(N'김부장',N'김재무','2222-1');
  INSERT INTO empTbl VALUES(N'이부장',N'김재무','2222-2');
  INSERT INTO empTbl VALUES(N'우대리',N'이부장','2222-2-1');
  INSERT INTO empTbl VALUES(N'지사원',N'이부장','2222-2-2');
  INSERT INTO empTbl VALUES(N'이영업',N'나사장','1111');
  INSERT INTO empTbl VALUES(N'한과장',N'이영업','1111-1');
  INSERT INTO empTbl VALUES(N'최정보',N'나사장','3333');
  INSERT INTO empTbl VALUES(N'윤차장',N'최정보','3333-1');
  INSERT INTO empTbl VALUES(N'이주임',N'윤차장','3333-1-1');
  ```
  * emp는 자신의 이름이고, manager는 관리자의 이름이고, empTel은 자신의 전화번호
    emp가 김부장인 사원의 관리자 번호를 알고자 하는 경우
  ```sql
  select e1.emp, e1.manager, e1.empTel, e2.empTel as managerTel from emptbl e1, emptbl e2 where e1.manager = e2.emp and e1.emp ='김부장';
  ```  

### 5) join과 subquery  
  * select절과 질의결과가 하나의 테이블에서만 가져오는 경우는 subquery로 해결할 수 있습니다.  
    2개 이상의 테이블에서 가져와야하는 경우는 join으로 해결해야 합니다.  

    usertbl테이블에는 userid, name, birthyear, addr, mdate가 존재
    buytbl테이블에는 num, userid, productname, groupname, price, amount가 존재

  * addr이 서울인 유저의 name과 productname을 조회  
    usertbl 테이블과 buytbl테이블을 이용해야하는데 name과 productname을 조회해야 하므로 join을 이용해서 해결  
    ```sql
    select name, productname from usertbl inner join buytbl on usertbl.userid = buytbl.userid;
    ```  

  * addr이 서울인 유저의 userid와 productname과 price, amount를 조회  
    usertbl테이블과 buytbl 테이블을 이용해야 하는데, userid와 productname과 price, amount가 buytbl에 존재하기 때문에 이 경우는 subquery로도 해결  
    **join을 한 경우에는 양쪽 테이블에 모두 존재하는 컬럼 이름을 사용할 때는 테이블을 명시해야합니다.**  
    ```sql
    -- join으로 해결
    select usertbl.userid, productname, price, amount from usertbl inner join buytbl on usertbl.userid = buytbl.userid where addr='서울';
    select buytbl.userid, productname, price, amount from usertbl inner join buytbl on usertbl.userid = buytbl.userid where addr='서울';
    -- subquery로 해결
    select buytbl.userid, productname, price, amount from buytbl where userid in (select userid from usertbl where addr='서울');
    ```  

### 6) 집합 연산
  * 동일한 구조(컬럼의 자료형과 개수 및 순서가 동일)를 갖는 테이블끼리 연산을 수행하는 것  
  * 합집합  
    select 구문  
    union  
    select 구문  

    union대신에 union all을 하게되면 동일한 데이터를 2번 출력  
    
  * 교집합 : union대신에 intersect
  * 한쪽에만 존재하는 데이터는 except(oracle은 minus)  

## 12. Transaction Control Language
### 1) Transaction
  * 한 번에 이루어져야 하는 작업의 논리적인 단위  
  * 제가 gamemoney를 이용해서 상대방의 item을 구매
    제 gamemoney의 값을 수정 - update  
    상대방 item을 수정 - update  
    상대방 gamemoney 수정 - update  
    제 item 수정 - update  
  
  이런 경우는 4개의 update 구문이 모두 수행되거나 하나돋 수행되지 않아야 합니다.  
  이렇게 한꺼번에 수행되어야 하는 작업의 단위를 트랜잭션이라고 합니다.  

### 2) commit, rollback  
  * commit : 작업이 완료  
  * rollback : 작업을 철회

### 3) Transaction의 성질  
  * Atomicity(원자성) : All or nothing
  * Consistency(일관성) : 트랜잭션 수행 전과 후가 일관성 있어야함(ex. 은행 예금 총액의 변동이 있어서는 안됨)  
  * Isolation(격리성) : 트랜잭션 수행 중에는 다른 트랜잭션이 이 데이터를 수정하면 안된다.(동시 기록 방지)
  * Durability(영속성) : 한 번 완료된 트랜잭션은 계속 되어야한다.(ex. atm이나 창구에서 발생한 거래는 취소가 안됨. 출금한 돈은 다시 입금하는 수밖에 없음 -> 거래를 취소할 수 있으면 많은 문제가 발생할 수 있다!)  

### 4) savepoint  
  * 트랜잭션이 수행완료되거나 철회가 될 때는 DB에 lock이 걸려서 다른 작업을 수행할 수 없습니다.
  * commit을 일정한 주기를 가지고 하는 방법으로 위의 문제를 해결할 수 있는데 이렇게 만들면 rollback할 때 너무 많이 rollback을 수행해야 합니다.
    중간 중간에 rollback할 수 있는 지점을 만드는데 이 지점을 savepoint 라고 합니다.  

### 5)commit 되는 경우  
  * DDL(Create, Alter, Drop, Truncate, Rename) 이나 DCL(Grant, Revoke)을 성공적으로 수행한 경우  
  * 접속 프로그램이 정상 종료되는 경우  
  * 명시적으로 commit을 호출하는 경우  

### 6)rollback 되는 경우  
  * 명시적으로 rollback을 호출하는 경우  
  * 접속 프로그램의 비정상적인 종료  
