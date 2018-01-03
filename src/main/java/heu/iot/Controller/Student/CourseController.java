package heu.iot.Controller.Student;

import heu.iot.Model.*;
import heu.iot.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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
        return "student/allCourse";
    }

    @RequestMapping("/course_select")
    public String showSelected(@RequestParam(value = "cname") String cname,HttpSession session, Model model) {
        //获取所有课程列表
        List<Course> courseList = courseService.showSelected(cname);
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
        return "student/allCourse";
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
        return "student/new_CourseDetail";
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

//    @RequestMapping("/test")
//    public String test(){
//        return "student/new_test";
//    }
}
