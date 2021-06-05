package cn.celess.house.service.impl;

import cn.celess.house.dao.UserDao;
import cn.celess.house.entity.User;
import cn.celess.house.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author: 小海
 * @date： 2021/06/05 11:27
 * @description：
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer, UserDao> implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }
}
