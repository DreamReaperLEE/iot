package heu.iot.Controller.Student;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.Inform_Emploee;
import heu.iot.Model.Score_Exam_Paper;
import heu.iot.Service.CourseService;
import heu.iot.Service.InformService;
import heu.iot.Service.RecordService;
import heu.iot.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description: 学生端主页
 * @Since: 2017/12/25 9:39
 */
@RequestMapping("/student")
@Controller
public class IndexController {
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private InformService informService;

    @RequestMapping("/index")
    public String IndexPage(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();
        Integer id = Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString());
        //获取最近五科成绩
        List<Score_Exam_Paper> scoreList = scoreService.showRecent5(id);
        //获取平均成绩
        float average=scoreService.getAverageScore(id);
        //获取学习课程数量
        int learnNum=recordService.countLearnNum(id);
        //获取参加过考试数量
        int examNum=scoreService.countExamNum(id);
        //获取课程数量
        int courseNum=courseService.countCourseNum();
        //获取近五条通知
        List<Inform_Emploee> inform_emploeeList=informService.showRecent5();
        //学习课程数量
        model.addAttribute("course_num", learnNum);
        //参加考试数量
        model.addAttribute("exam_num", examNum);
        //课程学习率
        model.addAttribute("course_rates", ((learnNum*100)/courseNum));
        //考试平均成绩
        model.addAttribute("exam_rates", average);
        //最近五门成绩
        model.addAttribute("scoreList", scoreList);
        //最近五条通知
        model.addAttribute("inform_emploeeList", inform_emploeeList);

        return "student/index";
    }
}
