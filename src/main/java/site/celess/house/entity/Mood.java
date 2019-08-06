package site.celess.house.entity;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 小海
 * @Date： 2019/07/27 14:59
 * @Description： 情绪
 */
@Data
@Entity
public class Mood {
    @Id
    @Column(name = "m_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "m_type")
    private Integer type;

    @Column(name = "m_desc")
    private String desc;

    @Column(name = "m_score")
    private Integer score;

    @Column(name = "m_time")
    private Long time;

    public Date getTime() {
        return new Date(time);
    }
}
