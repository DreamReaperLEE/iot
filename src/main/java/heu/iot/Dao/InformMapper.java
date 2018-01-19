package heu.iot.Dao;

import heu.iot.Model.Inform;

import java.util.List;

public interface InformMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Inform record);

    int insertSelective(Inform record);

    Inform selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Inform record);

    int updateByPrimaryKey(Inform record);

    List<Inform> selectAll();
}