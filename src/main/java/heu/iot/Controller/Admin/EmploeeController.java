package heu.iot.Controller.Admin;

import heu.iot.Model.Emploee;
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
public class EmploeeController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private EmploeeService employeeService;

    @RequestMapping("/adminstor")
    public String showAdminstor(Model model) {
        List<Emploee> adminList = employeeService.selectByEmploeePriv(0);
        model.addAttribute("adminList", adminList);
        return "admin/allEmploee";
    }

    @RequestMapping("/teacher")
    public String showTeacher(Model model) {
        List<Emploee> adminList = employeeService.selectByEmploeePriv(1);
        model.addAttribute("adminList", adminList);
        return "admin/allTeacher";
    }

    @RequestMapping("/student")
    public String showStudent(Model model) {
        List<Emploee> adminList = employeeService.selectByEmploeePriv(2);
        model.addAttribute("adminList", adminList);
        return "admin/allStudent";
    }

    @RequestMapping("/emploee/detail")
    public String showDetail(@RequestParam(value = "id") Integer id, Model model) {
        Emploee emploee = employeeService.selectByPrimaryKey(id);
        model.addAttribute("emploee", emploee);
        return "admin/emploeeDetail";

    }

    @PostMapping("/emploee/update")
    public String updateEmploee(Emploee emploee, @RequestParam("fileupload") MultipartFile file, @RequestParam(value = "id") Integer id, Model model) {

        if (!file.isEmpty()) {
            if (employeeService.selectByPrimaryKey(id).getPic()!=null){
                Emploee deemploee = employeeService.selectByPrimaryKey(id);
                File oranfile = new File(deemploee.getPic());
                oranfile.delete();}


        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的文件夹
        String filePath = "D://file//";

        String filefull = filePath + DateDealwith.getSHC()+ fileName;

        emploee.setPic(filefull);



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

        employeeService.updateByPrimaryKey(emploee);
        return "redirect:/admin/adminstor";
    }


    @RequestMapping("/emploee/delete")
    public String deleteEmploee(@RequestParam(value = "id") Integer id) {
        Emploee emploee = employeeService.selectByPrimaryKey(id);
        if(emploee.getPic()!=null){
        File file = new File(emploee.getPic());
        if (file.exists() && file.isFile()) {
//            if (file.delete()) {
//                System.out.println("删除单个文件" + emploee.getPic() + "成功！");
//
//            } else {
//                System.out.println("删除单个文件" + emploee.getPic() + "失败！");
//
//            }
            file.delete();}
            employeeService.deleteEmploee(id);}

        else {
        employeeService.deleteEmploee(id);}
        return "redirect:/admin/adminstor";
    }

    @RequestMapping(value = "/selectemploee")
    public String selectEmploee(@RequestParam(value = "classid") int classid, @RequestParam(value = "keyboard") String name, Model model) {

        switch (classid) {
            case 0:
                Emploee list = employeeService.selectByPrimaryKey(Integer.valueOf(name));
                model.addAttribute("adminList", list);
                break;
            case 1:
                List<Emploee> adminList = employeeService.selectEmploeeByName(name);
                model.addAttribute("adminList", adminList);
        }
        return "admin/allEmploee";
    }

    @RequestMapping(value = "/teacher/excel")
    public String sexcel(HttpServletResponse response) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        //声明一个单子并命名
        HSSFSheet sheet = wb.createSheet("教师信息");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("密码");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("权限");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("邮箱");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("手机号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("个人简介");
        cell.setCellStyle(style);

        List<Emploee> emploeeList = employeeService.selectByEmploeePriv(0);
        //向单元格里填充数据
        for (short i = 0; i < emploeeList.size(); i++) {
            row = sheet.createRow(i + 1);
            Emploee man = emploeeList.get(i);
            String str = null;
            switch (man.getPriv()) {
                case 0:
                    str = "管理员";
                    break;
                case 1:
                    str = "教师";
                    break;
                case 2:
                    str = "学生";
                    break;
            }
            row.createCell(0).setCellValue(man.getId());
            row.createCell(1).setCellValue(man.getName());
            row.createCell(2).setCellValue(man.getPassword());
            row.createCell(3).setCellValue(str);
            row.createCell(4).setCellValue(man.getEmail());
            row.createCell(5).setCellValue(man.getTel());
            row.createCell(6).setCellValue(man.getIntroduce());
        }
        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=student.xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
        return "admin/allTeacher";
    }

    @RequestMapping(value = "/adminstor/excel")
    public String aexcel(HttpServletResponse response) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        //声明一个单子并命名
        HSSFSheet sheet = wb.createSheet("管理员信息");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("密码");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("权限");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("邮箱");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("手机号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("个人简介");
        cell.setCellStyle(style);

        List<Emploee> emploeeList = employeeService.selectByEmploeePriv(0);
        //向单元格里填充数据
        for (short i = 0; i < emploeeList.size(); i++) {
            row = sheet.createRow(i + 1);
            Emploee man = emploeeList.get(i);
            String str = null;
            switch (man.getPriv()) {
                case 0:
                    str = "管理员";
                    break;
                case 1:
                    str = "教师";
                    break;
                case 2:
                    str = "学生";
                    break;
            }
            row.createCell(0).setCellValue(man.getId());
            row.createCell(1).setCellValue(man.getName());
            row.createCell(2).setCellValue(man.getPassword());
            row.createCell(3).setCellValue(str);
            row.createCell(4).setCellValue(man.getEmail());
            row.createCell(5).setCellValue(man.getTel());
            row.createCell(6).setCellValue(man.getIntroduce());
        }
        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=student.xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
        return "admin/allAdminstor";
    }

    @RequestMapping(value = "/student/excel")
    public String excel(HttpServletResponse response) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        //声明一个单子并命名
        HSSFSheet sheet = wb.createSheet("学生信息");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("密码");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("权限");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("邮箱");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("手机号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("个人简介");
        cell.setCellStyle(style);

        List<Emploee> emploeeList = employeeService.selectByEmploeePriv(0);
        //向单元格里填充数据
        for (short i = 0; i < emploeeList.size(); i++) {
            row = sheet.createRow(i + 1);
            Emploee man = emploeeList.get(i);
            String str = null;
            switch (man.getPriv()) {
                case 0:
                    str = "管理员";
                    break;
                case 1:
                    str = "教师";
                    break;
                case 2:
                    str = "学生";
                    break;
            }
            row.createCell(0).setCellValue(man.getId());
            row.createCell(1).setCellValue(man.getName());
            row.createCell(2).setCellValue(man.getPassword());
            row.createCell(3).setCellValue(str);
            row.createCell(4).setCellValue(man.getEmail());
            row.createCell(5).setCellValue(man.getTel());
            row.createCell(6).setCellValue(man.getIntroduce());
        }
        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=student.xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
        return "admin/allStudent";
    }

    @PostMapping("/admin/adminstor/uploadExcel")
    public String addSource(Model model, @RequestParam("file") MultipartFile file) throws Exception

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
        Emploee emploee = null;
        List<Emploee> list = new ArrayList<Emploee>();
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
                    emploee = new Emploee();
                    //HSSFCell name = hssfRow.getCell(0);
                    //HSSFCell pwd = hssfRow.getCell(1);

                    Cell id = hssfRow.getCell(0);
                    Cell name = hssfRow.getCell(1);
                    Cell pwd = hssfRow.getCell(2);
                    Cell eml = hssfRow.getCell(3);
                    Cell tel = hssfRow.getCell(4);


//这里是自己的逻辑
//                    int a = Integer.parseInt();
                    System.out.println("********1**********");
                    String strNum = id.toString();
                    System.out.println("*"+strNum);
                    if(strNum.indexOf(".")!=-1){
                    strNum = strNum.substring(0, strNum.indexOf("."));}
                    System.out.println("**"+strNum);
                    Integer num = new Integer(strNum);
                    System.out.println("***"+num);




                    emploee.setId(num);
                    System.out.println("*******");
                    emploee.setName(name.toString());
                    System.out.println("*******1");
                    emploee.setPassword(pwd.toString());
                    System.out.println("*******2");
                    emploee.setPriv(0);
                    System.out.println("*******3");
                    if(eml!=null){
                    emploee.setEmail(eml.toString());}
                    System.out.println("******4");
                    if(tel!=null){
                    emploee.setTel(tel.toString());}
                    System.out.println("****5");
                    emploee.setActive(1);
                    System.out.println("*******6");
                    employeeService.insertEmploee(emploee);
                }
            }
        }

        return "redirect:/admin/adminstor";
    }

    @PostMapping("/admin/teacher/uploadExcel")
    public String addteacher(Model model, @RequestParam("file") MultipartFile file) throws Exception

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
        Emploee emploee = null;
        List<Emploee> list = new ArrayList<Emploee>();
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
                    emploee = new Emploee();
                    //HSSFCell name = hssfRow.getCell(0);
                    //HSSFCell pwd = hssfRow.getCell(1);
                    Cell id = hssfRow.getCell(0);
                    Cell name = hssfRow.getCell(1);
                    Cell pwd = hssfRow.getCell(2);
                    Cell eml = hssfRow.getCell(3);
                    Cell tel = hssfRow.getCell(4);


