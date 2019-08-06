package site.celess.house.service.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.celess.house.entity.Todo;
import site.celess.house.entity.TodoCategory;
import site.celess.house.enumpac.ResponseEnum;
import site.celess.house.exception.ResponseException;
import site.celess.house.repository.TodoCategoryRepository;
import site.celess.house.repository.TodoRepository;
import site.celess.house.service.TodoService;

import java.util.List;
import java.util.Optional;

/**
 * @Author: 小海
 * @Date： 2019/08/02 23:25
 * @Description：
 */
@Service
public class TodoServiceImpl implements TodoService {
    private final static Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
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
    @Transactional(rollbackFor = Exception.class)
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteTodoCategory(Integer id) {
        Optional<TodoCategory> todoCategoryOptional = todoCategoryRepository.findById(id);
        if (!todoCategoryOptional.isPresent()) {
            throw new ResponseException(ResponseEnum.TODOCATEGORY_NOT_EXIST);
        }
        TodoCategory todoCategory = todoCategoryOptional.get();
        // 批量删除
        todoRepository.deleteBatch(todoCategory.getTodo());
        todoCategoryRepository.deleteById(id);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteTodo(Integer id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        // 查询数据是否存在
        if (!optionalTodo.isPresent()) {
            throw new ResponseException(ResponseEnum.TODO_NOT_EXIST);
        }
        // 获取待删除的todo item
        Todo todo = optionalTodo.get();
        // 获取todo item对应的分类信息
        TodoCategory todoCategory = todoCategoryRepository.findTodoCategoryById(todo.getCategory());
        if (todoCategory == null) {
            // 此处不应该出现这种情况，若出现则说明数据库被修改
            logger.debug("=> id:{}删除失败", todo.getCategory());
            return false;
        }
        // 先更新分类的todo字段
        List<Integer> todo1 = todoCategory.getTodo();
        todo1.removeIf(todo2 -> todo2.equals(todo.getId()));
        todoCategory.setTodo(todo1);
        todoCategoryRepository.save(todoCategory);
        // 删除todo item
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
