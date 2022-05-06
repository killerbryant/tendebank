package tw.com.tendebank.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
 
 /**
 * 日期處理相關工具
 */
public class DateUtils {
     
    /**定義常量**/
    public static final String DATE_PATTERN_YM = "yyyyMM";
    public static final String DATE_PATTERN_YM_DESH = "yyyyMM";
    public static final String DATE_PATTERN_YMD = "yyyyMMdd";
    public static final String DATE_PATTERN_YMD_DESH = "yyyy-MM-dd";
    public static final String DATE_PATTERN_YMDHMS = "yyyyMMddHHmmss";
    public static final String DATE_PATTERN_YMDHMS_DESH = "yyyy-MM-dd HH:mm:ss";
    
    public static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_YMD_DESH);
     
    /**
     * 使用預設格式提取字串日期
     * @param strDate 日期字串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate,DATE_PATTERN_YMDHMS_DESH);
    }
     
    /**
     * 使用使用者格式提取字串日期
     * @param strDate 日期字串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
     
    /**
     * 兩個時間比較
     * @param date
     * @return
     */
    public static int compareDateWithNow(Date date1){
        Date date2 = new Date();
        int rnum =date1.compareTo(date2);
        return rnum;
    }
     
    /**
     * 兩個時間比較(時間戳記比較)
     * @param date
     * @return
     */
    public static int compareDateWithNow(long date1){
        long date2 = dateToUnixTimestamp();
        if(date1>date2){
            return 1;
        }else if(date1<date2){
            return -1;
        }else{
            return 0;
        }
    }
     
 
    /**
     * 獲取系統當前時間
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN_YMDHMS_DESH);
        return df.format(new Date());
    }
     
    /**
     * 獲取系統當前時間
     * @return
     */
    public static String getNowTime(String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        return df.format(new Date());
    }
     
    /**
     * 獲取系統當前計費期
     * @return
     */
    public static String getJFPTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN_YM);
        return df.format(new Date());
    }
     
    /**
     * 將指定的日期轉換成Unix時間戳記
     * @param String date 需要轉換的日期 yyyy-MM-dd HH:mm:ss
     * @return long 時間戳記
     */
    public static long dateToUnixTimestamp(String date) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(DATE_PATTERN_YMDHMS_DESH).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }
     
    /**
     * 將指定的日期轉換成Unix時間戳記
     * @param String date 需要轉換的日期 yyyy-MM-dd
     * @return long 時間戳記
     */
    public static long dateToUnixTimestamp(String date, String dateFormat) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }
     
    /**
     * 將當前日期轉換成Unix時間戳記
     * @return long 時間戳記
     */
    public static long dateToUnixTimestamp() {
        long timestamp = new Date().getTime();
        return timestamp;
    }
     
     
    /**
     * 將Unix時間戳記轉換成日期
     * @param long timestamp 時間戳記
     * @return String 日期字串
     */
    public static String unixTimestampToDate(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_PATTERN_YMDHMS_DESH);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }
    
    //獲取當前月第一天
    public static Date getMonthFirstDate() {
        Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//設置為1號,當前日期既為本月第一天
        return c.getTime();
    }
    
    //獲取當前月最後一天
    public static Date getMonthLastDate() {
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return ca.getTime();
    }
    
    //獲取當前月第一天
    public static String getMonthFirstDateStr() {
        Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//設置為1號,當前日期既為本月第一天 
        String first = sdf.format(c.getTime());
        return first;
    }
    
    //獲取當前月最後一天
    public static String getMonthLastDateStr() {
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String last = sdf.format(ca.getTime());
        return last;
    }

}

