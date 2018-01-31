package heu.iot.Controller.Admin;

import heu.iot.Model.Emploee;
import heu.iot.Model.Inform;
import heu.iot.Model.Inform_Emploee;
import heu.iot.Service.EmploeeService;
import heu.iot.Service.InformService;
import heu.iot.Util.MyJson;
import heu.iot.Util.TimeFactory;
import heu.iot.Util.dealFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:消息管理
 * @Since: 2018/1/24 10:49
 */
@Controller
@RequestMapping("/admin/notice")
public class NoticeAdminController {
    @Autowired
    private InformService informService;
    @Autowired
    private EmploeeService emploeeService;


    /**
     * @Author: Sumail-Lee
     * @Description:所有通知公告
     * @param model
     * @Date: 2018/1/24 14:38
     */
    @RequestMapping("/allnotice")
    public String showNotice(Model model) {
        List<Inform_Emploee> inform_emploeeList=informService.showAdminAll();
        model.addAttribute("inform_emploeeList", inform_emploeeList);
        return "admin/notice";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:跳转到添加公告界面
     * @param
     * @Date: 2018/1/30 14:47
     */
    @RequestMapping("/preaddnotice")
    public String showAddNotice(){

        return "admin/addNotice";
    }


    /**
     * @Author: Sumail-Lee
     * @Description:添加公告
     * @param model
     * @param inform 公告信息
     * @param imgfile 图片
     * @param addfile 附件
     * @Date: 2018/1/30 14:48
     */
    @RequestMapping("/addnotice")
    public String addNotice(Model model,Inform inform,@RequestParam("imgfile") MultipartFile[] imgfile,@RequestParam("addfile") MultipartFile addfile){
        ArrayList<String> filelist=new ArrayList<String>();
        String filename;
        //自动化存数据
        inform.setDate(TimeFactory.getCurrentTime());
        inform.setType(0);
        inform.setActive(1);

        //存附件
        if(!addfile.isEmpty()) {
            filename = dealFile.saveFile("file",addfile);
            inform.setFile("/file/"+filename);
        }

        //存图片
        for(MultipartFile every:imgfile){
            if(every!=null){
                filename = dealFile.saveFile("pic",every);
                filelist.add("/pic/"+filename);
            }
        }
        if(filelist.size()!=0)
            inform.setPic(MyJson.toJson(filelist));
        //通知入库
        informService.insertSelective(inform);
        //返回新建成功
        model.addAttribute("success", "新建通知成功");
        return "admin/addNotice";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:通知公告细节
     * @param model
     * @param id 通知公告id
     * @Date: 2018/1/24 14:39
     */
    @RequestMapping("/noticeDetail")
    public String noticeDetail(Model model,@RequestParam(value = "id", defaultValue = "0") Integer id) {
        Inform inform=informService.selectByPrimaryKey(id);
        Emploee emploee=emploeeService.selectByPrimaryKey(inform.getIid());
        ArrayList<String> picList= MyJson.JsonToStringList(inform.getPic());
        if(picList!=null){
            model.addAttribute("picList", picList);
        }
        model.addAttribute("emploee", emploee);
        model.addAttribute("inform", inform);
        return "admin/noticeDetail";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:删除通知
     * @param id 通知ID
     * @Date: 2018/1/30 15:00
     */
    @RequestMapping("/delete")
    public String deleteNotice(@RequestParam(value = "id", defaultValue = "0") Integer id) {

        int state=informService.deleteByPrimaryKey(id);
        return "redirect:/admin/notice/allnotice";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:通知审核
     * @param id 被审核通知ID
     * @param active 激活状态
     * @Date: 2018/1/31 11:26
     */
    @RequestMapping("/checkActice")
    public String checkActive(@RequestParam(value = "id", defaultValue = "0") Integer id,@RequestParam(value = "active", defaultValue = "0") Integer active){

        Inform inform=new Inform();
        inform.setId(id);
        inform.setActive(active);
        informService.updateByPrimaryKeySelective(inform);
        return "redirect:/admin/notice/allnotice";
    }

}
