package heu.iot.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description: 知识体系具体内容Json用
 * @Since: 2018/1/8 17:04
 */
public class HierarchyDetail {
    private String topic;
    private String introduce;
    private ArrayList<Integer> intList;
    private List<Course> courseList;

    public HierarchyDetail() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public ArrayList<Integer> getIntList() {
        return intList;
    }

    public void setIntList(ArrayList<Integer> intList) {
        this.intList = intList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
