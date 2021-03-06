package heu.iot.Service;

import heu.iot.Dao.CourseMapper;
import heu.iot.Dao.Course_EmploeeMapper;
import heu.iot.Model.Course;
import heu.iot.Model.Course_Emploee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Date: 16:55 2017/11/29
 */
@Service("courseService")
public class CourseService {
    @Autowired
    private Course_EmploeeMapper course_emploeeMapper;
    @Autowired
    private CourseMapper courseMapper;

    public List<Course_Emploee> showAllCourse(){
        return course_emploeeMapper.showAllCourse();
    }
    public Course selectByPrimaryKey(Integer id){return courseMapper.selectByPrimaryKey(id);}
}
