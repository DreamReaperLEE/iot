package heu.iot.Controller.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Sumail-Lee
 * @Date: 9:13 2017/11/29
 */
@Controller
@RequestMapping("/student")
public class ExamController {


    @RequestMapping("/exam")
    public String allExam(Model model){
        return "student/allExam";
    }
}
