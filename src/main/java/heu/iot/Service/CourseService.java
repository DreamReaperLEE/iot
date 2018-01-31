package heu.iot.Service;

import heu.iot.Dao.CourseMapper;
import heu.iot.Dao.Course_EmploeeMapper;
import heu.iot.Dao.Emploee_CourseMapper;
import heu.iot.Model.Course;
import heu.iot.Model.Course_Emploee;
import heu.iot.Model.Emploee_Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    //根据课程名称选择
    public List<Course> selectByCname(String cname){
        return courseMapper.selectByCname(cname);
    }
    //根据课程方向、课程类型选择
    public List<Course> selectByTypeDirect(Integer type,Integer direct){
        return courseMapper.selectByTypeDirect(type,direct);
    }
    //选具体某一门课
    public Course selectByPrimaryKey(Integer id){return courseMapper.selectByPrimaryKey(id);}
    //获取总课程数量
    public int countCourseNum(){
        return courseMapper.countCourseNum();
    }
    //获取所有课程
    public List<Course> showAllCourse(){
        return courseMapper.showAllCourse();
    }

    public List<Emploee_Course> showAllEmploeeCourse(){
        return emploee_courseMapper.showAllCourse();
    }

    public List<Emploee_Course> selectByName(String cname ){
        return emploee_courseMapper.selectCourseListByName(cname);
    }
    public int insertSelective(Course course){
        return  courseMapper.insertSelective(course);
    }

    public int updateByPrimaryKeySelective(Course course){
        return courseMapper.updateByPrimaryKeySelective(course);
    }

    public Emploee_Course selectByID(Integer id){
        return emploee_courseMapper.selectByID(id);
    }


    public int deleteCourse(Integer id){return courseMapper.deleteByPrimaryKey(id);}

    public List<Course> showSelected(ArrayList<Integer> info){
        return courseMapper.showSelected(info);
    }

    public List<Emploee_Course> showNew5(){
        return emploee_courseMapper.showNew5();
    }
}
