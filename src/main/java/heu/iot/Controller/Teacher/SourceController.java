package heu.iot.Controller.Teacher;

import heu.iot.Service.CourseService;
import heu.iot.Service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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

    @RequestMapping({"/source","/"})
    public String showSource(HttpSession session, Model model){
        //返回列表型的数据
        List<heu.iot.Model.Source> sourceList=sourceService.showAllSource();
        model.addAttribute("sourceList",sourceList);
        return "teacher/AllSource";
    }


    @RequestMapping(value = "/source_list")
    public String showSourceDetail(@RequestParam(value = "id") Integer id, Model model, HttpSession session)
    {
        //返回单行数据
        id=1;
        heu.iot.Model.Source source=sourceService.selectByPrimaryKey(id);
        model.addAttribute("source",source);
        //要返回source的数据！！！
        return "teacher/SourceList";
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