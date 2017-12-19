package heu.iot.Controller.Teacher;

import heu.iot.Model.Score_Emploee;
import heu.iot.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by wangyueyan on 2017/12/5.
 */
@Controller
@RequestMapping("/teacher")
public class AnalysisController {

    @Autowired
    private ScoreService scoreService;

    // 成绩查询模块
    @RequestMapping("/analysis")
    public String analysis(Model model) {
        int id = 1;
        // 试卷id

        List<Score_Emploee> score_emploeeList = scoreService.showAllExamById();
        if (score_emploeeList == null) {
            return "teacher/Analysis";
        } else {
            model.addAttribute("score_emploeeList", score_emploeeList);
            return "teacher/Analysis";
        }

    }

}