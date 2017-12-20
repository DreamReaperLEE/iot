package heu.iot.Model;

import java.util.ArrayList;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2017/12/19 19:54
 */
public class Online_Test {
    private Question question;
    private ArrayList<String> choice;
    private ArrayList<Integer> correct;
    private ArrayList<Integer> answer;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ArrayList<String> getChoice() {
        return choice;
    }

    public void setChoice(ArrayList<String> choice) {
        this.choice = choice;
    }

    public ArrayList<Integer> getCorrect() {
        return correct;
    }

    public void setCorrect(ArrayList<Integer> correct) {
        this.correct = correct;
    }

    public ArrayList<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<Integer> answer) {
        this.answer = answer;
    }
}
