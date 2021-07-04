package cn.celess.house.entity.dto;

import cn.celess.house.entity.User;
import cn.celess.house.entity.vo.BaseVO;
import lombok.Data;

/**
 * @author: 小海
 * @date： 2021/07/04 14:20
 * @description：
 */
@Data
public class UserDTO extends BaseDTO<User> {
    private Integer id;

    private String email;

    private String password;

    @Override
    public User toEntity() {
        return super.beanCopy(this, new User());
    }
}
