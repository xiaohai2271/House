package site.celess.house.service;

import org.springframework.stereotype.Service;
import site.celess.house.entity.Todo;
import site.celess.house.entity.TodoCategory;

import java.util.List;

/**
 * @Author: 小海
 * @Date： 2019/08/02 23:25
 * @Description： operation of database
 */
@Service
public interface TodoService {
    /**
     * create a 'TodoCategory'
     *
     * @param name name of the category
     * @return data after insert
     */
    TodoCategory createTodoCategory(String name);

    /**
     * create a item data which belong to categoryId
     *
     * @param desc       the description detail of the item
     * @param categoryId the category's id, it should be exist,or it would not be create the data
     * @return data after insert
     */
    Todo createTodoItem(String desc, Integer categoryId);

    /**
     * update data
     *
     * @param name name of the category
     * @param id   id of the category
     * @return data after update
     */
    TodoCategory updateTodoCategory(String name, Integer id);

    /**
     * update data
     *
     * @param desc     the description detail of the item
     * @param schedule completion schedule
     * @param id       id
     * @return data after update
     */
    Todo updateTodoItem(String desc, Integer schedule, Integer id);

    /**
     * remove a data, if it has child item they also would be remove
     *
     * @param id id of the data
     * @return remove status
     */
    boolean deleteTodoCategory(Integer id);

    /**
     * remove single item
     *
     * @param id id of the data
     * @return remove status
     */
    boolean deleteTodo(Integer id);

    /**
     * get all category
     *
     * @return a list of category
     */
    List<TodoCategory> findAll();

    /**
     * get all item whose category'name is param
     *
     * @param name category'name
     * @return a list of item
     */
    List<Todo> findByCategory(String name);

}
