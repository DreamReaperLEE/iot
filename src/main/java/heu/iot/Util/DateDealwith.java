package heu.iot.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDealwith {
    public static Date getCurrDate() {
        // 获取系统当前时间
        SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 将字符串转换为Date格式
        Date date = null;
        try {
            date = std.parse(std.format(new Date()).toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 给定格式和参数格式要统一不然就会抛出异常
        return date;
    }

    public static String getCurrDateStr() {
        // 获取系统当前时间
        SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 将字符串转换为Date格式
        return std.format(new Date()).toString();
        // 给定格式和参数格式要统一不然就会抛出异常
    }

    /**
     * 获取系统当前年份
     *
     * @return
     */
    public static String getCurrYear() {
        // 获取系统当前时间
        SimpleDateFormat std = new SimpleDateFormat("yyyy");
        // 将字符串转换为Date格式
        System.out.println(std.format(new Date()).toString());
        return std.format(new Date()).toString();
    }

    /**
     * 获取两时间差
     *
     * @throws ParseException
     */
    public static long getshijiancha(Date da) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = df.parse(df.format(new Date()).toString());
        Date date = df.parse(df.format(da).toString());
        long l = now.getTime() - date.getTime();
        return l;
    }

    //生成唯一文件名
    public static String getSHC() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = sdf.format(date) + (int) (1 + Math.random() * (100 - 1 + 1));
        return str;
    }

}
