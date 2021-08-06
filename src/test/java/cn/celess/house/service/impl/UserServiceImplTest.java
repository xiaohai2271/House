package cn.celess.house.service.impl;

import cn.celess.house.AbstractTest;
import cn.celess.house.dao.UserDao;
import cn.celess.house.entity.User;
import cn.celess.house.entity.dto.UserDto;
import cn.celess.house.service.UserService;
import cn.celess.house.util.StringsUtil;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest extends AbstractTest {

    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;

    User user;


    @BeforeEach
    void createUser() {
        user = new User();
        user.setUsername(randomStr());
        user.setEmail(randomStr());
        user.setPassword(randomStr());
        user = userDao.save(user);
        System.out.println("新建用户：" + StringsUtil.toJson(user));

    }

    @Test
    void login() {
    }

    @Test
    void registration() {
    }

    @AfterEach
    void deleteUser() {
        userDao.delete(user);
        System.out.println("清理用户：" + StringsUtil.toJson(user));
    }
}