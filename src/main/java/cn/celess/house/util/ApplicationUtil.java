package cn.celess.house.util;

import cn.celess.house.constant.ApplicationConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author zhenghai
 * @date 2021/07/26 9:44
 * @description
 */
@Slf4j
public class ApplicationUtil {
    private static Properties properties;


    public static boolean isFirstRun() {
        Properties config = getConfig();
        return config == null || config.size() == 0;
    }

    public static Properties getConfig() {
        if (properties != null) {
            return properties;
        }
        synchronized (ApplicationUtil.class) {
            if (properties == null) {
                String configPath = FileUtil.getRealPath(ApplicationConstant.CONFIG_PATH);
                try {
                    properties = new Properties();
                    properties.load(new FileInputStream(configPath));
                } catch (FileNotFoundException e) {
                    log.info("读取文件失败，在[{}]未找到项目配置文件", configPath);
                    properties = null;
                } catch (IOException e) {
                    log.error("解析配置文件失败");
                    properties = null;
                    e.printStackTrace();
                }
            }
            return properties;
        }
    }

    public static void saveConfig(Properties config) {
        String configPath = FileUtil.getRealPath(ApplicationConstant.CONFIG_PATH);
        try {
            config.store(new FileOutputStream(configPath), "save");
        } catch (FileNotFoundException e) {
            log.info("写入文件失败，在[{}]未找到项目配置文件", configPath);
        } catch (IOException e) {
            log.error("写入文件失败");
            e.printStackTrace();
        }
    }
}
