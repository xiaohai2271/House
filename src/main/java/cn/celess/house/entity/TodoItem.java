package cn.celess.house.entity;

import java.util.Date;

import lombok.Data;

/**
 * (TdItem)表实体类
 *
 * @author 禾几海
 * @since 2021-06-05 00:41:14
 */
@Data
public class TodoItem {

    private Integer id;

    private String title;

    private String description;

    private String topicId;

    private Date createDate;

    private Date completeDate;

    private Date deadlineDate;
}
