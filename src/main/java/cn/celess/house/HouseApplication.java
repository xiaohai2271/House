package cn.celess.house;

import cn.celess.house.configuration.event.HouseAppReadyEvent;
import cn.celess.house.configuration.event.HouseAppStartingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HouseApplication {
    private static final Logger logger = LoggerFactory.getLogger(HouseApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(HouseApplication.class);
        application.addListeners(new HouseAppStartingEvent());
        application.addListeners(new HouseAppReadyEvent());
        application.run(args);
    }

}
