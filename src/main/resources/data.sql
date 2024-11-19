INSERT INTO MEMBER (name, create_dt) VALUES('A', now());
INSERT INTO MEMBER (name, create_dt) VALUES('B', now());

INSERT INTO ACCOUNT (amount, create_dt, member_seq) VALUES(100000, now(), 1);
INSERT INTO ACCOUNT (amount, create_dt, member_seq) VALUES(100000, now(), 2);