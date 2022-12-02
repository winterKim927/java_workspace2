CREATE SEQUENCE seq_member
INCREMENT BY 1
START WITH 1;

SELECT * FROM MEMBER;

CREATE TABLE Diary(
diary_idx NUMBER PRIMARY key
, yy number
, mm number
, dd number
, content varchar2(1000)
, icon varchar2(20)
);

CREATE SEQUENCE seq_diary
INCREMENT BY 1
START WITH 1;


