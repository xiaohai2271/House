package cn.celess.house.service.impl;

import cn.celess.house.dao.TodoItemDao;
import cn.celess.house.dao.TodoTopicDao;
import cn.celess.house.entity.TodoTopic;
import cn.celess.house.service.TodoTopicService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 小海
 * @date： 2021/06/05 12:32
 * @description：
 */
@Service
public class TodoTopicServiceImpl extends BaseServiceImpl<TodoTopic, Integer, TodoTopicDao> implements TodoTopicService {

    private TodoTopicDao todoTopicDao;

    @Resource
    private TodoItemDao todoItemDao;

    public TodoTopicServiceImpl(TodoTopicDao repository) {
        super(repository);
        this.todoTopicDao = repository;
    }

    @Override
    public TodoTopic queryById(Integer integer) {
        TodoTopic todoTopic = super.queryById(integer);
        todoTopic.setTodos(todoItemDao.findAllByTopicId(todoTopic.getId()));
        return todoTopic;
    }

    @Override
    public List<TodoTopic> queryAll() {
        return super.queryAll().stream()
                .peek(todoTopic -> todoTopic.setTodos(todoItemDao.findAllByTopicId(todoTopic.getId())))
                .collect(Collectors.toList());
    }
}
