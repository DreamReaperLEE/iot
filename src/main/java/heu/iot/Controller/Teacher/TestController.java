package heu.iot.Controller.Teacher;

import heu.iot.Util.dealFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wangyueyan on 2018/2/8.
 */
@Controller
@RequestMapping("/teacher")
public class TestController {


    @RequestMapping("/test")
    public String test() {
        return "teacher_test/AllSource_test";
    }

    @RequestMapping("AllSource_test")
    public String showAllSource() {
        return "/teacher_test/AllSource_test";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/lesson")
    public String lesson() {
        return "teacher/LessonList";
    }

    @RequestMapping("/lessondetail")
    public String lessondetail() {
        return "teacher/LessonDetailList";
    }

    @RequestMapping("/downloadFile")
    public String downloadFile(HttpServletResponse response, @RequestParam(value = "fileName", defaultValue = "") String fileName) throws IOException {

        return dealFile.downloadFile(response, fileName);
    }

}
