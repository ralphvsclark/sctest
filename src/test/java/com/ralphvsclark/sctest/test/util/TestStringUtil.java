package com.ralphvsclark.sctest.test.util;

import com.ralphvsclark.sctest.util.StringUtil;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class TestStringUtil {

    @Test
    public void testSplit() {

        String input = "a|b|c|d";

        String parts[] = input.split("\\|");
        Assert.assertEquals(parts.length, 4);

    }

    @Test
    public void testParse() {
        {
            String input = "display";

            Pair<String, String> cmdAndParams = StringUtil.parseInput(input);
            Assert.assertEquals(cmdAndParams.getKey(), "display");
            Assert.assertNull(cmdAndParams.getValue());
        }

        {
            String input = "publish xxx";

            Pair<String, String> cmdAndParams = StringUtil.parseInput(input);
            Assert.assertEquals(cmdAndParams.getKey(), "publish");
            Assert.assertEquals(cmdAndParams.getValue(), "xxx");
        }
    }

}
