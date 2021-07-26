package cn.celess.house.util;

import java.io.File;
import java.util.regex.Matcher;

/**
 * @author zhenghai
 * @date 2021/07/26 15:24
 * @description
 */
public class FileUtil {
    public static String getRealPath(String path) {
        if (path == null || path.isEmpty()) {
            return "";
        }
        if (path.startsWith("~")) {
            path = path.replaceFirst("~", Matcher.quoteReplacement(System.getProperty("user.home")));
        }
        return path.replaceAll("[\\/]+", Matcher.quoteReplacement(File.separator));
    }
}
