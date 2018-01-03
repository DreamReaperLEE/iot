package heu.iot.Dao;

import heu.iot.Model.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer id);

    Score selectBySidPid(@Param("sid") Integer sid, @Param("pid") Integer pid);

    List<Score> selectByStudentId(Integer id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);

    int updatePaper(Score record);

    int countExamNum(Integer sid);

}