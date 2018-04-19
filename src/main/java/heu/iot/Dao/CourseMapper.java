package heu.iot.Dao;

import heu.iot.Model.Course;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;


public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> showAllCourse();

    int countCourseNum();

    List<Course> selectByCname(String cname);

    List<Course> selectByTid(Integer tid);

    List<Course> selectByTypeDirect(@Param("type") Integer type, @Param("direct") Integer direct);

    List<Course> showSelected(ArrayList<Integer> info);
}