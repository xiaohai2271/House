package cn.celess.house.service.impl;

import cn.celess.house.dao.TodoItemDao;
import cn.celess.house.entity.TodoItem;
import cn.celess.house.service.TodoItemService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author: 小海
 * @date： 2021/06/05 12:29
 * @description：
 */
@Service
public class TodoItemServiceImpl extends BaseServiceImpl<TodoItem, Integer> implements TodoItemService {
    private TodoItemDao todoItemDao;

    public TodoItemServiceImpl(TodoItemDao todoItemDao) {
        super(todoItemDao);
        this.todoItemDao = todoItemDao;
    }
}
