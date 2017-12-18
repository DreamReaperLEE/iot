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
public class StudentController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/student/{page}")
    public String showStudent(@PathVariable("page") int page, Model model) {
        List<Emploee> studentList = employeeService.showStudentByPage(page);
        model.addAttribute("a", studentList);
        return "admin/allStudent";
    }

    @RequestMapping("/student/delete/{id}")
    public String deletestudent(@PathVariable("id") int id,Model model){
        employeeService.deleteEmploee(id);
        return "redirect:../0";}


    @RequestMapping("/gostudent")
    public String goToUpdatestudent(@RequestParam(value = "id")Integer id, Model model){
        Emploee emploee = employeeService.selectByPrimaryKey(id);
        model.addAttribute("emploee",emploee);
        return "admin/updateStudent";
    }

    @PostMapping("/updatestudent")
    public String updateteacher(Emploee emploee,Model model){
        emploee.setPriv(2);
        employeeService.updateByPrimaryKey(emploee);
        model.addAttribute("up","更改成功！");
        return "redirect:../admin/student/0";
    }

    @RequestMapping("/goaddstudent")
    public String gotoadds(){
        return "/admin/addstudent";
    }

    @PostMapping("/addstudent")
    public String addteacher(Emploee emploee,Model model){
        emploee.setPriv(2);
        employeeService.insertEmploee(emploee);
        return "redirect:../admin/student/0";}


    @PostMapping("/selectstudent")
    public String gotoselect(@RequestParam(value = "sid")Integer id,Model model){
        heu.iot.Model.Emploee emploee = employeeService.selectByPrimaryKey(id);
        model.addAttribute("emploee",emploee);
        return "admin/selectstudent";}
}
