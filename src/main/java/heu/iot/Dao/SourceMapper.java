package heu.iot.Dao;

import heu.iot.Model.Source;
import org.springframework.stereotype.Repository;


public interface SourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Source record);

    int insertSelective(Source record);

    Source selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Source record);

    int updateByPrimaryKey(Source record);
}