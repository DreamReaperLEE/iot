package heu.iot.Controller.Teacher;

import heu.iot.Model.Course;
import heu.iot.Model.Source;
import heu.iot.Service.CourseService;
import heu.iot.Service.SourceService;
import heu.iot.Util.Judge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @Author:hupengyu
 * @Date: 17:00 2017/12/7
 */

@Controller
@RequestMapping("/teacher")
public class AddLessonController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    Judge judge = new Judge();

    @Autowired
    private SourceService sourceService;

    @Autowired
    private CourseService courseService;

//   添加某一章节内的资源，cid;lesson已经传过去了，准备当成默认值
    @RequestMapping("/add_lesson_page")
    public String showAddSourcePage(@RequestParam(value = "cid") Integer cid, @RequestParam(value = "lesson") Integer lesson, HttpSession session, Model model)
    {
        model.addAttribute("source_cid",cid);
        model.addAttribute("source_lesson",lesson);
//      List<Source> sourceList = sourceService.showSourceByPage(page);
//        List<Source> sourceList = sourceService.showAllSource();
//        model.addAttribute("sourcelist",sourceList);
        return "teacher/AddLessonPage";
    }

//    添加新的章节，id已经传进去了
    @RequestMapping("/add_new_lesson")
    public String AddNewLesson(@RequestParam(value = "id") Integer id, HttpSession session, Model model)
    {
        Course course = courseService.selectByPrimaryKey(id);
        model.addAttribute("course_name",course.getCname());
        model.addAttribute("source_cid",course.getId());
//      List<Source> sourceList = sourceService.showSourceByPage(page);
//        List<Source> sourceList = sourceService.showAllSource();
//        model.addAttribute("sourcelist",sourceList);
        return "teacher/AddNewLesson";
    }

//    public String showAddLesson

//    向某一章的某一节上传资源
    @PostMapping("addSource")
    //只能传"detail"不能传source，难道有 @RequestParam("detail")时就不能传对象了吗？
//    这个file是必须有的，否则没法把文件传到数据库上！但是这样的话就不能把其他值传到数据库中了
//    传source就不能传file，怎么能同时传？
    public String addSource(Source source, Model model, @RequestParam("fileupload") MultipartFile file) throws Exception
//    public String addSource(Source source, Model model) throws Exception
    {

        if (file.isEmpty()) {
        return "文件为空";
    }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的文件夹
        String filePath = "/pic";
//        文件的绝对路径
        String filefull = filePath + fileName;
//        将绝对路径添加到表单中
        source.setDetail(filefull);
//        System.out.println(source);
//        把该source实体对象加入到数据库表单中
        sourceService.addSource(source);
        model.addAttribute("sign", "添加" + source.getTopic() + "成功！");
        model.addAttribute("id",source.getCid());
        model.addAttribute("lesson",source.getLesson());
//        source.setDetail(filefull);
//        source.aetDetail(filefull);
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        //这个东西就是文件，并且它的路径是"filePath + fileName"
        File dest = new File(filePath + fileName);
        // 检测是否存在目录，如果不存在路径，创建一个文件夹
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        //这个才是上传文件
        try {
            file.transferTo(dest);
            judge.flag = true;
        }
//        当try出现问题时执行catch语句，并初始化IllegalStateException函数
        catch (IllegalStateException e) {
            //打印问题
            e.printStackTrace();
//            抛出的是io异常
        } catch (IOException e) {
            e.printStackTrace();
        }
            judge.flag = false;
//        重定向到加课的页面上
        return "redirect:./lesson_detail_list?id="+source.getCid()+"&"+"lesson="+source.getLesson();
    }

    @PostMapping("addLesson")
    public String addLesson(Source source, Model model)throws Exception
    {
        sourceService.addSource(source);
        model.addAttribute("sign", "添加第" +source.getLesson()+"章"+source.getTopic() + "成功！");
//        重定向到显示章节的界面,并把新添加的章节显示出来
        return "redirect:/teacher/onlyLesson_list?id="+source.getCid();
    }

}
