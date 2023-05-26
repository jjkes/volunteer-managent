package com.zj.common.utils;

import com.zj.common.exception.MyCommonException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/26 11:10
 */

public class DateUtil {
    public enum DateFormat{
        yyyyMMddTHHmmss("yyyy-MM-dd HH:mm:ss");
        final String format;

        DateFormat(String format) {
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
    }
    /**
     * TODO
     * @author 赵健
     * @date 2023/5/26 11:22
     */
    public static Date parseStrToDate(String dateStr,DateFormat dateFormat){
        String date = dateStr.trim();
        Date finalDate = null;
        try {
            if(!date.isEmpty()){
                SimpleDateFormat format = new SimpleDateFormat(dateFormat.getFormat());
                finalDate = format.parse(date);
            }else {
                throw new MyCommonException("时间转化失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new MyCommonException("时间转化失败");
        }

        return finalDate;
    }


    public static void main(String[] args) {
        Date date = parseStrToDate("2023-10-05 10:36:00", DateFormat.yyyyMMddTHHmmss);
        System.err.println(date);
    }
}
