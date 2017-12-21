package heu.iot.Controller.Student;

import heu.iot.Model.Course;
import heu.iot.Model.Course_Emploee;
import heu.iot.Model.Source;
import heu.iot.Service.CourseService;
import heu.iot.Service.SourceService;
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
        List<Course_Emploee> course_emploeeList = courseService.showAllCourse();
        model.addAttribute("course_emploeeList", course_emploeeList);
        return "student/allCourse";
    }

    @RequestMapping("/course_select")
    public String showSelected(@RequestParam(value = "cname") String cname,HttpSession session, Model model) {
        //获取所有课程列表
        List<Course_Emploee> course_emploeeList = courseService.showSelected(cname);
        model.addAttribute("course_emploeeList", course_emploeeList);
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
    public String showCourseDetail(@RequestParam(value = "id") Integer id, @RequestParam(value = "lesson", required = false, defaultValue = "1") Integer lesson, Model model) {
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
        //课程名称
        model.addAttribute("cname", cname.getTopic());
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
        model.addAttribute("course", course);
        model.addAttribute("sourceList", sourceList);
        return "student/CourseList";
    }
}
