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
--insert into test values(#{id}, #{pw},#{age},sysdate);
