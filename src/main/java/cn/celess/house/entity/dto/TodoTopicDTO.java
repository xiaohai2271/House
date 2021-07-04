package cn.celess.house.entity.dto;

import cn.celess.house.entity.TodoTopic;
import lombok.Data;

import java.util.Date;

/**
 * @author: 小海
 * @date： 2021/06/05 14:47
 * @description：
 */
@Data
public class TodoTopicDTO implements BaseDTO<TodoTopic> {
    private Integer id;

    private String title;

    private Date date;

    private Integer userId;

    private String color;

    private String icon;

    @Override
    public TodoTopic toEntity() {
        return beanCopy(this, new TodoTopic());
    }
}
