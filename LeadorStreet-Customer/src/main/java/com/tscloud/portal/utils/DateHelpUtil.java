package com.tscloud.portal.utils;

import java.util.Calendar;
import java.util.Date;

public class DateHelpUtil {
    private static Calendar calS=Calendar.getInstance();
    /**
     * 计算剩余时间
     * @param fromDate
     * @param toDate
     * @return
     */
    public static String remainDateToString(Date fromDate , Date toDate){

        calS.setTime(fromDate);
        int startY = calS.get(Calendar.YEAR);
        int startM = calS.get(Calendar.MONTH);
        int startD = calS.get(Calendar.DATE);
        int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        calS.setTime(toDate);
        int endY = calS.get(Calendar.YEAR);
        int endM = calS.get(Calendar.MONTH);
        //处理2011-01-10到2011-01-10，认为服务已经过期
        int endD = calS.get(Calendar.DATE);
        int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        StringBuilder sBuilder = new StringBuilder();

        if (toDate.compareTo(fromDate)<=0) {
            return sBuilder.append("一天以内").toString();
        }
        int lday = endD-startD;
        if (lday<0) {
            endM = endM -1;
            lday = startDayOfMonth+ lday;
        }
        //处理天数问题，如：2011-01-01 到 2013-12-31  2年11个月31天     实际上就是3年
        if (lday == endDayOfMonth) {
            endM = endM+1;
            lday =0;
        }

        int mos = (endY - startY)*12 + (endM- startM);
        int lyear = mos/12;
        int lmonth = mos%12;
        if (lyear >0) {
            sBuilder.append(lyear+"年");
        }
        if (lmonth > 0) {
            sBuilder.append(lmonth+"个月");
        }
        if (lday >0 ) {
            sBuilder.append(lday+"天");
        }
        if(lyear > 20){
            sBuilder = new StringBuilder();
            sBuilder.append("很久以前");
        }
        return sBuilder.toString();
    }
}
