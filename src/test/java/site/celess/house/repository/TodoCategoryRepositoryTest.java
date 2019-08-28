package site.celess.house.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import site.celess.house.BaseTest;
import site.celess.house.entity.TodoCategory;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TodoCategoryRepositoryTest extends BaseTest {

    @Autowired
    TodoCategoryRepository repository;

    @Test
    public void save() {
        TodoCategory todoCategory = new TodoCategory();
        todoCategory.setName("test");
        todoCategory.setTodo(new ArrayList<>());
        todoCategory.setTime(System.currentTimeMillis());
        repository.save(todoCategory);
        // after insert the data's id is 1
    }

    @Test
    public void existsById() {
        Assert.assertTrue(repository.existsById(1));
    }

    @Test
    public void existsByName() {
        Assert.assertTrue(repository.existsByName("test"));
    }

    @Test
    public void findTodoCategoryById() {
        Assert.assertNotNull(repository.findTodoCategoryById(1));
    }

    @Test
    public void getIdByName() {
        assertEquals(1, (int) repository.getIdByName("test"));
    }
}