package heu.iot.Controller.Teacher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class main {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
