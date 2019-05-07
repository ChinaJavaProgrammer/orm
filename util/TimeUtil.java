package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: wuxiaogang
 * Date: 2018/08/16
 * Description:
 * Version: V1.0
 */
public class TimeUtil {
    /**
     * 将Timestamp对象转为字符串
     * @param object sql.Timesteap对象
     * @return 字符串型时间
     */
    public static String timestampForTimeStr(Object object){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        java.sql.Timestamp timestamp = (java.sql.Timestamp) object;
        String time = simpleDateFormat.format(timestamp);
        return time;
    }
    /**
     * 将时间字符串转换成java.sql.Timestamp对象
     * @param time  字符串时间
     * @return java.sql.Timestamp对象
     */
    public static java.sql.Timestamp timeStrForTimeStamp(String  time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        java.sql.Timestamp timestamp = null;
        try {
            java.util.Date date = simpleDateFormat.parse(time);
            timestamp = new java.sql.Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将页面获取json时间字符串 转成Timestamp对象
     * @param jsonTime json时间 格式为yyyy-MM-dd
     * @return Timestamp对象
     */
    public static java.sql.Timestamp jsonTimeForTimeStamp(String jsonTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Timestamp timestamp = null;
        try {
            java.util.Date date = simpleDateFormat.parse(jsonTime);
            timestamp = new java.sql.Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }
}
