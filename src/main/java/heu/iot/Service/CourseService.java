package heu.iot.Service;

import heu.iot.Dao.CourseMapper;
import heu.iot.Dao.Course_EmploeeMapper;
import heu.iot.Dao.Emploee_CourseMapper;
import heu.iot.Model.Course;
import heu.iot.Model.Course_Emploee;
import heu.iot.Model.Emploee_Course;
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
    private Emploee_CourseMapper emploee_courseMapper;
    @Autowired
    private CourseMapper courseMapper;

    public List<Course> showSelected(String cname){
        return courseMapper.showSelected(cname);
    }


    public Course selectByPrimaryKey(Integer id){return courseMapper.selectByPrimaryKey(id);}

    public int countCourseNum(){
        return courseMapper.countCourseNum();
    }

    public List<Course> showAllCourse(){
        return courseMapper.showAllCourse();
    }

    public List<Emploee_Course> showAllEmploeeCourse(){
        return emploee_courseMapper.showAllCourse();
    }

    public List<Emploee_Course> selectByName(String cname ){
        return emploee_courseMapper.selectCourseListByName(cname);
    }
    public int insertCourse(Course course){
        return  courseMapper.insertSelective(course);
    }

    public int updateCourse(Course course){
        return courseMapper.updateByPrimaryKey(course);
    }

    public Emploee_Course selectByID(Integer id){
        return emploee_courseMapper.selectByID(id);
    }
    public int updateCourses(Course course){
        return courseMapper.updateByPrimaryKeySelective(course);
    }
    public int deleteCourse(Integer id){return courseMapper.deleteByPrimaryKey(id);}
}
