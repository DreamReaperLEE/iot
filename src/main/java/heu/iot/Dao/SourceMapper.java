package heu.iot.Dao;

import heu.iot.Model.Source;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Source record);

    int insertSelective(Source record);

    Source selectByPrimaryKey(Integer id);

    List<Source> selectByCourseLesson(@Param("id") Integer id,@Param("lesson") Integer lesson);

    List<Source> selectByCourse(Integer cid);

    int updateByPrimaryKeySelective(Source record);

    int updateByPrimaryKey(Source record);
}