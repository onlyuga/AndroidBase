package com.suri.core.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public final class DateConvertUtils {

    public static final String DATA_FORMAT_PATTEN_YYYY_MMMM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATA_FORMAT_PATTEN_YYYY_MMMM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     *
     * @param data
     * @param dataFormatPatten
     */
    public static long dateToTimeStamp(String data, String dataFormatPatten) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormatPatten);
        Date date = null;
        try {
            date = simpleDateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        return date.getTime();
    }

    /**
     * @param time
     * @param dataFormatPatten
     */
    public static String timeStampToDate(long time, String dataFormatPatten) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormatPatten);
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }
}
