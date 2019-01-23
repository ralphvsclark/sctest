package com.ralphvsclark.sctest.util;

import javafx.util.Pair;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public class StringUtil {

    public static boolean isEmpty(String str) {

        return str == null || str.length() == 0;
    }

    public static Pair<String, String> parseInput(String input) {

        if (input == null) {
            return null;
        }

        input = input.trim();

        String parts[] = input.split(" ", 2);

        String key = parts[0];
        String value = null;
        if (parts.length > 1) {
            value = parts[1];
        }

        return new Pair<>(key, value);
    }
}
