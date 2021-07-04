package cn.celess.house.entity.vo;

import cn.celess.house.entity.TodoTopic;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: 小海
 * @date： 2021/06/05 14:55
 * @description：
 */
@Data
public class TodoTopicVO implements BaseVO<TodoTopic> {
    private Integer id;

    private String title;

    private Date date;

    private UserVO user;

    private String color;

    private String icon;

    private List<TodoItemVO> items;
}
