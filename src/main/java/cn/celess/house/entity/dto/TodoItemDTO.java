package cn.celess.house.entity.dto;

import cn.celess.house.entity.TodoItem;
import lombok.Data;

import java.util.Date;

/**
 * @author: 小海
 * @date： 2021/06/05 14:47
 * @description：
 */
@Data
public class TodoItemDTO extends BaseDTO<TodoItem> {
    private Integer id;

    private String title;

    private String description;

    private String topicId;

    private Date createDate;

    private Date completeDate;

    private Date deadlineDate;

}
