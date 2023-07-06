/* member */
create table member2(
  mno       int             auto_increment,
  userid    varchar(18)     unique ,
  passwd    varchar(18)     not null,
  name      varchar(10)     not null,
  email     varchar(50)     unique,
  zipcode   char(7)         not null,
  addr1     varchar(100)    not null,
  addr2     varchar(100)    not null,
  phone     char(13)        not null,
  regdate   datetime        default current_timestamp,
  primary key (mno)
);

insert into member2(userid, passwd, name, email, zipcode, addr1, addr2, phone)
values ('abc123','987xyz','minha','minha@abc123.co.kr','123-456','서울 관악구','블라블라','123-4567-8912');

select * from member2;