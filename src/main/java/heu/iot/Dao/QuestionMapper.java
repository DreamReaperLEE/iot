package heu.iot.Dao;

import heu.iot.Model.Question;

import java.util.List;


public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    List<Question> selectByCourseId(Integer cid);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> showAllQuestion();

    List<Question> showSelected(List<Integer> info);

    int countQuestionNum();

}