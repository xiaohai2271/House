package site.celess.house.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.celess.house.entity.Response;
import site.celess.house.service.UserService;
import site.celess.house.util.ResponseUtil;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 小海
 * @Date： 2019/08/07 12:38
 * @Description：
 */
@RestController
public class AuthorizationController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Response login(@RequestParam("pwd") String pwd, HttpServletResponse response) {
        return ResponseUtil.success(userService.login(pwd,response));
    }

    @GetMapping("/logout")
    public Response logout(HttpServletResponse response) {
        return ResponseUtil.success(userService.logout(response));
    }
}
