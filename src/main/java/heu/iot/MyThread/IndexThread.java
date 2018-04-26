package heu.iot.MyThread;

import heu.iot.ComponentTools.TestUtils;
import heu.iot.Model.Course;
import heu.iot.Model.Emploee;
import heu.iot.Model.Homepage;
import heu.iot.Model.Inform_Emploee;
import heu.iot.Util.MyJson;
import org.aspectj.weaver.ast.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/4/25 14:14
 */
public class IndexThread implements Runnable {
    public static String pic="";
    public static List<Emploee> emploeeList=new ArrayList<Emploee>();
    public static List<Course> courseList=new ArrayList<Course>();
    public static List<Integer> top4=new ArrayList<Integer>();
    public static List<Inform_Emploee> inform_emploees=new ArrayList<Inform_Emploee>();

    public void run(){
        while(true){
            updatePic();
            updateEmploee();
            updateCourse();
            updateTop4();
            updateInformEmploee();
            try {
                Thread.sleep(60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updatePic(){
        List<Homepage> homepages=TestUtils.selectByTypeHomePage(2);
        pic=homepages.get(0).getOther();
    }

    public static void updateTop4(){
        top4=TestUtils.indexTop4();
    }

    public static void updateCourse(){
        //获取所有课程列表JSON格式数据
        List<Homepage> homepages=TestUtils.selectByTypeHomePage(0);
        //将Json格式转换为Course格式列表
        String list=homepages.get(0).getOther();
        List<String> stringlist= MyJson.JsonToStringList(list);
        ArrayList<Integer> intlist=new ArrayList<>();
        for(String each:stringlist)
            intlist.add(Integer.valueOf(each));
        courseList=TestUtils.showSelectedCourse(intlist);
    }

    public static void updateEmploee(){
        List<Homepage> homepages=TestUtils.selectByTypeHomePage(1);
        //将Json格式转换为Emploee格式列表
        String list=homepages.get(0).getOther();
        List<String> stringlist= MyJson.JsonToStringList(list);
        ArrayList<Integer> intlist=new ArrayList<>();
        for(String each:stringlist)
            intlist.add(Integer.valueOf(each));
        emploeeList=TestUtils.showSelectedEmploee(intlist);
    }

    public static void updateInformEmploee(){
        inform_emploees=TestUtils.showRecentInformEmploee(3);
    }

}
