package heu.iot.Util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

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
     * @param sheetname 表格名称
     * @param title
     * @param data
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
}
