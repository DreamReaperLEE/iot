package heu.iot.Util;

import com.google.gson.Gson;
import heu.iot.Model.Exam_Json;
import heu.iot.Model.Question_Json;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @Author: Sumail-Lee
 * @Date: 10:01 2017/11/27
 */
public class MyJson {

    private static Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static String ExamToJson(ArrayList<String> info, String level){
        Exam_Json exam_json=new Exam_Json();
        exam_json.setInfo(info);
        exam_json.setLevel(level);
        return gson.toJson(exam_json);
    }

    public static Exam_Json JsonToExam(String json){
        return gson.fromJson(json,Exam_Json.class);
    }

    public static String QuestionToJson(ArrayList<String> choice, ArrayList<String> answer){
        Question_Json question_json=new Question_Json();
        question_json.setChoice(choice);
        question_json.setAnswer(answer);
        return gson.toJson(question_json);
    }

    public static Question_Json JsonToQuestion(String json){
        return gson.fromJson(json,Question_Json.class);
    }


}
