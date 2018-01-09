package heu.iot.Dao;

import heu.iot.Model.Hierarchy;

import java.util.List;

public interface HierarchyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hierarchy record);

    int insertSelective(Hierarchy record);

    Hierarchy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hierarchy record);

    int updateByPrimaryKey(Hierarchy record);

    List<Hierarchy> showAll();
}