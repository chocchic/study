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
-- 다중 행 연산자
-- = : in, != : not in
select * from usertbl where birthyear in (select birthyear from usertbl where addr='광주');

-- <. <=, >, >= : ALL이나 ANY와 함께 사용
-- ALL이면 전체가 되는 것이고 ANU는 아무가나 하나
-- MIN, MAX로 대체 가능

-- 최소값보다 작은 것
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
