package heu.iot.Controller.Student;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.*;
import heu.iot.Service.ExamService;
import heu.iot.Service.PaperService;
import heu.iot.Service.QuestionService;
import heu.iot.Service.ScoreService;
import heu.iot.Util.Excel;
import heu.iot.Util.MyJson;
import heu.iot.Util.dealQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @param model
     * @param request
     * @Author: Sumail-Lee
     * @Description: 展示本人成绩
     * @Date: 2017/12/17 21:04
     */
    @RequestMapping("/score")
    public String allScore(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer id = Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString());
        //获取本人成绩列表
        List<Score_Exam_Paper> scoreList = scoreService.showAllExamByStudentId(id);
        model.addAttribute("scoreList", scoreList);
        return "student/allScore";
    }

    /**
     * @Author: Sumail-Lee
     * @Description: 获取本人成绩并以图表显示
     * @param model
     * @param request
     * @Date: 2018/1/6 10:08
     */
    @RequestMapping("/scorepic")
    public String scorePic(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer id = Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString());
        //获取本人成绩列表
        List<Score_Exam_Paper> scoreList = scoreService.showAllExamByStudentId(id);
        String scoreJson=MyJson.toJson(scoreList);
        model.addAttribute("scoreList", scoreList);
        model.addAttribute("scoreJson",scoreJson);
        return "student/ScorePic";
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

    /**
     * @Author: Sumail-Lee
     * @Description: 导出学生成绩
     * @param response
     * @param request
     * @Date: 2018/1/15 16:16
     */
    @RequestMapping("/excel")
    @ResponseBody
    public String excle(HttpServletResponse response,HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Integer id = Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString());
        //获取本人成绩列表
        List<Score_Exam_Paper> scoreList = scoreService.showAllExamByStudentId(id);

        String fineName="score";
        ArrayList<String> title=new ArrayList<String>();
        title.add("课程名称");
        title.add("成绩");

        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        for(Score_Exam_Paper score_exam_paper:scoreList){
            ArrayList<String> every=new ArrayList<String>();
            every.add(score_exam_paper.getEname());
            every.add(String.valueOf(score_exam_paper.getScore()));
            data.add(every);
        }

        return Excel.createExcel(fineName,title,data,response);

    }


}
