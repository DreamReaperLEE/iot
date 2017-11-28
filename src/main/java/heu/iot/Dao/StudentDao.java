package heu.iot.Dao;

import heu.iot.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Date: 9:48 2017/11/27
 */
public interface StudentDao extends JpaRepository<Student,Integer>{
//    @Query("select p from Person p where p.name=:name and p.address=:address")
//    Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);
}
