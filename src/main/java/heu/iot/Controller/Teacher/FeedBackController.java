package heu.iot.Controller.Teacher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangyueyan on 2018/1/3.
 */
@Controller
@RequestMapping("/teacher")
public class FeedBackController {
//    @Autowired
//    private FeedbackService feedbackService;


    @RequestMapping("/feedback")
    public String feedback() {
        return "teacher/FeedBack";
    }

//    @RequestMapping("/submitFeedback")
//    public String submitFeedback(Model model, HttpServletRequest request, HttpSession session, @RequestParam(value = "feedback", defaultValue = "") String feedback, @RequestParam(value = "anymous", defaultValue = "") String anymous, @RequestParam("file") MultipartFile file) {
//        Feedback item = new Feedback();
//
////        if (!file.isEmpty()) {
////            String filepath = dealFile.saveFile(request, "pic", file);
////            item.setFile(filepath);
////        }
//
//        if (!anymous.equals("on")) {
//            item.setSid(Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString()));
//        }
//
//        item.setDetail(feedback);
//
//        item.setDate(TimeFactory.getCurrentTime());
//
//        Integer status = feedbackService.insertSelective(item);
//        if (status == 1) {
//            model.addAttribute("success", "反馈意见提交成功！");
//        } else {
//            model.addAttribute("fail", "反馈意见提交失败！");
//        }
//        return "teacher/FeedBack";
//    }

}
