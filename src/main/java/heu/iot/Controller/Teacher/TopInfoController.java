package heu.iot.Controller.Teacher;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.Inform_Emploee;
import heu.iot.Model.Score_Emploee;
import heu.iot.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyueyan on 2018/1/17.
 */
@Controller
@RequestMapping("/teacher")
//顶部信息栏
public class TopInfoController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private ExamService examService;
    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/info")
    public String info(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer id = Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString());


        List<String> teachertop4 = new ArrayList<String>();
        if (session.getAttribute(WebSecurityConfig.TeacherTop4) == null) {
            int course = courseService.countCourseNum();
            int question = questionService.countQuestionNum();
            int paper = paperService.countPaperNum();
            int exam = examService.countExamNum();
            teachertop4.add(String.valueOf(course));
            teachertop4.add(String.valueOf(question));
            teachertop4.add(String.valueOf(paper));
            teachertop4.add(String.valueOf(exam));
            session.setAttribute(WebSecurityConfig.TeacherTop4, teachertop4);
        } else {
            teachertop4 = (List) session.getAttribute(WebSecurityConfig.TeacherTop4);

        }

        List<Score_Emploee> score_emploeeList = scoreService.showAllExamById();
        model.addAttribute("score_emploeeList", score_emploeeList);
        model.addAttribute("info_course", teachertop4.get(0));
        model.addAttribute("info_question", teachertop4.get(1));
        model.addAttribute("info_paper", teachertop4.get(2));
        model.addAttribute("info_exam", teachertop4.get(3));


        return "teacher/TeacherIndex";

    }


}
