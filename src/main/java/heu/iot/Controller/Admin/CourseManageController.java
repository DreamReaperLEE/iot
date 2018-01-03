package heu.iot.Controller.Admin;

import heu.iot.Model.Course;
import heu.iot.Model.Emploee;
import heu.iot.Model.Emploee_Course;
import heu.iot.Service.CourseService;
import heu.iot.Service.EmploeeService;
import heu.iot.Util.DateDealwith;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CourseManageController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private EmploeeService employeeService;
    @Autowired
    private CourseService courseService;

    @RequestMapping("/allcourse")
    public String showemploee_course(Model model){
        List<Emploee_Course> list = courseService.showAllEmploeeCourse();
        model.addAttribute("course",list);
        return "admin/allcourse";
    }
    @RequestMapping("/course/coursedetail")
    public String showcoursedetail(@RequestParam(value = "id") Integer id,Model model){
        Course coursel =courseService.selectByPrimaryKey(id);
        Emploee emploee = employeeService.selectByPrimaryKey(coursel.getTid());
        model.addAttribute("emploee",emploee);
        model.addAttribute("course",coursel);
//        try{
//        String str = coursel.getDate();
//        Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
//            System.out.println(d);
//            model.addAttribute("cdate",d);}
//        catch (ParseException e) {
//// TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        String cdate = coursel.getDate();
        System.out.println(cdate);
        model.addAttribute("cdate",cdate);
        List<Emploee> emploeeList = employeeService.selectByEmploeePriv(1);
        model.addAttribute("emploeeList",emploeeList);
        return "admin/courseDetail";

    }
    @PostMapping("/course/updatecourse")
    public String updatecourse(Course course, @RequestParam("fileupload") MultipartFile file, @RequestParam(value = "id") Integer id, Model model) {
        if (!file.isEmpty()) {
            if (courseService.selectByPrimaryKey(id).getCpic()!=null){
                Course course1= courseService.selectByPrimaryKey(id);
                File oranfile = new File(course1.getCpic());
                oranfile.delete();}
            String fileName = file.getOriginalFilename();
            logger.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            logger.info("上传的后缀名为：" + suffixName);
            // 文件上传后的文件夹
            String filePath = "D://file//";

            String filefull = filePath + DateDealwith.getSHC()+ fileName;

            course.setCpic(filefull);



            File dest = new File(filePath +DateDealwith.getSHC()+ fileName);

            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            try {
                file.transferTo(dest);



            }


            catch (IllegalStateException e) {

                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("********");
        System.out.println(course.getDate());
        course.setDate(String.valueOf(course.getDate()));
        System.out.println(course.getDate());
        courseService.updateCourses(course);

        return "redirect:/admin/allcourse";
    }
    @RequestMapping("/addcourse")
    public String goadd(Model model) {
        List<Emploee> list = employeeService.selectByEmploeePriv(1);
        model.addAttribute("emploeeList",list);
        return "/admin/addcourse";
    }
    @PostMapping("/course/addcour")
    public String addcourse(Course course, @RequestParam("fileupload") MultipartFile file, Model model) {
        if (courseService.selectByID( course.getId())!=null){model.addAttribute("a","该课程号已经存在");}
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            logger.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            logger.info("上传的后缀名为：" + suffixName);
            // 文件上传后的文件夹
            String filePath = "D://file//";

            String filefull = filePath + DateDealwith.getSHC()+ fileName;

            course.setCpic(filefull);



            File dest = new File(filePath +DateDealwith.getSHC()+ fileName);

            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            try {
                file.transferTo(dest);



            }


            catch (IllegalStateException e) {

                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        course.setDate(String.valueOf(course.getDate()));
        courseService.insertCourse(course);

        return "redirect:/admin/allcourse";
    }
    @PostMapping("/course/delete")
    public String deleteEmploee(@RequestParam(value = "id") Integer id) {
        Course course = courseService.selectByPrimaryKey(id);
        if(course.getCpic()!=null){
            File file = new File(course.getCpic());
            if (file.exists() && file.isFile()) {
//
                file.delete();}

            courseService.deleteCourse(id);}

        else {
            courseService.deleteCourse(id);}
        return "redirect:/admin/allcourse";
    }
    @RequestMapping(value = "/selectcourse")
    public String selectCourse(@RequestParam(value = "classid") int classid, @RequestParam(value = "keyboard") String cname, Model model) {
        switch (classid) {
            case 0:
                Emploee_Course course = courseService.selectByID(Integer.valueOf(cname));
                model.addAttribute("course", course);
                break;
            case 1:
                List<Emploee_Course> courseList = courseService.selectByName(cname);
                model.addAttribute("course", courseList);
        }
        return "admin/allcourse";
    }
    @RequestMapping(value = "/course/excel")
    public String sexcel(HttpServletResponse response) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        //声明一个单子并命名
        HSSFSheet sheet = wb.createSheet("课程信息");
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
        cell = row.createCell((short) 0);
        cell.setCellValue("课程编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("课程名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("课程简介");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("任课教师");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("发布时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("课程类型");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("课程方向");
        cell.setCellStyle(style);

        List<Emploee_Course> list = courseService.showAllEmploeeCourse();
        //向单元格里填充数据
        for (short i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            Emploee_Course man = list.get(i);
            String str = null;


            row.createCell(0).setCellValue(man.getId());
            row.createCell(1).setCellValue(man.getCname());
            row.createCell(2).setCellValue(man.getCdetail());
            row.createCell(3).setCellValue(man.getTname());
            row.createCell(4).setCellValue(man.getDate());
            row.createCell(5).setCellValue(man.getCtype());
            row.createCell(6).setCellValue(man.getCdirect());
        }
        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=课程.xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
        return "admin/allcourse";
    }
    @PostMapping(value = "/course/uploadExcel")
    public String addccourse(Model model, @RequestParam("file") MultipartFile file) throws Exception

    {

        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的文件夹
        String filePath = "D://file//";
//        文件的绝对路径
        String filefull = filePath + fileName;
//        将绝对路径添加到表单中

        File dest = new File(filePath + fileName);
        // 检测是否存在目录，如果不存在路径，创建一个文件夹
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        //这个才是上传文件
        try {
            file.transferTo(dest);

        }
//        当try出现问题时执行catch语句，并初始化IllegalStateException函数
        catch (IllegalStateException e) {
            //打印问题
            e.printStackTrace();
//            抛出的是io异常
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream is = new FileInputStream(dest);
        Workbook hssfWorkbook = null;
        if (dest.getName().endsWith("xlsx")) {
            hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
        } else if (dest.getName().endsWith("xls")) {
            hssfWorkbook = new HSSFWorkbook(is);//Excel 2003

        }
        // HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        // XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
        Course course = null;
        List<Course> list = new ArrayList<Course>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            //HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                Row hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    course = new Course();
                    //HSSFCell name = hssfRow.getCell(0);
                    //HSSFCell pwd = hssfRow.getCell(1);
                    Cell id = hssfRow.getCell(0);
                    Cell cname = hssfRow.getCell(1);
                    Cell cdetail = hssfRow.getCell(2);
                    Cell tid = hssfRow.getCell(3);
                    Cell date = hssfRow.getCell(4);
                    Cell ctype = hssfRow.getCell(5);
                    Cell cdirect = hssfRow.getCell(6);


//这里是自己的逻辑
//                    int a = Integer.parseInt();
                    System.out.println("********1**********");
                    String strNum = id.toString();
                    if(strNum.indexOf(".")!=-1){
                    strNum = strNum.substring(0, strNum.indexOf("."));}
                    Integer num = new Integer(strNum);
                    System.out.println(num);

                    String strTid = tid.toString();
                    System.out.println("*"+strTid);
                    if(strTid.indexOf(".")!=-1){
                    strTid = strTid.substring(0,strTid.indexOf("."));}
                    Integer sTid = new Integer(strTid);

                    course.setId(num);
                    course.setCname(cname.toString());
                    if(cdetail!=null){
                    course.setCdetail(cdetail.toString());}
                    course.setTid(sTid);
                    if(date!=null){
                    course.setDate(date.toString());}
                    if(ctype!=null){
                    course.setCtype(ctype.toString());}
                    if(cdirect!=null){
                    course.setCdirect(cdirect.toString());}

                    courseService.insertCourse(course);
                }
            }
        }

        return "admin/allcourse";
    }





}

