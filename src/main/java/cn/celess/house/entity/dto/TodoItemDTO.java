package cn.celess.house.entity.dto;

import cn.celess.house.entity.TodoItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    private Integer topicId;

    private Date createDate;

    private Date completeDate;

    private Date deadlineDate;

    @Override
    public TodoItem toEntity() {
        return beanCopy(this, new TodoItem());
    }
}
