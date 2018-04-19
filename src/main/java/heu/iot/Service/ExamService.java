package heu.iot.Service;

import heu.iot.Dao.ExamMapper;
import heu.iot.Model.Exam;
import heu.iot.Model.ExamEmploee;
import heu.iot.Util.TimeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Since: 16:49 2017/12/11
 */
@Service("examService")
public class ExamService {
    @Autowired
    private ExamMapper examMapper;

    public int insert(Exam record) {
        return examMapper.insert(record);
    }


    public List<Exam> showCurrentExam() {
        String date= TimeFactory.getCurrentDate();
        String time=TimeFactory.getCurrentHour();
        List<Exam> examList=examMapper.showCurrentExam(date,time);
        return examList;
    }

    public int countExamNum(){
        return examMapper.countExamNum();
    }

    public List<ExamEmploee> selectExamEmploeeRecent5(){
        return examMapper.selectExamEmploeeRecent5();
    }

    public List<ExamEmploee> selectAllExamEmploee(){
        return examMapper.selectAllExamEmploee();
    }

    public int deleteByPrimaryKey(Integer id){
        return examMapper.deleteByPrimaryKey(id);
    }

    public Exam selectByPrimaryKey(Integer id){
        return examMapper.selectByPrimaryKey(id);
    }

    public List<Exam> selectByEmploeeId(Integer tid){return examMapper.selectByEmploeeId(tid);}

    public int updateByPrimaryKeySelective(Exam exam){
        return examMapper.updateByPrimaryKeySelective(exam);
    }

    public int insertSelective(Exam exam){
        return examMapper.insertSelective(exam);
    }

    public List<Exam> selectAllExam(){
        return examMapper.selectAllExam();
    }


}
