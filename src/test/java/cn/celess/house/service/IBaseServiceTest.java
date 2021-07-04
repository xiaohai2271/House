package cn.celess.house.service;

import cn.celess.house.AbstractTest;
import cn.celess.house.entity.User;
import cn.celess.house.entity.vo.UserVO;
import cn.celess.house.util.StringsUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


public class IBaseServiceTest extends AbstractTest {

    @Autowired
    UserService userService;

    @Test
    public void insert() {
        UserVO insert = userService.insert(createUser());
        assertNotNull(insert.getId());
    }

    @Test
    public void remove() {
        UserVO insert = userService.insert(createUser());

        assertNotNull(insert.getId());

        assertEquals(insert, userService.queryById(insert.getId()));
    }

    @Test
    public void update() {
        User user = createUser();
        UserVO insert = userService.insert(createUser());

        assertNotNull(insert.getId());

        user.setPassword(StringsUtil.getMD5("11111111"));
        user.setId(insert.getId());
        UserVO update = userService.update(user);

        assertEquals(update, userService.queryById(insert.getId()));

    }

    @Test
    public void queryById() {
        UserVO user = userService.insert(createUser());
        assertNotNull(user);
    }

    @Test
    public void queryAll() {
        List<UserVO> users = userService.queryAll();
        assertTrue(users.size() > 0);
    }

    private User createUser() {
        User user = new User();
        user.setEmail("zh56462271@qq.com");
        user.setPassword(StringsUtil.getMD5("123456"));
        return user;
    }
}
