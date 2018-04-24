package heu.iot.ComponentTools;

import heu.iot.Model.*;
import heu.iot.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class TestUtils {

    @Autowired
    private ExamService examService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ScoreService scoreService;

    public static TestUtils testUtils;
    @PostConstruct
    public void init() {
        testUtils = this;
    }
    public static List<Exam> showTodayUndoneExam(){
        return testUtils.examService.showTodayUndoneExam();
    }

    public static Paper selectByEid(Integer eid){
        return testUtils.paperService.selectByEid(eid);
    }

    public static List<Question> showSelected(List<Integer> info){return testUtils.questionService.showSelected(info);}

    public static List<Score> selectByPid(Integer pid){return testUtils.scoreService.selectByPid(pid);}

    public static int updateByPrimaryKeySelectiveScore(Score score){return testUtils.scoreService.updateByPrimaryKeySelective(score);}

    public static int updateByPrimaryKeySelectiveExam(Exam exam){return testUtils.examService.updateByPrimaryKeySelective(exam);}





}
