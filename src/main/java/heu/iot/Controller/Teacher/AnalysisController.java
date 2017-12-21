package heu.iot.Controller.Teacher;

import heu.iot.Model.Score_Emploee;
import heu.iot.Model.Serie;
import heu.iot.Service.ScoreService;
import heu.iot.Util.ChartUtils;
import org.apache.poi.hssf.usermodel.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Vector;

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

    // 将考试成绩的excel表格导出
    @RequestMapping("excel")
    public String excel(HttpServletResponse response) throws Exception {

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
        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=score.xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
        return "teacher/Analysis";
    }

    ChartPanel frame1;

    @RequestMapping("/generate_graph")
    public String BarChart(Model model) throws IOException {
        CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D(
                "成绩", // 图表标题
                "分数", // 目录轴的显示标签
                "分数", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true,      // 是否显示图例(对于简单的柱状图必须是false)
                false,     // 是否生成工具
                false      // 是否生成URL链接
        );
        //从这里开始
        CategoryPlot plot = chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis();     //水平底部列表
        domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14));     //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); //垂直标题
        ValueAxis rangeAxis = plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));//设置标题字体
        //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题
        frame1 = new ChartPanel(chart, true);    //这里也可以用chartFrame,可以直接生成一个独立的Frame
        OutputStream os = new FileOutputStream("company.jpeg");//图片是文件格式的，故要用到FileOutputStream用来输出。
        ChartUtilities.writeChartAsJPEG(os, chart, 1000, 800);
        //使用一个面向application的工具类，将chart转换成JPEG格式的图片。第3个参数是宽度，第4个参数是高度。
        os.close();//关闭输出流
        return "/teacher/GenarateGraph";
    }

    private static CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(3, "北京", "0-10");
        dataset.addValue(5, "北京", "11-20");
        dataset.addValue(6, "北京", "21-30");
        dataset.addValue(1, "北京", "31-40");
        dataset.addValue(6, "北京", "41-50");
        dataset.addValue(8, "北京", "51-60");
        dataset.addValue(6, "北京", "61-70");
        dataset.addValue(11, "北京", "71-80");
        dataset.addValue(5, "北京", "81-90");
        dataset.addValue(7, "北京", "91-100");
        return dataset;
    }

    public ChartPanel getChartPanel() {
        return frame1;
    }


}


