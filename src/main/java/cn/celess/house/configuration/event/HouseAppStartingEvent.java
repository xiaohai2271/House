package cn.celess.house.configuration.event;

import cn.celess.house.constant.ApplicationConstant;
import cn.celess.house.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * @author zhenghai
 * @date 2021/07/26 15:06
 * @description
 */
@Slf4j
public class HouseAppStartingEvent implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        Properties config = ApplicationUtil.getConfig();
        if (config != null) {
            environment.getPropertySources().addFirst(new PropertiesPropertySource("dynamic", config));
            log.info("成功从{}加载配置文件", ApplicationConstant.CONFIG_PATH);
        }
    }
}
