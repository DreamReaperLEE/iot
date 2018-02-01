package heu.iot.Controller.Admin;

import heu.iot.Model.Emploee;
import heu.iot.Service.EmploeeService;
import heu.iot.Util.EmploeeExcel;
import heu.iot.Util.Excel;
import heu.iot.Util.MD5;
import heu.iot.Util.dealFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:人员管理
 * @Since: 2018/1/31 11:24
 */
@Controller
@RequestMapping("/admin")
public class EmploeeController {


    @Autowired
    private EmploeeService employeeService;

    /**
     * @Author: Sumail-Lee
     * @Description: 展示所有老师
     * @param model
     * @Date: 2018/1/16 15:15
     */
    @RequestMapping("/teacher")
    public String showTeacher(Model model) {
        List<Emploee> adminList = employeeService.selectByEmploeePriv(1);
        model.addAttribute("adminList", adminList);
        return "admin/allTeacher";
    }

    /**
     * @Author: Sumail-Lee
     * @Description: 展示所有学生
     * @param model
     * @Date: 2018/1/16 15:15
     */
    @RequestMapping("/student")
    public String showStudent(Model model) {
        List<Emploee> adminList = employeeService.selectByEmploeePriv(2);
        model.addAttribute("adminList", adminList);
        return "admin/allStudent";
    }

    /**
     * @Author: Sumail-Lee
     * @Description: 人员详细信息
     * @param id 人员ID
     * @param url 是从哪个页面跳转过来的
     * @param model
     * @Date: 2018/1/16 15:15
     */
    @RequestMapping("/emploee/detail")
    public String showDetail(@RequestParam(value = "id") Integer id,@RequestParam(value = "url") String url, Model model) {
        Emploee emploee = employeeService.selectByPrimaryKey(id);
        model.addAttribute("emploee", emploee);
        model.addAttribute("url", url);
        return "admin/emploeeDetail";

    }

    /**
     * @Author: Sumail-Lee
     * @Description: 更新人员信息
     * @param model
     * @param session
     * @param url 从哪个页面跳转过来的
     * @param id 人员ID
     * @param name 人员姓名
     * @param email 人员邮箱
     * @param tel 人员电话
     * @param introduce 人员简介
     * @Date: 2018/1/16 15:17
     */
    @PostMapping("/emploee/update")
    public String updateEmploee(Model model, HttpSession session,@RequestParam(value = "url", defaultValue = "") String url, @RequestParam(value = "id") Integer id,@RequestParam(value = "name", defaultValue = "") String name, @RequestParam(value = "email", defaultValue = "") String email, @RequestParam(value = "tel", defaultValue = "") String tel, @RequestParam(value = "introduce", defaultValue = "") String introduce) {
        System.out.println(id);
        System.out.println(email);
        System.out.println(tel);
        System.out.println(introduce);
        Emploee emploee=new Emploee();
        emploee.setId(id);
        emploee.setName(name);
        emploee.setEmail(email);
        emploee.setTel(tel);
        emploee.setIntroduce(introduce);
        employeeService.updateByPrimaryKeySelective(emploee);
        if(url.equals("student"))
            return "redirect:/admin/student";
        else
            return "redirect:/admin/teacher";
    }


    /**
     * @Author: Sumail-Lee
     * @Description: 删除人员信息
     * @param id 人员id
     * @param url 从哪个页面跳转过来的
     * @Date: 2018/1/16 15:18
     */
    @RequestMapping("/emploee/delete")
    public String deleteEmploee(@RequestParam(value = "id") Integer id,@RequestParam(value = "url") String url) {
        Emploee emploee = employeeService.selectByPrimaryKey(id);
        if(emploee.getPic()!=null){
        File file = new File(emploee.getPic());
        if (file.exists() && file.isFile()) {
            file.delete();}
            employeeService.deleteEmploee(id);}
        else {
        employeeService.deleteEmploee(id);}
        if(url.equals("student"))
            return "redirect:/admin/student";
        else
            return "redirect:/admin/teacher";
    }

    /**
     * @Author: Sumail-Lee
     * @Description: 批量上传文件
     * @param model
     * @param file
     * @Date: 2018/1/18 18:05
     */
    @PostMapping("/addemploee/uploadExcel")
    public String addSource(Model model, @RequestParam("file") MultipartFile file) throws Exception {
        int addi=0,existi=0,faili=0;
        String filename=dealFile.saveFile("excel",file);
        String filePath="D:\\java_workplace\\iot\\src\\main\\resources\\static\\excel\\"+filename;
        File dest = new File(filePath);
        List<Emploee> emploeeList= EmploeeExcel.addEmploee(dest);
        for(Emploee each:emploeeList){
            Emploee exist=employeeService.selectByPrimaryKey(each.getId());
            if(exist!=null){
                existi++;
                continue;
            }
            else{
                each.setPassword(MD5.getMd5(each.getPassword()));
                if(employeeService.insertSelective(each)==1)
                    addi++;
                else
                    faili++;
            }
        }
        model.addAttribute("pisuccess", "批量上传成功！");
        model.addAttribute("addi", addi);
        model.addAttribute("existi", existi);
        model.addAttribute("faili", faili);
        return "/admin/addemploee";
    }

    /**
     * @Author: Sumail-Lee
     * @Description: 跳转到新建人员界面
     * @param
     * @Date: 2018/1/16 16:01
     */
    @RequestMapping("/addemploee")
    public String goadd() {
        return "/admin/addemploee";
    }

    /**
     * @Author: Sumail-Lee
     * @Description: 新建人员，单个
     * @param emploee 人员信息
     * @param file 头像
     * @param model
     * @Date: 2018/1/16 16:02
     */
    @PostMapping("/emploee/add")
    public String addadminstor(Emploee emploee, HttpServletRequest request, @RequestParam("imgfile") MultipartFile file, Model model) {
        //判断是否已存在账号
        Emploee exist=employeeService.selectByPrimaryKey(emploee.getId());
        if(exist!=null){
            model.addAttribute("fail", "已经存在该工号的账号");
            return "/admin/addemploee";
        }
        //默认激活
        emploee.setActive(1);
        //默认密码为账号
        emploee.setPassword(MD5.getMd5(String.valueOf(emploee.getId())));
        //存头像
        if(!file.isEmpty()) {
            String filename = dealFile.saveFile("pic",file);
            emploee.setPic("/pic/"+filename);
        }

        //存人员信息
        if(employeeService.insertSelective(emploee)==1)
            model.addAttribute("success", "账号创建成功！");

        return "/admin/addemploee";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:批量导出人员数据
     * @param response
     * @param type 导出学生/老师
     * @param request
     * @Date: 2018/2/1 13:44
     */
    @RequestMapping("/emploee/excel")
    @ResponseBody
    public String emploeeExcle(HttpServletResponse response,@RequestParam("type") String type,HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List<Emploee> emploeeList;
        if(type.equals("teacher"))
            emploeeList= employeeService.selectByEmploeePriv(1);
        else
            emploeeList = employeeService.selectByEmploeePriv(2);

        String fineName="emploeeList";
        ArrayList<String> title= EmploeeExcel.getTitle();
        ArrayList<ArrayList<String>> data=EmploeeExcel.getData(emploeeList);
        return Excel.createExcel(fineName,title,data,response);

    }



}

