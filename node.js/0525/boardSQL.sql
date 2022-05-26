show databases;
-- 테이블 구조 확인
desc tbl_member;
desc board;
desc reply;
drop table tbl_member;
drop table board;
drop table reply;
commit;
-- 데이터 100개 들어갔는지 확인
select * from tbl_member;
select * from board;
select * from reply;

-- reply테이블에서 board_bno로 그룹화해서 board_bno와 데이터 개수를 조회
-- 데이터 개수의 내림차순으로 정렬
select board_bno, count(*) from reply group by board_bno order by 2 desc;