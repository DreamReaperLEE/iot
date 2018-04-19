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
     * @param questionList 题库类列表
     * @Author: Sumail-Lee
     * @Description: 将题库类列表转换成Online_Test以便传送给前端使用，将Question里的json格式选项数据整理成ArrayList格式
     * @Date: 2017/12/20 19:58
     */
    public static ArrayList<Online_Test> QuestionToList(List<Question> questionList, String... result) {
        ArrayList<Online_Test> online_tests = new ArrayList<Online_Test>();
        ArrayList<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> noanswer = new ArrayList<Integer>();
        noanswer.add(-1);
        if (result.length != 0) {
            answer = MyJson.JsonToList(result[0]);
        }
        for (int i = 0; i < questionList.size(); i++) {
            Question_Json question_json = MyJson.JsonToQuestion(questionList.get(i).getAnswer());
            Online_Test online_test = new Online_Test();
            online_test.setQuestion(questionList.get(i));
            online_test.setChoice(question_json.getChoice());
            if (result.length != 0) {
                online_test.setAnswer(answer.get(i));
            } else {
                online_test.setAnswer(noanswer);
            }
            online_tests.add(online_test);
        }
        return online_tests;
    }

    /**
     * @param questionList
     * @param answer
     * @Author: Sumail-Lee
     * @Description: 将题库类列表转换成Online_Test以便传送给前端使用，将Question里的json格式选项数据整理成ArrayList格式，将学生作答答案（Answer）处理成ArrayList，将标准答案（Correct）处理成ArrayList
     * @Date: 2017/12/20 20:00
     */
    public static ArrayList<Online_Test> CheckPaper(List<Question> questionList, String answer) {
        ArrayList<Online_Test> online_tests = new ArrayList<Online_Test>();
        ArrayList<ArrayList<Integer>> answerList = MyJson.JsonToList(answer);
        for (int i = 0; i < questionList.size(); i++) {
            Question_Json question_json = MyJson.JsonToQuestion(questionList.get(i).getAnswer());
            Online_Test online_test = new Online_Test();
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
