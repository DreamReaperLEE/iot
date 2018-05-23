package heu.iot.Service;

import heu.iot.Dao.ScoreMapper;
import heu.iot.Dao.Score_EmploeeMapper;
import heu.iot.Dao.Score_Exam_PaperMapper;
import heu.iot.Model.Score;
import heu.iot.Model.Score_Emploee;
import heu.iot.Model.Score_Exam_Paper;
import heu.iot.Model.Score_Exam_Paper_List;
import heu.iot.Util.TimeFactory;
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
    @Autowired
    private Score_EmploeeMapper score_emploeeMapper;

    public Score selectByPrimaryKey(Integer id){return scoreMapper.selectByPrimaryKey(id);}

    public List<Score_Exam_Paper_List> showOldExamByTidDate(Integer tid){
        return score_exam_paperMapper.showOldExamByTidDate(tid,TimeFactory.getCurrentDate());
    }
    public List<Score_Exam_Paper_List> showAllOldExamByDate(){
        return score_exam_paperMapper.showAllOldExamByDate(TimeFactory.getCurrentDate());
    }

    public List<Score_Exam_Paper> showAllExamByStudentId(Integer id) {
        return score_exam_paperMapper.showAllExamByStudentId(id);
    }

    public List<Score_Exam_Paper> showRecent5(Integer id){
        return score_exam_paperMapper.showRecent5(id);
    }

    public int submitPaper(Score score) {

        return scoreMapper.insertSelective(score);
    }

    public int updatePaper(Score score) {
        return scoreMapper.updatePaper(score);
    }

    public Score selectBySidPid(Integer sid, Integer pid) {
        return scoreMapper.selectBySidPid(sid, pid);
    }

    //求平均分
    public float getAverageScore(Integer sid){
        List<Score> scoreList=scoreMapper.selectByStudentId(sid);
        int i=0;
        float average=0;
        for(Score each:scoreList){
            average=average+each.getScore();
            i++;
        }
        average=average/i;
        return average;
    }

    public int countExamNum(Integer sid){
        return scoreMapper.countExamNum(sid);
    }
    public List<Score_Emploee> showAllExamById() {
        return score_emploeeMapper.showAllExamById();
    }

    public List<Score_Emploee> showExamByPid(Integer pid){return score_emploeeMapper.showExamByPid(pid);}

    public List<Score> selectByPid(Integer pid){return scoreMapper.selectByPid(pid);}

    public int updateByPrimaryKeySelective(Score score){return scoreMapper.updateByPrimaryKeySelective(score);}


}
