package heu.iot.Dao;

import heu.iot.Model.Co_type;

import java.util.List;

public interface Co_typeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Co_type record);

    int insertSelective(Co_type record);

    Co_type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Co_type record);

    int updateByPrimaryKey(Co_type record);

    List<Co_type> showAll();
}