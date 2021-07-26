package cn.celess.house.configuration.event;

import cn.celess.house.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @author zhenghai
 * @date 2021/07/26 15:43
 * @description
 */
@Slf4j
@Component
public class HouseAppReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
        String property = environment.getProperty("server.port");
        log.info("House 启动成功！http://127.0.0.1:{}", property);
        if (ApplicationUtil.isFirstRun()) {
            savaConfig(environment);
        }
    }

    private void savaConfig(Environment environment) {
        Properties config = new Properties();
        config.put("spring.sql.init.enabled", "false");
        ApplicationUtil.saveConfig(config);
    }
}
