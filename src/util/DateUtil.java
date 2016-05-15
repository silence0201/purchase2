package util;

import entity.Request;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Description: DateUtil
 * Author: silence
 * Update: silence(2016-05-06 23:37)
 */
public class DateUtil {
    public static Date getStartOfMonth(){
        Calendar calendar = Calendar.getInstance() ;

        int year = calendar.get(Calendar.YEAR) ;
        int month = calendar.get(Calendar.MONTH) ;

        Date startDate = new Date(year-1900,month,1) ;
        return startDate ;
    }

    public static boolean isPlan(){
        Calendar calendar = Calendar.getInstance() ;

        int day = calendar.get(Calendar.DAY_OF_YEAR) ;

        //每隔10天进行统计一次
        return day % 10 == 0 ;
    }

    //获取当前时间
    public static long currentDate(){
        Calendar calendar = Calendar.getInstance() ;
        return calendar.getTimeInMillis() ;
    }

}
