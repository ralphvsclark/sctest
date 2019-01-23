package com.ralphvsclark.sctest.test.util;

import com.ralphvsclark.sctest.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public class TestDateUtil {

    @Test
    public void testDateFormat() {

        Calendar c = Calendar.getInstance();
        c.set(2010, 12 - 1, 10);

        String dtStr = DateUtil.toStandardDate(c.getTime());
        Assert.assertEquals(dtStr, "10-12-2010");
    }
}
