package heu.iot.Controller.Admin;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.Emploee_Course;
import heu.iot.Model.ExamEmploee;
import heu.iot.Model.Inform_Emploee;
import heu.iot.Service.CourseService;
import heu.iot.Service.EmploeeService;
import heu.iot.Service.ExamService;
import heu.iot.Service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description: 管理员端主页
 * @Since: 2018/1/16 9:46
 */
@RequestMapping("/admin")
@Controller
public class AdminIndexController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ExamService examService;

    @Autowired
    private EmploeeService emploeeService;

    @Autowired
    private InformService informService;


    /**
     * @Author: Sumail-Lee
     * @Description:显示管理员主页信息
     * @param session
     * @param model
     * @Date: 2018/2/1 8:56
     */
    @RequestMapping("/index")
    public String IndexPage(HttpSession session, Model model){

        //获取近五条通知
        List<Inform_Emploee> inform_emploeeList=informService.showRecent5();
        //获取最近五门考试
        List<ExamEmploee> examEmploeeList=examService.selectExamEmploeeRecent5();
        //获取最新五门课程
        List<Emploee_Course> emploee_courseList=courseService.showNew5();

        List<String> admintop4=new ArrayList<String>();

        int coursenum=courseService.countCourseNum();
        int examnum=examService.countExamNum();
        int teachernum=emploeeService.countTeacherNum();
        int studentnum=emploeeService.countStudentNum();

        admintop4.add(String.valueOf(coursenum));
        admintop4.add(String.valueOf(examnum));
        admintop4.add(String.valueOf(studentnum));
        admintop4.add(String.valueOf(teachernum));

        session.setAttribute(WebSecurityConfig.AdminTop4, admintop4);
        //最近五条通知
        model.addAttribute("inform_emploeeList", inform_emploeeList);
        //最近五门考试
        model.addAttribute("examEmploeeList", examEmploeeList);
        //最新五门课程
        model.addAttribute("emploee_courseList", emploee_courseList);
        return "admin/admin";
    }
}
