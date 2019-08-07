package site.celess.house.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 小海
 * @Date： 2019/08/07 12:04
 * @Description：
 */
@Service
public interface UserService {
    boolean login(String pwd, HttpServletResponse response);

    boolean logout(HttpServletResponse response);
}
