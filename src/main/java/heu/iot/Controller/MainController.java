package heu.iot.Controller;


import heu.iot.Model.Emploee;
import heu.iot.Service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2017/12/17 19:22
 */
@Controller
public class MainController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/loginPost")
    public String loginPost(String account, String password, HttpSession session) {
        Emploee emploee=infoService.login(Integer.valueOf(account),password);
        if (emploee==null) {
            return "login";
        }
        // 设置session
        session.setAttribute(WebSecurityConfig.ID, account);
        session.setAttribute(WebSecurityConfig.NAME, emploee.getName());
        session.setAttribute(WebSecurityConfig.LEVEL,emploee.getPriv());
        if(emploee.getPriv()==2)
            return "student/allCourse";
        else
            return "student/allScore";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.ID);
        session.removeAttribute(WebSecurityConfig.NAME);
        session.removeAttribute(WebSecurityConfig.LEVEL);
        return "redirect:/login";
    }

}