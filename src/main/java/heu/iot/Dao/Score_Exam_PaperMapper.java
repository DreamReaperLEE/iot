package heu.iot.Dao;

import heu.iot.Model.Score_Exam_Paper;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Since: 17:31 2017/12/11
 */
public interface Score_Exam_PaperMapper {

    List<Score_Exam_Paper> showAllExamByStudentId(Integer id);

    List<Score_Exam_Paper> showRecent5(Integer id);
}
