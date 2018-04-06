package com.xyf.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class Lg {

    private static final Map<String, Logger> LOGGER_MAP = new HashMap<>();

    private static Logger getLogger(@Nonnull String tag) {
        if (!LOGGER_MAP.containsKey(tag)) {
            LOGGER_MAP.put(tag, LoggerFactory.getLogger(tag));
        }

        return LOGGER_MAP.get(tag);
    }

    private static final int LOG_LENGTH = 100;

    private static void logMethodHead(@Nonnull String tag) {
        final StackTraceElement element = new Throwable().getStackTrace()[2];

        StringBuilder builder = new StringBuilder();
        builder.append("-------------");
        builder.append(element.getMethodName());
        final int length = builder.length();
        for (int i = 0; i < LOG_LENGTH - length; i++) {
            builder.append("-");
        }

        getLogger(tag).info(builder.toString());
    }

    private static void logMethodTail(@Nonnull String tag) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < LOG_LENGTH; i++) {
            builder.append("-");
        }
        getLogger(tag).info(builder.toString());
    }

    public static void i(@Nonnull String tag, @Nonnull Object... messages) {
        logMethodHead(tag);
        for (Object message : messages) {
            final String string = message.toString();
            for (String s : string.split("\n")) {
                getLogger(tag).info("| " + s);
            }
        }
        logMethodTail(tag);
    }

    public static void e(@Nonnull String tag, @Nonnull Object... messages) {
        logMethodHead(tag);
        for (Object message : messages) {
            final String string = message.toString();
            for (String s : string.split("\n")) {
                getLogger(tag).error("| " + s);
            }
        }
        logMethodTail(tag);
    }

}
