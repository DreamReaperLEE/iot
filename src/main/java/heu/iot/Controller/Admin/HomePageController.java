package heu.iot.Controller.Admin;

import heu.iot.Model.Course;
import heu.iot.Model.Emploee;
import heu.iot.Model.Homepage;
import heu.iot.Service.CourseService;
import heu.iot.Service.EmploeeService;
import heu.iot.Service.HomepageService;
import heu.iot.Util.MyJson;
import heu.iot.Util.dealFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:首页配置
 * @Since: 2018/1/30 15:44
 */
@RequestMapping("/admin/homepage")
@Controller
public class HomePageController {

    @Autowired
    private HomepageService homepageService;

    @Autowired
    private EmploeeService emploeeService;

    @Autowired
    private CourseService courseService;

    /**
     * @Author: Sumail-Lee
     * @Description:更改首页图片界面
     * @param model
     * @Date: 2018/1/30 16:29
     */
    @RequestMapping("/showpic")
    public String showPic(Model model){
        List<Homepage> homepages=homepageService.selectByType(2);

        model.addAttribute("pic",homepages.get(0).getOther());
        model.addAttribute("homepageid",homepages.get(0).getId());

        return "admin/homepagePic";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:修改首页图片
     * @param file 首页图片
     * @param id 图片所在条目ID
     * @Date: 2018/1/30 16:29
     */
    @RequestMapping("/changePic")
    public String changePic(@RequestParam("imgfile") MultipartFile file,@RequestParam("id") Integer id){

        Homepage homepage=new Homepage();
        homepage.setId(id);
        //存图片
        if(!file.isEmpty()) {
            String filename = dealFile.saveFile("pic",file);
            homepage.setOther("/pic/"+filename);
        }
        homepageService.updateByPrimaryKeySelective(homepage);
        return "redirect:/admin/homepage/showpic";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:显示所有需要展示的教师
     * @param model
     * @Date: 2018/1/30 17:20
     */
    @RequestMapping("/showEmploee")
    public String showEmploee(Model model){
        //获取所有教师列表JSON格式数据
        List<Homepage> homepages=homepageService.selectByType(1);
        //将Json格式转换为Emploee格式列表
        String list=homepages.get(0).getOther();
        List<String> stringlist= MyJson.JsonToStringList(list);
        ArrayList<Integer> intlist=new ArrayList<>();
        for(String each:stringlist)
            intlist.add(Integer.valueOf(each));
        List<Emploee> emploeeList=emploeeService.showSelected(intlist);

        //获取所有教师列表
        List<Emploee> emploeeList1=emploeeService.selectByEmploeePriv(1);
        //全部教师列表中剔除已选择教师
        for(Emploee each:emploeeList){
            emploeeList1.remove(each);
        }

        //已经选中的教师
        model.addAttribute("emploeeList",emploeeList);
        //所有未选中的教师
        model.addAttribute("teacherList",emploeeList1);
        return "admin/homepageEmploee";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:删除展示的教师
     * @param id 删除教师的ID
     * @Date: 2018/1/30 17:27
     */
    @RequestMapping("/deleteEmploee")
    public String deleteEmploee(@RequestParam("id") String id){

        Homepage homepage;
        //获取所有教师列表JSON格式数据
        List<Homepage> homepages=homepageService.selectByType(1);
        homepage=homepages.get(0);
        String list=homepage.getOther();
        ArrayList<String> stringlist= MyJson.JsonToStringList(list);
        stringlist.remove(id);
        homepage.setOther(MyJson.toJson(stringlist));
        homepageService.updateByPrimaryKeySelective(homepage);

        return "redirect:/admin/homepage/showEmploee";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:添加展示的教师
     * @param tid 教师ID
     * @Date: 2018/1/31 10:03
     */
    @RequestMapping("/addEmploee")
    public String addEmploee(@RequestParam("tid") String tid){

        Homepage homepage;
        //获取所有教师列表JSON格式数据
        List<Homepage> homepages=homepageService.selectByType(1);
        homepage=homepages.get(0);
        String list=homepage.getOther();
        ArrayList<String> stringlist= MyJson.JsonToStringList(list);
        stringlist.add(tid);
        homepage.setOther(MyJson.toJson(stringlist));
        homepageService.updateByPrimaryKeySelective(homepage);

        return "redirect:/admin/homepage/showEmploee";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:显示所需要展示的课程
     * @param model
     * @Date: 2018/1/31 10:18
     */
    @RequestMapping("/showCourse")
    public String showCourse(Model model){

        //获取所有教师列表JSON格式数据
        List<Homepage> homepages=homepageService.selectByType(0);
        //将Json格式转换为Course格式列表
        String list=homepages.get(0).getOther();
        List<String> stringlist= MyJson.JsonToStringList(list);
        ArrayList<Integer> intlist=new ArrayList<>();
        for(String each:stringlist)
            intlist.add(Integer.valueOf(each));
        List<Course> courseList=courseService.showSelected(intlist);

        //获取所有课程列表
        List<Course> courseList1=courseService.showAllCourse();
        //全部考试列表中剔除已选择考试
        for(Course each:courseList){
            courseList1.remove(each);
        }

        //已经选中的课程
        model.addAttribute("courseList",courseList);
        //所有未选中的课程
        model.addAttribute("steadyList",courseList1);
        return "admin/homepageCourse";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:删除课程
     * @param id 要删除的课程ID
     * @Date: 2018/1/31 10:30
     */
    @RequestMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("id") String id){

        Homepage homepage;
        //获取所有课程列表JSON格式数据
        List<Homepage> homepages=homepageService.selectByType(0);
        homepage=homepages.get(0);
        String list=homepage.getOther();
        ArrayList<String> stringlist= MyJson.JsonToStringList(list);
        stringlist.remove(id);
        homepage.setOther(MyJson.toJson(stringlist));
        homepageService.updateByPrimaryKeySelective(homepage);

        return "redirect:/admin/homepage/showCourse";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:添加课程
     * @param id 要添加的课程ID
     * @Date: 2018/1/31 10:30
     */
    @RequestMapping("/addCourse")
    public String addCourse(@RequestParam("id") String id){

        Homepage homepage;
        //获取所有课程列表JSON格式数据
        List<Homepage> homepages=homepageService.selectByType(0);
        homepage=homepages.get(0);
        String list=homepage.getOther();
        ArrayList<String> stringlist= MyJson.JsonToStringList(list);
        stringlist.add(id);
        homepage.setOther(MyJson.toJson(stringlist));
        homepageService.updateByPrimaryKeySelective(homepage);

        return "redirect:/admin/homepage/showCourse";
    }



}
