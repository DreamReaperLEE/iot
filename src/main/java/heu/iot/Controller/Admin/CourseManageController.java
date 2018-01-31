package heu.iot.Controller.Admin;

import heu.iot.Model.*;
import heu.iot.Service.Co_directService;
import heu.iot.Service.Co_typeService;
import heu.iot.Service.CourseService;
import heu.iot.Service.EmploeeService;
import heu.iot.Util.CourseExcel;
import heu.iot.Util.Excel;
import heu.iot.Util.TimeFactory;
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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CourseManageController {
    @Autowired
    private EmploeeService employeeService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private Co_typeService co_typeService;
    @Autowired
    private Co_directService co_directService;


    /**
     * @Author: Sumail-Lee
     * @Description:所有课程展示
     * @param model
     * @Date: 2018/1/23 10:12
     */
    @RequestMapping("/allcourse")
    public String showemploee_course(Model model){
        List<Emploee_Course> list = courseService.showAllEmploeeCourse();
        model.addAttribute("course",list);
        return "admin/allCourse";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:删除课程
     * @param id
     * @Date: 2018/1/23 10:20
     */
    @RequestMapping("/course/delete")
    public String deleteEmploee(@RequestParam(value = "id") Integer id,Model model) {
        int state=courseService.deleteCourse(id);
        return "redirect:/admin/allcourse";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:课程详细信息（用于修改课程）
     * @param id 课程ID
     * @param model
     * @Date: 2018/1/23 10:23
     */
    @RequestMapping("/course/coursedetail")
    public String showcoursedetail(@RequestParam(value = "id") Integer id,Model model){
        Emploee_Course emploee_course=courseService.selectByID(id);
        List<Emploee> emploeeList = employeeService.selectByEmploeePriv(1);
        List<Co_direct> co_directList=co_directService.showAllDirect();
        List<Co_type> co_typeList=co_typeService.showAllType();
        Integer direct=Integer.valueOf(emploee_course.getCdirect());
        Integer type=Integer.valueOf(emploee_course.getCtype());

        //课程信息
        model.addAttribute("emploee_course",emploee_course);
        //所有教师
        model.addAttribute("emploeeList",emploeeList);
        //所有方向
        model.addAttribute("co_directList",co_directList);
        //所有类型
        model.addAttribute("co_typeList",co_typeList);
        //类型
        model.addAttribute("direct",direct);
        //方向
        model.addAttribute("type",type);

        return "admin/courseDetail";

    }


    /**
     * @Author: Sumail-Lee
     * @Description:更新课程
     * @param course 更新的资源
     * @param model
     * @Date: 2018/1/23 14:22
     */
    @PostMapping("/course/updatecourse")
    public String updatecourse(Course course, Model model) {
        courseService.updateByPrimaryKeySelective(course);
        return "redirect:/admin/allcourse";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:更新课程封面
     * @param file 新课程封面
     * @param id 课程ID
     * @Date: 2018/1/23 15:31
     */
    @RequestMapping("/course/updatePic")
    public String updatePic(@RequestParam("imgfile") MultipartFile file,@RequestParam("id") Integer id){
        Course course=new Course();
        course.setId(id);
        //存头像
        if(!file.isEmpty()) {
            String filename = dealFile.saveFile("pic",file);
            course.setCpic("/pic/"+filename);
        }
        courseService.updateByPrimaryKeySelective(course);
        return "redirect:/admin/course/coursedetail?id="+String.valueOf(id);
    }


    /**
     * @Author: Sumail-Lee
     * @Description:跳转到添加课程页面
     * @param model
     * @Date: 2018/1/23 14:24
     */
    @RequestMapping("/addcourse")
    public String goadd(Model model) {

        List<Emploee> emploeeList = employeeService.selectByEmploeePriv(1);
        List<Co_direct> co_directList=co_directService.showAllDirect();
        List<Co_type> co_typeList=co_typeService.showAllType();

        //所有教师
        model.addAttribute("emploeeList",emploeeList);
        //所有方向
        model.addAttribute("co_directList",co_directList);
        //所有类型
        model.addAttribute("co_typeList",co_typeList);

        return "admin/addCourse";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:添加课程（单个）
     * @param course 课程信息
     * @param file 课程封面
     * @param model
     * @Date: 2018/1/23 15:32
     */
    @PostMapping("/course/addcour")
    public String addcourse(Course course, @RequestParam("imgfile") MultipartFile file, Model model) {
        //寸课程封面
        if(!file.isEmpty()) {
            String filename = dealFile.saveFile("pic",file);
            course.setCpic("/pic/"+filename);
        }
        //创建时间
        course.setDate(TimeFactory.getCurrentDate());
        //存课程信息
        if(courseService.insertSelective(course)==1)
            model.addAttribute("success", "课程创建成功！");
        List<Emploee> emploeeList = employeeService.selectByEmploeePriv(1);
        List<Co_direct> co_directList=co_directService.showAllDirect();
        List<Co_type> co_typeList=co_typeService.showAllType();

        //所有教师
        model.addAttribute("emploeeList",emploeeList);
        //所有方向
        model.addAttribute("co_directList",co_directList);
        //所有类型
        model.addAttribute("co_typeList",co_typeList);

        return "admin/addCourse";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:批量上传课程
     * @param model
     * @param file 上传课程的Excel
     * @Date: 2018/1/18 18:05
     */
    @PostMapping("/course/uploadExcel")
    public String addSourceExcel(Model model, @RequestParam("file") MultipartFile file) throws Exception {
        int addi=0,faili=0;
        String filename=dealFile.saveFile("excel",file);
        String filePath="D:\\java_workplace\\iot\\src\\main\\resources\\static\\excel\\"+filename;
        File dest = new File(filePath);
        List<Course> courseList= CourseExcel.addCourse(dest);
        for(Course each:courseList){
            if(employeeService.selectByPrimaryKey(each.getTid())!=null){
                if(courseService.insertSelective(each)==1)
                    addi++;
                else
                    faili++;
            }
            else
                faili++;
        }
        model.addAttribute("pisuccess", "批量上传成功！");
        model.addAttribute("addi", addi);
        model.addAttribute("faili", faili);


        List<Emploee> emploeeList = employeeService.selectByEmploeePriv(1);
        List<Co_direct> co_directList=co_directService.showAllDirect();
        List<Co_type> co_typeList=co_typeService.showAllType();

        //所有教师
        model.addAttribute("emploeeList",emploeeList);
        //所有方向
        model.addAttribute("co_directList",co_directList);
        //所有类型
        model.addAttribute("co_typeList",co_typeList);

        return "admin/addCourse";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:课程导出到Excel
     * @param response
     * @param request
     * @Date: 2018/1/24 9:01
     */
    @RequestMapping("/course/excel")
    @ResponseBody
    public String courseExcle(HttpServletResponse response,HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        List<Emploee_Course> emploee_courseList = courseService.showAllEmploeeCourse();
        List<Co_direct> co_directList=co_directService.showAllDirect();
        List<Co_type> co_typeList=co_typeService.showAllType();

        String fineName="CourseList";
        ArrayList<String> title= CourseExcel.getTitle();
        ArrayList<ArrayList<String>> data=CourseExcel.getData(emploee_courseList,co_directList,co_typeList);
        return Excel.createExcel(fineName,title,data,response);

    }





}

