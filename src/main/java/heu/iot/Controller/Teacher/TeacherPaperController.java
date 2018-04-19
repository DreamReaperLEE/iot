package heu.iot.Controller.Teacher;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.*;
import heu.iot.Service.*;
import heu.iot.Util.MyJson;
import heu.iot.Util.dealFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/4/17 17:09
 */

@Controller
@RequestMapping("/teacher/paper")
public class TeacherPaperController {
    @Autowired
    private PaperService paperService;
    @Autowired
    private ExamService examService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CourseService courseService;

    @RequestMapping("/showExamList")
    public String showCourseList(Model model, HttpSession session) {
        List<Exam> examList=examService.selectByEmploeeId(Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString()));

        model.addAttribute("examList",examList);
        return "teacher/Paper/PaperList";
    }

    @RequestMapping("/examset")
    public String examSet(Model model,Integer id) {
        Exam exam=examService.selectByPrimaryKey(id);
        model.addAttribute("exam",exam);
        return "teacher/Paper/ExamSet";
    }

    @RequestMapping("/updateexam")
    public String updateExam(Model model, Exam exam,@RequestParam("digitalfile") MultipartFile file) {
        if(!file.isEmpty()) {
            String filepath = dealFile.saveFile("pic", file);
            exam.setEpic(filepath);
        }
        examService.updateByPrimaryKeySelective(exam);
        return "redirect:/teacher/paper/showExamList";
    }

    @RequestMapping("/paperset")
    public String paperSet(Model model, HttpSession session,Integer id) {
        Paper paper=paperService.selectByEid(id);
        if(paper==null){
            paper=new Paper();
            paper.setEid(id);
            paper.setDetail("{\"info\":[],\"level\":\"\"}");
            paperService.insertSelective(paper);
        }
        Exam exam=examService.selectByPrimaryKey(id);
        Exam_Json exam_json = MyJson.JsonToExam(paper.getDetail());
        //已有题目列表
        List<String> info = exam_json.getInfo();
        ArrayList<Integer> questionListId=new ArrayList<>();
        for(String every:info){
            questionListId.add(Integer.valueOf(every));
        }
        //获取所有题目
        List<Question> questionList = questionService.showSelected(questionListId);
        //获取所有题库
        List<Course> courseList=courseService.selectByTid(Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString()));
        model.addAttribute("questionList",questionList);
        model.addAttribute("pid",paper.getId());
        model.addAttribute("courseList",courseList);
        model.addAttribute("exam",exam);

        return "teacher/Paper/PaperConfig";
    }

    @RequestMapping("/getQustion")
    @ResponseBody
    public List<Question> getQuestion(int id){
        List<Question> questionList=questionService.selectByCourseId(id);
        return questionList;
    }

    @RequestMapping("/addquestion")
    public String addQuestion(Model model, HttpSession session,Integer pid,String qid) {
        Paper paper=paperService.selectByPrimaryKey(pid);
        Exam_Json exam_json = MyJson.JsonToExam(paper.getDetail());
        //已有题目列表
        ArrayList<String> info = exam_json.getInfo();
        if(!info.contains(qid)) {
            info.add(qid);

        }
        exam_json.setInfo(info);
        paper.setDetail(MyJson.toJson(exam_json));
        paperService.updateByPrimaryKeySelective(paper);
        return "redirect:/teacher/paper/paperset?id="+paper.getEid();
    }

    @RequestMapping("/deletequestion")
    public String deleteQuestion(Integer pid,String qid) {
        Paper paper=paperService.selectByPrimaryKey(pid);
        Exam_Json exam_json = MyJson.JsonToExam(paper.getDetail());
        //已有题目列表
        ArrayList<String> info = exam_json.getInfo();
        info.remove(qid);
        exam_json.setInfo(info);
        paper.setDetail(MyJson.toJson(exam_json));
        paperService.updateByPrimaryKeySelective(paper);
        return "redirect:/teacher/paper/paperset?id="+paper.getEid();
    }

    @RequestMapping("/autoconfig")
    public String autoConfig(Integer pid,Integer eid,Integer single,Integer check,Integer panduan) {
        //获取题库题目
        List<Question> originList=questionService.selectByCourseId(eid);
        //三种题目
        ArrayList<Integer> originsingle=new ArrayList<>();
        ArrayList<Integer> originmutil=new ArrayList<>();
        ArrayList<Integer> originpanduan=new ArrayList<>();
        for(Question each:originList){
            switch (each.getType()){
                case 0:
                    originpanduan.add(each.getId());
                    break;
                case 1:
                    originsingle.add(each.getId());
                    break;
                case 2:
                    originmutil.add(each.getId());
                    break;
            }
        }
        //三种题目
        ArrayList<Integer> singleList=new ArrayList<>();
        ArrayList<Integer> checkList=new ArrayList<>();
        ArrayList<Integer> panduanList=new ArrayList<>();
        //获取已有题目
        Paper paper=paperService.selectByPrimaryKey(pid);
        Exam_Json exam_json = MyJson.JsonToExam(paper.getDetail());
        //已有题目列表
        ArrayList<String> info = exam_json.getInfo();
        ArrayList<Integer> questionListId=new ArrayList<>();
        for(String every:info){
            questionListId.add(Integer.valueOf(every));
        }
        //获取所有题目
        List<Question> questionList = questionService.showSelected(questionListId);
        for(Question question:questionList){
            switch (question.getType()){
                case 0:
                    panduanList.add(question.getId());
                    break;
                case 1:
                    singleList.add(question.getId());
                    break;
                case 2:
                    checkList.add(question.getId());
                    break;
                default:
                    break;
            }
        }
        Random random = new Random();
        //开始自动加题
        while(panduanList.size()<panduan&&panduanList.size()<originpanduan.size()&&originpanduan.size()!=0){
            int[] arr = random.ints(1, 0, (originpanduan.size()>0?originpanduan.size():1)).toArray();
            if(!panduanList.contains(originpanduan.get(arr[0]))){
                panduanList.add(originpanduan.get(arr[0]));
            }
        }
        while(singleList.size()<single&&singleList.size()<originsingle.size()&&originsingle.size()!=0){
            int[] arr = random.ints(1, 0, (originsingle.size()>0?originsingle.size():1)).toArray();
            if(!singleList.contains(originsingle.get(arr[0]))){
                singleList.add(originsingle.get(arr[0]));
            }
        }
        while(checkList.size()<check&&checkList.size()<originmutil.size()&&originmutil.size()!=0){
            int[] arr = random.ints(1, 0, (originmutil.size()>0?originmutil.size():1)).toArray();
            if(!checkList.contains(originmutil.get(arr[0]))){
                checkList.add(originmutil.get(arr[0]));
            }
        }
        questionListId=panduanList;
        questionListId.addAll(singleList);
        questionListId.addAll(checkList);
        System.out.println("=============="+MyJson.toJson(questionListId)+"===============");
        info=new ArrayList<String>();
        for(Integer every:questionListId){
            info.add(String.valueOf(every));
        }
        exam_json.setInfo(info);
        paper.setDetail(MyJson.toJson(exam_json));

        paperService.updateByPrimaryKeySelective(paper);
        return "redirect:/teacher/paper/paperset?id="+paper.getEid();
    }

}
