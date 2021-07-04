package cn.celess.house.service.impl;

import cn.celess.house.dao.UserDao;
import cn.celess.house.entity.User;
import cn.celess.house.entity.dto.UserDTO;
import cn.celess.house.entity.vo.UserVO;
import cn.celess.house.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: 小海
 * @date： 2021/06/05 11:27
 * @description：
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer, UserVO, UserDTO> implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public JpaRepository<User, Integer> getJpaRepository() {
        return userDao;
    }
}
