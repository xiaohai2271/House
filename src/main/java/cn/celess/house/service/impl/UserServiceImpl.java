package cn.celess.house.service.impl;

import cn.celess.house.dao.UserDao;
import cn.celess.house.entity.User;
import cn.celess.house.entity.dto.UserDto;
import cn.celess.house.entity.vo.UserVO;
import cn.celess.house.enums.ResponseEnum;
import cn.celess.house.exception.ResponseException;
import cn.celess.house.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: 小海
 * @date： 2021/06/05 11:27
 * @description：
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer, UserVO, User> implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public JpaRepository<User, Integer> getJpaRepository() {
        return userDao;
    }

    @Override
    public UserVO login(UserDto userDto) {
        User userQuery = new User();
        BeanUtils.copyProperties(userDto, userQuery);

        userQuery.setId(null);
        userQuery.setPassword(null);

        User user = userDao.findOne(Example.of(userQuery)).orElseThrow(() -> new ResponseException(ResponseEnum.USER_LOGIN_FAILURE));
        user.setLastLoginTime(System.currentTimeMillis());
        userDao.save(user);

        return user.toViewObject();
    }

    @Override
    public UserVO registration(UserDto userDto) {
        User queryUser = new User();
        queryUser.setUsername(userDto.getUsername());
        queryUser.setEmail(userDto.getEmail());
        User user = userDao.findOne(Example.of(queryUser, ExampleMatcher.matchingAny())).orElse(null);
        if (user != null) {
            throw new ResponseException(ResponseEnum.DATA_HAS_EXIST);
        }

        userDao.save(userDto);

        return null;
    }
}
