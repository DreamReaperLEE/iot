package heu.iot.Dao;

import heu.iot.Model.Co_direct;

import java.util.List;

public interface Co_directMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Co_direct record);

    int insertSelective(Co_direct record);

    Co_direct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Co_direct record);

    int updateByPrimaryKey(Co_direct record);

    List<Co_direct> showAll();
}