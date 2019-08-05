package site.celess.house.service;

import org.springframework.stereotype.Service;
import site.celess.house.entity.Todo;
import site.celess.house.entity.TodoCategory;

import java.util.List;

/**
 * @Author: 小海
 * @Date： 2019/08/02 23:25
 * @Description：
 */
@Service
public interface TodoService {
    TodoCategory createTodoCategory(String name);

    Todo createTodoItem(String desc, Integer categoryId);

    TodoCategory updateTodoCategory(String name, Integer id);

    Todo updateTodoItem(String desc, Integer schedule, Integer id);

    boolean deleteTodoCategory(Integer id);

    boolean deleteTodo(Integer id);

    List<TodoCategory> findAll();

    List<Todo> findByCategory(String name);

}
