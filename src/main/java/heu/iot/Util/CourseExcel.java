package heu.iot.Util;

import heu.iot.Model.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:处理课程导出数据
 * @Since: 2018/1/23 16:29
 */
public class CourseExcel {
    /**
     * @Author: Sumail-Lee
     * @Description:标题
     * @param
     * @Date: 2018/1/23 16:21
     */
    public static ArrayList<String> getTitle(){

        ArrayList<String> title=new ArrayList<String>();
        title.add("课程编号");
        title.add("课程名称");
        title.add("课程简介");
        title.add("任课教师");
        title.add("课程详细介绍");
        title.add("发布日期");
        title.add("课程类型");
        title.add("课程方向");
        title.add("课程要点");
        return title;
    }

    /**
     * @Author: Sumail-Lee
     * @Description:具体内容
     * @param emploee_courseList 人员列表
     * @Date: 2018/1/23 16:21
     */
    public static ArrayList<ArrayList<String>> getData(List<Emploee_Course> emploee_courseList,List<Co_direct> co_directList,List<Co_type> co_typeList){
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        HashMap<String,String> Co_typeHash=new HashMap<String,String>();
        HashMap<String,String> Co_directHash=new HashMap<String,String>();

        for(Co_type each:co_typeList){
            Co_typeHash.put(String.valueOf(each.getId()),each.getTypeName());
        }
        for(Co_direct each:co_directList){
            Co_directHash.put(String.valueOf(each.getId()),each.getDirectName());
        }


        for(Emploee_Course each:emploee_courseList){
            ArrayList<String> every=new ArrayList<String>();

            every.add(String.valueOf(each.getId()));
            every.add(each.getCname());
            every.add(each.getCdetail());
            every.add(each.getTname());
            every.add(each.getCintroduce());
            every.add(each.getDate());
            every.add(Co_typeHash.get(each.getCtype()));
            every.add(Co_directHash.get(each.getCdirect()));
            every.add(each.getCpoint());

            data.add(every);
        }
        return  data;
    }

    /**
     * @Author: Sumail-Lee
     * @Description: 批量添加课程
     * @param dest 添加的课程的文件
     * @Date: 2018/1/23 15:42
     */
    public static List<Course> addCourse(File dest) throws IOException {

        //添加的课程列表
        ArrayList<Course> courseList=new ArrayList<>();
        Course course;
        //打开文件
        InputStream is = new FileInputStream(dest);
        Workbook hssfWorkbook = null;
        if (dest.getName().endsWith("xlsx")) {
            hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
        } else if (dest.getName().endsWith("xls")) {
            hssfWorkbook = new HSSFWorkbook(is);//Excel 2003
        }
        Sheet hssfSheet = hssfWorkbook.getSheetAt(0);
        // 循环行Row
        for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            try {
                Row hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    course = new Course();
                    Cell name = hssfRow.getCell(0);
                    Cell tid = hssfRow.getCell(1);
                    Cell detail = hssfRow.getCell(2);
                    if(name!=null)
                        course.setCname(Excel.dealCell(name));
                    if(tid!=null)
                        course.setTid(Integer.valueOf(Excel.dealCell(tid)));
                    if(detail!=null)
                        course.setCdetail(Excel.dealCell(detail));
                    course.setDate(TimeFactory.getCurrentDate());
                    courseList.add(course);
                }
            }catch (Exception e){

            }

        }

        return courseList;
    }
}
