package heu.iot.Controller.Teacher;

import heu.iot.Model.Exam;
import heu.iot.Model.Question;
import heu.iot.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wangyueyan on 2017/12/4.
 */
@Controller
@RequestMapping("/teacher")
public class PaperController {
    @Autowired
    private ExamService examService;

    @RequestMapping("/paper")
    public String allpaper() {
        return "teacher/Allpaper";
    }

    // 自动组卷生成试卷
    @RequestMapping(value = "/add_exam", method = RequestMethod.POST)
    public String addexam(Question question, Model model) {
        model.addAttribute("level", question.getLevel());
        return "/teacher/SetPaper";
    }

    // 设置考试时间/添加考试
    @RequestMapping(value = "/add_paper", method = RequestMethod.POST)
    public String addpaper(Exam exam, Model model) {
        model.addAttribute("ename", exam.getEname());
        model.addAttribute("date", exam.getDate());
        model.addAttribute("stime", exam.getStime());
        model.addAttribute("etime", exam.getEtime());
        // 这里应该传入考试id 先随机生成一个0-100的数
        double number = Math.floor(Math.random());
        exam.setId((int) number);
        exam.setDate(String.valueOf(exam.getDate()));
        exam.setEname(exam.getEname());
        exam.setStime(String.valueOf(exam.getStime()));
        exam.setEtime(String.valueOf(exam.getEtime()));
        int flag = examService.insert(exam);
        System.out.println(flag + "flag");
        if (flag == 1) {
            // 这里应该设置一个设置完考试时间后的跳转页面 暂且先跳转到主页
            return "/teacher/Allpaper";
        } else {
            return "添加失败";
        }
    }

}
