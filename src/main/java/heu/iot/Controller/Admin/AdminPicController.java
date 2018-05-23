package heu.iot.Controller.Admin;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.ScorePic;
import heu.iot.Model.Score_Emploee;
import heu.iot.Model.Score_Exam_Paper_List;
import heu.iot.Service.EmploeeService;
import heu.iot.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/5/7 16:20
 */
@Controller
@RequestMapping("/admin/pic")
public class AdminPicController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private EmploeeService emploeeService;

    @RequestMapping("/score")
    public String scorePic(Model model) {
        List<Score_Exam_Paper_List> score_exam_paper_lists=scoreService.showAllOldExamByDate();
        model.addAttribute("score_exam_paper_lists",score_exam_paper_lists);
        return "admin/Pic/ScorePic";
    }

    @RequestMapping("/changeExam")
    @ResponseBody
    public List<ScorePic> changeExam(int pid){
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

}
