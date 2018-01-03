package heu.iot.Controller.Teacher;

import heu.iot.Model.Course;
import heu.iot.Model.Course_Source;
import heu.iot.Model.Source;
import heu.iot.Service.CourseService;
import heu.iot.Service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

/**
 * Created by wangyueyan on 2017/12/5.
 */


@Controller
@RequestMapping("/teacher")
public class SourceController {
    @Autowired
    private SourceService sourceService;

    @Autowired
    private CourseService courseService;


//    这里的地址是在网页上显示的地址，即完全对应与实际界面
    @RequestMapping({"/source","/"})
    public String showSource(HttpSession session, Model model){
        //返回列表型的数据
//        List<heu.iot.Model.Source> sourceList=sourceService.showAllSource();
//        model.addAttribute("sourceList",sourceList);
//        return "teacher/AllSource";
        List<Course_Source> course_sourceList = sourceService.showAllSourse();
        model.addAttribute("course_sourceList", course_sourceList);
        return "teacher/AllSource";
    }

    @RequestMapping(value = "/watch_lesson")
    public String WatchLesson(@RequestParam(value = "id") Integer id, @RequestParam(value = "lesson") Integer lesson,
                              @RequestParam(value = "topic") String topic, Model model, HttpSession session)
    {

        Source source = sourceService.showSourceContent(id,lesson,topic);
        System.out.println("into");
        Integer cid = source.getCid();
        model.addAttribute("cid",cid);
        model.addAttribute("lesson",lesson);


        Source textSource;
        Source picSource;
        Source videoSource;

            switch (source.getType()) {
                case 1:
                    textSource=source;
                    model.addAttribute("textSource",source);
                    break;
                case 2:
                    picSource=source;
                    model.addAttribute("picSource",picSource);
                    break;
                case 3:
                    videoSource=source;
                    model.addAttribute("videoSource",videoSource);
                    break;
            }

        //课程名称
        model.addAttribute("cname", source.getTopic());

        return "teacher/SourceContent";
    }

    //返回某一个课程的各章内容
    @RequestMapping(value = "/course_source_list")
    public String showSourceDetail(@RequestParam(value = "id") Integer id, Model model, HttpSession session)
    {
        List<Course_Source> course_sourceList=sourceService.showAllLessonSource(id);
        Course_Source c2 = course_sourceList.get(0);
//        Course_Source course_source=sourceService.selectByPrimaryKey(id);
        //model.addAttribute是把course_sourceList加入到html前端！！！
        model.addAttribute("course_sourceList",course_sourceList);
        model.addAttribute("course_name",c2.getCname());
        model.addAttribute("course_id",c2.getId());
        //遍历每一个course_sourceList,type = 0时，就把这个东西当做章节名
//        for(Course_Source each : course_sourceList){
//            if (each.getType().equals("0")){
//                model.addAttribute("course_topic",each.getTopic());
//            }
//        }
        //要返回source的数据！！！
        return "teacher/SourceList";
    }

//    现在这个界面里的id就是course.id了，即把课程号传给了LessonList了
    @RequestMapping(value = "/onlyLesson_list")
    public String showOnlyLesson(@RequestParam(value = "id") Integer id, Model model, HttpSession session)
    {
//        这个是该id课程的所有章节（章节唯一）的显示：章节号+章节名
        List<Course_Source> course_sourceList2=sourceService.showOnlyLesson(id);
        Course course = courseService.selectByPrimaryKey(id);
        String course_topic = course.getCname();
        Integer course_id = course.getId();
//        System.out.println(course_sourceList2.get(0).getLesson());
        model.addAttribute("course_sourceList2",course_sourceList2);
        model.addAttribute("course_topic",course_topic);
//        把课程号给LessonList界面了
        model.addAttribute("course_id",course_id);
        return "teacher/LessonList";
    }

//    把课程号和章节号传进来了
    @RequestMapping(value = "lesson_detail_list")
    public String showSpecialLessonDetail(@RequestParam(value = "id") Integer id,@RequestParam(value = "lesson") Integer lesson, Model model, HttpSession session)
    {

//        显示该章节内的所有资源，但是我不想把type=0也显示出来,未完待续
        List<Course_Source> course_sourceList3=sourceService.showSpecialLesson(id,lesson);
        model.addAttribute("course_sourceList3",course_sourceList3);
        model.addAttribute("course_id",course_sourceList3.get(0).getId());
        model.addAttribute("source_lesson",course_sourceList3.get(0).getLesson());

        List<Course_Source> course_sourceList4=sourceService.showSpecialLessonType0(id,lesson);
        model.addAttribute("lesson_name",course_sourceList4.get(0).getTopic());
        return "teacher/LessonDetailList";
    }

//    删除整个课程下的所有资源
//    删除id为指定id的课程资源：course.id == source.cid
    @RequestMapping(value = "/delete_course_source")
    public String deleteClassSource(@RequestParam(value = "id") Integer id, Model model, HttpSession session)
    {
        sourceService.deleteClassSource(id);

        //重定向到原页面
        return "redirect:/teacher/source";
    }

//    删除某一课程的某一章节下的所有资源：所有id为该课程，章节为该章节的source中的资源中的所有资源
//    删除id为指定id的课程资源：course.id == source.cid,lesson为指定的source.lesson
    @RequestMapping(value = "/delete_lesson_source")
    public String deleteLessonSource(@RequestParam(value = "id") Integer id, @RequestParam(value = "lesson") Integer lesson,Model model, HttpSession session)
    {
//        选定课程号和章节号,在source中选择，然后删除

        sourceService.deleteLessonSource(id,lesson);
        //重定向到原页面
        return "redirect:/teacher/onlyLesson_list?id="+id;
    }

//    删除某一课程某一章节下的某个资源：指定的id，lesson,topic
//    同时删除服务器中的资源文件
    @RequestMapping(value = "/delete_source")
    public String deleteSource(@RequestParam(value = "id") Integer id, @RequestParam(value = "lesson") Integer lesson,
                               @RequestParam(value = "topic") String topic,Model model, HttpSession session)
    {
        Source source = sourceService.selectByPrimaryKeyAndLessonAndTopic(id,lesson,topic);
        File oranfile = new File(source.getDetail());
        oranfile.delete();
        sourceService.deleteSource(id,lesson,topic);

        return "redirect:/teacher/lesson_detail_list?id="+id+"&lesson="+lesson;
    }



    @RequestMapping(value = "/learn_lesson")
//    取出该id对应的课的detail，并显示detail（视频或者PPT）
    public String learnLesson(@RequestParam(value = "id") Integer id, Model model, HttpSession session)
    {
        //
        heu.iot.Model.Source source=sourceService.selectByPrimaryKey(id);
        model.addAttribute("source",source);
        //返回的是多媒体
        return "teacher/SourceList";
    }
}