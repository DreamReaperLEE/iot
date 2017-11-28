package heu.iot.Controller.Student;

import heu.iot.Model.Emploee;
import heu.iot.Service.EmploeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Date: 9:09 2017/11/27
 */

@Controller
@RequestMapping("/student")
public class hello {
    @Autowired
    private EmploeeService emploeeService;
    @RequestMapping("/class")
    public String showclass(){
        return "student/sclass";
    }

    @RequestMapping("/show")
//    @RequestParam("id") Integer id, @RequestParam("name") String name
    public List<Emploee> show(){
        return emploeeService.getEmploee();
    }
}
