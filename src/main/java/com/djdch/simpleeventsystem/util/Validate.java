package com.djdch.simpleeventsystem.util;

public class Validate {
    public static void notNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }
}
