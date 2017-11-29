package heu.iot.Controller.Student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Sumail-Lee
 * @Date: 8:41 2017/11/29
 */

@Controller
@RequestMapping("/student")
public class Course {
    @RequestMapping("/course")
    public String showCourse(){
        return "student/allCourse";
    }
}
