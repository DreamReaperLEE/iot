package heu.iot.Model;

import java.util.ArrayList;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2017/12/19 17:45
 */
public class Question_Json {
    private ArrayList<String> choice;
    private ArrayList<String> answer;

    public Question_Json() {
    }

    public ArrayList<String> getChoice() {
        return choice;
    }

    public void setChoice(ArrayList<String> choice) {
        this.choice = choice;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }
}
