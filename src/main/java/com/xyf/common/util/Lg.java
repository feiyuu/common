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

    public static void i(@Nonnull String tag, @Nonnull String message) {
        getLogger(tag).info(message);
    }

    public static void e(@Nonnull String tag, @Nonnull String message, @Nonnull Throwable e) {
        getLogger(tag).error(message, e);
    }

}
