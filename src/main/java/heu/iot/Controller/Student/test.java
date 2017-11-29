package heu.iot.Controller.Student;

import heu.iot.Model.Course_Emploee;
import heu.iot.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Date: 16:58 2017/11/29
 */
@RestController
public class test {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/student/test")
    public List<Course_Emploee> show(){
        return courseService.showAllCourse();
    }
}
