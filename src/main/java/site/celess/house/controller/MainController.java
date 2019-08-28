package site.celess.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 小海
 * @Date： 2019/08/07 16:59
 * @Description：
 */
@Controller
public class MainController {

    @RequestMapping({"", "index"})
    public String index() {
        return "index";
    }

}
