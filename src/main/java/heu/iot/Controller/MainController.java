package heu.iot.Controller;


import heu.iot.Model.Course;
import heu.iot.Model.Course_Emploee;
import heu.iot.Model.Emploee;
import heu.iot.Model.Homepage;
import heu.iot.MyThread.IndexThread;
import heu.iot.Service.CourseService;
import heu.iot.Service.EmploeeService;
import heu.iot.Service.HomepageService;
import heu.iot.Service.InfoService;
import heu.iot.Util.MD5;
import heu.iot.Util.MyJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2017/12/17 19:22
 */
@Controller
public class MainController {
    //不活动的登陆最长存活时间
    public final static int MAX_ACTIVE_TIME = 30 * 60;

    @Autowired
    private InfoService infoService;
    @Autowired
    private HomepageService homepageService;
    @Autowired
    private EmploeeService emploeeService;
    @Autowired
    private CourseService courseService;

    //登陆
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    //主页
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("pic",IndexThread.pic);
        model.addAttribute("emploeeList",IndexThread.emploeeList);
        model.addAttribute("courseList",IndexThread.courseList);
        model.addAttribute("inform_emploees",IndexThread.inform_emploees);
        return "index";
    }

    /**
     * @param account  账号
     * @param password 密码
     * @param session
     * @Author: Sumail-Lee
     * @Description: 处理登陆请求
     * @Date: 2017/12/17 21:07
     */
    @PostMapping("/loginPost")
    public String loginPost(String account, String password, HttpSession session,Model model) {
        if (infoService.login(Integer.valueOf(account), password) == null) {
            model.addAttribute("fail","账号或密码错误");
            return "login";
        }
        //获取数据库中账号信息
        Emploee emploee = infoService.login(Integer.valueOf(account), password);
        // 设置session
        session.setAttribute(WebSecurityConfig.ID, account);
        session.setAttribute(WebSecurityConfig.NAME, emploee.getName());
        session.setAttribute(WebSecurityConfig.LEVEL, emploee.getPriv());
        session.setAttribute(WebSecurityConfig.PIC, emploee.getPic());
        session.setMaxInactiveInterval(MAX_ACTIVE_TIME);
        if (emploee.getPriv() == 2) {
            return "redirect:/student/index";
        }
        if (emploee.getPriv() == 0) {
            return "redirect:/admin/index";
        }
        if (emploee.getPriv() == 1) {
            return "redirect:/teacher/index";
        }
        else
            return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.ID);
        session.removeAttribute(WebSecurityConfig.NAME);
        session.removeAttribute(WebSecurityConfig.LEVEL);
        return "redirect:/login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/registerPost")
    public String register(Emploee emploee,Model model) {
        if(emploee.getPassword()==null){
            model.addAttribute("fail","请输入密码");
            return "register";
        }else if(emploee.getName()==null)
        {
            model.addAttribute("fail","请输入姓名");
            return "register";
        }
        emploee.setPassword(MD5.getMd5(emploee.getPassword()));
        emploeeService.insertSelective(emploee);
        Emploee emploee1=emploeeService.getIdByName(emploee.getName());
        model.addAttribute("success","用户创建成功，请牢记您的账号为:"+emploee1.getId());
        return "login";
    }

}