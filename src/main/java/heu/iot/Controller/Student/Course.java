package heu.iot.Controller.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author: Sumail-Lee
 * @Date: 8:41 2017/11/29
 */

@Controller
@RequestMapping("/student")
public class Course {
    @Autowired
    private Course course;

    @RequestMapping({"/course","/"})
    public String showCourse(HttpSession session, Model model){

        model.addAttribute("catogary",null);
        return "student/allCourse";
    }
}
