package heu.iot.Controller.Student;

import heu.iot.Service.InfoService;
import heu.iot.Util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Sumail-Lee
 * @Date: 9:45 2017/11/29
 */
@Controller
@RequestMapping("/student")
public class InfoController {
    @Autowired
    private InfoService infoService;

    private MD5 md5 = new MD5();

    @RequestMapping("/info")
    public String showInfo() {
        return "student/info";
    }

    @RequestMapping("/changePassword")
    public String changePassword(String inputOrigin, String inputPassword1, String inputPassword2, Model model) {
        if (!inputPassword2.equals(inputPassword1)) {
            model.addAttribute("fail", "更新失败，新密码两次输入不正确");
            return "student/info";
        }
        System.out.println("correct");
        inputOrigin = md5.getMd5(inputOrigin);
        inputPassword1 = md5.getMd5(inputPassword1);
        System.out.println(inputOrigin);
        Integer id = 2013201308;


        int result = infoService.changePassword(id, inputOrigin, inputPassword1);
        if (result == 0)
            model.addAttribute("fail", "更新失败，密码错误");
        else
            model.addAttribute("success", "更新成功");
        return "student/info";
    }
}
