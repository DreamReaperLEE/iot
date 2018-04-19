package heu.iot.Controller.Teacher;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.Course;
import heu.iot.Model.Emploee_Course;
import heu.iot.Model.Question;
import heu.iot.Model.Question_Json;
import heu.iot.Service.CourseService;
import heu.iot.Service.QuestionService;
import heu.iot.Util.MyJson;
import heu.iot.Util.dealFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/4/17 17:09
 */

@Controller
@RequestMapping("/teacher/question")
public class TeacherQuestionController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/showQuestionList")
    public String showQuestionList(Model model, HttpSession session) {
        List<Course> courseList=courseService.selectByTid(Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString()));
        model.addAttribute("courseList",courseList);
        return "teacher/QuestionList";
    }

    @RequestMapping("/detailquestion")
    public String showQuestionDetail(Model model, HttpSession session,Integer cid) {
        List<Question> questionList=questionService.selectByCourseId(cid);
        Course course=courseService.selectByPrimaryKey(cid);
        model.addAttribute("course",course);
        model.addAttribute("questionList",questionList);
        return "teacher/QuestionDetailList";
    }

    @RequestMapping("/addquestion")
    public String addQuestion(Model model, HttpServletRequest request,HttpSession session,Question question,@RequestParam("digitalfile") MultipartFile file,String cid) {
        if(!file.isEmpty()) {
            if(question.getMore()==1){
                String filepath = dealFile.saveFile("pic", file);
                question.setDigital(filepath);
            }else if(question.getMore()==2){
                String filepath = dealFile.saveFile("video", file);
                question.setDigital(filepath);
            }
        }
        Question_Json question_json=new Question_Json();
        ArrayList<String> choiceList=new ArrayList<>();
        ArrayList<Integer> answerList=new ArrayList<>();
        if(question.getType()==0){
            String answer=request.getParameter("answerunform");
            answerList.add(Integer.valueOf(answer));
        }else{
            String[] answer=request.getParameterValues("answerunform");
            for(String every:answer){
                answerList.add(Integer.valueOf(every));
            }
            String[] choice=request.getParameterValues("answerun");
            for(String every:choice){
                choiceList.add(every);
            }
        }
        question_json.setChoice(choiceList);
        question_json.setAnswer(answerList);

        question.setAnswer(MyJson.toJson(question_json));

        questionService.insertSelective(question);

        return "redirect:/teacher/question/detailquestion?cid="+cid;
    }

    @RequestMapping("/questiondelete")
    public String questionDelete(Model model, HttpSession session,Integer cid,Integer id) {
        questionService.deleteByPrimaryKey(id);
        return "redirect:/teacher/question/detailquestion?cid="+cid;
    }

    @RequestMapping("/questiondetail")
    public String questionDetail(Model model, Integer id) {
        Question question=questionService.selectByPrimaryKey(id);
        model.addAttribute("question",question);
        Question_Json question_json=MyJson.JsonToQuestion(question.getAnswer());
        model.addAttribute("question_json",question_json);
        return "teacher/QuestionChange";
    }

    @RequestMapping("/changeQuestionInfo")
    public String changeQuestionInfo(Model model, Question question,HttpServletRequest request,@RequestParam("digitalfile") MultipartFile file) {

        if(!file.isEmpty()) {
            if(question.getMore()==1){
                String filepath = dealFile.saveFile("pic", file);
                question.setDigital(filepath);
            }else if(question.getMore()==2){
                String filepath = dealFile.saveFile("video", file);
                question.setDigital(filepath);
            }
        }
        Question_Json question_json=new Question_Json();
        ArrayList<String> choiceList=new ArrayList<>();
        ArrayList<Integer> answerList=new ArrayList<>();
        if(question.getType()==0){
            String answer=request.getParameter("answerunform");
            answerList.add(Integer.valueOf(answer));
        }else{
            String[] answer=request.getParameterValues("answerunform");
            for(String every:answer){
                answerList.add(Integer.valueOf(every));
            }
            String[] choice=request.getParameterValues("answerun");
            for(String every:choice){
                choiceList.add(every);
            }
        }
        question_json.setChoice(choiceList);
        question_json.setAnswer(answerList);
        question.setAnswer(MyJson.toJson(question_json));
        questionService.updateByPrimaryKeySelective(question);
        return "redirect:/teacher/question/detailquestion?cid="+question.getCid();
    }

}
