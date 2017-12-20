package heu.iot.Util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

    public static ArrayList<ArrayList<Integer>> JsonToList(String json){
        return gson.fromJson(json,new TypeToken<ArrayList<ArrayList<Integer>>>(){}.getType());
    }

    public static String ExamToJson(ArrayList<String> info, String level){
        Exam_Json exam_json=new Exam_Json();
        exam_json.setInfo(info);
        exam_json.setLevel(level);
        return gson.toJson(exam_json);
    }

    //将Json处理成Exam_Json数据
    public static Exam_Json JsonToExam(String json){
        return gson.fromJson(json,Exam_Json.class);
    }

    //将Question_Json转换为Json数据
    public static String QuestionToJson(ArrayList<String> choice, ArrayList<Integer> answer){
        Question_Json question_json=new Question_Json();
        question_json.setChoice(choice);
        question_json.setAnswer(answer);
        return gson.toJson(question_json);
    }

    //将Json数据处理成Question_Json类
    public static Question_Json JsonToQuestion(String json){
        return gson.fromJson(json,Question_Json.class);
    }



}
