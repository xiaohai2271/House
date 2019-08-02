package site.celess.house.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: 小海
 * @Date： 2019/07/27 16:12
 * @Description： todo单项数据
 */
@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Integer id;

    @Column(name = "t_desc")
    private String desc;

    @Column(name = "t_schedule")
    private int schedule;

    @Column(name = "t_category")
    private Integer category;

    @Column(name = "t_time")
    private Long time;
}
