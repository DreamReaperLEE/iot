package heu.iot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Sumail-Lee
 * @Date: 16:17 2017/11/29
 */
@Controller
public class Login {
    @RequestMapping("/")
    public String login(){
        return "login";
    }
}
