package heu.iot.Controller.Student;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.Emploee;
import heu.iot.Model.Feedback;
import heu.iot.Model.Inform;
import heu.iot.Model.Inform_Emploee;
import heu.iot.Service.EmploeeService;
import heu.iot.Service.FeedbackService;
import heu.iot.Service.InformService;
import heu.iot.Util.MyJson;
import heu.iot.Util.TimeFactory;
import heu.iot.Util.dealFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:其他功能类
 * @Since: 2018/1/15 9:51
 */
@Controller
@RequestMapping("/student")
public class OtherController {
    @Autowired
    private InformService informService;

    @Autowired
    private EmploeeService emploeeService;

    @Autowired
    private FeedbackService feedbackService;

    /**
     * @Author: Sumail-Lee
     * @Description:查看通知公告
     * @param model
     * @Date: 2018/2/1 13:50
     */
    @RequestMapping("/notice")
    public String showNotice(Model model) {
        List<Inform_Emploee> inform_emploeeList=informService.showAll();
        model.addAttribute("inform_emploeeList", inform_emploeeList);
        return "student/notice";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:通知公告详细信息
     * @param model
     * @param id 通知ID
     * @Date: 2018/2/1 13:50
     */
    @RequestMapping("/noticeDetail")
    public String noticeDetail(Model model,@RequestParam(value = "id", defaultValue = "0") Integer id) {

        Inform inform=informService.selectByPrimaryKey(id);
        Emploee emploee=emploeeService.selectByPrimaryKey(inform.getIid());
        ArrayList<String> picList= MyJson.JsonToStringList(inform.getPic());
        if(picList!=null){
            model.addAttribute("picList", picList);
        }
        model.addAttribute("emploee", emploee);
        model.addAttribute("inform", inform);
        return "student/noticeDetail";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:显示意见反馈界面
     * @param
     * @Date: 2018/2/1 13:50
     */
    @RequestMapping("/feedback")
    public String feedback(){

        return "student/feedback";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:提交反馈
     * @param model
     * @param request
     * @param session
     * @param feedback 反馈详细信息
     * @param anymous 是否匿名
     * @param file 附件
     * @Date: 2018/2/1 13:51
     */
    @RequestMapping("/submitFeedback")
    public String submitFeedback(Model model,HttpServletRequest request,HttpSession session, @RequestParam(value = "feedback", defaultValue = "") String feedback, @RequestParam(value = "anymous", defaultValue = "") String anymous, @RequestParam("file") MultipartFile file){

        Feedback item=new Feedback();

        if(!file.isEmpty()) {
            String filepath = dealFile.saveFile("pic", file);
            item.setFile(filepath);
        }

        if(!anymous.equals("on")){
            item.setSid(Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString()));
        }

        item.setDetail(feedback);

        item.setDate(TimeFactory.getCurrentTime());

        Integer status=feedbackService.insertSelective(item);
        if(status==1){
            model.addAttribute("success", "反馈意见提交成功！");
        }
        else{
            model.addAttribute("fail","反馈意见提交失败！");
        }
        return "student/feedback";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:下载文件
     * @param response
     * @param fileName 下载文件的名称
     * @Date: 2018/2/1 13:51
     */
    @RequestMapping("/downloadFile")
    public String downloadFile(HttpServletResponse response,@RequestParam(value = "fileName", defaultValue = "") String fileName) throws IOException {

        return dealFile.downloadFile(response,fileName);
    }


}
