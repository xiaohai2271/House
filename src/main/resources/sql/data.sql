INSERT INTO user (id, email, password)
VALUES (1, 'a@celess.cn', '123456');

insert into td_topic(id, title, date, user_id, color, icon)
VALUES (1, 'hello', '2020-1-1 18:06', 1, '#ffffff', '');

insert into td_item(id, title, description, topic_id, create_date, complete_date, deadline_date)
VALUES (1, '测试1', 'test1', 1, '2020-1-2 15:30', null, null),
       (2, '测试2', 'test2', 1, '2020-1-2 15:30', null, null)
;
