package cn.celess.house.entity;

import cn.celess.house.entity.dto.BaseDTO;
import cn.celess.house.entity.vo.UserVO;
import lombok.Data;

import javax.persistence.*;

/**
 * (User)表实体类
 *
 * @author 禾几海
 * @since 2021-06-05 00:42:19
 */

@Data
@Entity
@Table(name = "user")
public class User implements BaseEntity<User, Integer>, BaseDTO<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;

    @Override
    public Integer getPrimaryKey() {
        return id;
    }


    @Override
    public UserVO toViewObject() {
        return beanCopy(this, new UserVO());
    }

    @Override
    public User toEntity() {
        return this;
    }
}
