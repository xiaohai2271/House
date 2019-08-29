package site.celess.house.service;

import org.springframework.stereotype.Service;
import site.celess.house.entity.WebConfig;

/**
 * @author : xiaohai
 * @date : 2019/08/28 20:38
 * @Description：
 */
@Service
public interface WebConfigService {
    /**
     * find a configuration by key name
     *
     * @param key key Name
     * @return configuration
     */
    WebConfig findByKey(String key);

    /**
     * insert or update a configuration into db
     *
     * @param config the configuration to insert or update
     * @return the configuration  after update
     */
    WebConfig save(WebConfig config);
}
