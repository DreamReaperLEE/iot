package heu.iot.Dao;

import heu.iot.Model.Exam;
import heu.iot.Model.ExamEmploee;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ExamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);

    List<Exam> showCurrentExam(@Param("date") String date, @Param("time") String time);

    int countExamNum();

    List<ExamEmploee> selectExamEmploeeRecent5();

    List<ExamEmploee> selectAllExamEmploee();

    List<Exam> selectAllExam();
}