package heu.iot.Dao;

import heu.iot.Model.Score_Exam_Paper;
import heu.iot.Model.Score_Exam_Paper_List;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Since: 17:31 2017/12/11
 */
public interface Score_Exam_PaperMapper {

    List<Score_Exam_Paper> showAllExamByStudentId(Integer id);

    List<Score_Exam_Paper> showRecent5(Integer id);

    List<Score_Exam_Paper_List> showOldExamByTidDate(@Param("tid") Integer tid, @Param("date") String date);

    List<Score_Exam_Paper_List> showAllOldExamByDate(String date);

}
