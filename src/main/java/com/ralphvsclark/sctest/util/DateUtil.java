package com.ralphvsclark.sctest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public class DateUtil {

    private static final String SDF_TEMPLATE_1 = "dd-MM-yyyy";
    private static final SimpleDateFormat SDF_1 = new SimpleDateFormat(SDF_TEMPLATE_1);

    private static final String SDF_TEMPLATE_2 = "MM_yyyy";
    private static final SimpleDateFormat SDF_2 = new SimpleDateFormat(SDF_TEMPLATE_2);

    public static String toStandardDate(Date dt) {

        if (dt == null) {
            return null;
        }

        String str = SDF_1.format(dt);
        return str;
    }

    public static Date fromStandardDate(String str) {
        if (StringUtil.isEmpty( str )) {
            return null;
        }

        Date dt = null;
        try {
            dt = SDF_1.parse(str);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return dt;
    }

    public static String to_MM_yyyy(Date dt) {

        if (dt == null) {
            return null;
        }

        String str = SDF_2.format(dt);
        return str;

    }
}
