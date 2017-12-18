package heu.iot.Controller.Student;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.Score_Exam_Paper;
import heu.iot.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 学生成绩查询
 *
 * @Author: Sumail-Lee
 * @Date: 9:32 2017/11/29
 */
@Controller
@RequestMapping("/student")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;


    /**
     * @param httpSession
     * @param model
     * @param request
     * @Author: Sumail-Lee
     * @Description: 展示本人成绩
     * @Date: 2017/12/17 21:04
     */
    @RequestMapping("/score")
    public String allScore(HttpSession httpSession, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Integer id = Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString());
        //获取本人成绩列表
        List<Score_Exam_Paper> scoreList = scoreService.showAllExamByStudentId(id);
        model.addAttribute("scoreList", scoreList);
        return "student/allScore";
    }
}
