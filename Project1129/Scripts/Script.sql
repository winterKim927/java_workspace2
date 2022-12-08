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

CREATE TABLE topcategory(
topcategory_idx NUMBER PRIMARY KEY
, topcategory_name varchar2(20)
);

CREATE SEQUENCE seq_topcategory
INCREMENT BY 1
START WITH 1;

CREATE TABLE subcategory(
	subcategory_idx NUMBER PRIMARY KEY
	, topcategory_idx NUMBER -- 부모의 pk
	, subcategory_name varchar2(20)
	, CONSTRAINT fk_topcategory_subcategory FOREIGN KEY (topcategory_idx) 
	references topcategory(topcategory_idx)
);


CREATE SEQUENCE seq_subcategory
INCREMENT BY 1
START WITH 1;

-- 상위 카테고리중 상의 등록
INSERT INTO topcategory(topcategory_idx, TOPCATEGORY_name) 
VALUES(seq_topcategory.nextval, '상의');

SELECT * FROM topcategory;

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 1,'티셔츠'); 

SELECT * FROM subcategory;


DELETE FROM subcategory;

DROP TABLE topcategory;

DROP TABLE subcategory;

DROP sequence seq_subcategory;

INSERT INTO topcategory(topcategory_idx, TOPCATEGORY_name) 
VALUES(seq_topcategory.nextval, '하의');

INSERT INTO topcategory(topcategory_idx, TOPCATEGORY_name) 
VALUES(seq_topcategory.nextval, '액세서리');

INSERT INTO topcategory(topcategory_idx, TOPCATEGORY_name) 
VALUES(seq_topcategory.nextval, '신발');

INSERT INTO topcategory(topcategory_idx, TOPCATEGORY_name) 
VALUES(seq_topcategory.nextval, '모자');

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 1,'가디건'); 

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 1,'니트'); 

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 1,'점퍼'); 

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 2,'면바지'); 

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 2,'반바지'); 

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 2,'청바지'); 

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 2,'레깅스'); 

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 3,'귀걸이'); 

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 3,'목걸이'); 

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 3,'팔찌'); 

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 3,'반지'); 

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 4,'운동화'); 

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 4,'구두'); 

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 4,'샌들'); 

INSERT INTO subcategory(SUBCATEGORY_IDX, topcategory_idx, SUBCATEGORY_name) 
VALUES(seq_subcategory.nextval, 4,'슬리퍼'); 


CREATE TABLE product(
product_idx NUMBER PRIMARY KEY
, subcategory_idx NUMBER
, product_name varchar2(30)
, brand varchar2(30)
, price NUMBER DEFAULT 0
, filename varchar2(20)
, CONSTRAINT fk_subcategory_product FOREIGN KEY (subcategory_idx) 
	references subcategory(subcategory_idx)
);

CREATE SEQUENCE seq_product
INCREMENT BY 1
START WITH 1;


SELECT s.subcategory_idx, subcategory_name, product_idx, product_name, brand, price, filename
FROM SUBCATEGORY s , PRODUCT p WHERE s.SUBCATEGORY_IDX = p.SUBCATEGORY_IDX;

SELECT t.topcategory_idx, topcategory_name, s.subcategory_idx, subcategory_name, product_idx, product_name, brand, price, filename
FROM topcategory t, subcategory s, product p
WHERE t.topcategory_idx = s.topcategory_idx
AND s.subcategory_idx = p.subcategory_idx
AND product_idx = 1;

SELECT s.subcategory_idx AS subcategory_idx, subcategory_name, product_idx, product_name, brand, price, filename
FROM topcategory t, subcategory s, product p
WHERE t.TOPCATEGORY_IDX  = s.TOPCATEGORY_IDX 
AND s.SUBCATEGORY_IDX = p.SUBCATEGORY_IDX 
AND topcategory_name = '액세서리';

