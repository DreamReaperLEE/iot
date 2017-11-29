package heu.iot.Dao;

import heu.iot.Model.Emploee;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmploeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Emploee record);

    int insertSelective(Emploee record);

    Emploee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Emploee record);

    int updateByPrimaryKey(Emploee record);

    List<Emploee> showAll();
}