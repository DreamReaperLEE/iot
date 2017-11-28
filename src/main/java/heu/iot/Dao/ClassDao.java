package heu.iot.Dao;

import heu.iot.Model.Class;
import heu.iot.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Sumail-Lee
 * @Date: 10:06 2017/11/28
 */
public interface ClassDao extends JpaRepository<Class,Integer>{
    public List<Class> findByStudent_idAndSid();
}
