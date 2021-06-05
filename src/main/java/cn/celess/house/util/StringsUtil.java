package cn.celess.house.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;

/**
 * @author: 小海
 * @date： 2021/06/05 21:50
 * @description：
 */
@Slf4j
public class StringsUtil {
    private static ObjectMapper objectMapper;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getMD5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public static <T> String toJson(T obj) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
        }
        String s;
        try {
            s = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.debug("occur error when " + obj.getClass().getName() + "‘s instance obj write to json, exception message is : " + e.getMessage());
            return Strings.EMPTY;
        }
        return s;
    }

    public static <T> T toObject(String jsonStr, Class<T> tClass) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
        }
        T obj;
        try {
            obj = objectMapper.readValue(jsonStr, tClass);
        } catch (JsonProcessingException e) {
            log.debug("occur error when serialization jsonStr to " + tClass.getName() + " object, exception message is " + e.getMessage());
            return null;
        }
        return obj;
    }
}
