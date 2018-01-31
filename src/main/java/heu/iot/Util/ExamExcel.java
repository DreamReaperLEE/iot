package heu.iot.Util;

import heu.iot.Model.Emploee;
import heu.iot.Model.Exam;
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

import static heu.iot.Util.Excel.dealCell;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/1/24 10:20
 */
public class ExamExcel {

    /**
     * @Author: Sumail-Lee
     * @Description:标题
     * @param
     * @Date: 2018/1/23 16:21
     */
    public static ArrayList<String> getTitle(){

        ArrayList<String> title=new ArrayList<String>();
        title.add("考试编号");
        title.add("考试名称");
        title.add("主考姓名");
        title.add("考试描述");
        title.add("考试日期");
        title.add("开考时间");
        title.add("结束时间");
        return title;
    }

    /**
     * @Author: Sumail-Lee
     * @Description:具体内容
     * @param emploeeList 人员列表
     * @param examList 考试列表
     * @Date: 2018/1/23 16:21
     */
    public static ArrayList<ArrayList<String>> getData(List<Emploee> emploeeList,List<Exam> examList){
        HashMap<String,String> emploeeHashMap=new HashMap<String,String>();
        for(Emploee emploee:emploeeList){
            emploeeHashMap.put(String.valueOf(emploee.getId()),emploee.getName());
        }

        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        for(Exam each:examList){
            ArrayList<String> every=new ArrayList<String>();

            every.add(String.valueOf(each.getId()));
            every.add(each.getEname());
            every.add(emploeeHashMap.get(each.getTid()));
            every.add(each.getEdesc());
            every.add(each.getDate());
            every.add(each.getStime());
            every.add(each.getEtime());

            data.add(every);
        }
        return  data;
    }

    /**
     * @Author: Sumail-Lee
     * @Description:批量导入考试
     * @param dest
     * @Date: 2018/1/24 10:31
     */
    public static List<Exam> addExam(File dest) throws IOException {

        //添加的考试列表
        ArrayList<Exam> examList=new ArrayList<>();
        Exam exam;
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
                    exam = new Exam();
                    Cell ename = hssfRow.getCell(0);
                    Cell tid = hssfRow.getCell(1);
                    Cell edesc = hssfRow.getCell(2);
                    Cell date = hssfRow.getCell(3);
                    Cell stime = hssfRow.getCell(4);
                    Cell etime=hssfRow.getCell(5);

                    exam.setEname(dealCell(ename));
                    exam.setTid(dealCell(tid));
                    exam.setEdesc(dealCell(edesc));
                    exam.setDate(dealCell(date));
                    exam.setStime(dealCell(stime));
                    exam.setEtime(dealCell(etime));
                    examList.add(exam);
                }
            }catch (Exception e){

            }

        }

        return examList;
    }
}
