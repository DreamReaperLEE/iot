package heu.iot.Controller.Teacher;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.Emploee;
import heu.iot.Model.Inform;
import heu.iot.Model.Inform_Emploee;
import heu.iot.Service.EmploeeService;
import heu.iot.Service.InfoService;
import heu.iot.Service.InformService;
import heu.iot.Util.MD5;
import heu.iot.Util.MyJson;
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
 * Created by wangyueyan on 2018/1/4.
 */
@Controller
@RequestMapping("/teacher")
public class UserInfoController {
    @Autowired
    private InfoService infoService;

    @Autowired
    private InformService informService;
    @Autowired
    private EmploeeService emploeeService;

    @RequestMapping("/userinfo")
    public String userinfo(Model model, HttpSession session) {
        Integer id = Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString());
        model.addAttribute("emploee", emploeeService.selectByPrimaryKey(id));
        return "teacher/EditInfo";
    }

    @RequestMapping("/notice")
    public String showNotice(Model model) {
        List<Inform_Emploee> inform_emploeeList = informService.showAll();
        model.addAttribute("inform_emploeeList", inform_emploeeList);
        return "teacher/Notice";
    }

    @RequestMapping("/noticeDetail")
    public String noticeDetail(Model model, @RequestParam(value = "id", defaultValue = "0") Integer id) {

        Inform inform = informService.selectByPrimaryKey(id);
        Emploee emploee = emploeeService.selectByPrimaryKey(inform.getIid());
        ArrayList<String> picList = MyJson.JsonToStringList(inform.getPic());
        if (picList != null) {
            model.addAttribute("picList", picList);
        }
        model.addAttribute("emploee", emploee);
        model.addAttribute("inform", inform);
        return "teacher/NoticeDetail";
    }


    private MD5 md5 = new MD5();

    @RequestMapping("/info1")
    public String showInfo() {
        return "teacher/info1";
    }

    @RequestMapping("/changePassword")
    public String changePassword1(String inputOrigin, String inputPassword1, String inputPassword2, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //判断两次密码输入是否一致
        if (!inputPassword2.equals(inputPassword1)) {
            model.addAttribute("fail", "更新失败，新密码两次输入不正确");
            return "teacher/info1";
        }
        //将密码转换为md5
        inputOrigin = md5.getMd5(inputOrigin);
        inputPassword1 = md5.getMd5(inputPassword1);
        Integer id = Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString());
        //判断是否更新成功
        int result = infoService.changePassword(id, inputOrigin, inputPassword1);
        if (result == 0)
            model.addAttribute("fail", "更新失败，密码错误");
        else
            model.addAttribute("success", "更新成功");
        return "teacher/info1";
    }


}
