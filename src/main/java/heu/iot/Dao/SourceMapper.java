package heu.iot.Dao;

import heu.iot.Model.Course_Source;
import heu.iot.Model.Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Source record);

    int insertSelective(Source record);

    Source selectByPrimaryKey(Integer id);

    List<Source> selectByCourseLesson(@Param("id") Integer id, @Param("lesson") Integer lesson);

    List<Source> selectByCourse(Integer cid);

    int updateByPrimaryKeySelective(Source record);

    int updateByPrimaryKey(Source record);

    List<Source> showAllSource();

    //    增加课程
    int addSource(Source source);

    int deleteByPrimaryKeyAndLowKey(@Param("id") Integer id,@Param("lesson") Integer lesson);

    int deleteByPrimaryKeyAndLowKeyAndTopic(@Param("id") Integer id,@Param("lesson") Integer lesson,
                                            @Param("topic") String topic);

    Source selectByPrimaryKeyAndLowKeyAndTopic(@Param("id") Integer id,@Param("lesson") Integer lesson,
                                               @Param("topic") String topic);



}