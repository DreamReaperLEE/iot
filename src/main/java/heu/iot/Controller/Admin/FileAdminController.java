package heu.iot.Controller.Admin;

import heu.iot.Util.dealFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:下载文件
 * @Since: 2018/1/24 15:03
 */
@Controller
@RequestMapping("/admin")
public class FileAdminController {

    /**
     * @Author: Sumail-Lee
     * @Description:下载文件
     * @param response
     * @param filename 文件名称，默认从file文件夹中读取
     * @Date: 2018/1/24 15:32
     */
    @RequestMapping("/download")
    @ResponseBody
    public String download(HttpServletResponse response,@RequestParam(value = "filename") String filename) throws Exception {

        return dealFile.downloadFile(response,filename);
    }


}