//这里是自己的逻辑
//                    int a = Integer.parseInt();
                    System.out.println("********1**********");
                    System.out.println("*");
                    String strNum = id.toString();

                    if(strNum.indexOf(".")!=-1){
                    strNum = strNum.substring(0, strNum.indexOf("."));}


                    Integer num = new Integer(strNum);
                    emploee.setId(num);
                    emploee.setName(name.toString());
                    emploee.setPassword(pwd.toString());
                    emploee.setPriv(1);
                    if(eml!=null){
                    emploee.setEmail(eml.toString());}
                    if(tel!=null){
                    emploee.setTel(tel.toString());}
                    emploee.setActive(1);

                    employeeService.insertEmploee(emploee);
                }
            }
        }

        return "admin/allTeacher";
    }

    @PostMapping("/admin/student/uploadExcel")
    public String addstudent(Model model, @RequestParam("file") MultipartFile file) throws Exception

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
        Emploee emploee = null;
        List<Emploee> list = new ArrayList<Emploee>();
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
                    emploee = new Emploee();
                    //HSSFCell name = hssfRow.getCell(0);
                    //HSSFCell pwd = hssfRow.getCell(1);
                    Cell id = hssfRow.getCell(0);
                    Cell name = hssfRow.getCell(1);
                    Cell pwd = hssfRow.getCell(2);
                    Cell eml = hssfRow.getCell(3);
                    Cell tel = hssfRow.getCell(4);


