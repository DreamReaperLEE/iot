package heu.iot.Util;

import heu.iot.Model.Online_Test;
import heu.iot.Model.Question;
import heu.iot.Model.Question_Json;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description: 处理题库数据
 * @Since: 2017/12/20 14:13
 */
public class dealQuestion {
    /**
     * @Author: Sumail-Lee
     * @Description: 将题库类列表转换成Online_Test以便传送给前端使用，将Question里的json格式选项数据整理成ArrayList格式
     * @param questionList 题库类列表
     * @Date: 2017/12/20 19:58
     */
    public static ArrayList<Online_Test> QuestionToList(List<Question> questionList){
        ArrayList<Online_Test> online_tests=new ArrayList<Online_Test>();
        for(Question each:questionList){
            Question_Json question_json=MyJson.JsonToQuestion(each.getAnswer());
            Online_Test online_test=new Online_Test();
            online_test.setQuestion(each);
            online_test.setChoice(question_json.getChoice());
            online_tests.add(online_test);
        }
        return online_tests;
    }

    /**
     * @Author: Sumail-Lee
     * @Description: 将题库类列表转换成Online_Test以便传送给前端使用，将Question里的json格式选项数据整理成ArrayList格式，将学生作答答案（Answer）处理成ArrayList，将标准答案（Correct）处理成ArrayList
     * @param questionList
     * @param answer
     * @Date: 2017/12/20 20:00
     */
    public static ArrayList<Online_Test> CheckPaper(List<Question> questionList,String answer){
        ArrayList<Online_Test> online_tests=new ArrayList<Online_Test>();
        ArrayList<ArrayList<Integer>> answerList=MyJson.JsonToList(answer);
        for(int i=0;i<questionList.size();i++){
            Question_Json question_json=MyJson.JsonToQuestion(questionList.get(i).getAnswer());
            Online_Test online_test=new Online_Test();
            online_test.setQuestion(questionList.get(i));
            online_test.setChoice(question_json.getChoice());
            online_test.setCorrect(question_json.getAnswer());
            System.out.println(online_test.getCorrect());
            online_test.setAnswer(answerList.get(i));
            online_tests.add(online_test);
        }
        return online_tests;
    }
}
