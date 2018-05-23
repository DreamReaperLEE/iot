package heu.iot.Controller.Teacher;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.*;
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
import javax.xml.ws.Action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/4/20 8:42
 */
@Controller
@RequestMapping("/teacher/score")
public class TeacherScoreController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/showScoreList")
    public String showCourseList(Model model, HttpSession session) {
        List<Score_Exam_Paper_List> score_exam_paper_lists=scoreService.showOldExamByTidDate(Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString()));
        model.addAttribute("score_exam_paper_lists",score_exam_paper_lists);
        return "teacher/Score/ScoreList";
    }

    @RequestMapping("/scorePic")
    public String scorePic(Model model,HttpSession session) {
        List<Score_Exam_Paper_List> score_exam_paper_lists=scoreService.showOldExamByTidDate(Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString()));
        model.addAttribute("score_exam_paper_lists",score_exam_paper_lists);
        return "teacher/Score/ScorePic";
    }

    @RequestMapping("/changeExam")
    @ResponseBody
    public List<ScorePic> checkClient(int pid){
        List<Score_Emploee> score_emploees=scoreService.showExamByPid(pid);
        Integer bujige=0,jige=0,zhongdeng=0,lianghao=0,youxiu=0;
        for(Score_Emploee each:score_emploees){
            if(each.getScore()<60){
                bujige++;
            }else if(each.getScore()<65){
                jige++;
            }else if(each.getScore()<75){
                zhongdeng++;
            }else if(each.getScore()<85){
                lianghao++;
            }else{
                youxiu++;
            }
        }

        ArrayList<ScorePic> scorePics=new ArrayList<ScorePic>();
        scorePics.add(new ScorePic(bujige,"不及格"));
        scorePics.add(new ScorePic(jige,"及格"));
        scorePics.add(new ScorePic(zhongdeng,"中等"));
        scorePics.add(new ScorePic(lianghao,"良好"));
        scorePics.add(new ScorePic(youxiu,"优秀"));
        return scorePics;
    }

    @RequestMapping("/scorelist")
    public String scoreList(Model model,Integer pid,String ename) {
        List<Score_Emploee> score_emploees=scoreService.showExamByPid(pid);
        model.addAttribute("score_emploees",score_emploees);
        model.addAttribute("ename",ename);
        model.addAttribute("pid",pid);
        return "teacher/Score/DetailList";
    }

    @RequestMapping("/excel")
    @ResponseBody
    public String excel(HttpServletResponse response, Integer pid) throws IOException {
        List<Score_Emploee> score_emploees=scoreService.showExamByPid(pid);
        String fineName="score";
        ArrayList<String> title=new ArrayList<String>();
        title.add("学号");
        title.add("姓名");
        title.add("成绩");
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        for(Score_Emploee score_emploee:score_emploees){
            ArrayList<String> every=new ArrayList<String>();
            every.add(String.valueOf(score_emploee.getSid()));
            every.add(score_emploee.getName());
            every.add(String.valueOf(score_emploee.getScore()));
            data.add(every);
        }

        return Excel.createExcel(fineName,title,data,response);
    }

    @RequestMapping("/detailScore")
    public String detailScore(@RequestParam(value = "sid") Integer sid, @RequestParam(value = "ename") String ename, Model model, HttpSession session) {
        Score score=scoreService.selectByPrimaryKey(sid);
        //获取试卷
        Paper paper = paperService.selectByPrimaryKey(score.getPid());
        Exam_Json exam_json = MyJson.JsonToExam(paper.getDetail());
        List<String> info = exam_json.getInfo();
        //获取题号列表
        List<Integer> numinfo = new ArrayList<Integer>();
        for (String each : info) {
            numinfo.add(Integer.valueOf(each));
        }
        //获取所有题目
        List<Question> questionList = questionService.showSelected(numinfo);
        //将试卷以及答题情况整理成为list
        ArrayList<Online_Test> online_tests = dealQuestion.CheckPaper(questionList, score.getDetail());
        model.addAttribute("online_tests", online_tests);
        model.addAttribute("pid", score.getPid());
        model.addAttribute("ename", ename);
        return "teacher/Score/CheckPaper";
    }
}
