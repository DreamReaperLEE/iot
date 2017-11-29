package heu.iot.Controller.Student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Sumail-Lee
 * @Date: 9:13 2017/11/29
 */
@Controller
@RequestMapping("/student")
public class Exam {
    @RequestMapping("/exam")
    public String allExam(){
        return "student/allExam";
    }
}
