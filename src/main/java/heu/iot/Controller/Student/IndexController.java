package heu.iot.Controller.Student;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.Score_Exam_Paper;
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


    @RequestMapping("/index")
    public String IndexPage(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();
        Integer id = Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString());
        String name=session.getAttribute(WebSecurityConfig.NAME).toString();
        //获取最近五科成绩
        List<Score_Exam_Paper> scoreList = scoreService.showRecent5(id);
        //学生姓名
        model.addAttribute("sname", name);
        //学习课程数量
        model.addAttribute("course_num", 35);
        //参加考试数量
        model.addAttribute("exam_num", 5);
        //课程学习率
        model.addAttribute("course_rates", 50);
        //考试平均成绩
        model.addAttribute("exam_rates", 25);
        //最近五门成绩
        model.addAttribute("scoreList", scoreList);

        return "student/index";
    }
}
