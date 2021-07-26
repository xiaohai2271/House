package cn.celess.house;

import cn.celess.house.util.ApplicationUtil;
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
        if (!ApplicationUtil.isFirstRun()) {
            // 读文件
        }
        SpringApplication.run(HouseApplication.class, args);
        if (ApplicationUtil.isFirstRun()) {
            // 写文件
        }
        logger.info("House 启动成功！");
    }

}
