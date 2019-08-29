package site.celess.house.util;

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
    @Value("${spring.jackson.date-format}")
    private String date_format;

    public String get(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(date_format);
        return sdf.format(date);
    }

    public String getNow() {
        return get(new Date());
    }
}
