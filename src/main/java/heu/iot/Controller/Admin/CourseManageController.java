package heu.iot.Controller.Admin;

import heu.iot.Model.*;
import heu.iot.Service.*;
import heu.iot.Util.*;
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

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:课程管理
 * @Date: 2018/2/1 8:57
 */
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
    @Autowired
    private HierarchyService hierarchyService;


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
            course.setCpic(filename);
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
        //存课程封面
        if(!file.isEmpty()) {
            String filename = dealFile.saveFile("pic",file);
            course.setCpic(filename);
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
        String filePath=SomeConfig.filepath+"\\excel\\"+filename;
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


    /**
     * @Author: Sumail-Lee
     * @Description:展示所有课程方向和类别
     * @param model
     * @Date: 2018/2/1 14:09
     */
    @RequestMapping("/course/directtype")
    public String showDirectType(Model model){

        List<Co_direct> co_directList=co_directService.showAllDirect();
        List<Co_type> co_typeList=co_typeService.showAllType();

        model.addAttribute("co_directList",co_directList);
        model.addAttribute("co_typeList",co_typeList);
        return "admin/allDirectType";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:添加方向
     * @param name 方向名称
     * @Date: 2018/2/1 14:32
     */
    @PostMapping("/course/addDirect")
    public String addDirect(@RequestParam("name") String name){
        Co_direct co_direct=new Co_direct();
        co_direct.setDirectName(name);
        co_directService.insertSelective(co_direct);
        return "redirect:/admin/course/directtype";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:添加类别
     * @param name 类别名称
     * @Date: 2018/2/1 14:32
     */
    @PostMapping("/course/addType")
    public String addType(@RequestParam("name") String name){
        Co_type co_type= new Co_type();
        co_type.setTypeName(name);
        co_typeService.insertSelective(co_type);
        return "redirect:/admin/course/directtype";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:删除方向
     * @param id 删除的方向ID
     * @Date: 2018/2/1 14:32
     */
    @RequestMapping("/course/deleteDirect")
    public String deleteDirect(@RequestParam("id") Integer id){
        co_directService.deleteByPrimaryKey(id);
        return "redirect:/admin/course/directtype";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:删除类别
     * @param id 删除的类别ID
     * @Date: 2018/2/1 14:32
     */
    @RequestMapping("/course/deleteType")
    public String deleteType(@RequestParam("id") Integer id){
        co_typeService.deleteByPrimaryKey(id);
        return "redirect:/admin/course/directtype";
    }



    @RequestMapping("/hierarchy/showall")
    public String showAllHierarchy(Model model){
        List<Hierarchy> hierarchies=hierarchyService.showAll();
        model.addAttribute("hierarchies",hierarchies);
        return "admin/Hierarchie/showall";
    }

    @RequestMapping("/hierarchy/add")
    public String addHierarchy(Hierarchy hierarchy,@RequestParam("imgfile") MultipartFile file){
        //存课程封面
        if(file!=null){
            if(!file.isEmpty()) {
                String filename = dealFile.saveFile("pic",file);
                hierarchy.setPic(filename);
            }
        }

        hierarchyService.insertSelective(hierarchy);

        return "redirect:/admin/hierarchy/showall";
    }

    @RequestMapping("/hierarchy/delete")
    public String DeleteHierarchy(Integer id){
        hierarchyService.deleteByPrimaryKey(id);
        return "redirect:/admin/hierarchy/showall";
    }

    @RequestMapping("/hierarchy/detail")
    public String HierarchyDetail(Model model,Integer id){
        //获取标题等信息
        Hierarchy hierarchy=hierarchyService.selectByPrimaryKey(id);
        //获取列表
        ArrayList<HierarchyDetail> hierarchyDetailArrayList=MyJson.JsonToHierarchy(hierarchy.getDetail());
        //标题等信息
        model.addAttribute("hierarchy", hierarchy);

        //具体课程等信息
        model.addAttribute("hierarchyDetailArrayList", hierarchyDetailArrayList);
        return "admin/Hierarchie/DetailList";
    }

    @RequestMapping("/hierarchy/addtopic")
    public String addTopic(Model model,HierarchyDetail hierarchyDetail,Integer id){
        //获取标题等信息
        Hierarchy hierarchy=hierarchyService.selectByPrimaryKey(id);
        hierarchyDetail.setIntList(new ArrayList<Integer>());
        //获取列表
        ArrayList<HierarchyDetail> hierarchyDetailArrayList=MyJson.JsonToHierarchy(hierarchy.getDetail());
        hierarchyDetailArrayList.add(hierarchyDetail);
        hierarchy.setDetail(MyJson.toJson(hierarchyDetailArrayList));
        hierarchyService.updateByPrimaryKeySelective(hierarchy);
        //标题等信息
        model.addAttribute("hierarchy", hierarchy);
        model.addAttribute("success", "添加小节成功！");
        //具体课程等信息
        model.addAttribute("hierarchyDetailArrayList", hierarchyDetailArrayList);
        return "admin/Hierarchie/DetailList";
    }

    @RequestMapping("/hierarchy/deleteTopic")
    public String deleteTopic(Model model,Integer topic,Integer id){
        //获取标题等信息
        Hierarchy hierarchy=hierarchyService.selectByPrimaryKey(id);
        //获取列表
        ArrayList<HierarchyDetail> hierarchyDetailArrayList=MyJson.JsonToHierarchy(hierarchy.getDetail());
        hierarchyDetailArrayList.remove(hierarchyDetailArrayList.get(topic));
        hierarchy.setDetail(MyJson.toJson(hierarchyDetailArrayList));
        hierarchyService.updateByPrimaryKeySelective(hierarchy);
        //标题等信息
        model.addAttribute("hierarchy", hierarchy);
        model.addAttribute("success", "删除小节成功！");
        //具体课程等信息
        model.addAttribute("hierarchyDetailArrayList", hierarchyDetailArrayList);
        return "admin/Hierarchie/DetailList";
    }

    @RequestMapping("/hierarchy/detailTopic")
    public String detailTopic(Model model,Integer topic,Integer id){
        List<Course> courses=courseService.showAllCourse();

        //获取标题等信息
        Hierarchy hierarchy=hierarchyService.selectByPrimaryKey(id);
        //获取列表
        ArrayList<HierarchyDetail> hierarchyDetailArrayList=MyJson.JsonToHierarchy(hierarchy.getDetail());

        HierarchyDetail hierarchyDetail=hierarchyDetailArrayList.get(topic);
        if(hierarchyDetail.getIntList().size()!=0){
            hierarchyDetail.setCourseList(courseService.showSelected(hierarchyDetail.getIntList()));
        }else{
            hierarchyDetail.setCourseList(new ArrayList<Course>());
        }

        //标题等信息
        model.addAttribute("courses", courses);
        //标题等信息
        model.addAttribute("topic", topic);
        //标题等信息
        model.addAttribute("hierarchy", hierarchy);
        //具体小节等信息
        model.addAttribute("hierarchyDetail", hierarchyDetail.getCourseList());

        return "admin/Hierarchie/TopicList";
    }

    @RequestMapping("/hierarchy/deleteCourse")
    public String deleteCourse(Integer topic,Integer id,Integer cid){
        //获取标题等信息
        Hierarchy hierarchy=hierarchyService.selectByPrimaryKey(id);
        //获取列表
        ArrayList<HierarchyDetail> hierarchyDetailArrayList=MyJson.JsonToHierarchy(hierarchy.getDetail());
        //获取某一小节
        HierarchyDetail hierarchyDetail=hierarchyDetailArrayList.get(topic);

        ArrayList<Integer> integers=hierarchyDetail.getIntList();
        integers.remove((Integer)cid);
        hierarchyDetail.setIntList(integers);

        hierarchyDetailArrayList.set(topic,hierarchyDetail);

        hierarchy.setDetail(MyJson.toJson(hierarchyDetailArrayList));
        hierarchyService.updateByPrimaryKeySelective(hierarchy);
        return "redirect:/admin/hierarchy/detailTopic?topic="+topic+"&id="+id;
    }

    @RequestMapping("/hierarchy/addCourse")
    public String addCourse(Integer topic,Integer id,Integer cid){
        //获取标题等信息
        Hierarchy hierarchy=hierarchyService.selectByPrimaryKey(id);
        //获取列表
        ArrayList<HierarchyDetail> hierarchyDetailArrayList=MyJson.JsonToHierarchy(hierarchy.getDetail());
        //获取某一小节
        HierarchyDetail hierarchyDetail=hierarchyDetailArrayList.get(topic);

        ArrayList<Integer> integers=hierarchyDetail.getIntList();
        integers.add((Integer)cid);
        hierarchyDetail.setIntList(integers);

        hierarchyDetailArrayList.set(topic,hierarchyDetail);

        hierarchy.setDetail(MyJson.toJson(hierarchyDetailArrayList));
        hierarchyService.updateByPrimaryKeySelective(hierarchy);
        return "redirect:/admin/hierarchy/detailTopic?topic="+topic+"&id="+id;
    }



}

