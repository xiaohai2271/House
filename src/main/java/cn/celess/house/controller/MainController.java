package cn.celess.house.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: 小海
 * @Date： 2019/08/07 16:59
 * @Description：
 */
@Controller
@Slf4j
public class MainController {

    @GetMapping({"/", "${server.error.path:${error.path:/error}}"})
    public String todo() {
        return "forward:/index.html";
    }
}
