package heu.iot.Controller.Teacher;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.Course;
import heu.iot.Model.Source;
import heu.iot.Service.*;
import heu.iot.Util.dealFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/4/17 17:09
 */

@Controller
@RequestMapping("/teacher/paper")
public class TeacherPaperController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private Co_directService co_directService;
    @Autowired
    private Co_typeService co_typeService;
    @Autowired
    private SourceService sourceService;

    @RequestMapping("/showCourseList")
    public String showCourseList(Model model, HttpSession session) {
        List<Course> courseList=courseService.selectByTid(Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString()));
        model.addAttribute("courseList",courseList);
        return "teacher/Course/CourseList";
    }



}
