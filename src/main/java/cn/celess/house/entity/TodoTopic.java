package cn.celess.house.entity;

import java.util.Date;

import lombok.Data;

/**
 * (TdTopic)表实体类
 *
 * @author 禾几海
 * @since 2021-06-05 00:42:01
 */
@Data
public class TodoTopic {

    private Integer id;

    private String title;

    private Date date;

    private Integer userId;

    private String color;

    private String icon;
}
