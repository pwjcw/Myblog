create database blog  character set utf8;
use blog;
create table user  (
                       user_id int primary key  auto_increment,
                       user_name varchar(10),
                       user_pwd varchar(40),
                       user_type int,
                       user_state int,
                       register_time datetime
);
create table msg(
                    msg_id int primary key  auto_increment,
                    msg_title varchar(50),
                    msg_content varchar(10000),
                    msg_username varchar(10),
                    msg_time datetime
);