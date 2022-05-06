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