create table gallery(
gallery_id number primary key
,title varchar2(100)
,writer varchar2(30)
,content clob
,filename varchar2(30)
,regdate date default sysdate
);

CREATE SEQUENCE seq_gallery
INCREMENT BY 1
START WITH 1;