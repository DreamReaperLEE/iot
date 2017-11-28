package heu.iot.Controller.Student;

import heu.iot.Model.Student;
import heu.iot.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Date: 9:09 2017/11/27
 */

@RestController
@RequestMapping("/student")
public class hello {
    @Autowired
    private StudentService studentservice;
    @RequestMapping("/hello")
    public List<Student> hello(){
        return studentservice.getStudent();
    }

    @RequestMapping("/add")
    public Student add(@RequestParam("id") Integer id,@RequestParam("name") String name){
        Student student=new Student();
        student.setId(id);
        student.setName(name);
        return studentservice.addStudent(student);
    }
}