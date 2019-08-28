package site.celess.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.celess.house.entity.WebConfig;

/**
 * @author : xiaohai
 * @date : 2019/08/28 16:41
 * @Description：
 */
public interface WebConfigRepository extends JpaRepository<WebConfig, Integer> {

    /**
     * find config by key
     * @param key key name
     * @return webConfig
     */
    WebConfig findByKey(String key);

}
