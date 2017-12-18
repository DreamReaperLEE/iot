package heu.iot.Controller.Teacher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangyueyan on 2017/12/4.
 */
@Controller
@RequestMapping("/teacher")
public class Paper {
    @RequestMapping("/paper")
    public String allpaper() {
        return "teacher/Allpaper";
    }
}
