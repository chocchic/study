-- 테이블 생성 
CREATE TABLE memberSec(
	id varchar2(50), 
	pw varchar2(100) NOT NULL, 
	name varchar2(50) NOT NULL,
	email varchar2(100) NOT NULL,
	gender varchar2(20) NOT NULL,
	age NUMBER NOT NULL, 
	reg DATE DEFAULT sysdate, 
	updatedate DATE DEFAULT sysdate, 
	enabled varchar2(10) DEFAULT '1', 
	CONSTRAINT memberSec_pk PRIMARY KEY(id)
);
CREATE TABLE memberSec_auth(
	id varchar2(50) NOT NULL, 
	auth varchar2(50) NOT NULL, 
	CONSTRAINT memberSec_auth_fk FOREIGN KEY(id) REFERENCES memberSec(id)
); 
COMMIT;

SELECT * FROM memberSec;
SELECT * FROM memberSec_auth;

-- remember-me 지정된 형식의 테이블 생성 
CREATE TABLE persistent_logins(
	username varchar2(64) NOT NULL, 
	series varchar2(64) PRIMARY KEY, 
	token varchar2(64) NOT NULL, 
	last_used timestamp NOT NULL
); 
SELECT * FROM persistent_logins;


