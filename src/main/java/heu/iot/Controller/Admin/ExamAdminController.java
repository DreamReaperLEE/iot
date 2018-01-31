package heu.iot.Controller.Admin;

import heu.iot.Model.Emploee;
import heu.iot.Model.Exam;
import heu.iot.Model.ExamEmploee;
import heu.iot.Service.EmploeeService;
import heu.iot.Service.ExamService;
import heu.iot.Util.ExamExcel;
import heu.iot.Util.Excel;
import heu.iot.Util.dealFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/1/24 9:06
 */
@Controller
@RequestMapping("/admin/exam")
public class ExamAdminController {

    @Autowired
    private ExamService examService;
    @Autowired
    private EmploeeService emploeeService;

    /**
     * @Author: Sumail-Lee
     * @Description:显示全部考试
     * @param model
     * @Date: 2018/1/24 9:15
     */
    @RequestMapping("/allexam")
    public String showAllExam(Model model){
        List<Emploee> emploeeList=emploeeService.selectByEmploeePriv(1);
        List<ExamEmploee> examEmploeeList=examService.selectAllExamEmploee();
        model.addAttribute("examEmploeeList",examEmploeeList);
        model.addAttribute("emploeeList",emploeeList);
        return "admin/allExam";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:删除考试
     * @param id 被删除的考试ID
     * @param model
     * @Date: 2018/1/24 9:16
     */
    @RequestMapping("/delete")
    public String deleteEmploee(@RequestParam(value = "id") Integer id, Model model) {
        int state=examService.deleteByPrimaryKey(id);
        return "redirect:/admin/exam/allexam";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:考试详细信息用于修改考试
     * @param id 考试ID
     * @param model
     * @Date: 2018/1/24 9:38
     */
    @RequestMapping("/examdetail")
    public String showExamDetail(@RequestParam(value = "id") Integer id,Model model){

        List<Emploee> emploeeList = emploeeService.selectByEmploeePriv(1);
        Exam exam=examService.selectByPrimaryKey(id);

        //主管教师ID
        model.addAttribute("tid",Integer.valueOf(exam.getTid()));
        //所有教师
        model.addAttribute("emploeeList",emploeeList);
        //考试详细信息
        model.addAttribute("exam",exam);

        return "admin/examDetail";

    }

    /**
     * @Author: Sumail-Lee
     * @Description:更新考试信息
     * @param exam 更新的考试信息
     * @param model
     * @Date: 2018/1/24 9:56
     */
    @PostMapping("/updateexam")
    public String updatecourse(Exam exam, Model model) {

        examService.updateByPrimaryKeySelective(exam);
        return "redirect:/admin/exam/allexam";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:更改考试图片
     * @param file 考试的图片
     * @param id 考试ID
     * @Date: 2018/1/24 9:58
     */
    @RequestMapping("/updatePic")
    public String updatePic(@RequestParam("imgfile") MultipartFile file, @RequestParam("id") Integer id){
        Exam exam=new Exam();
        exam.setId(id);
        //存头像
        if(!file.isEmpty()) {
            String filename = dealFile.saveFile("pic",file);
            exam.setEpic("/pic/"+filename);
        }
        examService.updateByPrimaryKeySelective(exam);
        return "redirect:/admin/exam/examdetail?id="+String.valueOf(id);
    }

    /**
     * @Author: Sumail-Lee
     * @Description:添加考试
     * @param exam 考试信息
     * @param model
     * @Date: 2018/1/24 10:01
     */
    @RequestMapping("/addexam")
    public String addExam(Exam exam,Model model) {
        examService.insertSelective(exam);
        return "redirect:/admin/exam/allexam";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:批量导入考试信息
     * @param model
     * @param file
     * @Date: 2018/1/24 10:50
     */
    @PostMapping("/uploadExcel")
    public String addSourceExcel(Model model, @RequestParam("file") MultipartFile file) throws Exception {

        int addi=0,faili=0;
        String filename=dealFile.saveFile("excel",file);
        String filePath="D:\\java_workplace\\iot\\src\\main\\resources\\static\\excel\\"+filename;
        File dest = new File(filePath);
        List<Exam> examList= ExamExcel.addExam(dest);
        for(Exam each:examList){
            if(examService.insertSelective(each)==1)
                addi++;
            else
                faili++;

        }
        model.addAttribute("pisuccess", "批量上传成功！");
        model.addAttribute("addi", addi);
        model.addAttribute("faili", faili);

        List<Emploee> emploeeList=emploeeService.selectByEmploeePriv(1);
        List<ExamEmploee> examEmploeeList=examService.selectAllExamEmploee();
        model.addAttribute("examEmploeeList",examEmploeeList);
        model.addAttribute("emploeeList",emploeeList);

        return "admin/allExam";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:导出考试信息到Excel
     * @param response
     * @param request
     * @Date: 2018/1/24 10:50
     */
    @RequestMapping("/download")
    @ResponseBody
    public String examExcle(HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<Emploee> emploeeList = emploeeService.selectByEmploeePriv(1);
        List<Exam> examList=examService.selectAllExam();
        String fineName="ExamList";
        ArrayList<String> title= ExamExcel.getTitle();
        ArrayList<ArrayList<String>> data=ExamExcel.getData(emploeeList,examList);
        return Excel.createExcel(fineName,title,data,response);

    }
}
