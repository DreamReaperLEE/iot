package heu.iot.Service;

import heu.iot.Dao.ExamMapper;
import heu.iot.Model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int insert(Exam record){
        return examMapper.insert(record);
    }

//    public List<Exam> showAllExam() throws ParseException {
//        String DateStr1 = "10:20:16";
//        String DateStr2 = "15:50:35";
//        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//        Date dateTime1 = dateFormat.parse(DateStr1);
//        Date dateTime2 = dateFormat.parse(DateStr2);
//        int i = dateTime1.compareTo(dateTime2);
//        System.out.println(i < 0);
//    }
}
