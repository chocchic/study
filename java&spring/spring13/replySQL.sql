 select bno, title, content, writer, regdate, updatedate from
             (select rownum r, bno, title, content, writer, regdate, updatedate from 
                 (select /*+ INDEX_DESC(board pk_board)*/ 
                  bno, title, content, writer, regdate, updatedate 
                  from board) 
             where rownum <= 10) 
         where r > 0;
         
-- ´ñ±Û ½ÃÄö½º
create table reply(
         rno number,
        bno number not null,
        reply varchar2(1000) not null,
        replyer varchar2(50) not null,
        replyDate date default sysdate,
        updateDate date default sysdate
);
drop table reply;
commit;
alter table reply add constraint pk_reply primary key(rno);
alter table reply add constraint fk_reply foreign key(bno) REFERENCES board(bno);

-- ´ñ±Û ½ÃÄö½º
create sequence reply_seq;

desc reply;
select * from reply;
select * from board;