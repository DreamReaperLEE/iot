package heu.iot.MyThread;

import heu.iot.ComponentTools.TestUtils;
import heu.iot.Model.*;
import heu.iot.Util.MyJson;
import heu.iot.Util.TimeFactory;
import org.aspectj.weaver.ast.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:自动判卷线程
 * @Since: 2018/4/24 9:01
 */
public class GradePaper implements Runnable{
    public static ConcurrentHashMap<Integer,Exam> examConcurrentHashMap=new ConcurrentHashMap<Integer,Exam>();
    public GradePaper(){
        List<Exam> examList=TestUtils.showTodayUndoneExam();
        for(Exam each:examList){
            examConcurrentHashMap.put(each.getId(),each);
        }
    }

    /**
     * @Author: Sumail-Lee
     * @Description:自动判卷线程
     * @param
     * @Date: 2018/4/24 15:28
     */
    public void run(){

        String time;
        String Date=TimeFactory.getCurrentDate();
        while(true){
            //新的一天更新考试列表
            if(!Date.equals(TimeFactory.getCurrentDate())){
                updateExamList();
                Date=TimeFactory.getCurrentDate();
            }

            time=TimeFactory.getCurrentTime();
            for(Map.Entry<Integer,Exam> each: examConcurrentHashMap.entrySet() ){
                Exam exam=each.getValue();
                if(exam.getEtime().compareTo(time)<0){
                    CheckPaper(exam.getId());
                    exam.setDone(1);
                    TestUtils.updateByPrimaryKeySelectiveExam(exam);
                    examConcurrentHashMap.remove(exam.getId());
                }
            }
            try {
                Thread.sleep(60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @Author: Sumail-Lee
     * @Description:判卷第一步
     * @param eid 考试ID
     * @Date: 2018/4/24 15:27
     */
    public static void CheckPaper(Integer eid){

        Paper paper=TestUtils.selectByEid(eid);
        Exam_Json exam_json = MyJson.JsonToExam(paper.getDetail());
        List<String> info = exam_json.getInfo();
        //获取题号列表
        List<Integer> numinfo = new ArrayList<Integer>();
        for (String each : info) {
            numinfo.add(Integer.valueOf(each));
        }
        //获取所有题目
        List<Question> questionList = TestUtils.showSelected(numinfo);
        int questionnum=questionList.size();
        Integer totalpriv=0;
        for(Question question:questionList){
            totalpriv=totalpriv+question.getLevel();
        }
        //获取所有试卷
        List<Score> scores=TestUtils.selectByPid(paper.getId());

        for(Score score:scores){
            score.setScore(PanPaper(questionList,score,totalpriv));
            TestUtils.updateByPrimaryKeySelectiveScore(score);
        }
    }

    /**
     * @Author: Sumail-Lee
     * @Description:判卷第二步
     * @param questionList 题目列表
     * @param score 学生所答试卷
     * @param totalpriv 题目权值合
     * @Date: 2018/4/24 15:27
     */
    public static float PanPaper(List<Question> questionList,Score score,Integer totalpriv){

        float endscore=0;
        Question_Json question_json;
        ArrayList<Integer> correctList;
        ArrayList<Integer> myChoice;
        ArrayList<ArrayList<Integer>> choice=MyJson.JsonToList(score.getDetail());
        for(int i=0;i<questionList.size();i++){
            //当前题目答案
            question_json=MyJson.JsonToQuestion(questionList.get(i).getAnswer());
            correctList=question_json.getAnswer();
            //学生答案
            myChoice=choice.get(i);
            if(correctList.equals(myChoice)){
                endscore=endscore+questionList.get(i).getLevel()*100/totalpriv;
            }
        }
        return endscore;
    }

    /**
     * @Author: Sumail-Lee
     * @Description:更新当天的考试列表
     * @param
     * @Date: 2018/4/24 15:26
     */
    public static void updateExamList(){

        examConcurrentHashMap=new ConcurrentHashMap<Integer,Exam>();
        List<Exam> examList=TestUtils.showTodayUndoneExam();
        for(Exam each:examList){
            examConcurrentHashMap.put(each.getId(),each);
        }
    }

}
