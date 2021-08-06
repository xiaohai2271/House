drop table if exists td_item;
drop table if exists td_topic;
drop table if exists user;



-- -----------用户------------
-- 用户表
CREATE TABLE user
(
    id              int primary key auto_increment,
    username        varchar(255) not null,
    email           varchar(255) not null,
    password        varchar(255) not null,
    avatar          varchar(255) default null,
    last_login_time bigint       default null,
    github_token    varchar(255) default null,
    wechat_token    varchar(255) default null,
    qq_token        varchar(255) default null
);



-- -------------待办事项----------------
-- 待办事项主题表
CREATE TABLE td_topic
(
    id      int primary key auto_increment,
    title   char(255) not null,
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
    title         char(255) not null,
    description   text,
    is_done       tinyint  not null  default 0,
    topic_id      int,
    create_date   timestamp not null default NOW(),
    complete_date timestamp,
    deadline_date timestamp,
    foreign key (topic_id) references td_topic (id)
);
