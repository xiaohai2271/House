package cn.celess.house.entity.vo;

import cn.celess.house.entity.User;
import lombok.Data;

/**
 * @author: 小海
 * @date： 2021/07/04 14:20
 * @description：
 */
@Data
public class UserVO implements BaseVO<User> {
    private Integer id;

    private String email;
}
