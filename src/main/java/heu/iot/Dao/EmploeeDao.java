package heu.iot.Dao;

import heu.iot.Model.Emploee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Sumail-Lee
 * @Date: 19:40 2017/11/28
 */
public interface EmploeeDao extends JpaRepository<Emploee,Integer>{
    //    @Query("select p from Person p where p.name=:name and p.address=:address")
//    Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);
}
