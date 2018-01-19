package heu.iot.Controller.Student;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.Emploee;
import heu.iot.Service.EmploeeService;
import heu.iot.Service.InfoService;
import heu.iot.Util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 学生修改密码
 *
 * @Author: Sumail-Lee
 * @Date: 9:45 2017/11/29
 */
@Controller
@RequestMapping("/student")
public class InfoController {
    @Autowired
    private InfoService infoService;

    @Autowired
    private EmploeeService emploeeService;
    //将密码转换为md5
    private MD5 md5 = new MD5();

    /**
     * @param
     * @Author: Sumail-Lee
     * @Description: 跳转到修改密码界面
     * @Date: 2017/12/17 20:56
     */
    @RequestMapping("/info")
    public String showInfo() {
        return "student/info";
    }

    /**
     * @Author: Sumail-Lee
     * @Description: 个人信息修改界面
     * @param model
     * @param session
     * @Date: 2018/1/10 15:35
     */
    @RequestMapping("/Detailinfo")
    public String detailInfo(Model model,HttpSession session) {
        Integer id=Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString());
        model.addAttribute("emploee", emploeeService.selectByPrimaryKey(id));
        return "student/DetailInfo";
    }

    @RequestMapping("/changeDetailInfo")
    public String changeDetailInfo(Model model,HttpSession session,@RequestParam(value = "name", defaultValue = "") String name,@RequestParam(value = "email", defaultValue = "") String email,@RequestParam(value = "tel", defaultValue = "") String tel,@RequestParam(value = "introduce", defaultValue = "") String introduce) {
        Integer id=Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString());
        Emploee emploee=new Emploee();
        emploee.setId(id);
        emploee.setName(name);
        emploee.setEmail(email);
        emploee.setTel(tel);
        emploee.setIntroduce(introduce);
        int status=emploeeService.updateByPrimaryKeySelective(emploee);
        if(status==0){
            model.addAttribute("fail", "更新失败，请检查输入项是否符合格式");
        }
        else{
            model.addAttribute("success", "更新成功！请重新登陆以显示最新信息");
        }
        model.addAttribute("emploee", emploeeService.selectByPrimaryKey(id));

        return "student/DetailInfo";
    }

    /**
     * @param inputOrigin    原始密码
     * @param inputPassword1 新密码
     * @param inputPassword2 再次输入新密码
     * @param model
     * @Author: Sumail-Lee
     * @Description: 修改密码
     * @Date: 2017/12/17 20:57
     */
    @RequestMapping("/changePassword")
    public String changePassword(String inputOrigin, String inputPassword1, String inputPassword2, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //判断两次密码输入是否一致
        if (!inputPassword2.equals(inputPassword1)) {
            model.addAttribute("fail", "更新失败，新密码两次输入不正确");
            return "student/info";
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
        return "student/info";
    }
}
