package heu.iot.Dao;

import heu.iot.Model.Homepage;

import java.util.List;

public interface HomepageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Homepage record);

    int insertSelective(Homepage record);

    Homepage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Homepage record);

    int updateByPrimaryKey(Homepage record);

    List<Homepage> selectByType(Integer htype);
}