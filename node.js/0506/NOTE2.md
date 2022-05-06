# Repository
  저장소  
## 1. 종류  
  * File System : 일반 File System과 Haddop과 같은 분산 파일 시스템  
  * DataBase  
    * 로컬 데이터베이스 : 로컬에서만 접속이 가능한 데이터베이스로 대표적인 Local Database가 SQLite(Android, iOS, Web Brower에는 내장되어 있음)이고 모바일에서 자주 사용하는 데이터를 cacheing할 목적으로 많이 사용  
    * 서버 데이터베이스 : 여러 사용자가 데이터를 공유할 목적으로 로컬 컴퓨터 이외의 곳에 데이터를 보관하는 방식  
      RDBMS(관계형 DB) : 테이블을 이용해서 데이터를 저장하는 방식으로 Oracle, MySQL(MariaDB), MSSQL, Tibero, HANA DB, Postgre SQL 등  
        공공기관에서는 국산을 선호하기 때문에 Tibero도 많이 사용.  
      NoSQL : 테이블 구조를 이용하지 않는 데이터베이스로 여러 종류가 있는데 그 중에서 대중에게 가장 많이 알려진 NoSQL이 MongoDB  
        스마트폰 애플리케이션 제작에는 google의 FireBase를 많이 사용함.  
