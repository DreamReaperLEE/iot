package heu.iot.Controller.Admin;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.Emploee;
import heu.iot.Service.EmploeeService;
import heu.iot.Service.InfoService;
import heu.iot.Util.MD5;
import heu.iot.Util.dealFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:个人信息
 * @Since: 2018/1/24 15:32
 */
@Controller
@RequestMapping("/admin/info")
public class InfoAdminController {
    @Autowired
    private EmploeeService emploeeService;
    @Autowired
    private InfoService infoService;
    //将密码转换为md5
    private MD5 md5 = new MD5();

    /**
     * @Author: Sumail-Lee
     * @Description:跳转到修改密码
     * @param
     * @Date: 2018/1/24 15:33
     */
    @RequestMapping("/password")
    public String showInfo() {
        return "admin/info";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:个人详细信息
     * @param model
     * @param session
     * @Date: 2018/1/24 15:34
     */
    @RequestMapping("/Detailinfo")
    public String detailInfo(Model model, HttpSession session) {
        Integer id=Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString());
        model.addAttribute("emploee", emploeeService.selectByPrimaryKey(id));
        return "admin/DetailInfo";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:修改个人详细信息
     * @param model
     * @param session
     * @param name 姓名
     * @param email 邮箱
     * @param tel 电话
     * @param introduce 简介
     * @Date: 2018/1/24 15:35
     */
    @RequestMapping("/changeDetailInfo")
    public String changeDetailInfo(Model model, HttpSession session, @RequestParam(value = "name", defaultValue = "") String name, @RequestParam(value = "email", defaultValue = "") String email, @RequestParam(value = "tel", defaultValue = "") String tel, @RequestParam(value = "introduce", defaultValue = "") String introduce) {

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

        return "admin/DetailInfo";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:更换头像
     * @param file 头像
     * @param id 用户ID
     * @Date: 2018/1/24 15:36
     */
    @RequestMapping("/changePic")
    public String changePic(@RequestParam("imgfile") MultipartFile file, @RequestParam("id") Integer id){

        Emploee emploee=new Emploee();
        emploee.setId(id);
        //存头像
        if(!file.isEmpty()) {
            String filename = dealFile.saveFile("pic",file);
            emploee.setPic(filename);
        }
        emploeeService.updateByPrimaryKeySelective(emploee);
        return "redirect:/admin/info/Detailinfo";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:修改密码
     * @param inputOrigin 原始密码
     * @param inputPassword1 新密码
     * @param inputPassword2 重复输入
     * @param model
     * @param request
     * @Date: 2018/1/24 15:37
     */
    @RequestMapping("/changePassword")
    public String changePassword(String inputOrigin, String inputPassword1, String inputPassword2, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //判断两次密码输入是否一致
        if (!inputPassword2.equals(inputPassword1)) {
            model.addAttribute("fail", "更新失败，新密码两次输入不正确");
            return "admin/info";
        }
        //将密码转换为md5
        inputOrigin = MD5.getMd5(inputOrigin);
        inputPassword1 = MD5.getMd5(inputPassword1);
        Integer id = Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString());
        //判断是否更新成功
        int result = infoService.changePassword(id, inputOrigin, inputPassword1);
        if (result == 0)
            model.addAttribute("fail", "更新失败，密码错误");
        else
            model.addAttribute("success", "更新成功");
        return "admin/info";
    }
}
