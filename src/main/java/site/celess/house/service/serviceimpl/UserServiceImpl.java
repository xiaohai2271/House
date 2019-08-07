package site.celess.house.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import site.celess.house.service.UserService;
import site.celess.house.util.MD5Util;
import site.celess.house.util.RedisUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 小海
 * @Date： 2019/08/07 12:05
 * @Description：
 */
@Service
public class UserServiceImpl implements UserService {
    @Value("${house.password}")
    private String password;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean login(String pwd,HttpServletResponse response) {
        if (pwd == null) {
            return false;
        }
        if (MD5Util.getMD5(pwd).toUpperCase().equals(password)) {
            // 缓存写数据
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            redisUtil.setEx("House-uuid", uuid, 3, TimeUnit.HOURS);
            // cookie 写数据
            Cookie cookie = new Cookie("uuid", uuid);
            cookie.setMaxAge(10800);
            response.addCookie(cookie);
            return true;
        }
        return false;
    }

    @Override
    public boolean logout(HttpServletResponse response) {
        String uuid = redisUtil.get("House-uuid");
        if (uuid != null) {
            // 删除redis里面的uuid
            redisUtil.delete("House-uuid");
        }
        // 删除cookie
        Cookie cookie = new Cookie("uuid", uuid);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return true;
    }
}
