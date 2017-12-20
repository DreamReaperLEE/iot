package heu.iot.Service;

import heu.iot.Dao.ScoreMapper;
import heu.iot.Dao.Score_Exam_PaperMapper;
import heu.iot.Model.Score;
import heu.iot.Model.Score_Exam_Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Since: 17:07 2017/12/11
 */
@Service("scoreService")
public class ScoreService {
    @Autowired
    private Score_Exam_PaperMapper score_exam_paperMapper;
    @Autowired
    private ScoreMapper scoreMapper;

    public List<Score_Exam_Paper> showAllExamByStudentId(Integer id){
        return score_exam_paperMapper.showAllExamByStudentId(id);
    }

     public int submitPaper(Score score){
//        scoreMapper.updatePaper(score);
        return scoreMapper.insertSelective(score);
     }

    public Score selectBySidPid(Integer sid,Integer pid){
        return scoreMapper.selectBySidPid(sid,pid);
    }


}
