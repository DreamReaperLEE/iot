package heu.iot.ComponentTools;

import heu.iot.Model.*;
import heu.iot.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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
    @Autowired
    private HomepageService homepageService;
    @Autowired
    private EmploeeService emploeeService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private InformService informService;

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

    public static List<Homepage> selectByTypeHomePage(Integer id){return testUtils.homepageService.selectByType(id);}

    public static List<Emploee> showSelectedEmploee(ArrayList<Integer> intlist){return testUtils.emploeeService.showSelected(intlist);}

    public static List<Course> showSelectedCourse(ArrayList<Integer> intlist){return testUtils.courseService.showSelected(intlist);}

    public static List<Integer> indexTop4(){
        int coursenum = testUtils.courseService.countCourseNum();
        int examnum = testUtils.examService.countExamNum();
        int teachernum = testUtils.emploeeService.countTeacherNum();
        int studentnum = testUtils.emploeeService.countStudentNum();
        List<Integer> top4=new ArrayList<Integer>();
        top4.add(coursenum);
        top4.add(examnum);
        top4.add(teachernum);
        top4.add(studentnum);
        return top4;
    }

    public static List<Inform_Emploee> showRecentInformEmploee(Integer num){
        return testUtils.informService.showRecent(num);
    }

}
