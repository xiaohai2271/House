package site.celess.house.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: 小海
 * @Date： 2019/07/27 16:18
 * @Description： 网站信息配置
 */
@Data
@Entity
public class WebConfig {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "config_id")
    private Integer id;

    @Column(name = "config_name")
    private String name;

    @Column(name = "config_value")
    private String value;
}