//这里是自己的逻辑
//                    int a = Integer.parseInt();
                    System.out.println("********1**********");
                    String strNum = id.toString();
                    if(strNum.indexOf(".")!=-1){
                    strNum = strNum.substring(0, strNum.indexOf("."));}
                    Integer num = new Integer(strNum);


                    emploee.setId(num);
                    emploee.setName(name.toString());
                    emploee.setPassword(pwd.toString());
                    emploee.setPriv(2);
                    if (eml!=null){
                    emploee.setEmail(eml.toString());}
                    if(tel!=null){
                    emploee.setTel(tel.toString());}
                    emploee.setActive(1);

                    employeeService.insertEmploee(emploee);
                }
            }
        }

        return "admin/allStudent";
    }

    @RequestMapping("/addemploee")
    public String goadd() {
        return "/admin/addemploee";
    }

    @PostMapping("/emploee/add")
    public String addadminstor(Emploee emploee, @RequestParam("fileupload") MultipartFile file, Model model) {
        if (!file.isEmpty()) {


             //a为一个随机数
            String fileName = file.getOriginalFilename();
            logger.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            logger.info("上传的后缀名为：" + suffixName);
            // 文件上传后的文件夹
            String filePath = "D://file//";

            String filefull = filePath +DateDealwith.getSHC()+ fileName;

            emploee.setPic(filefull);



            File dest = new File(filePath +DateDealwith.getSHC()+fileName);

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
        employeeService.insertEmploee(emploee);
        return "redirect:/admin/adminstor";
    }

    @RequestMapping("/checkteacher")
    public String checklist(Model model) {
        List<Emploee> list = employeeService.selectByActive(0);
        model.addAttribute("adminList", list);
        return "admin/allTeacher";

    }



}

