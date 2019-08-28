package site.celess.house.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.celess.house.entity.WebConfig;
import site.celess.house.repository.WebConfigRepository;
import site.celess.house.service.WebConfigService;

/**
 * @author : xiaohai
 * @date : 2019/08/28 20:39
 * @Description：
 */
@Service
public class WebConfigServiceImpl implements WebConfigService {
    @Autowired
    WebConfigRepository webConfigRepository;

    @Override
    public WebConfig findByKey(String key) {
        return webConfigRepository.findByKey(key);
    }

    @Override
    public WebConfig save(WebConfig config) {
        return webConfigRepository.save(config);
    }
}
