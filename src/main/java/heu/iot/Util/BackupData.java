package heu.iot.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:数据库备份
 * @Since: 2018/1/31 10:46
 */
public class BackupData {

    /**
     * @param
     * @Author: Sumail-Lee
     * @Description:数据库备份
     * @Date: 2018/1/31 14:17
     */
    public static String backup() throws IOException {

        StringBuffer sb = new StringBuffer();
        Random random = new java.util.Random();
        String filename = TimeFactory.getCurrentDate() + String.valueOf(random.nextInt(899) + 100) + "backup.sql";
        sb.append("D:\\phpStudy\\MySQL\\bin\\");
        sb.append("mysqldump ");
        sb.append("--opt ");
        sb.append("-h ");
        sb.append(SomeConfig.dbaddress);
        sb.append(" ");
        sb.append("--user=");
        sb.append(SomeConfig.username);
        sb.append(" ");
        sb.append("--password=");
        sb.append(SomeConfig.password);
        sb.append(" ");
        sb.append("--lock-all-tables=true ");
        sb.append("--result-file=");
        sb.append(SomeConfig.filepath + "backup\\");
        sb.append(filename);
        sb.append(" ");
        sb.append("--default-character-set=utf8 ");
        sb.append(SomeConfig.database);
        Runtime.getRuntime().exec(sb.toString());
        return filename;
    }

    /**
     * @param filename 恢复数据库文件名称
     * @Author: Sumail-Lee
     * @Description:数据库恢复
     * @Date: 2018/1/31 13:56
     */
    public static void load(String filename) {

        // 备份的路径地址
        String filepath = SomeConfig.filepath + "backup\\" + filename;

        StringBuffer sb = new StringBuffer();
        sb.append("D:\\phpStudy\\MySQL\\bin\\");
        sb.append("mysqladmin -u ");
        sb.append(SomeConfig.username);
        sb.append(" -p");
        sb.append(SomeConfig.password);
        sb.append(" create " + SomeConfig.database);

        StringBuffer stmt2 = new StringBuffer();
        stmt2.append("D:\\phpStudy\\MySQL\\bin\\");
        stmt2.append("mysql -u ");
        stmt2.append(SomeConfig.username);
        stmt2.append(" -p");
        stmt2.append(SomeConfig.password);
        stmt2.append(" " + SomeConfig.database + " < ");
        stmt2.append(filepath);
        String[] cmd = {"cmd", "/c", stmt2.toString()};
        try {
            Runtime.getRuntime().exec(sb.toString());
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getFileName() {
        ArrayList<String> result = new ArrayList<String>();
        File file = new File(SomeConfig.filepath + "backup\\");
        File[] array = file.listFiles();
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile())//如果是文件
            {
                result.add(array[i].getName());
            }
        }
        return result;
    }


}
