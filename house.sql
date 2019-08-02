CREATE DATABASE `house`;

USE `house`;

CREATE TABLE `mood`
(
    `m_id`    int        NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `m_type`  tinyint(1) NOT NULL comment '心情类型',
    `m_desc`  tinytext   NOT NULL comment '描述',
    `m_score` tinyint(2) NOT NULL comment '心情分0-10',
    `m_time`  bigint(13) not null comment '时间戳'
) comment '心情表';

CREATE TABLE `todo_category`
(
    `tc_id`   int          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `tc_name` varchar(255) NOT NULL,
    `tc_todo` varchar(255),
    `tc_time` bigint(13)   NOT NULL comment '创建时间'

) comment 'todo的分类表';

CREATE TABLE `todo`
(
    `t_id`       int PRIMARY KEY AUTO_INCREMENT,
    `t_desc`     text       NOT NULL,
    `t_schedule` tinyint(3) NOT NULL default 0,
    `t_category` int        NOT NULL,
    `t_time`     bigint(13) NOT NULL
) comment 'TODO list';

CREATE TABLE `web_config`
(
    `config_id`    int PRIMARY KEY AUTO_INCREMENT,
    `config_name`  varchar(255) not null,
    `config_value` varchar(255) not null,
    unique key `u_config_name` (`config_name`)
);


### 密码为123456789
INSERT INTO `web_config`(config_name, config_value)
VALUES ('password', '25F9E794323B453885F5181F1B624D0B'),
       ('dateFormat', 'yyyy-MM-dd HH:mm:ss')
