package site.celess.house.util;

import org.springframework.util.DigestUtils;

/**
 * @Author: 小海
 * @Date： 2019/08/07 00:31
 * @Description：
 */
public class MD5Util {
    public static String getMD5(String str) {
        String md5 = DigestUtils.md5DigestAsHex(str.getBytes());
        return md5;
    }
}