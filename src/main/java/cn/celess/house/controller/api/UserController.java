package cn.celess.house.controller.api;

import cn.celess.house.entity.Response;
import cn.celess.house.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: 小海
 * @date： 2021/06/05 14:39
 * @description：
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/login")
    public Response<String> login() {
        return null;
    }
}
