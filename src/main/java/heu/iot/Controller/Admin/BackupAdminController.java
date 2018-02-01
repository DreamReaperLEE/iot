package heu.iot.Controller.Admin;

import heu.iot.Model.Feedback;
import heu.iot.Util.BackupData;
import heu.iot.Util.dealFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:数据库备份
 * @Since: 2018/1/31 11:24
 */
@Controller
@RequestMapping("/admin/backup")
public class BackupAdminController {

    /**
     * @Author: Sumail-Lee
     * @Description:显示数据备份界面
     * @param
     * @Date: 2018/1/31 15:39
     */
    @RequestMapping("/showbackup")
    public String showBackup(){

        return "admin/backup";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:备份数据库并返回
     * @Date: 2018/1/31 14:57
     */
    @RequestMapping("/download")
    public String downloadBackup(Model model) throws IOException {
        String backup= BackupData.backup();
        model.addAttribute("success","备份文件创建成功，"+backup);
        return "admin/backup";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:显示所有备份
     * @param model
     * @Date: 2018/1/31 15:59
     */
    @RequestMapping("/showRestore")
    public String showRestore(Model model){
        ArrayList<String> result=BackupData.getFileName();
        Collections.sort(result);
        Collections.reverse(result);
        model.addAttribute("result",result);
        return "admin/restore";
    }

    /**
     * @Author: Sumail-Lee
     * @Description:数据库恢复
     * @param filename 备份文件名
     * @Date: 2018/1/31 16:02
     */
    @RequestMapping("/restore")
    public String restore(@RequestParam(value = "filename") String filename){
        BackupData.load(filename);
        return "admin/backup";
    }


}
