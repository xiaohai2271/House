package site.celess.house.configuration.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import site.celess.house.util.RedisUtil;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 小海
 * @Date： 2019/08/07 00:26
 * @Description：
 */
@Component
public class Authorization implements HandlerInterceptor {


    private static final Logger logger = LoggerFactory.getLogger(Authorization.class);
    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        String uuid = null;
        for (Cookie cookie : cookies) {
            if ("uuid".equals(cookie.getName())) {
                uuid = cookie.getValue();
                break;
            }
        }
        // 获取缓存里面的uuid
        String uuidFromCache = redisUtil.get("House-uuid");
        if (uuid == null || uuidFromCache == null) {
            // 没读取到 uuid 的cookie 或者 缓存里么没写入uuid (伪造的uuid cookie)
            return false;
        }
        return uuidFromCache.equals(uuid);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Cookie[] cookies = request.getCookies();
        Cookie uuidCookie = null;
        for (Cookie cookie : cookies) {
            if ("uuid".equals(cookie.getName())) {
                uuidCookie = cookie;
                break;
            }
        }
        if (uuidCookie == null) {
            logger.debug("uuidCookie为空，not expected value =>{}", new Date());
            throw new Exception("not expected value");
        }
        // 更新cookie
        uuidCookie.setMaxAge(10800);
        response.addCookie(uuidCookie);
        // 更新redis
        redisUtil.setEx("House-uuid", uuidCookie.getValue(), 3, TimeUnit.HOURS);
    }
}
