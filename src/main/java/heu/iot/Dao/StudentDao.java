package heu.iot.Dao;

import heu.iot.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Sumail-Lee
 * @Date: 9:48 2017/11/27
 */
public interface StudentDao extends JpaRepository<Student,Integer>{
}
