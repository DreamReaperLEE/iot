package heu.iot.Util;
import heu.iot.Bean.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:数据库备份
 * @Since: 2018/1/31 10:46
 */
public class BackupData {

    /**
     * @Author: Sumail-Lee
     * @Description:数据库备份
     * @param
     * @Date: 2018/1/31 14:17
     */
    public static String backup() throws IOException {

        StringBuffer sb = new StringBuffer();
        Random random=new java.util.Random();
        String filename=TimeFactory.getCurrentDate()+String.valueOf(random.nextInt(899)+100)+"backup.sql";
        sb.append("D:\\phpStudy\\MySQL\\bin\\");
        sb.append("mysqldump ");
        sb.append("--opt ");
        sb.append("-h ");
        sb.append("localhost");
        sb.append(" ");
        sb.append("--user=");
        sb.append("root");
        sb.append(" ");
        sb.append("--password=");
        sb.append("root");
        sb.append(" ");
        sb.append("--lock-all-tables=true ");
        sb.append("--result-file=");
        sb.append(ProjectPath.filepath+"backup\\");
        sb.append(filename);
        sb.append(" ");
        sb.append("--default-character-set=utf8 ");
        sb.append("ceats");
        Runtime.getRuntime().exec(sb.toString());
        return filename;
    }

    /**
     * @Author: Sumail-Lee
     * @Description:数据库恢复
     * @param filename 恢复数据库文件名称
     * @Date: 2018/1/31 13:56
     */
    public static void load(String filename) {

        // 备份的路径地址
        String filepath = ProjectPath.filepath+"backup\\"+filename;

        StringBuffer sb = new StringBuffer();
        sb.append("D:\\phpStudy\\MySQL\\bin\\");
        sb.append("mysqladmin -u ");
        sb.append("root");
        sb.append(" -p");
        sb.append("root");
        sb.append(" create ceats");

        StringBuffer stmt2 = new StringBuffer();
        stmt2.append("D:\\phpStudy\\MySQL\\bin\\");
        stmt2.append("mysql -u ");
        stmt2.append("root");
        stmt2.append(" -p");
        stmt2.append("root");
        stmt2.append(" ceats < ");
        stmt2.append(filepath);
        String[] cmd = {"cmd", "/c", stmt2.toString()};
        try {
            Runtime.getRuntime().exec(sb.toString());
            Runtime.getRuntime().exec(cmd);
            System.out.println("数据已从 " + filepath + " 导入到数据库中");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getFileName(){
        ArrayList<String> result=new ArrayList<String>();
        File file = new File(ProjectPath.filepath+"backup\\");
        File[] array = file.listFiles();
        for(int i=0;i<array.length;i++) {
            if (array[i].isFile())//如果是文件
            {
                result.add(array[i].getName());
            }
        }
        return result;
    }


}
