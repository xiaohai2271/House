package site.celess.house.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import site.celess.house.BaseTest;
import site.celess.house.entity.Todo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TodoRepositoryTest extends BaseTest {

    @Autowired
    TodoRepository todoRepository;

    @Test
    public void save() {
        // save one data first
        Todo todo = new Todo();
        todo.setDesc("test for save");
        todo.setSchedule(30);
        todo.setCategory(1);
        todo.setTime(System.currentTimeMillis());
        Assert.assertNotNull(todoRepository.save(todo));
        // after save the id is 1
    }

    @Test
    public void findTodoById() {
        Assert.assertNotNull(todoRepository.findTodoById(1));
    }

    @Test
    public void findAllByCategory() {
        Assert.assertNotEquals(0,todoRepository.findAllByCategory(1).size());
    }

    @Test
    public void deleteBatch() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        todoRepository.deleteBatch(list);
        Assert.assertEquals(0,todoRepository.findAllByCategory(1).size());
    }
}