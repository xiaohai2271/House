-- -----------用户------------
drop table if exists user;

-- 用户表
CREATE TABLE user
(
    id       int primary key auto_increment,
    email    varchar(255) not null,
    password varchar(255) not null
);


-- -------------待办事项----------------
drop table if exists td_topic;
drop table if exists td_item;

-- 待办事项主题表
CREATE TABLE td_topic
(
    id      int primary key auto_increment,
    title   char      not null,
    date    timestamp not null default NOW(),
    user_id int       not null,
    color   varchar(7),
    icon    varchar(255),
    foreign key (user_id) references user (id)
);

-- 待办项列表
CREATE TABLE td_item
(
    id            int primary key auto_increment,
    title         char      not null,
    description   text,
    topic_id      varchar(7),
    create_date   timestamp not null default NOW(),
    complete_date timestamp,
    deadline_date timestamp
);
