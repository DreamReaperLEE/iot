package heu.iot.Controller.Teacher;

import com.github.pagehelper.PageHelper;
import heu.iot.Model.Question;
import heu.iot.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 试卷模块
 * Created by wangyueyan on 2017/12/4.
 */
@Controller
@RequestMapping("/teacher")
public class QuestionBankController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/question")
    public String allQuestion() {
        return "teacher/AllQuestion";
    }

    //  手动组卷模块
    @RequestMapping("/question_list")
    public String showAllQuestion(Model model) {
        List<Question> questionsList = questionService.showAllQuestion();
        PageHelper.startPage(2, 3);
        model.addAttribute("questionsList", questionsList);
        return "teacher/QuestionList";
    }

    // 自动组卷模块
    @RequestMapping("/automatic_question")
    public String automaticQuestion() {
        return "teacher/AutomaticPaper";
    }


    // 设置考试时间以及科目
    @RequestMapping("/set_paper")
    public String setPaper() {
        return "teacher/SetPaper";
    }
}