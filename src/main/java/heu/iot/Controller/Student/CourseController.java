package heu.iot.Controller.Student;

import heu.iot.Model.Course_Emploee;
import heu.iot.Model.Source;
import heu.iot.Service.CourseService;
import heu.iot.Service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
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

    @RequestMapping({"/course","/"})
    public String showCourse(HttpSession session, Model model){

        List<Course_Emploee> course_emploeeList=courseService.showAllCourse();
        model.addAttribute("course_emploeeList",course_emploeeList);
        return "student/allCourse";
    }

    @RequestMapping("/course_detail")
    public String showCourseDetail(@RequestParam(value = "id") Integer id, @RequestParam(value = "lesson",required = false,defaultValue = "1") Integer lesson, Model model){

        List<Source> sourceList=sourceService.showCourseDetail(id,lesson);
        List<Source> textList=new ArrayList<Source>();
        List<Source> picList=new ArrayList<Source>();
        List<Source> videoList=new ArrayList<Source>();
        Source cname=new Source();
        for(Source source:sourceList){
            System.out.println(source.getType());
            switch (source.getType()){
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
                    cname=source;
            }
        }
        //课程名称
        model.addAttribute("cname",cname.getTopic());
        //课程ID
        model.addAttribute("cid",cname.getCid());
        //章节
        model.addAttribute("lesson",lesson);
        //文本
        model.addAttribute("textList",textList);
        //图片
        model.addAttribute("picList",picList);
        //视频
        model.addAttribute("videoList",videoList);
        return "student/CourseDetail";
    }

    @RequestMapping("/course_list")
    public String showCourseDetail(@RequestParam(value = "id") Integer id,Model model){
        List<Source> sourceList=sourceService.selectByCourse(id);
        heu.iot.Model.Course course=courseService.selectByPrimaryKey(id);
        model.addAttribute("course",course);
        model.addAttribute("sourceList",sourceList);
        return "student/CourseList";
    }
}
