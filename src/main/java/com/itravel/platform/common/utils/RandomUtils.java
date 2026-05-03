package com.itravel.platform.common.utils;

import java.util.Random;

public class RandomUtils {
    private static final String CHARS = "0123456789";
    private static final Random RND = new Random();

    public static String getRandomNumber(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(CHARS.charAt(RND.nextInt(CHARS.length())));
        }
        return sb.toString();
    }
}
