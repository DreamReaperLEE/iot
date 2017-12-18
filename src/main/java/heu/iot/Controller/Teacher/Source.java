package heu.iot.Controller.Teacher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangyueyan on 2017/12/5.
 */


@Controller
@RequestMapping("/teacher")
public class Source {
    @RequestMapping("/source")
    public String source() {
        return "teacher/AllSource";
    }

}