package heu.iot.Controller.Teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangyueyan on 2017/12/5.
 */
@Controller
@RequestMapping("/teacher")
public class Analysis {

    // 成绩查询模块
    @RequestMapping("/analysis")
    public String analysis(Model model) {
        return "teacher/Analysis";
    }

}