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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生考试类
 *
 * @Author: Sumail-Lee
 * @Date: 9:13 2017/11/29
 */
@Controller
@RequestMapping("/student")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ScoreService scoreService;

    /**
     * @param model
     * @Author: Sumail-Lee
     * @Description: 展示所有正在进行考试
     * @Date: 2017/12/17 20:56
     */
    @RequestMapping("/exam")
    public String allExam(Model model) {
        //获取所有考试列表
        List<Exam> examList = examService.showCurrentExam();
        model.addAttribute("examList", examList);
        return "student/allExam";
    }


    /**
     * @Author: Sumail-Lee
     * @Description: 开始考试
     * @param eid 考试id
     * @param ename 考试名称
     * @param model
     * @param session
     * @Date: 2017/12/20 19:52
     */
    @RequestMapping("/startExam")
    public String startExam(@RequestParam(value = "eid") Integer eid, @RequestParam(value = "ename") String ename, Model model, HttpSession session) {
        //获取试卷
        Paper paper = paperService.showPaper(eid);
        //如果已经答题过，则显示已经答题过
        Score score = scoreService.selectBySidPid(Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString()), paper.getId());
        if (score != null) {
            //获取所有考试列表
            List<Exam> examList = examService.showCurrentExam();
            model.addAttribute("examList", examList);
            model.addAttribute("info", "您已经进行过答题，请进入成绩查询界面查看成绩！");
            return "student/allExam";
        }
        Exam_Json exam_json = MyJson.JsonToExam(paper.getDetail());
        List<String> info = exam_json.getInfo();
        //获取题号列表
        List<Integer> numinfo = new ArrayList<Integer>();
        for (String each : info) {
            numinfo.add(Integer.valueOf(each));
        }
        //获取所有题目
        List<Question> questionList = questionService.showSelected(numinfo);
        //题目列表，即为试卷
        ArrayList<Online_Test> online_tests = dealQuestion.QuestionToList(questionList);
        model.addAttribute("online_tests", online_tests);
        model.addAttribute("eid", eid);
        model.addAttribute("ename", ename);
        return "student/OnlineTest";
    }

    /**
     * @Author: Sumail-Lee
     * @Description: 处理学生提交的考卷
     * @param model
     * @param request
     * @param session
     * @Date: 2017/12/20 19:53
     */
    @PostMapping("/dealPaper")
    public String dealPaper(Model model, HttpServletRequest request, HttpSession session) {
        //获取试卷
        Paper paper = paperService.showPaper(Integer.valueOf(request.getParameter("eid")));
        Exam_Json exam_json = MyJson.JsonToExam(paper.getDetail());
        //题目数量
        List<String> info = exam_json.getInfo();
        //学生答题答案列表
        ArrayList<String[]> answer = new ArrayList<>();
        for (int i = 0; i < info.size(); i++) {
            if (request.getParameter("option" + String.valueOf(i)) != null) {
                String[] result = request.getParameterValues("option" + String.valueOf(i));
                answer.add(result);
                System.out.println("单选");
            } else if (request.getParameter("choose" + String.valueOf(i)) != null) {
                String[] result = request.getParameterValues("choose" + String.valueOf(i));
                answer.add(result);
                System.out.println("判断");
            } else if (request.getParameter("check" + String.valueOf(i)) != null) {
                String[] result = request.getParameterValues("check" + String.valueOf(i));
                answer.add(result);
                System.out.println("多选");
            } else {
                String[] result = {" "};
                answer.add(result);
            }
        }
        //将答案数组转换为Json
        String result = MyJson.toJson(answer);
        //创建Score类，准备存入数据库
        Score score = new Score();
        score.setSid(Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString()));
        score.setPid(paper.getId());
        score.setDetail(result);
        //判断是否添加成功
        int num = scoreService.submitPaper(score);
        if (num == 1) {
            model.addAttribute("success", "提交试卷成功！");
        } else {
            model.addAttribute("fail", "交卷失败！");
        }
        //获取所有考试列表，返回考试列表界面
        List<Exam> examList = examService.showCurrentExam();
        model.addAttribute("examList", examList);
        return "student/allExam";
    }




}
