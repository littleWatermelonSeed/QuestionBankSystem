package cn.function.www;

import java.util.Calendar;

/**
 * Created by 123 on 2017/3/23.
 */

public class GetTime {
    private int year;
    private int month;
    private int day;
    private int hours;
    private int minute;
    private int second;
    private String time1;
    private String time2;

    private void nowTime(){
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hours = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        time1 = year+"-"+month+"-"+day+"   "+hours+":"+minute+":"+second;
        time2 = ""+hours+minute+second;
    }

    public String getNowTime(){
        nowTime();
        return time1;
    }

    public String getNowTimeSecond(){
        nowTime();
        return time2;
    }

}
