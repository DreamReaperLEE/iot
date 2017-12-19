package heu.iot.Controller.Teacher;

import heu.iot.Model.Score_Emploee;
import heu.iot.Service.ScoreService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by wangyueyan on 2017/12/5.
 */
@Controller
@RequestMapping("/teacher")
public class AnalysisController {

    @Autowired
    private ScoreService scoreService;

    // 成绩查询模块
    @RequestMapping("/analysis")
    public String analysis(Model model) {
        int id = 1;
        // 试卷id

        List<Score_Emploee> score_emploeeList = scoreService.showAllExamById();
        if (score_emploeeList == null) {
            return "teacher/Analysis";
        } else {
            model.addAttribute("score_emploeeList", score_emploeeList);
            return "teacher/Analysis";
        }

    }

    @RequestMapping("excel")
    public String excle(HttpServletResponse response) throws Exception {

        HSSFWorkbook wb = new HSSFWorkbook();
        //声明一个单子并命名
        HSSFSheet sheet = wb.createSheet("成绩单");
        //给单子名称一个长度
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        //创建第一行（也可以称为表头）
        HSSFRow row = sheet.createRow(0);
        //样式字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //给表头第一行一次创建单元格
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("学生编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("学生学号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("学生姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("成绩");
        cell.setCellStyle(style);

        List<Score_Emploee> score_emploeeList = scoreService.showAllExamById();
        //向单元格里填充数据
        for (short i = 0; i < score_emploeeList.size(); i++) {
            row = sheet.createRow(i + 1);
            Score_Emploee man = score_emploeeList.get(i);
            row.createCell(0).setCellValue(man.getId());
            row.createCell(1).setCellValue(man.getSid());
            row.createCell(2).setCellValue(man.getName());
            row.createCell(3).setCellValue(man.getScore());
        }


//输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=score.xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
        return "";
    }
}


