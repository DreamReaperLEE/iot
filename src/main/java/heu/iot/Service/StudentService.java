package heu.iot.Service;

import heu.iot.Dao.StudentDao;
import heu.iot.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Date: 9:49 2017/11/27
 */

@Service("studentService")
public class StudentService {
    @Autowired
    private StudentDao studentdao;

    public List<Student> getStudent(){
        return studentdao.findAll();
    }

    public Student addStudent(Student student){

        return studentdao.save(student);
    }

}
