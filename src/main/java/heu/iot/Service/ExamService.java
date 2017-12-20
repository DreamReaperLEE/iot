package heu.iot.Service;

import heu.iot.Dao.ExamMapper;
import heu.iot.Model.Exam;
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

    public List<Exam> showCurrentExam() {
        String date= TimeFactory.getCurrentDate();
        String time=TimeFactory.getCurrentHour();
        List<Exam> examList=examMapper.showCurrentExam(date,time);
        return examList;
    }


}
