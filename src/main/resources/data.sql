INSERT INTO scope(name) values ('HELLO.READ');
INSERT INTO scope(name) values ('HELLO.WRITE');
INSERT INTO scope(name) values ('BYE.READ');
INSERT INTO scope(name) values ('BYE.WRITE');

INSERT INTO authority(name) values ('ADMIN');
INSERT INTO authority(name) values ('WRITE');
INSERT INTO authority(name) values ('READ');

INSERT INTO authority_scope values (1,1);
INSERT INTO authority_scope values (1,2);
INSERT INTO authority_scope values (1,3);
INSERT INTO authority_scope values (1,4);


INSERT INTO authority_scope values (2,2);
INSERT INTO authority_scope values (2,4);


INSERT INTO authority_scope values (3,1);
INSERT INTO authority_scope values (3,3);

