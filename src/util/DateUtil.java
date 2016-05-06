package util;

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
}
