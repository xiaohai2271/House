package cn.celess.house.entity;

import lombok.Data;

/**
 * (User)表实体类
 *
 * @author 禾几海
 * @since 2021-06-05 00:42:19
 */

@Data
public class User {

    private Integer id;

    private String email;

    private String password;
}
