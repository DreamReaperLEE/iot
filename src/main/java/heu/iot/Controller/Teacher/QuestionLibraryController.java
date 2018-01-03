package heu.iot.Controller.Teacher;

import heu.iot.Model.Question;
import heu.iot.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class QuestionLibraryController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/showAllQuestionList")
    public String showAllQuestionList(HttpSession session, Model model)
    {
        List<Question> questionList = questionService.showAllQuestion();
        model.addAttribute("questionList",questionList);
        return "teacher/QuestionList";
    }
}
