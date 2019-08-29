package site.celess.house.service.serviceimpl;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.celess.house.entity.WebConfig;
import site.celess.house.enumpac.ResponseEnum;
import site.celess.house.exception.ResponseException;
import site.celess.house.repository.WebConfigRepository;
import site.celess.house.service.UserService;
import site.celess.house.util.MD5Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author: 小海
 * @Date： 2019/08/07 12:05
 * @Description：
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    WebConfigRepository webConfigRepository;


    @Override
    public boolean login(String pwd, HttpServletResponse response) {
        // get password configuration from database
        WebConfig pwdConfig = webConfigRepository.findByKey("password");
        if (pwdConfig == null || pwdConfig.getValue() == null || "".equals(pwdConfig.getValue())) {
            throw new ResponseException(ResponseEnum.NO_PASSWORD);
        }
        if (pwd == null) {
            throw new ResponseException(ResponseEnum.PASSWORD_ILLEGAL);
        }
        if (!MD5Util.getMD5(pwd).toUpperCase().equals(pwdConfig.getValue())) {
            throw new ResponseException(ResponseEnum.LOGIN_FAILURE);
        }
        // create the unique user id
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        // store the uuid to db
        WebConfig uuidConfig = webConfigRepository.findByKey("uuid");
        if (uuidConfig == null) {
            uuidConfig = new WebConfig();
        }
        uuidConfig.setValue(uuid);
        webConfigRepository.save(uuidConfig);

        // store the last login time to db
        WebConfig lastLoginTimeConfig = webConfigRepository.findByKey("lastLoginTime");
        if (lastLoginTimeConfig == null) {
            lastLoginTimeConfig = new WebConfig();
        }
        lastLoginTimeConfig.setValue(System.currentTimeMillis() + "");
        webConfigRepository.save(lastLoginTimeConfig);

        // create cookie
        Cookie cookie = new Cookie("uuid", uuid);
        cookie.setMaxAge(10800);
        response.addCookie(cookie);
        return true;
    }

    @Override
    public boolean logout(HttpServletResponse response) {
        WebConfig uuidConfig = webConfigRepository.findByKey("uuid");
        if (uuidConfig == null) {
            return true;
        }
        uuidConfig.setValue(null);
        webConfigRepository.save(uuidConfig);

        // remove cookie
        Cookie cookie = new Cookie("uuid", "delete");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return true;
    }

    @Override
    public JSONObject loginStatus(HttpServletRequest request) {
        Cookie c = null;
        JSONObject responseJson = new JSONObject();
        for (Cookie cookie : request.getCookies()) {
            if ("uuid".equals(cookie.getName())) {
                c = cookie;
                break;
            }
        }
        if (c == null) {
            throw new ResponseException(ResponseEnum.HAVE_NOT_LOGIN);
        }
        WebConfig lastLoginTime = webConfigRepository.findByKey("lastLoginTime");
        responseJson.put("lastLoginTime", lastLoginTime.getValue());
        return responseJson;
    }
}
