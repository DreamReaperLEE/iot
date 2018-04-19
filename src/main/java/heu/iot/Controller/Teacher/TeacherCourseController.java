package heu.iot.Controller.Teacher;

import heu.iot.Controller.WebSecurityConfig;
import heu.iot.Model.Course;
import heu.iot.Model.Question;
import heu.iot.Model.Question_Json;
import heu.iot.Model.Source;
import heu.iot.Service.*;
import heu.iot.Util.MyJson;
import heu.iot.Util.dealFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/4/17 17:09
 */

@Controller
@RequestMapping("/teacher/course")
public class TeacherCourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private Co_directService co_directService;
    @Autowired
    private Co_typeService co_typeService;
    @Autowired
    private SourceService sourceService;

    @RequestMapping("/showCourseList")
    public String showCourseList(Model model, HttpSession session) {
        List<Course> courseList=courseService.selectByTid(Integer.valueOf(session.getAttribute(WebSecurityConfig.ID).toString()));
        model.addAttribute("courseList",courseList);
        return "teacher/Course/CourseList";
    }

    @RequestMapping("/global")
    public String courseGlobal(Model model, Integer id) {
        Course course=courseService.selectByPrimaryKey(id);
        model.addAttribute("co_type",co_typeService.showAllType());
        model.addAttribute("co_direct",co_directService.showAllDirect());
        model.addAttribute("type",Integer.valueOf(course.getCtype()));
        model.addAttribute("direct",Integer.valueOf(course.getCdirect()));
        model.addAttribute("course",course);
        return "teacher/Course/CourseGlobal";
    }

    @RequestMapping("/changeGlobal")
    public String changeGlobal(Model model, Course course,@RequestParam("digitalfile") MultipartFile file) {
        if(!file.isEmpty()) {
            String filepath = dealFile.saveFile("pic", file);
            course.setCpic(filepath);
        }
        courseService.updateByPrimaryKeySelective(course);
        return "redirect:/teacher/course/showCourseList";
    }

    @RequestMapping("/lessonlist")
    public String lessonList(Model model, Integer id) {
        Course course=courseService.selectByPrimaryKey(id);
        List<Source> sourceList=sourceService.selectByCid(id);
        if(sourceList.size()==0){
            model.addAttribute("newid",1);
        }else{
            Source source=sourceList.get(sourceList.size()-1);
            model.addAttribute("newid",source.getLesson()+1);
        }
        model.addAttribute("course",course);
        model.addAttribute("sourceList",sourceList);
        return "teacher/Course/LessonList";
    }

    @RequestMapping("/addlesson")
    public String addCourse( Source source) {
        source.setType(0);
        sourceService.insertSelective(source);
        return "redirect:/teacher/course/lessonlist?id="+source.getCid();
    }

    @RequestMapping("/deletelesson")
    public String addCourse(Integer lesson,Integer cid) {
        sourceService.deleteByPrimaryKeyAndLowKey(cid,lesson);
        return "redirect:/teacher/course/lessonlist?id="+cid;
    }

    @RequestMapping("/lessondetail")
    public String lessonDetail(Model model,Integer lesson,Integer cid) {
        List<Source> sourceList=sourceService.selectByCourseLesson(cid,lesson);
        model.addAttribute("cid",cid);
        model.addAttribute("lesson",lesson);
        model.addAttribute("sourceList",sourceList);
        return "teacher/Course/SourceList";
    }

    @RequestMapping("/deletesource")
    public String addCourse(Integer id) {
        Source source=sourceService.selectByPrimaryKey(id);
        sourceService.deleteByPrimaryKey(id);
        return "redirect:/teacher/course/lessondetail?cid="+source.getCid()+"&lesson="+source.getLesson();
    }

    @RequestMapping("/addsource")
    public String addSource(Source source, MultipartFile digitalfile) {
        if(digitalfile!=null){
            if(!digitalfile.isEmpty()){
                String filepath;
                if(source.getType()==2){
                    filepath= dealFile.saveFile("pic", digitalfile);
                }else{
                    filepath= dealFile.saveFile("video", digitalfile);
                }
                source.setDetail(filepath);
            }
        }
        sourceService.insertSelective(source);

        return "redirect:/teacher/course/lessondetail?cid="+source.getCid()+"&lesson="+source.getLesson();
    }


}
