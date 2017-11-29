package heu.iot.Controller.Student;

import heu.iot.Model.Emploee;
import heu.iot.Service.EmploeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Date: 11:33 2017/11/29
 */
@RestController
@RequestMapping("/student")
public class test {
    @Autowired
    private EmploeeService emploeeService;

    @RequestMapping("/test")
    public Emploee showEmploee() throws Exception{
        return emploeeService.showEmploee();
    }
}
