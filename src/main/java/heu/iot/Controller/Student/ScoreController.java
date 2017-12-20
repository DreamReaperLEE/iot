package heu.iot.Controller.Student;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.*;
import heu.iot.Service.ExamService;
import heu.iot.Service.PaperService;
import heu.iot.Service.QuestionService;
import heu.iot.Service.ScoreService;
import heu.iot.Util.MyJson;
import heu.iot.Util.dealQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @Autowired
    private ExamService examService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private QuestionService questionService;

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

    /**
     * @param pid     试卷id
     * @param ename   考试名称
     * @param model
     * @param session
     * @Author: Sumail-Lee
     * @Description: 显示详细试卷成绩
     * @Date: 2017/12/20 19:54
     */
    @RequestMapping("/detailScore")
    public String detailScore(@RequestParam(value = "pid") Integer pid, @RequestParam(value = "ename") String ename, Model model, HttpSession session) {
        //获取试卷
        Paper paper = paperService.selectByPrimaryKey(pid);
        Exam_Json exam_json = MyJson.JsonToExam(paper.getDetail());
        List<String> info = exam_json.getInfo();
        //获取题号列表
        List<Integer> numinfo = new ArrayList<Integer>();
        for (String each : info) {
            numinfo.add(Integer.valueOf(each));
        }
        //获取所有题目
        List<Question> questionList = questionService.showSelected(numinfo);
        //获取学生所答题目
        Score score = scoreService.selectBySidPid((Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString())), pid);
        //将试卷以及答题情况整理成为list
        ArrayList<Online_Test> online_tests = dealQuestion.CheckPaper(questionList, score.getDetail());
        model.addAttribute("online_tests", online_tests);
        model.addAttribute("pid", pid);
        model.addAttribute("ename", ename);
        return "student/CheckPaper";
    }

}
