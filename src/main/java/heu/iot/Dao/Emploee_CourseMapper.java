package heu.iot.Dao;

import heu.iot.Model.Emploee_Course;

import java.util.List;

public interface Emploee_CourseMapper {
    List<Emploee_Course> showAllCourse();
    List<Emploee_Course> selectCourseListByName(String cname);
    Emploee_Course selectByID(Integer id);
    List<Emploee_Course> showNew5();
}
