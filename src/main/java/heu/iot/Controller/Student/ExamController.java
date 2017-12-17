package heu.iot.Controller.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 学生考试类
 * @Author: Sumail-Lee
 * @Date: 9:13 2017/11/29
 */
@Controller
@RequestMapping("/student")
public class ExamController {

    /**
     * @Author: Sumail-Lee
     * @Description: 展示所有正在进行考试
     * @param model
     * @Date: 2017/12/17 20:56
     */
    @RequestMapping("/exam")
    public String allExam(Model model){

        return "student/allExam";
    }
}
