package heu.iot.Util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Random;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description: 文件上传工具类
 * @Since: 2018/1/15 15:06
 */
public class dealFile {

    /**
     * @Author: Sumail-Lee
     * @Description: 文件上传类
     * @param Floder 上传的文件夹名称
     * @param file post过来的文件
     * @Date: 2018/1/18 16:04
     */
    public static String saveFile(String Floder, MultipartFile file){
        String fileName = file.getOriginalFilename();
        Random random=new java.util.Random();
        int result=random.nextInt(899)+100;
        fileName=TimeFactory.getTimeWithoutFormat()+String.valueOf(result)+fileName;
//        String filePath = request.getSession().getServletContext().getRealPath("./upload/");
        String filePath="D:\\java_workplace\\iot\\src\\main\\resources\\static\\"+Floder+"\\";
        System.out.println(filePath);
        try {
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(filePath+fileName);
            out.write(file.getBytes());
            out.flush();
            out.close();
            return fileName;
        } catch (Exception e) {
            return "fail";
        }
    }

    /**
     * @Author: Sumail-Lee
     * @Description: 文件下载 TODO
     * @param res
     * @Date: 2018/1/18 16:05
     */
    public static String downloadFile(HttpServletResponse res,String filename) throws IOException {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename="+filename);

        String filePath=SomeConfig.filepath+filename;

        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return "";
    }
}
