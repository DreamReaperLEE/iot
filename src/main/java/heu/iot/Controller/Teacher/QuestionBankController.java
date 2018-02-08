package heu.iot.Controller.Teacher;

import com.github.pagehelper.PageHelper;
import heu.iot.Model.Exam;
import heu.iot.Model.Question;
import heu.iot.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        return "teacher/test";
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
    public ModelAndView automaticQuestion() {
        Question question = new Question();
        return new ModelAndView("/teacher/AutomaticPaper").addObject(question);
    }

    // 设置考试时间以及科目的一个跳转
    @RequestMapping(value = "/set_paper")
    public ModelAndView setPaper(Model model) {
        Exam exam = new Exam();
        return new ModelAndView("teacher/SetPaper").addObject(exam);
    }
}