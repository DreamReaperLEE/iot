package heu.iot.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Sumail-Lee
 * @Date: 10:02 2017/11/27
 */
public class TimeFactory {

    public static boolean validateTimeStamp(Date TimeStamp) throws Exception {
        System.out.println("-----------" + TimeStamp);
        Date date = new Date();
        return (date.getTime() - TimeStamp.getTime()) / 60000 <= 5;
    }

    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static String getTimeWithoutFormat() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date());
    }

    public static String getCurrentHour() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(new Date());
    }

    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }
}