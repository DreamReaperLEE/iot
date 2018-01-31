package heu.iot.Controller.Admin;

import heu.iot.Model.Emploee;
import heu.iot.Model.Feedback;
import heu.iot.Service.EmploeeService;
import heu.iot.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:意见反馈
 * @Since: 2018/1/24 14:38
 */
@Controller
@RequestMapping("/admin/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private EmploeeService emploeeService;

    /**
     * @Author: Sumail-Lee
     * @Description:所有意见反馈
     * @param model
     * @Date: 2018/1/24 14:42
     */
    @RequestMapping("/allFeedback")
    public String showAllFeedback(Model model) {

        List<Feedback> feedbackList=feedbackService.showAll();
        model.addAttribute("feedbackList", feedbackList);
        return "admin/feedback";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:意见反馈细节
     * @param model
     * @param id 意见反馈ID
     * @Date: 2018/1/24 14:42
     */
    @RequestMapping("/feedbackDetail")
    public String noticeDetail(Model model,@RequestParam(value = "id", defaultValue = "0") Integer id) {
        Feedback feedback=feedbackService.selectByPrimaryKey(id);
        String name;
        if(feedback.getSid()!=null){
            Emploee emploee=emploeeService.selectByPrimaryKey(feedback.getSid());
            name=emploee.getName();
        }
        else
            name="匿名";

        model.addAttribute("feedback", feedback);
        model.addAttribute("name", name);
        return "admin/feedbackDetail";
    }
}
