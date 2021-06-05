package cn.celess.house.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author: 小海
 * @date： 2021/06/05 14:55
 * @description：
 */
@Data
public class TodoTopicVO extends BaseVO<TodoTopicVO> {
    private Integer id;

    private String title;

    private Date date;

    private Integer userId;

    private String color;

    private String icon;
}
