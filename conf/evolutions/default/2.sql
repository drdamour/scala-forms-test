# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

INSERT INTO "TYPEA" (NAME) VALUES ('NAME 1');
INSERT INTO "TYPEA" (NAME) VALUES ('NAME 2');
INSERT INTO "TYPEA" (NAME) VALUES ('NAME 3');

INSERT INTO "TYPEB" (PROP2) VALUES ('PROP 1');
INSERT INTO "TYPEB" (PROP2) VALUES ('PROP 2');
INSERT INTO "TYPEB" (PROP2) VALUES ('PROP 3');

# --- !Downs

delete from "TYPEA";
delete from "TYPEB";

