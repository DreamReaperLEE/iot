package heu.iot.Controller.Student;

import heu.iot.Model.Score;
import heu.iot.Model.Score_Exam_Paper;
import heu.iot.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Date: 9:32 2017/11/29
 */
@Controller
@RequestMapping("/student")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/score")
    public String allScore(HttpSession httpSession, Model model){
//        int id= (int)httpSession.getAttribute("id");
        List<Score_Exam_Paper> scoreList=scoreService.showAllExamByStudentId(2013201308);
        model.addAttribute("scoreList",scoreList);
        return "student/allScore";
    }
}
