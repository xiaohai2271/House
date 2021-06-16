package cn.celess.house.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : xiaohai
 * @date : 2019/08/29 11:25
 * @Description：
 */
@Component
public class DateFormatUtil {
    //    @Value("${spring.jackson.date-format}")
    public String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public String get(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    public String get(Long time) {
        if (time == null || String.valueOf(time).length() != 13) {
            return null;
        }
        return get(new Date(time));
    }

    public String getNow() {
        return get(new Date());
    }
}
