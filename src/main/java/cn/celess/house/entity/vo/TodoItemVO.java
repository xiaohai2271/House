package cn.celess.house.entity.vo;

import cn.celess.house.entity.TodoItem;
import lombok.Data;

import java.util.Date;

/**
 * @author: 小海
 * @date： 2021/06/05 14:48
 * @description：
 */
@Data
public class TodoItemVO implements BaseVO<TodoItem> {
    private Integer id;

    private String title;

    private String description;

    private TodoTopicVO topic;

    private Boolean done;
    
    private Date createDate;

    private Date completeDate;

    private Date deadlineDate;
}
