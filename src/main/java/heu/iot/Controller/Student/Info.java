package heu.iot.Controller.Student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Sumail-Lee
 * @Date: 9:45 2017/11/29
 */
@Controller
@RequestMapping("/student")
public class Info {
    @RequestMapping("/info")
    public String changePassword(){
        return "student/info";
    }
}
