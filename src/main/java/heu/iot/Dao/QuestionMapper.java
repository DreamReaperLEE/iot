package heu.iot.Dao;

import heu.iot.Model.Question;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> showAllQuestion();
}