package heu.iot.Dao;

import heu.iot.Model.Course_Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Course_SourceMapper {
    List<Course_Source> showAllLessonSource(Integer id);

    List<Course_Source> showOnlyLesson(Integer id);

//    传两个参数的时候，必须要写@Param("cid")Integer cid     ！！！！！！！！！！！！！！
    List<Course_Source> showSpecialLesson(@Param("cid") Integer cid, @Param("lesson") Integer lesson);

    List<Course_Source> showSpecialLessonType0(@Param("cid") Integer cid, @Param("lesson") Integer lesson);

    List<Course_Source> showAllSource();
}
