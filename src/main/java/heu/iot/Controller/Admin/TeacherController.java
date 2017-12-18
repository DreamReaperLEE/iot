package heu.iot.Controller.Admin;


import heu.iot.Model.Emploee;
import heu.iot.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TeacherController {


    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/teacher/{page}")
    public String showTeacher(@PathVariable("page") int page, Model model){
        List<Emploee> teacherList = employeeService.showTeacherByPage(page);
        model.addAttribute("a",teacherList);
        return "admin/allTeacher";}


    @RequestMapping("/teacher/delete/{id}")
    public String deleteteacher(@PathVariable("id") int id,Model model){
        employeeService.deleteEmploee(id);
        return "redirect:../0";
    }

    @RequestMapping("/goteacher")
    public String goToUpdateteacher(@RequestParam(value = "id")Integer id, Model model){
        Emploee emploee = employeeService.selectByPrimaryKey(id);
        model.addAttribute("emploee",emploee);
        return "admin/updateTeacher";
    }

    @PostMapping("/updateteacher")
    public String updateteacher(Emploee emploee,Model model){
        emploee.setPriv(1);
        employeeService.updateByPrimaryKey(emploee);
        model.addAttribute("up","更改成功！");
        return "redirect:../admin/teacher/0";
    }

    @RequestMapping("/goaddteacher")
    public String gotoadd(){
        return "/admin/addteacher";
    }

    @PostMapping("/addteacher")
    public String addteacher(Emploee emploee,Model model){
        emploee.setPriv(1);
        employeeService.insertEmploee(emploee);
        return "redirect:../admin/teacher/0";}


    @PostMapping("/selectteacher")
    public String gotoselect(@RequestParam(value = "sid")Integer id,Model model){
        heu.iot.Model.Emploee emploee = employeeService.selectByPrimaryKey(id);
        model.addAttribute("emploee",emploee);
        return "admin/selectteacher";}




    }