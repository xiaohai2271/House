package cn.celess.house.entity.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author: 小海
 * @date： 2021/06/05 14:48
 * @description：
 */
@Data
public class TodoItemVO extends BaseVO<TodoItemVO> {
    private Integer id;

    private String title;

    private String description;

    private Integer topicId;

    private Date createDate;

    private Date completeDate;

    private Date deadlineDate;
}
