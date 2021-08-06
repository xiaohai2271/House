package cn.celess.house.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;

/**
 * @author zhenghai
 * @date 2021/08/01 20:51
 * @description
 */
@Data
public class BaseUser {
    private String username;

    private String email;

    @JsonIgnore
    private String password;
}
