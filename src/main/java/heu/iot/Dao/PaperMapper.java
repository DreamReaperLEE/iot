package heu.iot.Dao;

import heu.iot.Model.Paper;


public interface PaperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Paper record);

    int insertSelective(Paper record);

    Paper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Paper record);

    int updateByPrimaryKey(Paper record);

    Paper selectByEid(Integer id);
}