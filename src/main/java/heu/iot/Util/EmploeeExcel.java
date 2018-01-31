package heu.iot.Util;

import heu.iot.Model.Emploee;
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
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/1/23 16:10
 */
public class EmploeeExcel {

    /**
     * @Author: Sumail-Lee
     * @Description:标题
     * @param
     * @Date: 2018/1/23 16:21
     */
    public static ArrayList<String> getTitle(){

        ArrayList<String> title=new ArrayList<String>();
        title.add("工号/学号");
        title.add("姓名");
        title.add("人员类型");
        title.add("邮箱");
        title.add("手机号");
        title.add("个人简介");
        title.add("激活状态");
        return title;
    }

    /**
     * @Author: Sumail-Lee
     * @Description:具体内容
     * @param emploeeList 人员列表
     * @Date: 2018/1/23 16:21
     */
    public static ArrayList<ArrayList<String>> getData(List<Emploee> emploeeList){
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        for(Emploee each:emploeeList){
            ArrayList<String> every=new ArrayList<String>();

            every.add(String.valueOf(each.getId()));
            every.add(each.getName());

            if(each.getPriv()==0)
                every.add("管理员");
            else if(each.getPriv()==1)
                every.add("教师");
            else
                every.add("学员");
            every.add(each.getEmail());
            every.add(each.getTel());
            every.add(each.getIntroduce());
            if(each.getActive()==0)
                every.add("未审核");
            else if(each.getActive()==1)
                every.add("正常");
            else
                every.add("审核未通过");
            data.add(every);
        }
        return  data;
    }

    /**
     * @Author: Sumail-Lee
     * @Description: 批量添加人员
     * @param dest 上传的文件
     * @Date: 2018/1/23 15:35
     */
    public static List<Emploee> addEmploee(File dest) throws IOException {

        //添加的人员列表
        ArrayList<Emploee> emploeeList=new ArrayList<>();
        Emploee emploee;
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
                    emploee = new Emploee();
                    Cell id = hssfRow.getCell(0);
                    Cell name = hssfRow.getCell(1);
                    Cell pwd = hssfRow.getCell(2);
                    Cell eml = hssfRow.getCell(3);
                    Cell tel = hssfRow.getCell(4);
                    Cell priv=hssfRow.getCell(5);

                    emploee.setId(Integer.valueOf(Excel.dealCell(id)));
                    emploee.setName(Excel.dealCell(name));
                    emploee.setPassword(Excel.dealCell(pwd));
                    if(Excel.dealCell(priv).startsWith("学生"))
                        emploee.setPriv(2);
                    else
                        emploee.setPriv(1);
                    if(eml!=null){
                        emploee.setEmail(Excel.dealCell(eml));}
                    if(tel!=null){
                        emploee.setTel(Excel.dealCell(tel));}
                    emploee.setActive(1);
                    emploeeList.add(emploee);
                }
            }catch (Exception e){

            }

        }

        return emploeeList;
    }
}
