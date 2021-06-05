package cn.celess.house.entity;

import java.util.Date;

import lombok.Data;

import javax.persistence.*;

/**
 * (TdTopic)表实体类
 *
 * @author 禾几海
 * @since 2021-06-05 00:42:01
 */
@Data
@Entity
@Table(name = "td_topic")
public class TodoTopic extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private Date date;

    private Integer userId;

    private String color;

    private String icon;

    @Override
    public Integer getPrimaryKey() {
        return id;
    }
}
