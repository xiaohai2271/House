package site.celess.house.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author : xiaohai
 * @date : 2019/08/28 16:34
 * @Description：
 */
@Entity
@Data
@Table(name = "web_config")
public class WebConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wc_id")
    private int id;

    @Column(name = "wc_key")
    private String key;

    @Column(name = "wc_value")
    private String value;

    public WebConfig() {
    }

    public WebConfig(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
