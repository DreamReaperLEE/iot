package heu.iot.Controller.Student;

import heu.iot.Model.*;
import heu.iot.Service.*;
import heu.iot.Util.MyJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生学习课程类
 *
 * @Author: Sumail-Lee
 * @Date: 8:41 2017/11/29
 */
@Controller
@RequestMapping("/student")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private SourceService sourceService;

    @Autowired
    private Co_directService co_directService;

    @Autowired
    private Co_typeService co_typeService;

    @Autowired
    private EmploeeService emploeeService;

    @Autowired
    private HierarchyService hierarchyService;


    /**
     * @param session
     * @param model
     * @Author: Sumail-Lee
     * @Description: 展示所有课程
     * @Date: 2017/12/17 20:51
     */
    @RequestMapping({"/course", "/"})
    public String showCourse(HttpSession session, Model model) {
        //获取所有课程列表
        List<Course> courseList = courseService.showAllCourse();
        //获取所有课程类型
        List<Co_direct> co_directList=co_directService.showAllDirect();
        //获取所有课程方向
        List<Co_type> co_typeList=co_typeService.showAllType();
        //所有课程列表
        model.addAttribute("courseList", courseList);
        //获取所有课程方向
        model.addAttribute("co_directList", co_directList);
        //获取所有课程类型
        model.addAttribute("co_typeList", co_typeList);
        //添加现有类型
        model.addAttribute("type", 0);
        //添加现有方向
        model.addAttribute("direct", 0);
        //筛选条件
        model.addAttribute("cname", "no");
        return "student/allCourse";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:选择课程展示
     * @param cname 根据输入名称选择课程
     * @param type 根据课程类型选择
     * @param direct 根据课程方向选择
     * @param session
     * @param model
     * @Date: 2018/2/1 13:47
     */
    @RequestMapping("/course_select")
    public String showSelected(@RequestParam(value = "cname",defaultValue = "no") String cname,@RequestParam(value = "type",defaultValue = "0") Integer type,@RequestParam(value = "direct",defaultValue = "0") Integer direct,HttpSession session, Model model) {

        //获取所有课程列表
        List<Course> courseList =new ArrayList<>();
        if(!cname.equals("no")) {
            courseList=courseService.selectByCname(cname);
        }
        else{
            courseList=courseService.selectByTypeDirect(type,direct);
        }
        //获取所有课程类型
        List<Co_direct> co_directList=co_directService.showAllDirect();
        //获取所有课程方向
        List<Co_type> co_typeList=co_typeService.showAllType();
        //所有课程列表
        model.addAttribute("courseList", courseList);
        //获取所有课程方向
        model.addAttribute("co_directList", co_directList);
        //获取所有课程类型
        model.addAttribute("co_typeList", co_typeList);
        //筛选条件
        model.addAttribute("cname", cname);
        //添加现有类型
        model.addAttribute("type", type);
        //添加现有方向
        model.addAttribute("direct", direct);
        return "student/allCourse";
    }

    /**
     * @Author: Sumail-Lee
     * @Description: 获取所有课程体系列表
     * @param model
     * @Date: 2018/1/8 16:43
     */
    @RequestMapping("/course_hierarchy")
    public String showHierarchy(Model model){
        List<Hierarchy> hierarchyList=hierarchyService.showAll();
        //所有体系列表
        model.addAttribute("hierarchyList", hierarchyList);
        return "student/courseHierarchy";

    }

    /**
     * @param id     课程id
     * @param lesson 章节id
     * @param model
     * @Author: Sumail-Lee
     * @Description: 具体课程具体章节学习
     * @Date: 2017/12/17 20:52
     */
    @RequestMapping("/course_detail")
    public String showCourseDetail(@RequestParam(value = "course") String course, @RequestParam(value = "id") Integer id, @RequestParam(value = "lesson", required = false, defaultValue = "1") Integer lesson, Model model) {
        //获取该章节资源列表
        List<Source> sourceList = sourceService.showCourseDetail(id, lesson);
        //获取该章节资源列表
        List<Source> nextLesson = sourceService.showCourseDetail(id, lesson + 1);
        //文本类资源列表
        List<Source> textList = new ArrayList<Source>();
        //图片类资源列表
        List<Source> picList = new ArrayList<Source>();
        //视频类资源列表
        List<Source> videoList = new ArrayList<Source>();

        //处理章节资源列表进行分类
        Source cname = new Source();
        for (Source source : sourceList) {
            System.out.println(source.getType());
            switch (source.getType()) {
                case 1:
                    textList.add(source);
                    break;
                case 2:
                    picList.add(source);
                    break;
                case 3:
                    videoList.add(source);
                    break;
                default:
                    cname = source;
            }
        }
        //是否有下一章
        if (nextLesson.size() != 0)
            model.addAttribute("nextLesson", "success");
        else
            model.addAttribute("nextLesson", "fail");
        //章节名称
        model.addAttribute("cname", cname.getTopic());
        //课程名称
        model.addAttribute("course", course);
        //课程ID
        model.addAttribute("cid", cname.getCid());
        //章节
        model.addAttribute("lesson", lesson);
        //文本
        model.addAttribute("textList", textList);
        //图片
        model.addAttribute("picList", picList);
        //视频
        model.addAttribute("videoList", videoList);
        return "student/CourseDetail";
    }


    /**
     * @param id    课程id
     * @param model
     * @Author: Sumail-Lee
     * @Description: 显示课程章节列表
     * @Date: 2017/12/17 20:52
     */
    @RequestMapping("/course_list")
    public String showCourseDetail(@RequestParam(value = "id") Integer id, Model model) {
        //获取课程章节列表
        List<Source> sourceList = sourceService.selectByCourse(id);
        //获取课程名称
        Course course = courseService.selectByPrimaryKey(id);
        //获取教师数据
        Emploee emploee=emploeeService.selectByPrimaryKey(course.getTid());
        model.addAttribute("course", course);
        model.addAttribute("sourceList", sourceList);
        model.addAttribute("emploee", emploee);
        return "student/CourseList";
    }

    @RequestMapping("/hierarchyDetail")
    public String hierarchyDetail(@RequestParam(value = "id") Integer id, Model model){
        //获取标题等信息
        Hierarchy hierarchy=hierarchyService.selectByPrimaryKey(id);
        //获取列表
        ArrayList<HierarchyDetail> hierarchyDetailArrayList=MyJson.JsonToHierarchy(hierarchy.getDetail());
        //列表转换
        for(int i=0;i<hierarchyDetailArrayList.size();i++){
            hierarchyDetailArrayList.get(i).setCourseList(courseService.showSelected(hierarchyDetailArrayList.get(i).getIntList()));
        }
        //标题等信息
        model.addAttribute("hierarchy", hierarchy);

        //具体课程等信息
        model.addAttribute("hierarchyDetailArrayList", hierarchyDetailArrayList);
        return "student/HierarchyDetail";
    }


}


