select * from movie;
select count(*) from movie;
select * from movie_image;
select count(*) from movie_image;

select * from m_member;
desc m_member;

select * from review;
select movie_mno, count(*) from review group by movie_mno;
select member_mid, count(*) from review group by member_mid;

-- drop table movie;
-- drop table movie_image;
-- drop table m_member;

