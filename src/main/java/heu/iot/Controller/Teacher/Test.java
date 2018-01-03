package heu.iot.Controller.Teacher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Test")
public class Test {

    @RequestMapping("/test")
    public String test()
    {
        return "teacher_test/AllSource_test";
    }

    @RequestMapping("AllSource_test")
    public String showAllSource()
    {
        return "/teacher_test/AllSource_test";
    }
}
