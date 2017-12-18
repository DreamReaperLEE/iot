package heu.iot.Controller.Admin;

//import heu.iot.Model.Emploee;
import heu.iot.Model.Emploee;
import heu.iot.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class  EmploeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/adminstor/{page}")
    public String showAdminstor(@PathVariable("page") int page, Model model) {
        List<Emploee> adminList = employeeService.showAdminByPage(page);
        model.addAttribute("a", adminList);
        return "admin/allAdminstor";
    }

    @RequestMapping("/adminstor/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        employeeService.deleteEmploee(id);
        model.addAttribute("sign", "删除成功");
        return "redirect:../0";
    }

    @RequestMapping("/goadminstor")
    public String goToUpdate(@RequestParam(value = "id") Integer id, Model model) {
        Emploee emploee = employeeService.selectByPrimaryKey(id);
        model.addAttribute("emploee", emploee);
        return "admin/updateAdmin";
    }

    @PostMapping("/updateadminstor")
    public String update(Emploee emploee, Model model) {
        emploee.setPriv(0);
        employeeService.updateByPrimaryKey(emploee);
        model.addAttribute("up", "更改成功！");
        return "redirect:../admin/adminstor/0";
    }

    @RequestMapping("/addadminstor")
    public String goadd() {
        return "/admin/addadminstor";
    }

    @PostMapping("/addadmin")
    public String addadminstor(Emploee emploee, Model model) {
        emploee.setPriv(0);
        employeeService.insertEmploee(emploee);
        return "redirect:../admin/adminstor/0";
    }

    @PostMapping("/selectadminstor")
    public String goselect(@RequestParam(value = "sid") Integer id, Model model) {
        heu.iot.Model.Emploee emploee = employeeService.selectByPrimaryKey(id);
        emploee.setPriv(0);
        model.addAttribute("emploee", emploee);
        return "admin/selectadminstor";
    }
}