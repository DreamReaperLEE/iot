package heu.iot.Controller.Student;

import heu.iot.Model.Course_Emploee;
import heu.iot.Model.Source;
import heu.iot.Service.CourseService;
import heu.iot.Service.SourceService;
import heu.iot.Util.Excel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: Sumail-Lee
 * @Date: 16:58 2017/11/29
 */
@RestController
@RequestMapping("/student")
public class testController {

//    @RequestMapping("/excel")
//    public String excle(HttpServletResponse response) throws Exception {
//        ArrayList<ArrayList<String>> list=new ArrayList<ArrayList<String>>() ;
//        ArrayList<String> every=new ArrayList<String>();
//        every.add("测试1");
//        every.add("测试2");
//        list.add(every);
//        list.add(every);
//        return Excel.createExcel("测试",every,list,response);
//    }

//    public static void main(String args[]){
//
//
//
//    }
}
