-- 댓글2 테이블 : 댓글 + 댓글

create table reply2(
        rno number,
        bno number not null,
        reply varchar2(1000) not null,
        replyer varchar2(50) not null,
        replyDate date default sysdate,
        updateDate date default sysdate,
        grp number,
        step number,
        lev number
);

-- grp : 댓글과 그의 답글을 그룹핑해줄 컬럼(댓글의 rno 값 대입)
-- step : 답글의 순서
-- lev : 댓글의 답글인지, 답글의 답글인지 ~> 들여쓰기 몇번 되는지
alter table reply2 drop column updateDate;
-- pk 추가
alter table reply2 add constraint pk_reply2 primary key(rno);
-- fk 추가
alter table reply2 add constraint fk_reply2 foreign key(bno) REFERENCES board(bno);

-- 댓글 시퀀스
create sequence reply_seq2 nocache;
commit;

desc reply2;

select * from board;
select * from reply2;

select reply_seq2.nextval from dual;

select rownum r, rno, bno, reply, replyer, replyDate, grp, step, lev from
    (select rownum r, rno, bno, reply, replyer, replyDate, grp, step, lev from
        (select rno, bno, reply, replyer, replyDate, grp, step, lev from reply2 where bno = 41 order by grp desc, step asc)
    where rownum <= 10 order by grp desc, step asc)
where r > 0 ;

