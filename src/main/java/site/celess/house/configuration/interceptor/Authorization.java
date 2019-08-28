package site.celess.house.configuration.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import site.celess.house.entity.WebConfig;
import site.celess.house.service.WebConfigService;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author: 小海
 * @Date： 2019/08/07 00:26
 * @Description：
 */
@Component
public class Authorization implements HandlerInterceptor {
    @Autowired
    WebConfigService webConfigService;

    private static final Logger logger = LoggerFactory.getLogger(Authorization.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // todo : this is just authorize , and there also need a response data
        if (request.getSession().isNew()) {
            response.sendRedirect("/");
        }
        Cookie[] cookies = request.getCookies();
        String uuid = null;
        for (Cookie cookie : cookies) {
            if ("uuid".equals(cookie.getName())) {
                uuid = cookie.getValue();
                break;
            }
        }
        // get the uuid from memory
        WebConfig uuidConfig = webConfigService.findByKey("uuid");
        if (uuid == null || uuidConfig == null || uuidConfig.getValue() == null) {
            // there isn`t cookie which contain uuid or there isn`t uuid in memory
            return false;
        }
        return uuidConfig.getValue().equals(uuid);
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
    }
}
