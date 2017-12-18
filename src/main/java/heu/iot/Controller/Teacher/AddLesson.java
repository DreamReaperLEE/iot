package heu.iot.Controller.Teacher;

import heu.iot.Model.Source;
import heu.iot.Service.SourceService;
import heu.iot.Util.Judge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

/**
 * @Author:hupengyu
 * @Date: 17:00 2017/12/7
 */

@Controller
@RequestMapping("/teacher")
public class AddLesson {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    Judge judge = new Judge();

    @Autowired
    private SourceService sourceService;

    @RequestMapping("/add_lesson_page")
    public String showAddLesson(HttpSession session, Model model)
    {

//      List<Source> sourceList = sourceService.showSourceByPage(page);
        List<Source> sourceList = sourceService.showAllSource();
        model.addAttribute("sourcelist",sourceList);
        return "teacher/AddLessonPage";
    }

    @PostMapping("addSource")
    //只能传"detail"不能传source，难道有 @RequestParam("detail")时就不能传对象了吗？
//    这个file是必须有的，否则没法把文件传到数据库上！但是这样的话就不能把其他值传到数据库中了
//    传source就不能传file，怎么能同时传？
    public String addSource(Source source, Model model, @RequestParam("fileupload") MultipartFile file) throws Exception
//    public String addSource(Source source, Model model) throws Exception
    {

//      Source source = new Source();
//        sourceService.addSource(source);
//        model.addAttribute("sign", "添加" + source.getTopic() + "成功！");

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
        String filePath = "E://file//";
//        文件的绝对路径
        String filefull = filePath + fileName;
//        将绝对路径添加到表单中
        source.setDetail(filefull);
//        System.out.println(source);
//        把该source实体对象加入到数据库表单中
        sourceService.addSource(source);
        model.addAttribute("sign", "添加" + source.getTopic() + "成功！");
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
        return "redirect:./add_lesson_page";
    }


}
