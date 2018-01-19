package heu.iot.Util;


import heu.iot.Model.Emploee;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2017/12/21 9:22
 */
public class Excel {

    /**
     * @Author: Sumail-Lee
     * @Description: 生成Excel表并返回
     * @param excelName 表格名称
     * @param title 标题
     * @param data 数据
     * @param response
     * @Date: 2017/12/21 9:54
     */
    public static String createExcel(String excelName, ArrayList<String> title, ArrayList<ArrayList<String>> data,HttpServletResponse response) throws IOException {

        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet(excelName);
        HSSFRow row=sheet.createRow(0);
        for(int i=0;i<title.size();i++){
            row.createCell(i).setCellValue(title.get(i));
        }
        for(int i=0;i<data.size();i++){
            HSSFRow every=sheet.createRow(i+1);
            for(int j=0;j<data.get(i).size();j++){
                every.createCell(j).setCellValue(data.get(i).get(j));
            }
        }
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename="+excelName+".xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
        return "";
    }

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

                    emploee.setId(Integer.valueOf(dealCell(id)));
                    emploee.setName(dealCell(name));
                    emploee.setPassword(dealCell(pwd));
                    if(dealCell(priv).startsWith("学生"))
                        emploee.setPriv(2);
                    else
                        emploee.setPriv(1);
                    if(eml!=null){
                        emploee.setEmail(dealCell(eml));}
                    if(tel!=null){
                        emploee.setTel(dealCell(tel));}
                    emploee.setActive(1);
                    emploeeList.add(emploee);
                    }
                }catch (Exception e){

                }

            }

        return emploeeList;
    }

    public static String dealCell(Cell cell){
        String cellValue;
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                DecimalFormat df = new DecimalFormat("0");
                cellValue = df.format(cell.getNumericCellValue());
                break;

            case HSSFCell.CELL_TYPE_STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;

            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                cellValue = cell.getBooleanCellValue() + "";
                break;

            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                cellValue = cell.getCellFormula() + "";
                break;

            case HSSFCell.CELL_TYPE_BLANK: // 空值
                cellValue = "";
                break;

            case HSSFCell.CELL_TYPE_ERROR: // 故障
                cellValue = "非法字符";
                break;

            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}
