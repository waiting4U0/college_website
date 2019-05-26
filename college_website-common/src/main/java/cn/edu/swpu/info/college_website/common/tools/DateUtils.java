package cn.edu.swpu.info.college_website.common.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

    private final static Logger LOG = LoggerFactory.getLogger(DateUtils.class);

    public static long getServerTime() {
        return System.currentTimeMillis();
    }

    /**
     * 格式化日期,默认返回yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化显示当前日期
     *
     * @param format
     * @return
     */
    public static String format(String format) {
        return format(new Date(), format);
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            LOG.warn("日期格式化失败.{}", e.getMessage());
        }
        return null;
    }

    /**
     * 时间格式化， 传入毫秒
     *
     * @param time
     * @return
     */
    public static String dateFormat(long time) {
        return format(new Date(time), "yyyy-MM-dd HH:mm:ss");
    }


    public static String getDateTimeOfDay(int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Date beginDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - day);
        Date endDate = null;
        try {
            endDate = sdf.parse(sdf.format(calendar.getTime()));
        } catch (ParseException e) {
            LOG.error("日期转换异常", e);
        }
        return sdf.format(endDate);
    }

    public static Date getDateTimeOfYesterDay(int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - day);
        Date endDate = null;
        try {
            endDate = sdf.parse(sdf.format(calendar.getTime()));
        } catch (ParseException e) {
            LOG.error("日期转换异常", e);
        }
        return endDate;
    }


    public static Long get7DaysAgoDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Date endDate = null;
        try {
            endDate = sdf.parse(getDateTimeOfDay(6));
        } catch (ParseException e) {
            LOG.error("日期转换异常", e);
        }
        return endDate.getTime();
    }


    public static Long getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Date currentDate = null;
        try {
            currentDate = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            LOG.error("日期转换异常", e);
        }
        return currentDate.getTime();
    }

    public static Date parseDate(String datetime, String partten) {
        SimpleDateFormat sdf = new SimpleDateFormat(partten);
        try {
            return sdf.parse(datetime);
        } catch (ParseException e) {
            LOG.error("日期格式化失败:" + datetime);
            LOG.warn("日期格式化失败.{}", e.getMessage());
        }
        return null;
    }

}
