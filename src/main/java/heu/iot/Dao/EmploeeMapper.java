package heu.iot.Dao;

import heu.iot.Model.Emploee;
import heu.iot.Model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmploeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Emploee record);

    int insertSelective(Emploee record);

    Emploee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Emploee record);

    int updateByPrimaryKey(Emploee record);

    List<Emploee> selectByEmploeePriv(Integer priv);

    int updatePassword(@Param("id") Integer id, @Param("origin") String origin, @Param("password") String password);

    Emploee checkPassword(@Param("id") Integer id, @Param("password") String password);

    List<Emploee> showAll();

    List<Emploee> selectEmploeeByName(String name);

    List<Emploee> selectByActive(Integer active);

    int countTeacherNum();

    int countStudentNum();

    List<Emploee> showSelected(List<Integer> info);

}