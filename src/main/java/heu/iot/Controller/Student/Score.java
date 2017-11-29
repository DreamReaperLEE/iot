package heu.iot.Controller.Student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Sumail-Lee
 * @Date: 9:32 2017/11/29
 */
@Controller
@RequestMapping("/student")
public class Score {
    @RequestMapping("/score")
    public String allScore(){
        return "student/allScore";
    }
}
