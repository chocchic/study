# MySQL
## 1. Data Type : 테이블에 저장되는 데이터의 자료형(데이터가 메모리에 저장되는 크기와 형식)  
### 1) 숫자 데이터 형식  
  bit  
  tinyint  
  smallint  
  mediumint  
  int(integer) : 절대값 21억 정도의 정수
  bigint  
  float : 10의 38승 정도 실수, 소수는 7째 자리 정도까지의 정밀도  
  double : 10의 308승 정도 실수, 소수는 15째 자리 정도까지의 정밀도  
  decimal(m,d) : d를 이용해서 소수 자릿수를 설정하는 것이 가능  

### 2) 문자 데이터 형식
  * char(크기) : 255자까지의 고정 길이 문자열, 처음 설정한 길이보다 작은 길이의 문자열이 오더라도 크기를 변화시키지 않음  
  * varchar(크기) : 65545자 까지의 가변 길이 문자열, 처음 설정한 길이보다 작은 길이의 문자열이 오면 빈 공간을 제거하고 길이만큼만 공간을 사용, 메모리효율을 높일 수 있음, 좌우 공백이 만들어지지 않기 떄문에 사용하기가 편리, 데이터의 수정이 빈번히 발생하는 경우 메모리 공간을늘였다 줄였다 하다보면 Row Migraton(행 이주)이 발생해서 작업 속도가 현저히 느려질 수 있습니다.  
  * char와 varchar차이
            char(5)         varchar(5)
  a b      ac     b         ac b
  a를 ac로
  수정
  
  * binary  
  * varbinary  
  * text(tinytext, text, mediumtext, longtext) : 문자열을 저장, index 설정 안됨  
  * blob(tinyblob, blob, mediumblob, lonbblob) : byte 배열을 저장, 파일의 내용을 저장하고자 할 때 사용  
  * enum : 열거형 상수  
  * set  
### 3) 데이터베이스에 파일 저장  
  * 데이터베이스에 파일의 경로를 저장  
    보통 프로젝트가 저장된 컴퓨터에 파일을 저장하는 경우가 많은데 실무에서는 별도의 파일 서버(amazon의 s3 cloud storage 등)를 만들어서 그 곳에 파일을 저장하고 경로를 사용합니다.

  * 데이터베이스에 blob타입으로 파일의 내용을 저장  

### 4) 날짜 - 데이터베이스는 날짜를 숫자로 취급  
  * mysql의 경우는 누구나 알 수 있는 날짜 형식의 문자열도 날짜로 취급
  Date : 날짜  
  Datetime : 날짜와 시간  
  timestamp : 날짜와 시간  
  time : 시간  
  year : 년도  

  * 날짜를 저장하고자 하는 경우 출력을 많이 하는 경우라면 문자열로 저장하는 것이 좋고 연산을 많이 하는 경우라면 날짜 데이터 형식으로 저장하는 것이 좋습니다.

### 5) BOOL  
### 6) JSON  
### 7) GEOMETRY : 공간 데이터 형식  

## 2. Constraint(제약조건)  
  데이터 무결성(정합성, Integrity) 유지를 위해서 테이블에 설정하는 조건  
### 1) 설정하는 위치에 따른 분류
  컬럼레벨 제약조건 : 컬럼을 만들 때 바로 설정  
  테이블렐벨 제약조건 : 컬럼을 다 만들고 난 후 설정  

### 2) 종류  
  * not null : 필수, 공간의 크기를 결정하기 때문에 컬럼 레벨에서만 설정할 수 있음  
  * unique : 유일성, 중복될 수 없음, null은 저장가능  
  * check : 값의 종류나 범위(Domain)를 설정  
  * primary key(기본키, 주키) : 테이블 내의 유일한 식별자로 한번만 설정 가능, 하나 이상의 컬럼으로 구성 가능, 2개 이상의 컬럼으로 기본키를 설정하는 경우 테이블 레벨로만 설정 가능, not null & unique  
  * foreign key(외래키, 주키) : 다른 테이블의 데이터를 참조하기 위해서 설정하는 것으로 다른 테이블에서 unique나 primary key인 속성을 지정하고 옵션을 추가합니다. 이 값은 null이거나 다른 테이블의 참조할 수 있는 값만 가져야 합니다.
    다른 테이블에서 foreign key로 참조되는 테이블은 삭제할 수 없습니다.  
    별다른 옵션 없이 마들면 foreign key가 참조하는 데이터도 삭제할 수 없습니다.  
    옵션으로는 on delete cascade(부모 테이블에서 삭제될때 같이 삭제), on delete set null(부모테이블에서 삭제되면 null로 설정),
    on update cascade(부모테이블에서 수정되면 같이 수정)  

    default : 기본값 설정  

    auto_increment : 일련번호를 입력해주는 것으로 unique나 primary key와 함께 사용되어야 하고 테이블에서 1번만 설정 가능  
  * ex) 학원 수강 관련 데이터 구조 설계
      주민번호 이름 전화번호 주소 수강과목코드 수강과목 수강료  
      1     김      010     서울    1       Java        500000
      1     김      010     서울    2       Python      300000
      2     이      011     인천    1       Java        500000
      **abnormal발생 -> normalization으로 해결**
      주민번호 이름 전화번호 주소  
      1     김      010     서울  
      1     김      010     서울  
      2     이      011     인천     
      수강과목코드 수강과목 수강료  
        1       Java        500000  
        2       Python      300000  
      주민번호 수강과목코드  
      1         1
      1         2
      2         1
      양쪽 테이블의 관계가 1:1이면 양쪽 테이블의 기본키를 상대방 테이블의 외래키로 추가  
      양쪽 테이블의 관계가 1:N이면 1쪽 테이블의 기본키를 N쪽 테이블의 외래키로 추가  
      양쪽 테이블의 관계가 N:N이면 양쪽 테이블의 기본키를 외래키로 갖는 별도의 테이블을 만들어서 1:N 관계 2개로 분할  
      
  * null  
      Java나 C언어는 null을 별도로 저장  
      데이터베이스는 null을 별도로 저장, null을 저장할 수 있는 자료형을 만들면 원래 크기보다 1개 더 크게 공간을 할당하고, 그 1개의 공간에 null 여부를 표시  

      ex) name char(5)  
        null일떄 : 1-----  
        null아닐 때 : 0abcde  
## 3. DDL(Data Definition Language)  
  * 구조(개체)를 생성하고 변경하고 삭제하는 SQL  
  * 종류는 Crate, Alter, Drop, Truncate, Rename  

### 1) 테이블 생성  
```sql
  create [temporary] table [if not exists] 테이블이름(
    컬럼이름 자료형 컬럼레벨 제약조건,
    ...
    [constraint 제약조건이름] 테이블 레벨 제약조건
  )옵션;
```
  * 옵션  
    - Engine  
      * MyISAM : 조회에 유리한 엔진으로 트랜잭션 기능이 약함  
      * InnoDB : 트랜잭션 처리에 유리하도록 만드는 엔진  
    - DEFAULT CHARSET : 테이블을 생성할 때 인코딩 설정하는 옵션  
    - auto_increment : 일련번호 시작 숫자 설정  