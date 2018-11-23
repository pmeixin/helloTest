package com.unisinsight.vdp.core.common.utils;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.util.StringUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author  tanjiquan [KF.tanjiquan@h3c.com]
 * @date    2018/9/6 11:03
 * @since   1.0HAO
 */
public class DateUtils {

    private static Logger logger = LoggerFactory.getLogger( DateUtils.class );

    public static String rfc3399 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static String ymdHms = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式字符串，包含毫秒
     */
    public static final String YMD_HMSS = "yyyy-MM-dd HH:mm:ss.SSS";

    public static String ymd = "yyyy-MM-dd";

    /**
     * 时间戳最小值
     */
    public static int timestampLength = 748800000;



    public static String convert2rfc3399(Date date) {
        return new SimpleDateFormat(rfc3399)
                .format(date);
    }


    public static boolean compareTime( String beginTime, String endTime ){
        SimpleDateFormat sdf = new SimpleDateFormat( ymdHms );
        Date bt = null;
        Date et = null;
        try {
            bt = sdf.parse( beginTime);
            et = sdf.parse(endTime);
        } catch (ParseException e) {
            logger.error("时间比较失败");
        }

        if ( bt.before(et) ) {
            return true;
        } else {
            return true;
        }
    }

    public static boolean compareTime( Date beginTime, Date endTime ){
        if ( beginTime.before(endTime) ) {
            return true;
        } else {
            return true;
        }
    }

    public static boolean compareTime( String beginTime, String endTime, String time ){
        if( compareTime( beginTime, time ) && compareTime( time, beginTime) ) {
            return true;
        }
        return false;
    }

    public static boolean compareTime( Date beginTime, Date endTime, Date time ){
        if( compareTime( beginTime, time ) && compareTime( time, beginTime) ) {
            return true;
        }
        return false;
    }

    public static Date timestamp2Date( Long timestamp){
        //要转换的时间格式
        SimpleDateFormat sdf = new SimpleDateFormat( ymdHms );
        Date date = null;
        try {
            date = sdf.parse(sdf.format(timestamp));
        } catch (ParseException e) {
            logger.error("时间戳转换失败");
        }
        return date;
    }

    public static Date timestamp3Date( Long timestamp, String format ){
        //要转换的时间格式
        if (StringUtil.isEmpty(format)) {
            format = ymdHms;
        }
        SimpleDateFormat sdf = new SimpleDateFormat( format );
        Date date = null;
        try {
            date = sdf.parse(sdf.format(timestamp));
        } catch (ParseException e) {
            logger.error("时间戳转换失败");
        }
        return date;
    }


    public static Date timestamp2Date( Timestamp timestamp ){
        return new Date( timestamp.getSeconds() * 1000 );
    }

    /**
     * 日期格式字符串转换成时间戳
     * String转时间戳
     * @return 返回结果精确到毫秒
     * */
    public static Long dateStrToTimeStamp(String dateStr,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return Long.valueOf(sdf.parse(dateStr).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到毫秒的字符串
     * @param
     * @return
     */
    public static String timeStampToDateStr(Long  seconds,String format) {
        if (seconds == null ) {
            return "";
        }
        if ( Strings.isNullOrEmpty(format) ) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(seconds));
    }

        /**
         * 获取现在时间
        * @return 返回格式字符串format
        */
        public static String getStringByDate(Date date,String format) {
            if ( Strings.isNullOrEmpty(format) ) {
                format = ymdHms;
            }
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            String dateString = formatter.format(date);
            return dateString;
         }
        /**
         * 英国格林治时间转为北京时间
         * IA返回抓拍时间为
         * @return 返回格式字符串format
         */
        public static Long utcToCst(String utcStr, String format) throws ParseException {
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse( utcStr );
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
            //calendar.getTime() 返回的是Date类型，也可以使用calendar.getTimeInMillis()获取时间戳
            return calendar.getTimeInMillis();
        }
        /**
         * 英国格林治时间转为北京时间
         * IA返回抓拍时间为
         * @return 返回格式字符串format
         */
        public static String CstToUtc(Long cstLong, String format) throws ParseException {
            String cstStr = timeStampToDateStr(cstLong,format);
            Date cstDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            cstDate = sdf.parse(cstStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(cstDate);
            calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 8);

            //calendar.getTime() 返回的是Date类型，也可以使用calendar.getTimeInMillis()获取时间戳
            return getStringByDate(calendar.getTime(),format);
        }

}
