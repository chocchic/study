create user 'user00'@'%' identified by 'user00';
grant all privileges on *.* to 'user00'@'%';
alter user 'user00'@'%' identified with mysql_native_password by 'user00';

flush privileges;