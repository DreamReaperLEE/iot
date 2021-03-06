package heu.iot.Dao;

import heu.iot.Model.Score;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer id);

    List<Score> selectByStudentId(Integer id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
}