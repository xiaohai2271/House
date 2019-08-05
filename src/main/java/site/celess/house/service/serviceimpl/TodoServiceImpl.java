package site.celess.house.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.celess.house.entity.Todo;
import site.celess.house.entity.TodoCategory;
import site.celess.house.enumpac.ResponseEnum;
import site.celess.house.exception.ResponseException;
import site.celess.house.repository.TodoCategoryRepository;
import site.celess.house.repository.TodoRepository;
import site.celess.house.service.TodoService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: 小海
 * @Date： 2019/08/02 23:25
 * @Description：
 */
@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    TodoCategoryRepository todoCategoryRepository;
    @Autowired
    TodoRepository todoRepository;


    @Override
    public TodoCategory createTodoCategory(String name) {
        // 判断数据是否存在
        if (todoCategoryRepository.existsByName(name)) {
            throw new ResponseException(ResponseEnum.TODOCATEGORY_HAS_EXIST);
        }
        TodoCategory todoCategory = new TodoCategory();
        todoCategory.setName(name);
        todoCategory.setTime(System.currentTimeMillis());
        return todoCategoryRepository.save(todoCategory);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Todo createTodoItem(String desc, Integer categoryId) {
        // 查找categoryId 对应的分类
        TodoCategory todoCategory = todoCategoryRepository.findTodoCategoryById(categoryId);
        if (todoCategory == null) {
            // 分类不存在 即 传入的categoryId 无效
            throw new ResponseException(ResponseEnum.TODOCATEGORY_NOT_EXIST);
        }
        // 设置属性
        Todo todo = new Todo();
        todo.setDesc(desc);
        todo.setCategory(categoryId);
        todo.setTime(System.currentTimeMillis());
        todo.setSchedule(0);
        // 存数据
        todo = todoRepository.save(todo);
        // 更新分类的信息
        List<Integer> todo1 = todoCategory.getTodo();
        todo1.add(todo.getId());
        todoCategory.setTodo(todo1);
        todoCategoryRepository.save(todoCategory);
        return todo;
    }

    @Override
    public TodoCategory updateTodoCategory(String name, Integer id) {
        TodoCategory todoCategoryById = todoCategoryRepository.findTodoCategoryById(id);
        if (todoCategoryById == null) {
            // id不存在
            throw new ResponseException(ResponseEnum.TODOCATEGORY_NOT_EXIST);
        }
        if (todoCategoryRepository.existsByName(name)) {
            // 待修改的那么已存在
            throw new ResponseException(ResponseEnum.TODOCATEGORY_HAS_EXIST);
        }
        todoCategoryById.setName(name);
        return todoCategoryRepository.save(todoCategoryById);
    }

    @Override
    public Todo updateTodoItem(String desc, Integer schedule, Integer id) {
        Todo todo = todoRepository.findTodosByid(id);
        if (todo == null) {
            throw new ResponseException(ResponseEnum.TODO_NOT_EXIST);
        }
        // null 字段不进行更新
        if (desc != null) {
            todo.setDesc(desc);
        }
        if (schedule != null) {
            todo.setSchedule(schedule);
        }
        return todoRepository.save(todo);
    }

    @Override
    public boolean deleteTodoCategory(Integer id) {
        if (!todoCategoryRepository.existsById(id)) {
            throw new ResponseException(ResponseEnum.TODOCATEGORY_NOT_EXIST);
        }
        // FIXME : 删除时应该删除其子item
        todoCategoryRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteTodo(Integer id) {
        if (!todoRepository.existsById(id)) {
            throw new ResponseException(ResponseEnum.TODO_NOT_EXIST);
        }
        // FIXME : 删除todo item时应该更新其对应的分类的todo字段
        todoRepository.deleteById(id);
        return true;
    }

    @Override
    public List<TodoCategory> findAll() {
        return todoCategoryRepository.findAll();
    }

    @Override
    public List<Todo> findByCategory(String name) {
        Integer categoryId = todoCategoryRepository.getIdByName(name);
        if (categoryId == null) {
            throw new ResponseException(ResponseEnum.TODOCATEGORY_NOT_EXIST);
        }
        return todoRepository.findAllByCategory(categoryId);
    }
}
