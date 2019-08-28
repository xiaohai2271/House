package site.celess.house.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.celess.house.entity.Response;
import site.celess.house.enumpac.ResponseEnum;
import site.celess.house.service.UserService;
import site.celess.house.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 小海
 * @Date： 2019/08/07 12:38
 * @Description：
 */
@RestController
@RequestMapping("/api")
public class AuthorizationController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Response login(@RequestParam("pwd") String pwd, HttpServletResponse response) {
        return ResponseUtil.response(userService.login(pwd, response) ? ResponseEnum.LOGIN_SUCCESS : ResponseEnum.LOGIN_FAILURE);
    }

    @GetMapping("/logout")
    public Response logout(HttpServletResponse response) {
        return ResponseUtil.success(userService.logout(response));
    }

    @GetMapping("/loginStatus")
    public Response loginStatus(HttpServletRequest request) {
        return ResponseUtil.success(userService.loginStatus(request));
    }

}
