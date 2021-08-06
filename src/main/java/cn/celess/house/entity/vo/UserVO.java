package cn.celess.house.entity.vo;

import cn.celess.house.entity.BaseUser;
import cn.celess.house.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author: 小海
 * @date： 2021/07/04 14:20
 * @description：
 */
@Data
public class UserVO extends BaseUser implements BaseVO<User> {
    private Integer id;
    private Long lastLoginTime;
    private String avatar;

    @Override
    @JsonIgnore
    public String getPassword() {
        return null;
    }
}
