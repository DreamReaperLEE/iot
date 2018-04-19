package heu.iot.Dao;

import heu.iot.Model.Course_Emploee;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Date: 16:53 2017/11/29
 */

public interface Course_EmploeeMapper {
    List<Course_Emploee> showAllCourse();

    List<Course_Emploee> showSelected(String cname);

    List<Course_Emploee> showRecent5();
}
