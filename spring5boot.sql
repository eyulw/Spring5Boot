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

/* board */
create table board2(
    bno         int             auto_increment,
    title       varchar(100)    not null ,
    userid      varchar(18)     not null ,
    regdate     datetime        default current_timestamp,
    thumbs      int             default 0,
    views       int             default 0,
    contents    text            not null ,
    ipaddr      varchar(15)     not null,
    primary key (bno)

#     ,foreign key (userid) references member2(userid)
);

alter table board2 add constraint fkuid foreign key (userid) references member2(userid);

insert into board2(title,userid,contents,ipaddr) values ('IT 개발 이야기들 - 요즘IT','abc123','개발은 재밌다','127.0.0.1');
insert into board2(title,userid,contents,ipaddr) values ('자바공부해','abc123a','자바를 잡아라','115.92.164.155');
insert into board2(title,userid,contents,ipaddr) values ('자료구조 알고리즘 공부해라','abc123b','스택..큐..정렬...탐색....','127.127.127.0');

select count(userid) cnt,ceil(count(userid)/25) from board2;

select ceil(count(userid)/25) from board2;

select * from board2 where title like '%아이%';

# pds (personal data store)

create table pds(
    pno         int             auto_increment,
    title       varchar(100)    not null ,
    userid      varchar(18)     not null ,
    regdate     datetime        default current_timestamp,
    thumbs      int             default 0,
    views       int             default 0,
    contents    text            not null ,
    ipaddr      varchar(15)     not null,
    primary key (pno)
);

create table pdsattach(
  pano          int             auto_increment,
  pno           int             not null ,  /*게시글 번호*/
  fname         varchar(200)    not null ,  /*uuid 포함*/
  ftype         varchar(3)      not null ,
  fsize         float           not null ,
  fdown         int             default 0,
  primary key (pano)
);

alter table pds add constraint fkpuid foreign key (userid) references member2(userid);

alter table pdsattach add constraint fkpno foreign key (pno) references pds(pno);

/*join*/
select * from pds p join pdsattach pa
                         using(pno) where p.pno='6';

/*view (가상테이블)*/
create view ppa
as select * from pds p join pdsattach pa
                            using(pno);

select * from ppa where pno='6';

/*comments*/
create table pdscomments(
  cno           int             auto_increment,
  comments      mediumtext      not null ,
  userid        varchar(18)     not null ,
  regdate       datetime        default current_timestamp,
  pno           int             not null ,
  ref           int             not null ,
  primary key (cno)
) ;

alter table pdscomments add constraint fkrefcno foreign key (ref) references pdscomments(cno);
alter table pdscomments add constraint fkpnopno foreign key (pno) references pds(pno);

insert into pdscomments(userid,comments,ref,pno) values ('abc123','댓글1','1','7');
insert into pdscomments(userid,comments,ref,pno) values ('abc123','댓글2','2','7');
insert into pdscomments(userid,comments,ref,pno) values ('def123','댓글3','3','7');
insert into pdscomments(userid,comments,ref,pno) values ('def123','대댓글1','2','7');
insert into pdscomments(userid,comments,ref,pno) values ('abc123a','댓글4','5','7');

select * from pdscomments where pno=7 order by ref;