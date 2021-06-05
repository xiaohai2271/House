package cn.celess.house.service;

import cn.celess.house.BaseTest;
import cn.celess.house.entity.User;
import cn.celess.house.util.MD5Util;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class IBaseServiceTest extends BaseTest {

    @Autowired
    UserService userService;

    @Test
    public void insert() {
        User user = createUser();
        userService.insert(user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void remove() {
        User user = createUser();
        userService.insert(user);

        Assert.assertNotNull(user.getId());

        Assert.assertEquals(user, userService.queryById(user.getId()));
    }

    @Test
    public void update() {
        User user = createUser();
        userService.insert(user);

        Assert.assertNotNull(user.getId());

        user.setPassword(MD5Util.getMD5("11111111"));
        userService.update(user);

        Assert.assertEquals(user, userService.queryById(user.getId()));

    }

    @Test
    public void queryById() {
        User user = userService.insert(createUser());
        Assert.assertNotNull(user);
    }

    @Test
    public void queryAll() {
        List<User> users = userService.queryAll();
        Assert.assertTrue(users.size() > 0);
    }

    private User createUser() {
        User user = new User();
        user.setEmail("zh56462271@qq.com");
        user.setPassword(MD5Util.getMD5("123456"));
        return user;
    }
}