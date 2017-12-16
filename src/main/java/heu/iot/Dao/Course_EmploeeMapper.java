package heu.iot.Dao;

import heu.iot.Model.Course_Emploee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Date: 16:53 2017/11/29
 */

public interface Course_EmploeeMapper {
    List<Course_Emploee> showAllCourse();
}
