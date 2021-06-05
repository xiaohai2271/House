package cn.celess.house.entity;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * (TdItem)表实体类
 *
 * @author 禾几海
 * @since 2021-06-05 00:41:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "td_item")
public class TodoItem extends BaseEntity<Integer> {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private String topicId;

    private Date createDate;

    private Date completeDate;

    private Date deadlineDate;

    @Override
    public Integer getPrimaryKey() {
        return id;
    }
}