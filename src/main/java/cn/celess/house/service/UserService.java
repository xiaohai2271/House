package cn.celess.house.service;

import cn.celess.house.entity.User;
import cn.celess.house.entity.dto.UserDTO;
import cn.celess.house.entity.vo.UserVO;
import org.springframework.stereotype.Service;

/**
 * (User)表服务接口
 *
 * @author 禾几海
 * @since 2021-06-05 00:42:20
 */
@Service
public interface UserService extends IBaseService<User, Integer, UserVO, UserDTO> {
}
