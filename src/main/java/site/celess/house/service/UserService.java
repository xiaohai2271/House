package site.celess.house.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 小海
 * @Date： 2019/08/07 12:04
 * @Description：
 */
@Service
public interface UserService {
    /**
     * login
     *
     * @param pwd      password
     * @param response HttpServletResponse
     * @return login status
     */
    boolean login(String pwd, HttpServletResponse response);

    /**
     * logout
     *
     * @param response HttpServletResponse
     * @return logout status
     */
    boolean logout(HttpServletResponse response);

    /**
     * get login status
     *
     * @param request HttpServletRequest
     * @return a json object contain lastLoginTime
     */
    JSONObject loginStatus(HttpServletRequest request);
}
