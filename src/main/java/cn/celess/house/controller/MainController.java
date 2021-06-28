package cn.celess.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author: 小海
 * @Date： 2019/08/07 16:59
 * @Description：
 */
@Controller
@RequestMapping
public class MainController {

    @RequestMapping("/**")
    public String todo(){
        return "forward:/assets/index.html";
    }
}
