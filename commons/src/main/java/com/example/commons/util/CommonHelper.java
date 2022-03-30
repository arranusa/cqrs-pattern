package com.example.commons.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonHelper {
    public CommonHelper() {
    }

    private static boolean isValidGUID(String str) {
        String regex = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$";
        Pattern p = Pattern.compile(regex);
        if (str == null) {
            return false;
        } else {
            Matcher m = p.matcher(str);
            return m.matches();
        }
    }

    public static String getGUID() {
        return UUID.randomUUID().toString();
    }

    public static String getGUID(String str) {
        return !isValidGUID(str) ? UUID.randomUUID().toString() : str;
    }

    public static String toStringGUID(UUID uuid) {
        return uuid.toString();
    }
}
