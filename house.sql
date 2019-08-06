CREATE DATABASE `house`;

USE `house`;

CREATE TABLE `mood`
(
    `m_id`    int        NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `m_type`  tinyint(1) NOT NULL comment '情绪类型',
    `m_desc`  tinytext   NOT NULL comment '描述',
    `m_score` tinyint(2) NOT NULL comment '情绪分0-10',
    `m_time`  bigint(13) not null comment '时间戳'
) comment '情绪表';

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
