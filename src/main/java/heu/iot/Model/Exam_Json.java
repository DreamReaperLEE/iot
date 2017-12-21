package heu.iot.Model;

import java.util.ArrayList;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2017/12/18 16:59
 */
public class Exam_Json {
    private ArrayList<String> info;
    private String level;

    public Exam_Json() {
    }

    public ArrayList<String> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<String> info) {
        this.info = info;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
